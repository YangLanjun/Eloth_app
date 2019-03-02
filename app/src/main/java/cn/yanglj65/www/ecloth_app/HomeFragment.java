package cn.yanglj65.www.ecloth_app;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import cn.yanglj65.www.ecloth_app.Util.AlterUtil;
import cn.yanglj65.www.ecloth_app.Util.ToolUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView mTextMessage;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mTextMessage = getView().findViewById(R.id.TEST_WEATHER);
        Button weatherBtn = getView().findViewById(R.id.GEI_WEATHER_BTN);
        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeather();
            }
        });
        Button occasionBtn = getView().findViewById(R.id.OCCASION_BTN);
        Button randomBtn = getView().findViewById(R.id.RANDOM_BTN);
        Button interactBtn = getView().findViewById(R.id.INTERACT_BTN);
        occasionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolUtil.changeActivity(getActivity(), OccasionActivity.class);
            }
        });
        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolUtil.changeActivity(getActivity(), RandomResultActivity.class);
            }
        });
        interactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlterUtil.makeAlter(getActivity(), "暂未开放，敬请期待");
            }
        });
    }

    @SuppressLint("CheckResult")
    private void getWeather() {
        final String WeatherUrl = "http://wthrcdn.etouch.cn/weather_mini?citykey=101280102";
        final OkHttpClient client = new OkHttpClient();
        // Subscription subscription= Observable.defer()
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws IOException {
                Request request = new Request.Builder().get().url(WeatherUrl).build();
                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    String res;
                    if (response.body() != null) {
                        res = response.body().string();
                        emitter.onNext(res);
                    } else {
                        emitter.onNext("网络异常,请检查网络后重试");
                    }

                } else {
                    emitter.onNext("网络异常");
                }
//               client.newCall(request).enqueue(new Callback() {
//                   @Override
//                   public void onFailure(Call call, IOException e) {
//                       emitter.onNext("网络异常");
//                   }
//                   @Override
//                   public void onResponse(Call call, Response response) throws IOException {
//                       String res=response.body().string();
//                       emitter.onNext(res);
//                   }
//               });

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                mTextMessage.setText(s);
            }
        });
    }
}
