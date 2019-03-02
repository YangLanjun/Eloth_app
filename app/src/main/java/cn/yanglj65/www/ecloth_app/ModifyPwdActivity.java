package cn.yanglj65.www.ecloth_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import cn.yanglj65.www.ecloth_app.Service.HttpService;
import cn.yanglj65.www.ecloth_app.Util.AlterUtil;
import cn.yanglj65.www.ecloth_app.Util.ToolUtil;

public class ModifyPwdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pwd);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Button back = findViewById(R.id.BACK_MODIFY_PWD);
        ToolUtil.setButtonImageLeft(back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(ModifyPwdActivity.this, PersonInfoActivity.class);
                startActivity(intent);
            }
        });
        final EditText oldPwdText = findViewById(R.id.OLD_PWD_TEXT);
        final EditText newPwdText1 = findViewById(R.id.NEW_PWD_TEXT1);
        final EditText newPwdText2 = findViewById(R.id.NEW_PWD_TEXT2);
        final Button modifyPwdBtn = findViewById(R.id.MODIFY_PWD_BTN);

        modifyPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String oldPwd = oldPwdText.getText().toString().trim();
                final String newPwd1 = newPwdText1.getText().toString().trim();
                final String newPwd2 = newPwdText2.getText().toString().trim();
                SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                String currentPwd = sp.getString("password", null);
                if (currentPwd != null) {
                    if (oldPwd.equals(currentPwd)) {
                        if (newPwd1.length() >= 6) {
                            if (newPwd1.equals(newPwd2)) {
                                HttpService.modifyPwd(ModifyPwdActivity.this, oldPwd, newPwd1);
                            } else {
                                AlterUtil.makeAlter(ModifyPwdActivity.this, "两次输入的密码不一致");
                            }
                        } else {
                            AlterUtil.makeAlter(ModifyPwdActivity.this, "新密码密码的长度至少为6位");
                        }
                    } else {
                        AlterUtil.makeAlter(ModifyPwdActivity.this, "旧密码输入不正确！！！");
                    }
                } else {
                    AlterUtil.makeAlter(ModifyPwdActivity.this, "请先登录");
                }
            }
        });
    }
}
