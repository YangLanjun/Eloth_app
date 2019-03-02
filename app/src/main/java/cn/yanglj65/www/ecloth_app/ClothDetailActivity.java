package cn.yanglj65.www.ecloth_app;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.TextView;

import cn.yanglj65.www.ecloth_app.Adapter.ClothDetailAdapter;

public class ClothDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_detail);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        TextView topText = findViewById(R.id.CLOTH_DETAIL_TYPE);
        Drawable[] drawables = topText.getCompoundDrawables();
        drawables[0].setBounds(400, 200, 500, 300);
        String clothType = getIntent().getStringExtra("type");

        switch (clothType) {
            case "tops/": {
                topText.setText("上衣");
                break;
            }
            case "pants/": {
                topText.setText("裤子");
                break;
            }
            case "shoes/": {
                topText.setText("鞋子");
                break;
            }
            case "hats/": {
                topText.setText("帽子");
                break;
            }
            default: {
                topText.setText("错误的衣服类型");
                break;
            }
        }
        RecyclerView recyclerView = findViewById(R.id.CLOTH_DETAIL_LIST);
        ClothDetailAdapter clothDetailAdapter = new ClothDetailAdapter(ClothDetailActivity.this, clothType);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ClothDetailActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(clothDetailAdapter);
    }
}
