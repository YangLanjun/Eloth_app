package cn.yanglj65.www.ecloth_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText UserName;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserName = (EditText) findViewById(R.id.USER_NAME_TEXT);
        Password = (EditText) findViewById(R.id.PWD_TEXT);
        Button Login = (Button) findViewById(R.id.LOGIN_BTN);
        Button SignUp=(Button)findViewById(R.id.SIGNUP_BTN);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(UserName.getText().toString().trim(),Password.getText().toString().trim());
            }
        });
    }

    private void check(String userName,String pwd){
        if(userName.equals("yanglj")&&pwd.equals("12345678")){
            Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(LoginActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();
        }
    }
}
