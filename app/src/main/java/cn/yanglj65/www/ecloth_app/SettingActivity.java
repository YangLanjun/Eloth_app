package cn.yanglj65.www.ecloth_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import java.util.Objects;

import cn.yanglj65.www.ecloth_app.Util.AlterUtil;
import cn.yanglj65.www.ecloth_app.Util.SettingUtil;
import cn.yanglj65.www.ecloth_app.Util.ToolUtil;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Toolbar settingToolbar=findViewById(R.id.SETTINGTOOLBAR);
        setSupportActionBar(settingToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Button commonSetting = findViewById(R.id.COMMONBTN);
        commonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(SettingActivity.this, CommonSettingsActivity.class);
                startActivity(intent);
            }
        });
        Button remindBtn = findViewById(R.id.REMINDBTN);
        remindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(SettingActivity.this, RemindActivity.class);
                startActivity(intent);
            }
        });
        Button privacyBtn=findViewById(R.id.PRIVACYBTN);
        privacyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(SettingActivity.this, PrivacyActivity.class);
                startActivity(intent);
            }
        });
        Button updateBtn = findViewById(R.id.UPADATEBTN);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlterUtil.makeAlter(SettingActivity.this, "当前版本: " + SettingUtil.version + "\n" + SettingUtil.shouldUpdate
                        + "\n不要问我为什么弹框，因为这样可以少写一个界面\nCopyRight: ylj 2018");
            }
        });
        Button aboutBtn = findViewById(R.id.ABOUTBTN);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlterUtil.makeAlter(SettingActivity.this, "当前版本: " + SettingUtil.version + "\n" + SettingUtil.aboutMe
                        + "\n不要问我为什么弹框，因为这样可以少写一个界面\nCopyRight: ylj 2018");
            }
        });
    }
}
