package cn.yanglj65.www.ecloth_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import cn.yanglj65.www.ecloth_app.Util.SettingUtil;

public class CommonSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_settings);
        Switch notificationSwitch = findViewById(R.id.NOTIFICATION_SWITCH);
        Switch imgSwitch = findViewById(R.id.IMG_SWITCH);
        Spinner fontSizeSpinner = findViewById(R.id.FONT_SIZE_SPINNER);
        fontSizeSpinner.setSelection(1, true);
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
