package cn.yanglj65.www.ecloth_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.yanglj65.www.ecloth_app.Adapter.ClothDetailAdapter;
import cn.yanglj65.www.ecloth_app.Adapter.OccasionBtnAdapter;
import cn.yanglj65.www.ecloth_app.Util.ToolUtil;

public class OccasionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occasion);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Button back=findViewById(R.id.BACK_OCCASION);
        ToolUtil.setButtonImageLeft(back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent=new Intent(OccasionActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        String[] occasions=getResources().getStringArray(R.array.occasions);
        List<String> occasionList=new ArrayList<>(Arrays.asList(occasions));
        RecyclerView recyclerView = findViewById(R.id.OCCASIONS_BTN);
        OccasionBtnAdapter occasionBtnAdapter=new OccasionBtnAdapter(OccasionActivity.this,occasionList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(OccasionActivity.this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(occasionBtnAdapter);
    }
}
