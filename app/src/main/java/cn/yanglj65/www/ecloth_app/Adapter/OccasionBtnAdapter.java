package cn.yanglj65.www.ecloth_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import cn.yanglj65.www.ecloth_app.R;
import cn.yanglj65.www.ecloth_app.RandomResultActivity;


public class OccasionBtnAdapter extends RecyclerView.Adapter<OccasionBtnAdapter.MyViewHolder> {
    private Context mContext;
    private View mView;
    private List<String> occasionList;

    public OccasionBtnAdapter(Context context, List<String> occasions) {
        mContext = context;
        occasionList = occasions;
    }

    @NonNull
    @Override
    public OccasionBtnAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_btn, viewGroup, false);
        OccasionBtnAdapter.MyViewHolder holder = new OccasionBtnAdapter.MyViewHolder(view);
        mView = view;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OccasionBtnAdapter.MyViewHolder holder, int position) {
        holder.itemBtn.setText(occasionList.get(position));
        final int pos = position;
        holder.itemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(mContext, RandomResultActivity.class);
                intent.putExtra("doing", occasionList.get(pos));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return occasionList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public Button itemBtn;

        MyViewHolder(View itemView) {
            super(itemView);
            itemBtn = itemView.findViewById(R.id.itemBtn);
        }
    }
}
