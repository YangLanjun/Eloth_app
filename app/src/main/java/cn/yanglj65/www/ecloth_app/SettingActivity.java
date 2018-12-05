package cn.yanglj65.www.ecloth_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import cn.yanglj65.www.ecloth_app.Util.ToolUtil;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Button back=findViewById(R.id.BACKSETTING);
        ToolUtil.setButtonImageLeft(back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent=new Intent(SettingActivity.this,HomeActivity.class);
                intent.putExtra("fragment","user");
                startActivity(intent);
            }
        });
        Button commonSetting=findViewById(R.id.COMMONBTN);
        commonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent=new Intent(SettingActivity.this,CommonSettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
