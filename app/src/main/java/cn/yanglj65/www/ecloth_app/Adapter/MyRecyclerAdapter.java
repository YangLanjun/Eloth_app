package cn.yanglj65.www.ecloth_app.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.util.List;
import cn.yanglj65.www.ecloth_app.R;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private List<String> mList;
    private Context mContext;
    private View mView;

    public MyRecyclerAdapter(Context context, List<String> data) {
        mList = data;
        mContext = context;
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
            Resources resources = mContext.getResources();
            if(position==0){
            } else if (position == 2) {
                holder.itemImage.setImageDrawable(resources.getDrawable(R.drawable.pant, null));
            } else if (position == 4) {
                holder.itemImage.setImageDrawable(resources.getDrawable(R.drawable.shoes, null));
            } else if (position == 6) {
                holder.itemImage.setImageDrawable(resources.getDrawable(R.drawable.hat, null));
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
        public ImageButton item1;
        public ImageButton item2;
        public ImageButton item3;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            item1 = itemView.findViewById(R.id.itemImage1);
            item2 = itemView.findViewById(R.id.itemImage2);
            item3 = itemView.findViewById(R.id.itemImage3);
        }
    }

}
