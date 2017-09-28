package cn.czu.t1.Util;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

public class JsonUtil {
    public static String objToJsonString(Object obj){
        JSONObject jsonObject1 = JSONObject.fromObject(obj);
        return jsonObject1.toString();
    }

    public static String arrayToJsonString(Object list){
        JSONArray jsonArray1 = JSONArray.fromObject(list);
        return jsonArray1.toString();
    }
}
