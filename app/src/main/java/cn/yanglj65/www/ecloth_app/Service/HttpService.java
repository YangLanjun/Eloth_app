package cn.yanglj65.www.ecloth_app.Service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;
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
import cn.yanglj65.www.ecloth_app.Entity.Cloth;
import cn.yanglj65.www.ecloth_app.Entity.Pants;
import cn.yanglj65.www.ecloth_app.Entity.Result;
import cn.yanglj65.www.ecloth_app.Entity.Shoes;
import cn.yanglj65.www.ecloth_app.Entity.Tops;
import cn.yanglj65.www.ecloth_app.R;
import cn.yanglj65.www.ecloth_app.Util.AlterUtil;
import cn.yanglj65.www.ecloth_app.Util.ClothUtil;
import cn.yanglj65.www.ecloth_app.Util.JsonUtil;
import cn.yanglj65.www.ecloth_app.Util.UserUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class HttpService {
    //public final static String serverUrl = "http://192.168.137.214:8008/";
    //public final static String serverUrl = "http://192.168.43.93:8008/";
    //public final static String serverUrl = "http://192.168.0.109:8008/";
    public final static String serverUrl = "http://eloth.yanglj65.cn:8008/";

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
                int len;
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
    public static void okHttpGetCloth(final String url, final Context context) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws IOException {
                Request request = new Request.Builder().get().url(url).addHeader("Authorization", UserUtil.accessToken).build();
                final OkHttpClient httpClient = new OkHttpClient();
                Response response = httpClient.newCall(request).execute();
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
                if (response.isSuccessful()) {
                    String result;
                    ResponseBody body = response.body();
                    if (body != null) {
                        result = body.string();
                        emitter.onNext(result);
                    } else {
                        emitter.onNext("error");
                    }
                } else {
                    emitter.onNext("error");
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws JSONException {
                //AlterUtil.makeAlter(context,s);
                if (s.equals("error")) {
                    s = "网络异常，数据刷新失败";
                    AlterUtil.makeAlter(context, s);
                } else {
                    JSONObject resultJson = new JSONObject(s);
                    Result result = JsonUtil.jsonToResult(resultJson);
                    if (result.getMsg().equals("ok")) {

                        JSONObject dataObject = resultJson.getJSONObject("data");
                        JSONArray topsJsonArray = dataObject.getJSONArray("tops");
                        JSONArray pantsJsonArray = dataObject.getJSONArray("pants");
                        JSONArray shoesJsonArray = dataObject.getJSONArray("shoes");
                        ClothUtil.tops = JsonUtil.jsonToTopList(topsJsonArray);
                        ClothUtil.pants = JsonUtil.jsonToPantList(pantsJsonArray);
                        ClothUtil.shoes = JsonUtil.jsonToShoesList(shoesJsonArray);

                    } else {
                        AlterUtil.makeAlter(context, result.getMsg());
                    }
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    public static void okHttpAddCloth(final Context context, final String color, final String type, final String size, final boolean usability, final String addClothType, final View view) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws IOException {
                String addUrl = serverUrl + "cloth/add";
                OkHttpClient httpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder()
                        .add("color", color)
                        .add("type", type)
                        .add("size", size)
                        .add("usability", String.valueOf(usability))
                        .add("class", addClothType)
                        .build();
                final Request request = new Request.Builder()
                        .url(addUrl)
                        .post(formBody)
                        .addHeader("Authorization", UserUtil.accessToken)
                        .build();
                Response response = httpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    String result;
                    ResponseBody body = response.body();
                    if (body != null) {
                        result = body.string();
                        emitter.onNext(result);
                    } else {
                        emitter.onNext("服务器异常，请稍后重试");
                    }
                } else {
                    emitter.onNext("网络异常，请稍后重试");
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (s.equals("服务器异常，请稍后重试") || s.equals("网络异常，请稍后重试")) {
                    AlterUtil.makeAlter(context, s);
                } else {
                    JSONObject resultObject = new JSONObject(s);
                    final Result result = JsonUtil.jsonToResult(resultObject);
                    if (result.getMsg().equals("ok")) {
                        Snackbar.make(view, "添加衣物成功", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        AlterUtil.makeAlter(context, "添加失败：" + result.getMsg());
                    }
                }
            }
        });
    }

    public static Disposable sendBug(final Context context, final String bugStr) {
        final String netError = context.getString(R.string.net_error);
        final String ServerError = context.getString(R.string.server_error);
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws IOException {
                String sendUrl = serverUrl + "bug/send";
                OkHttpClient httpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder()
                        .add("bug", bugStr)
                        .build();
                final Request request = new Request.Builder().url(sendUrl)
                        .addHeader("Authorization", UserUtil.accessToken)
                        .post(formBody)
                        .build();
                Response response = httpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    String result;
                    ResponseBody body = response.body();
                    if (body != null) {
                        result = body.string();
                        emitter.onNext(result);
                    } else {
                        emitter.onNext(ServerError);
                    }
                } else {
                    emitter.onNext(netError);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (s.equals(ServerError) || s.equals(netError)) {
                    AlterUtil.makeAlter(context, s);
                } else {
                    JSONObject resultObject = new JSONObject(s);
                    final Result result = JsonUtil.jsonToResult(resultObject);
                    if (result.getMsg().equals("ok")) {
                        AlterUtil.makeAlter(context, "Bug已成功反馈给开发者\n咨询QQ 1069148429 了解最新的解决进度\n感谢您的反馈，祝您生活愉快");
                    } else {
                        AlterUtil.makeAlter(context, "发送失败：" + result.getMsg());
                    }
                }
            }
        });
    }

    public static Disposable modifyPwd(final Context context, final String oldPwd, final String newPwd) {
        final String netError = context.getString(R.string.net_error);
        final String ServerError = context.getString(R.string.server_error);
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws IOException {
                String sendUrl = serverUrl + "user/modifypwd";
                OkHttpClient httpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder()
                        .add("oldpassword", oldPwd)
                        .add("newpassword", newPwd)
                        .build();
                final Request request = new Request.Builder().url(sendUrl)
                        .addHeader("Authorization", UserUtil.accessToken)
                        .post(formBody)
                        .build();
                Response response = httpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    String result;
                    ResponseBody body = response.body();
                    if (body != null) {
                        result = body.string();
                        emitter.onNext(result);
                    } else {
                        emitter.onNext(ServerError);
                    }
                } else {
                    emitter.onNext(netError);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (s.equals(ServerError) || s.equals(netError)) {
                    AlterUtil.makeAlter(context, s);
                } else {
                    JSONObject resultObject = new JSONObject(s);
                    final Result result = JsonUtil.jsonToResult(resultObject);
                    if (result.getMsg().equals("ok")) {
                        AlterUtil.makeAlter(context, "密码已重置，请重新登录");
                        UserUtil.logOut(context);
                    } else {
                        AlterUtil.makeAlter(context, "修改失败：" + result.getMsg());
                    }
                }
            }
        });
    }
}
