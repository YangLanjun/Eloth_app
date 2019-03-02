package cn.yanglj65.www.ecloth_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ImageView welcomeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent intent = new Intent(this, LoginActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        welcomeImg = findViewById(R.id.welcomeImg);
        welcomeImg.setImageDrawable(MainActivity.this.getResources().getDrawable(R.drawable.welcome, null));
        //  startActivity(intent);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(timerTask, 3000);
    }
}
