package cn.yanglj65.www.ecloth_app.Util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import cn.yanglj65.www.ecloth_app.LoginActivity;

public class UserUtil {
    public static int userId;
    public static String userName;
    public static String accessToken;
    public  static String role;
    public static  String phone;
    public static String createTime;
    public static void logOut(Context context){
        SharedPreferences sp=context.getSharedPreferences("login",Context.MODE_PRIVATE);
        sp.edit().putString("userName",null).putString("password",null).apply();
        final Intent intent=new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
