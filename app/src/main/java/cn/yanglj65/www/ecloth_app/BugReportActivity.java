package cn.yanglj65.www.ecloth_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import cn.yanglj65.www.ecloth_app.Service.HttpService;
import cn.yanglj65.www.ecloth_app.Util.AlterUtil;
import cn.yanglj65.www.ecloth_app.Util.ToolUtil;

public class BugReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_report);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Button back = findViewById(R.id.BACK_BUG_REPORT);
        ToolUtil.setButtonImageLeft(back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(BugReportActivity.this, HomeActivity.class);
                intent.putExtra("fragment", "user");
                startActivity(intent);
            }
        });
        final TextView bugText = findViewById(R.id.BUG_TEXT);
        Button sendBugBtn = findViewById(R.id.SEND_BUG_BTN);
        sendBugBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String bugStr = bugText.getText().toString().trim();
                if (bugStr.equals("")) {
                    AlterUtil.makeAlter(BugReportActivity.this, "请输入您的问题后再提交");
                } else {
                    HttpService.sendBug(BugReportActivity.this, bugStr);
                }

            }
        });
    }
}
