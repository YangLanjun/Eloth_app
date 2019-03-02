package cn.yanglj65.www.ecloth_app.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.yanglj65.www.ecloth_app.Entity.Pants;
import cn.yanglj65.www.ecloth_app.Entity.Result;
import cn.yanglj65.www.ecloth_app.Entity.Shoes;
import cn.yanglj65.www.ecloth_app.Entity.Tops;
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

    public static ArrayList<Tops> jsonToTopList(JSONArray jsonArray) throws JSONException {
        ArrayList<Tops> topList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject topObject = jsonArray.getJSONObject(i);
            if (topObject.getBoolean("usability")) {
                Tops tops = new Tops();
                tops.top.setId(topObject.getInt("id"));
                tops.top.setColor(topObject.getString("color"));
                tops.top.setSize(topObject.getString("size"));
                tops.top.setType(topObject.getString("type"));
                topList.add(tops);
            }
        }
        return topList;
    }

    public static ArrayList<Pants> jsonToPantList(JSONArray jsonArray) throws JSONException {
        ArrayList<Pants> pantList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject pantObject = jsonArray.getJSONObject(i);
            if (pantObject.getBoolean("usability")) {
                Pants pants = new Pants();
                pants.pant.setId(pantObject.getInt("id"));
                pants.pant.setColor(pantObject.getString("color"));
                pants.pant.setSize(pantObject.getString("size"));
                pants.pant.setType(pantObject.getString("type"));
                pantList.add(pants);
            }
        }
        return pantList;
    }

    public static ArrayList<Shoes> jsonToShoesList(JSONArray jsonArray) throws JSONException {
        ArrayList<Shoes> shoesList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject shoesObject = jsonArray.getJSONObject(i);
            if (shoesObject.getBoolean("usability")) {
                Shoes shoes = new Shoes();
                shoes.shoes.setId(shoesObject.getInt("id"));
                shoes.shoes.setColor(shoesObject.getString("color"));
                shoes.shoes.setSize(shoesObject.getString("size"));
                shoes.shoes.setType(shoesObject.getString("type"));
                shoesList.add(shoes);
            }
        }
        return shoesList;
    }
}
