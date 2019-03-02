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

public class RemindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Button back = findViewById(R.id.BACKREMIND);
        ToolUtil.setButtonImageLeft(back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(RemindActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        Switch remindSwitch = findViewById(R.id.REMIND_SWITCH);
        remindSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sp = getSharedPreferences("settings", Context.MODE_PRIVATE);
                if (isChecked) {
                    sp.edit().putBoolean("showRemind", true).apply();
                    SettingUtil.showRemind = true;
                } else {
                    sp.edit().putBoolean("showRemind", false).apply();
                    SettingUtil.showRemind = false;
                }
            }
        });
    }
}
