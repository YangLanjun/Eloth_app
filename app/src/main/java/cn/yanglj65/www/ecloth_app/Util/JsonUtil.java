package cn.yanglj65.www.ecloth_app.Util;

import org.json.JSONException;
import org.json.JSONObject;

import cn.yanglj65.www.ecloth_app.Entity.Result;
import cn.yanglj65.www.ecloth_app.Entity.User;

public class JsonUtil {
    public static Result jsonToResult(JSONObject jsonObject) throws JSONException {
        Result result = new Result();
        result.setMsg(jsonObject.getString("msg"));
        result.setErrCode(jsonObject.getInt("errCode"));
        return result;
    }

    public static void jsonToUser(JSONObject jsonObject) throws JSONException {
        UserUtil.accessToken = jsonObject.getString("accessToken");
        UserUtil.userId = jsonObject.getInt("id");
        UserUtil.userName = jsonObject.getString("userName");
        UserUtil.createTime = jsonObject.getString("createTime");
        UserUtil.role = jsonObject.getString("role");
        UserUtil.phone = jsonObject.getString("phone");
    }
}
