package com.bwjf.demo.util;
import com.alibaba.fastjson.JSONObject;
import com.bwjf.demo.entity.AccessToken;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetAccessTokenUtil {
    /**通过url获取token
     * */
     public  static AccessToken accessToken  ;

    public static void get(String requestUrl){
         StringBuilder stringBuilder = GetRequestUtil.get(requestUrl);
        JSONObject jsonObject = JSONObject.parseObject(new String(stringBuilder));
        String access_token = jsonObject.getString("access_token");
        String expires_in = jsonObject.getString("expires_in");
        /**
         * 使用一个对象存储accessToken*/
          accessToken = new AccessToken(access_token,expires_in);
    }
    public static String getToken(String requestUrl){
        if(accessToken==null||accessToken.isExpired()){
            get(requestUrl);
        }
        return accessToken.getAccess_token();
    }












}
