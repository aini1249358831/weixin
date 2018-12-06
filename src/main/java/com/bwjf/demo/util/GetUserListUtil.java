package com.bwjf.demo.util;

public class GetUserListUtil {
    public static String getUserListurl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";

    public static String getUserList(String requestTokenUrl){
        String token = GetAccessTokenUtil.getToken(requestTokenUrl);
        String url = getUserListurl.replace("ACCESS_TOKEN", token);
        StringBuilder stringBuilder = GetRequestUtil.get(url);
        if(stringBuilder!=null){
            return new String(stringBuilder);
        }
        return  null;


    }

}
