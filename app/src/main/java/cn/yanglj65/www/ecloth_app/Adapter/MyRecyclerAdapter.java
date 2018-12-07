package cn.yanglj65.www.ecloth_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.yanglj65.www.ecloth_app.ClothDetailActivity;
import cn.yanglj65.www.ecloth_app.Entity.Cloth;
import cn.yanglj65.www.ecloth_app.Entity.Pants;
import cn.yanglj65.www.ecloth_app.Entity.Shoes;
import cn.yanglj65.www.ecloth_app.Entity.Tops;
import cn.yanglj65.www.ecloth_app.R;
import cn.yanglj65.www.ecloth_app.Util.PathUtil;
import cn.yanglj65.www.ecloth_app.Util.ToolUtil;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private List<String> mList;
    private Context mContext;
    private View mView;
    private List<Tops> topsList;
    private List<Pants> pantsList;
    private List<Shoes> shoesList;

    public MyRecyclerAdapter(Context context, List<String> data, List<Tops> tops, List<Pants> pants, List<Shoes> shoes) {
        mList = data;
        mContext = context;
        topsList = tops;
        pantsList = pants;
        shoesList = shoes;
    }

    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        mView = view;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (position % 2 == 0) {
            ArrayList<Cloth> clothList = new ArrayList<>();
            int length;
           final String directory;
            Resources resources = mContext.getResources();
            if (position == 0) {
                directory = "tops/";
                for (int i = 0; i < topsList.size(); i++) {
                    clothList.add(topsList.get(i).top);
                }

            } else if (position == 2) {
                directory = "pants/";
                holder.itemImage.setImageDrawable(resources.getDrawable(R.drawable.pant, null));
                holder.imageText.setText("裤子");
                for (int i = 0; i < pantsList.size(); i++) {
                    clothList.add(pantsList.get(i).pant);
                }
            } else if (position == 4) {
                directory = "shoes/";
                holder.itemImage.setImageDrawable(resources.getDrawable(R.drawable.shoes, null));
                holder.imageText.setText("鞋子");
                for (int i = 0; i < shoesList.size(); i++) {
                    clothList.add(shoesList.get(i).shoes);
                }
            } else if (position == 6) {
                directory = "hats/";
                holder.itemImage.setImageDrawable(resources.getDrawable(R.drawable.hat, null));
                holder.imageText.setText("帽子");
            }else {
                directory="";
            }
            length = clothList.size() <= 3 ? clothList.size() : 3;
            if (length == 0) {
                return;
            }
            holder.itemImage.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent=new Intent(mContext, ClothDetailActivity.class);
                    intent.putExtra("type",directory);
                    mContext.startActivity(intent);
                }
            });
            ArrayList<Integer> showNumber = ToolUtil.getRandomIntArray(length, clothList.size());
            for (int i = 0; i < length; i++) {
                String fileName = directory + clothList.get(showNumber.get(i)).getType() + "/" + clothList.get(showNumber.get(i)).getColor() + ".png";
                holder.items.get(i).setImageDrawable(PathUtil.assets2Drawable(mContext, fileName));
                holder.items.get(i).setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams itemParams = (LinearLayout.LayoutParams) holder.items.get(i).getLayoutParams();
                itemParams.topMargin = 135;
                holder.items.get(i).setLayoutParams(itemParams);
            }
        } else {
            LinearLayout linearLayout = (LinearLayout) mView.findViewById(R.id.itemLinear);
            ViewGroup.LayoutParams linearLayoutLayoutParams = linearLayout.getLayoutParams();
            linearLayoutLayoutParams.height = 65;
            linearLayout.setLayoutParams(linearLayoutLayoutParams);
            linearLayout.setBackgroundColor(Color.parseColor("#8B4513"));
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageButton itemImage;
        public TextView imageText;
        public ArrayList<ImageButton> items;


        public MyViewHolder(View itemView) {
            super(itemView);
            items = new ArrayList<>();
            ImageButton item1;
            ImageButton item2;
            ImageButton item3;
            itemImage = itemView.findViewById(R.id.itemImage);
            imageText=itemView.findViewById(R.id.itemText);
            item1 = itemView.findViewById(R.id.itemImage1);
            item2 = itemView.findViewById(R.id.itemImage2);
            item3 = itemView.findViewById(R.id.itemImage3);
            items.add(item1);
            items.add(item2);
            items.add(item3);
        }
    }

}
