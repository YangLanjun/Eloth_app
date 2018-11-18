package cn.yanglj65.www.ecloth_app;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.yanglj65.www.ecloth_app.Entity.Result;
import cn.yanglj65.www.ecloth_app.Entity.User;
import cn.yanglj65.www.ecloth_app.Service.HttpService;
import cn.yanglj65.www.ecloth_app.Util.AlterUtil;

public class SignupActivity extends AppCompatActivity {
    EditText userNameText;
    EditText phoneText;
    EditText passwordText;
    String userName;
    String password;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        userNameText = findViewById(R.id.USERNAME_TEXT);
        phoneText = findViewById(R.id.PHONE_TEXT);
        passwordText = findViewById(R.id.PWD_SIGNUP_TEXT);
        final Button signUpButton = findViewById(R.id.SIGNUP_SEND_BTN);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = userNameText.getText().toString().trim();
                password = passwordText.getText().toString().trim();
                phone = phoneText.getText().toString().trim();
                if (phone.length() != 11) {
                    AlterUtil.makeAlter(SignupActivity.this,"请输入正确的中国大陆地区手机号");
                    return;
                }
                if (userName.length() < 4) {
                    AlterUtil.makeAlter(SignupActivity.this,"用户名长度需要大于等于4位，请重新输入");
                    return;
                }
                if (password.length() < 6) {
                    AlterUtil.makeAlter(SignupActivity.this,"密码长度需要大于等于6为，请重新输入");
                }
                new Thread(runnable).start();
            }
        });
    }




    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data=msg.getData();
            String resultMsg= data.getString("msg");
            if (resultMsg.equals("ok")) {
                //Toast.makeText(SignupActivity.this, "注册成功，即将返回登陆界面", Toast.LENGTH_SHORT).show();
                final Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                AlterUtil.makeAlter(SignupActivity.this,resultMsg);
            }

        }
    };
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Result result = HttpService.userSignUp(userName, password, phone);
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("msg", result.getMsg());
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };

}
