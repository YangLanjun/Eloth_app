package cn.yanglj65.www.ecloth_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import cn.yanglj65.www.ecloth_app.Util.SettingUtil;
import cn.yanglj65.www.ecloth_app.Util.ToolUtil;

public class CommonSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_settings);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Switch notificationSwitch = findViewById(R.id.NOTIFICATION_SWITCH);
        Switch imgSwitch = findViewById(R.id.IMG_SWITCH);
        Spinner fontSizeSpinner = findViewById(R.id.FONT_SIZE_SPINNER);
        fontSizeSpinner.setSelection(1, true);
        Button back=findViewById(R.id.BACKCOMMON);
        ToolUtil.setButtonImageLeft(back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent=new Intent(CommonSettingsActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sp = getSharedPreferences("settings", Context.MODE_PRIVATE);
                if (isChecked) {
                    sp.edit().putBoolean("notification", true).apply();
                    SettingUtil.notificationOn = true;
                } else {
                    sp.edit().putBoolean("notification", false).apply();
                    SettingUtil.notificationOn = false;
                }
            }
        });
        imgSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sp = getSharedPreferences("settings", Context.MODE_PRIVATE);
                if (isChecked) {
                    sp.edit().putBoolean("showImg", true).apply();
                    SettingUtil.showImgNoWifi = true;
                } else {
                    sp.edit().putBoolean("showImg", false).apply();
                    SettingUtil.showImgNoWifi = false;
                }
            }
        });
        fontSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SettingUtil.fontSize = getResources().getStringArray(R.array.font_size)[position];
                SharedPreferences sp=getSharedPreferences("settings",Context.MODE_PRIVATE);
                sp.edit().putString("fontSize",SettingUtil.fontSize).apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
