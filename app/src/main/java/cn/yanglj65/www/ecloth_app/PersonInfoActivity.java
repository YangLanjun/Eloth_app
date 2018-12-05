package cn.yanglj65.www.ecloth_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import cn.yanglj65.www.ecloth_app.Util.AlterUtil;
import cn.yanglj65.www.ecloth_app.Util.ToolUtil;
import cn.yanglj65.www.ecloth_app.Util.UserUtil;

public class PersonInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Button userPhoneBtn=findViewById(R.id.USERPHONEINFOBTN);
        Button back=findViewById(R.id.BACKINFO);
        ToolUtil.setButtonImageLeft(back);
        TextView userNameText=findViewById(R.id.USERNAMEINFOTEXT);
        TextView userPhoneText=findViewById(R.id.USERPHONEINFOTEXT);
        TextView createTimeText=findViewById(R.id.TIMEINFOTEXT);
        userNameText.setText(UserUtil.userName);
        userPhoneText.setText(UserUtil.phone);
        createTimeText.setText(UserUtil.createTime);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent=new Intent(PersonInfoActivity.this,HomeActivity.class);
                intent.putExtra("fragment","user");
                startActivity(intent);
            }
        });
        userPhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlterUtil.makeAlter(PersonInfoActivity.this,UserUtil.phone);
            }
        });
    }
}
