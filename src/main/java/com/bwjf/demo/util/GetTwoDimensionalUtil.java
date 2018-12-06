package com.bwjf.demo.util;

import com.alibaba.fastjson.JSONObject;

public class GetTwoDimensionalUtil {
    public static String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";

    public static String getTwoDimensional(String getTokenUrl){
        /**调用获取token的方法
         * */
        String s = GetAccessTokenUtil.getToken(getTokenUrl);
        String getTwoCodeUrl = url.replace("TOKEN", s);
        /**
         * 拼接json数据*/
        JSONObject getTwoCodeJson = new JSONObject();
         getTwoCodeJson.put("action_name","QR_LIMIT_SCENE");
         JSONObject action_info = new JSONObject();
         JSONObject scene = new JSONObject();
         scene.put("scene_id",123);
         action_info.put("scene",scene);
         getTwoCodeJson.put("action_info",action_info);
        System.out.println(getTwoCodeJson.toJSONString());
        String chat = GetRespXml.chat(getTwoCodeUrl, getTwoCodeJson.toJSONString());


        return  chat;
    }





}
