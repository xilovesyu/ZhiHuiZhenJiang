package com.xixi.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by xijiaxiang on 16-7-17.
 */
public class JsonUtils {
    public static Map JsontoMap(String jsonString) throws JSONException {

        JSONObject jsonObject = new JSONObject(jsonString);

        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;

        while (iterator.hasNext()) {

            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);
            System.out.println(key+" "+value);
        }
        return result;

    }

    public static void main(String[] args) {
        String test="{\"charset\":\"utf8\",\"res\":{\"list\":{\"cookiepre\":\"GRHR_2132_\",\"auth\":\"6cf67jBWQeXKDTT0ZgSaju5qoP84DlQaeibBRsdfxMmabN2b8BzYtbN6yHl2V9wp5V22Aa8Zz28dZ46uzAdXBrQcy5k\",\"saltkey\":\"oA0AfNXf\",\"appsites\":{\"QicC_\":{\"cookiepre\":\"QicC_2132_\",\"url\":\"http:\\/\\/bbs.jsw.com.cn\\/bbs\\/\",\"auth\":\"7b68DyGT4RnSBC2t+zLmPyIfIDnDWr7zrFudG4DWFRrYTx2f+QxR02V3Tp\\/Z7tTaOwG4HjT7yfNVAHPL8RjDxUYG90A\",\"saltkey\":\"vDdA026j\"},\"3q1K_\":{\"cookiepre\":\"3q1K_2132_\",\"url\":\"http:\\/\\/v.m.zjxuexi.com\\/a\\/\",\"auth\":\"e1dd4wdXWLRFg8yTDPh\\/6LE5VG72EetUTH+fcYNfNEcHRRcQVGJa5GpZYlZX0GJ1Kf32htdZwb+VxIDZNH2m+acTwlw\",\"saltkey\":\"jT8FI3tT\"}},\"member_uid\":\"196391\",\"member_username\":\"user174165\",\"member_mobile\":\"18852866178\",\"groupid\":\"10\",\"formhash\":\"6cf67jBWQeXKDTT0ZgSaju5qoP84DlQaeibBRsdfxMmabN2b8BzYtbN6yHl2V9wp5V22Aa8Zz28dZ46uzAdXBrQcy5k\",\"ismoderator\":null,\"member_properties\":{\"uid\":\"196391\",\"mobile\":\"\",\"isvalid\":\"0\",\"field1\":\"eyJzc19iaW5kaW5nc3kiOiIiLCJzc19iaW5kaW5nIjoiMCIsInNzX3Bhc3N3b3JkIjoiIiwic3NfdXNlcm5hbWUiOiIiLCJzc19iaW5kaW5neWIiOiIiLCJzc19iaW5kaW5neWwiOiIiLCJzc19iaW5kaW5nZ3MiOiIiLCJzc19iaW5kaW5nc3l1IjoiIn0=\",\"field2\":\"\",\"field3\":\"\",\"field4\":\"\",\"openid\":null},\"mobile_auth\":\"36be%2FO3cGEMAAs37%2Bk0psrTjYyjPSk2RWnFn5dgo3Ad0hauU%2F0Caf6EhnRER3C7O%2Ftgg\"}},\"msg\":{\"msgvar\":\"login_success\",\"msgstr\":\"\"}}\n";
        String test2="{\"1\":\"{\"2\":\"2\"}\"}";
        JsontoMap(test2);
    }
}
