package cn.yanglj65.www.ecloth_app.Service;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import cn.yanglj65.www.ecloth_app.Adapter.MyRecyclerAdapter;
import cn.yanglj65.www.ecloth_app.Entity.Result;
import cn.yanglj65.www.ecloth_app.Entity.User;
import cn.yanglj65.www.ecloth_app.Util.AlterUtil;
import cn.yanglj65.www.ecloth_app.Util.JsonUtil;
import cn.yanglj65.www.ecloth_app.Util.UserUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class HttpService {
    //public final static String serverUrl = "http://192.168.137.214:8008/";
   // public final static String serverUrl = "http://192.168.43.93:8008/";
    public final static String serverUrl = "http://192.168.0.109:8008/";

    public static Result userSignUp(final String name, final String pwd, final String phone) {
        String signUpUrl = serverUrl + "user/signup";
        HttpURLConnection conn = null;
        Result result = new Result();
        try {
            conn = (HttpURLConnection) new URL(signUpUrl).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置请求方式,请求超时信息
        if (conn == null) {
            result.setMsg("请求发送异常");
            result.setErrCode(1);
            return result;
        }
        try {
            conn.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        conn.setReadTimeout(5000);
        conn.setConnectTimeout(5000);
        //设置运行输入,输出:
        conn.setDoOutput(true);
        conn.setDoInput(true);
        //Post方式不能缓存,需手动设置为false
        conn.setUseCaches(false);
        //我们请求的数据:
        String data = null;
        try {
            data = "name=" + URLEncoder.encode(name, "UTF-8") + "&password=" + URLEncoder.encode(pwd, "UTF-8") + "&phone=" + URLEncoder.encode(phone, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //这里可以写一些请求头的东东...
        //获取输出流
        OutputStream out = null;
        try {
            out = conn.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (out == null) {
                result.setMsg("发送失败，请确认请求地址是否正确");
                result.setErrCode(1);
                return result;
            }
            assert data != null;
            out.write(data.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            if (conn.getResponseCode() == 200) {
                // User user = new User();
                InputStream is = conn.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    message.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                message.close();
                // 返回字符串
                String msg = new String(message.toByteArray());
                JSONObject resultObject = null;
                try {
                    resultObject = new JSONObject(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //JSONObject userObject=resultObject.getJSONObject("data");
                try {
                    if (resultObject == null) {
                        result.setMsg("服务器维护中，请稍后重试");
                        result.setErrCode(1);
                        return result;
                    }
                    result.setMsg(resultObject.getString("msg"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    result.setErrCode(resultObject.getInt("errCode"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                result.setMsg("网络连接异常，请稍后重试");
                result.setErrCode(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressLint("CheckResult")
    public static void okHttpGetCloth(final String url, final Context context, final RecyclerView recyclerView, final List<String> mList){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws IOException {
                Request request=new Request.Builder().get().url(url).addHeader("Authorization", UserUtil.accessToken).build();
                final OkHttpClient httpClient=new OkHttpClient();
                Response response=httpClient.newCall(request).execute();
//                httpClient.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                        emitter.onNext("网络异常，数据刷新失败");
//                    }
//
//                    @Override
//                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                        String result=response.body().string();
//                        emitter.onNext(result);
//                    }
//                });
                if(response.isSuccessful()){
                    String result;
                    if(response.body()!=null){
                        result=response.body().string();
                        emitter.onNext(result);
                    }else{
                        emitter.onNext("网络异常，数据刷新失败");
                    }
                }else{
                    emitter.onNext("网络异常，数据刷新失败");
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s){
                AlterUtil.makeAlter(context,s);
                MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(context, mList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(myRecyclerAdapter);
            }
        });
    }

    @SuppressLint("CheckResult")
    public static void okHttpAddCloth(final Context context, final String color, final String type, final String size, final boolean usability , final String addClothType, final View view){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String>emitter) throws IOException {
                String addUrl=serverUrl+"cloth/add";
                OkHttpClient httpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("color",color)
                        .add("type",type)
                        .add("size",size)
                        .add("usability",String.valueOf(usability))
                        .add("class",addClothType)
                        .build();
                final Request request=new Request.Builder()
                        .url(addUrl)
                        .post(formBody)
                        .addHeader("Authorization",UserUtil.accessToken)
                        .build();
                Response response=httpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String result;
                    ResponseBody body = response.body();
                    if(body !=null){
                        result= body.string();
                        emitter.onNext(result);
                    }else{
                        emitter.onNext("服务器异常，请稍后重试");
                    }
                }else{
                    emitter.onNext("网络异常，请稍后重试");
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if(s.equals("服务器异常，请稍后重试")||s.equals("网络异常，请稍后重试")){
                    AlterUtil.makeAlter(context,s);
                }else{
                    JSONObject resultObject=new JSONObject(s);
                    final Result result= JsonUtil.jsonToResult(resultObject);
                    if(result.getMsg().equals("ok")){
                        Snackbar.make(view, "添加衣物成功", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }else{
                        AlterUtil.makeAlter(context,"添加失败："+result.getMsg());
                    }
                }
            }
        });

    }
}
