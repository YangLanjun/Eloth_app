package cn.yanglj65.www.ecloth_app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;

public class HomeActivity extends AppCompatActivity {
    private HomeFragment homeFragment;
    private ClothFragment clothFragment;
    private UserFragment userFragment;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                    }
                    transaction.replace(R.id.FRAME_LAYOUT, homeFragment);
                    transaction.commit();
                    navigation.setBackgroundColor(Color.parseColor("#dbdbdb"));
                    return true;
                case R.id.navigation_dashboard:

                    if (clothFragment == null) {
                        clothFragment = new ClothFragment();
                    }
                    transaction.replace(R.id.FRAME_LAYOUT, clothFragment);
                    transaction.commit();
                    navigation.setBackgroundColor(Color.parseColor("#deb887"));
                    return true;
                case R.id.navigation_notifications:

                    if (userFragment == null) {
                        userFragment = new UserFragment();
                    }
                    transaction.replace(R.id.FRAME_LAYOUT, userFragment);
                    transaction.commit();
                    navigation.setBackgroundColor(Color.parseColor("#dbdbdb"));
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
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment();
        transaction.replace(R.id.FRAME_LAYOUT, homeFragment);
        transaction.commit();
    }


}
