package cn.yanglj65.www.ecloth_app;


import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;
import java.util.List;

import cn.yanglj65.www.ecloth_app.Adapter.MyRecyclerAdapter;
import cn.yanglj65.www.ecloth_app.Service.HttpService;
import cn.yanglj65.www.ecloth_app.Util.ClothUtil;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClothFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClothFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private List<String> mList;
    private FloatingActionButton addButton;
    private FloatingActionMenu addButtonMenu;
    private TextView topText;

    public ClothFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClothFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClothFragment newInstance(String param1, String param2) {
        ClothFragment fragment = new ClothFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        initActionButton();
    }

    private void initList() {
        mList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mList.add("Cloth " + i);
        }
    }

    private void initActionButton() {
        final ImageView addButtonIcon = new ImageView(getActivity());
        Resources resources = getActivity().getResources();
        addButtonIcon.setImageDrawable(resources.getDrawable(R.drawable.add, null));
        addButtonIcon.setScaleType(ImageView.ScaleType.FIT_XY);
        addButton = new FloatingActionButton.Builder(getActivity()).setContentView(addButtonIcon).build();
        ((FloatingActionButton.LayoutParams) addButton.getLayoutParams()).setMargins(200, 500, 30, 100);
        SubActionButton.Builder subAddButtons = new SubActionButton.Builder(getActivity());
        final ImageView subButtonImg1 = new ImageView(getActivity());
        final ImageView subButtonImg2 = new ImageView(getActivity());
        final ImageView subButtonImg3 = new ImageView(getActivity());
        final ImageView subButtonImg4 = new ImageView(getActivity());

        subButtonImg1.setImageDrawable(resources.getDrawable(R.drawable.top, null));
        subButtonImg1.setScaleType(ImageView.ScaleType.FIT_XY);
        subButtonImg2.setImageDrawable(resources.getDrawable(R.drawable.pant, null));
        subButtonImg2.setScaleType(ImageView.ScaleType.FIT_XY);
        subButtonImg3.setImageDrawable(resources.getDrawable(R.drawable.shoes, null));
        subButtonImg3.setScaleType(ImageView.ScaleType.FIT_XY);
        subButtonImg4.setImageDrawable(resources.getDrawable(R.drawable.hat, null));
        subButtonImg4.setScaleType(ImageView.ScaleType.FIT_XY);

        SubActionButton subButton1 = subAddButtons.setContentView(subButtonImg1).build();
        SubActionButton subButton2 = subAddButtons.setContentView(subButtonImg2).build();
        SubActionButton subButton3 = subAddButtons.setContentView(subButtonImg3).build();
        SubActionButton subButton4 = subAddButtons.setContentView(subButtonImg4).build();

        addButtonMenu = new FloatingActionMenu.Builder(getActivity()).addSubActionView(subButton1)
                .addSubActionView(subButton2)
                .addSubActionView(subButton3)
                .addSubActionView(subButton4)
                .attachTo(addButton).build();
        addButtonMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                addButtonIcon.setRotation(0);
                PropertyValuesHolder holder = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(addButtonIcon, holder);
                animator.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                addButtonIcon.setRotation(45);
                PropertyValuesHolder holder = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(addButtonIcon, holder);
                animator.start();
            }
        });
        subButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                intent.putExtra("addClothType", "top");
                startActivity(intent);
            }
        });
        subButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                intent.putExtra("addClothType", "pants");
                startActivity(intent);
            }
        });
        subButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                intent.putExtra("addClothType", "shoes");
                startActivity(intent);
            }
        });
        subButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                intent.putExtra("addClothType", "hat");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        initList();
        recyclerView = getView().findViewById(R.id.CLOTH_TYPE_LIST);
        topText = getView().findViewById(R.id.topText);
        Drawable[] drawables = topText.getCompoundDrawables();
        drawables[0].setBounds(300, 0, 400, 100);
        topText.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
        getList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cloth, container, false);
    }

    @Override
    public void onDetach() {
        addButtonMenu.close(true);
        addButton.setVisibility(View.INVISIBLE);
        super.onDetach();
    }

    private void getList() {
        String url = HttpService.serverUrl + "cloth/getall";
        HttpService.okHttpGetCloth(url, getActivity());
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(getActivity(), mList, ClothUtil.tops, ClothUtil.pants, ClothUtil.shoes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myRecyclerAdapter);
    }

}
