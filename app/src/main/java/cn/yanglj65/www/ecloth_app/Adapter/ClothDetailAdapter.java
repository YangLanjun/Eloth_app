package cn.yanglj65.www.ecloth_app.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.yanglj65.www.ecloth_app.Entity.Cloth;
import cn.yanglj65.www.ecloth_app.R;
import cn.yanglj65.www.ecloth_app.Util.ClothUtil;
import cn.yanglj65.www.ecloth_app.Util.PathUtil;

public class ClothDetailAdapter extends RecyclerView.Adapter<ClothDetailAdapter.MyViewHolder> {
    private Context mContext;
    private View mView;
    private List<Cloth> cloths;
    private String directory;

    public ClothDetailAdapter(Context context, String clothType) {
        mContext = context;
        directory = clothType;
        cloths = new ArrayList<>();
        switch (clothType) {
            case "tops/": {
                for (int i = 0; i < ClothUtil.tops.size(); i++) {
                    cloths.add(ClothUtil.tops.get(i).top);
                }
                break;
            }
            case "pants/": {
                for (int i = 0; i < ClothUtil.pants.size(); i++) {
                    cloths.add(ClothUtil.pants.get(i).pant);
                }
                break;
            }
            case "shoes/": {
                for (int i = 0; i < ClothUtil.shoes.size(); i++) {
                    cloths.add(ClothUtil.shoes.get(i).shoes);
                }
                break;
            }
            case "hats/": {
                break;
            }
            default:
                break;
        }
    }

    @NonNull
    @Override
    public ClothDetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        mView = view;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Cloth cloth = cloths.get(position);
        String fileName = directory + cloth.getType() + "/" + cloth.getColor() + ".png";
        holder.itemImage.setImageDrawable(PathUtil.assets2Drawable(mContext, fileName));
        setClothDetailText(cloth.getType(), cloth.getColor(), cloth.getSize(), holder);
    }

    @Override
    public int getItemCount() {
        return cloths.size();
    }

    private void setClothDetailText(String type, String color, String size, MyViewHolder holder) {
        String str = "类型：" + type + "\t\t颜色：" + color + "\n大小：" + size;
        holder.imageText.setText(str);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageButton itemImage;
        TextView imageText;

        MyViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            imageText = itemView.findViewById(R.id.itemText);
        }
    }

}
