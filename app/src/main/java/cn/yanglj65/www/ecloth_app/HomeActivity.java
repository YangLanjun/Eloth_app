package cn.yanglj65.www.ecloth_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.reactivestreams.Subscription;

import java.io.IOException;

import cn.yanglj65.www.ecloth_app.Entity.Result;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
//import okhttp3.Call;
//import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Button testGetWeather = findViewById(R.id.GETWEATHERTEST_BTN);
        testGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeather();
            }
        });
    }

    @SuppressLint("CheckResult")
    private void getWeather(){
        final String WeatherUrl="http://wthrcdn.etouch.cn/weather_mini?citykey=101010100";
        final OkHttpClient client=new OkHttpClient();
       // Subscription subscription= Observable.defer()
       Observable.create(new ObservableOnSubscribe<String>() {
           @Override
           public void subscribe(final ObservableEmitter<String> emitter) throws IOException {
               Request request=new Request.Builder().get().url(WeatherUrl).build();
              Response response= client.newCall(request).execute();
              if(response.isSuccessful()){
                  String res=response.body().string();
                  emitter.onNext(res);
              }else{
                  emitter.onNext("网络异常");
              }
//               client.newCall(request).enqueue(new Callback() {
//                   @Override
//                   public void onFailure(Call call, IOException e) {
//                       emitter.onNext("网络异常");
//                   }
//                   @Override
//                   public void onResponse(Call call, Response response) throws IOException {
//                       String res=response.body().string();
//                       emitter.onNext(res);
//                   }
//               });

           }
       }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
           @Override
           public void accept(String s) throws Exception {
               mTextMessage.setText(s);
           }
       });
    }

}
