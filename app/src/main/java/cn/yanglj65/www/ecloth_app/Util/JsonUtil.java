package cn.yanglj65.www.ecloth_app.Util;

import org.json.JSONException;
import org.json.JSONObject;

import cn.yanglj65.www.ecloth_app.Entity.Result;

public class JsonUtil {
    public static Result jsonToResult(JSONObject jsonObject) throws JSONException {
        Result result = new Result();
        result.setMsg(jsonObject.getString("msg"));
        result.setErrCode(jsonObject.getInt("errCode"));
        return result;
    }
}
