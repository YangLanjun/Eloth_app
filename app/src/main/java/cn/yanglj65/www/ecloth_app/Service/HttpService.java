package cn.yanglj65.www.ecloth_app.Service;

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

import cn.yanglj65.www.ecloth_app.Entity.Result;
import cn.yanglj65.www.ecloth_app.Entity.User;


public class HttpService {
    public final static String serverUrl = "http://192.168.137.214:8008/";

    public static Result userSignUp(final  String name, final  String pwd, final  String phone) {
                String signUpUrl = serverUrl + "user/signup";
                HttpURLConnection conn = null;
                Result result = new Result();
                try {
                    conn = (HttpURLConnection) new URL(signUpUrl).openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //设置请求方式,请求超时信息
                if(conn==null){
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
                    if(out==null){
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
                            if(resultObject==null){
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

                    }else{
                        result.setMsg("网络连接异常，请稍后重试");
                        result.setErrCode(1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
    }
}
