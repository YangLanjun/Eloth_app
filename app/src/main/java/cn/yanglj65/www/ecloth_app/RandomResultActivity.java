package cn.yanglj65.www.ecloth_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import cn.yanglj65.www.ecloth_app.Entity.Cloth;
import cn.yanglj65.www.ecloth_app.Util.AlterUtil;
import cn.yanglj65.www.ecloth_app.Util.ClothUtil;
import cn.yanglj65.www.ecloth_app.Util.PathUtil;
import cn.yanglj65.www.ecloth_app.Util.ToolUtil;

public class RandomResultActivity extends AppCompatActivity {
    private ImageView topImgResult;
    private ImageView pantImgResult;
    private ImageView shoesImgResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_result);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Button back = findViewById(R.id.BACK_RANDOM_RESULT);
        ToolUtil.setButtonImageLeft(back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(RandomResultActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        String doingStr = getIntent().getStringExtra("doing");
        if (doingStr != null) {
            String btnText = "根据您即将 " + doingStr + "，我们为您做出如下穿搭推荐：";
            back.setText(btnText);
        }
        topImgResult = findViewById(R.id.TOP_IMG_RESULT);
        pantImgResult = findViewById(R.id.PANTS_IMG_RESULT);
        shoesImgResult = findViewById(R.id.SHOES_IMG_Result);
        Button feedbackBtn = findViewById(R.id.FEEDBACK_BTN);
        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolUtil.changeActivity(RandomResultActivity.this, BugReportActivity.class);
            }
        });
        getResult();
    }

    private void getResult() {
        if (ClothUtil.tops.size() > 0 && ClothUtil.pants.size() > 0 && ClothUtil.shoes.size() > 0) {
            int topNumber = ToolUtil.getRandomInt(ClothUtil.tops.size());
            int pantNumber = ToolUtil.getRandomInt(ClothUtil.pants.size());
            int shoesNumber = ToolUtil.getRandomInt(ClothUtil.shoes.size());
            String topImgPath = "tops/" + ClothUtil.tops.get(topNumber).top.getType() + "/" + ClothUtil.tops.get(topNumber).top.getColor() + ".png";
            String pantImgPath = "pants/" + ClothUtil.pants.get(pantNumber).pant.getType() + "/" + ClothUtil.pants.get(pantNumber).pant.getColor() + ".png";
            String shoesImgPath = "shoes/" + ClothUtil.shoes.get(shoesNumber).shoes.getType() + "/" + ClothUtil.shoes.get(shoesNumber).shoes.getColor() + ".png";
            topImgResult.setImageDrawable(PathUtil.assets2Drawable(RandomResultActivity.this, topImgPath));
            pantImgResult.setImageDrawable(PathUtil.assets2Drawable(RandomResultActivity.this, pantImgPath));
            shoesImgResult.setImageDrawable(PathUtil.assets2Drawable(RandomResultActivity.this, shoesImgPath));
        } else {
            AlterUtil.makeAlter(this, "您的衣橱中还没有足够的衣物，先去添加一些吧");
        }

    }
}
