package cn.yanglj65.www.ecloth_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.yanglj65.www.ecloth_app.Entity.Result;
import cn.yanglj65.www.ecloth_app.Service.HttpService;
import cn.yanglj65.www.ecloth_app.Util.AlterUtil;
import cn.yanglj65.www.ecloth_app.Util.JsonUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    EditText UserName;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//设置底部导航栏栏
        UserName = findViewById(R.id.USER_NAME_TEXT);
        Password = findViewById(R.id.PWD_TEXT);
        Button Login = findViewById(R.id.LOGIN_BTN);
        Button SignUp = findViewById(R.id.SIGNUP_BTN);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(UserName.getText().toString().trim(), Password.getText().toString().trim());
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private DialogInterface.OnClickListener positiveClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface arg0, int arg1) {
            arg0.cancel();
        }
    };

    private void check(String userName, String pwd) {
        String loginUrl = HttpService.serverUrl + "user/login";
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("name", userName)
                .add("password", pwd)
                .build();
        final Request request = new Request.Builder()
                .url(loginUrl)
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlterUtil.makeAlter(LoginActivity.this, "网络异常，请稍后重试");
                    }
                });
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String res = response.body().string();
                try {
                    JSONObject resultObject=new JSONObject(res);
                    final Result result= JsonUtil.jsonToResult(resultObject);
                    if (result.getMsg().equals("ok")){
                        final Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AlterUtil.makeAlter(LoginActivity.this,result.getMsg());
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
