package cn.yanglj65.www.ecloth_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import cn.yanglj65.www.ecloth_app.Util.SettingUtil;
import cn.yanglj65.www.ecloth_app.Util.ToolUtil;

public class PrivacyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Switch locationSwitch=findViewById(R.id.LOCATION_SWITCH);
        Switch showSelfSwitch=findViewById(R.id.SPACE_SWITCH);
        Switch dataAnalysisSwitch=findViewById(R.id.DATA_ANALYSIS_SWITCH);
        Button back=findViewById(R.id.BACK_PRIVACY);
        ToolUtil.setButtonImageLeft(back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent=new Intent(PrivacyActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });
        locationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sp = getSharedPreferences("settings", Context.MODE_PRIVATE);
                if (isChecked) {
                    sp.edit().putBoolean("openLocation", true).apply();
                    SettingUtil.openLocation = true;
                } else {
                    sp.edit().putBoolean("openLocation", false).apply();
                    SettingUtil.openLocation = false;
                }
            }
        });
        showSelfSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sp = getSharedPreferences("settings", Context.MODE_PRIVATE);
                if (!isChecked) {
                    sp.edit().putBoolean("showSelf", true).apply();
                    SettingUtil.showSelf = true;
                } else {
                    sp.edit().putBoolean("showSelf", false).apply();
                    SettingUtil.showSelf = false;
                }
            }
        });
        dataAnalysisSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sp = getSharedPreferences("settings", Context.MODE_PRIVATE);
                if (isChecked) {
                    sp.edit().putBoolean("openAnalysis", true).apply();
                    SettingUtil.openAnalysis = true;
                } else {
                    sp.edit().putBoolean("openAnalysis", false).apply();
                    SettingUtil.openAnalysis = false;
                }
            }
        });
    }
}
