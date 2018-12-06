package com.bwjf.demo.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bwjf.demo.entity.*;
import com.thoughtworks.xstream.XStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetRespXml {
    public static final String APPKEY = "5c8f07d2643147749cc9220a38511e47";
    public static final String url = "http://openapi.tuling123.com/openapi/api/v2"; //调用接口地址
    public static final String user_id = "18731219625";
    public static String getResponse(Map<String, String> requestMap) {
        BaseMessage textMessage = null;
        String MsgType = requestMap.get("MsgType");
        String Content = requestMap.get("Content");
        if(Content.equals("图文")){
            textMessage = dealNewsMessage(requestMap);
            return beanToXml(textMessage);
        }
        switch (MsgType) {
            case "text":
                textMessage = dealTextMessage(requestMap);
                break;
            case "image":
                textMessage = dealImageMessage(requestMap);
                break;
            case "voice":

                break;
            case "video":

                break;
            case "music":

                break;
            case "news":

                break;
            default:

                break;

        }
        //把消息对象处理为xml

        if (textMessage != null) {
            return beanToXml(textMessage);
        }
        return null;
    }

    private static BaseMessage dealNewsMessage(Map<String,String> requestMap) {
        List<Article> list = new ArrayList<>();
        Article article = new Article("图文消息","第一次的外卖","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544092846209&di=1dc50897b05000aa8849e63b759bfb0b&imgtype=0&src=http%3A%2F%2Fimg.liansuo.com%2Fhtml%2Fimages%2F20170914%2F74551505363729.jpg","www.baidu.com");
        list.add(article);
        NewsMessage newsMessage = new NewsMessage(requestMap,list.size()+"",list);


        return newsMessage;
    }

    private static BaseMessage dealImageMessage(Map<String,String> requestMap) {


        ImageMessage imageMessage = new ImageMessage(requestMap);
        return imageMessage;
    }

    private static String beanToXml(BaseMessage textMessage) {
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        xStream.processAnnotations(BaseMessage.class);
        xStream.processAnnotations(ImageMessage.class);
        xStream.processAnnotations(NewsMessage.class);
        xStream.processAnnotations(Article.class);
        String xml = xStream.toXML(textMessage);
        return xml;
    }
    private static BaseMessage dealTextMessage(Map<String, String> requestMap) {
        String msg = requestMap.get("Content");


        /**得到请求的json字符串
         * */
        String requestJson = getRequestJson(msg);
        /**发送请求
         * */
        String  content = chat(url, requestJson);
        /**处理返回的json字符串
         * */
        String responseString = getResponseString(content);
        TextMessage tm = new TextMessage(requestMap,responseString);
        return tm;
    }
    /**
     * 连接图灵机器人的接口
     */
    public static String chat(String url,String requestJson) {
        /**
         *  请求的msg是json格式的*/
        PrintWriter writer = null;
        BufferedReader br = null;
        URL realUrl = null;
        String status = "";
        String responseStr = "";
        try {
            realUrl = new URL(url);
            //打开和url之间的连接
            URLConnection conn = realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) conn;
            //设置请求的属性
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("x-adviewrtb-version", "2.1");
            //发送post请求必须设置如下俩行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            //获取urlConnection对应的输出流
            writer = new PrintWriter(httpURLConnection.getOutputStream());
            //将请求相应给接口
            writer.print(requestJson);
            writer.flush();
            httpURLConnection.connect();
            //定义BufferReader来读取
            br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"utf-8"));
            String line;
            while ((line=br.readLine())!=null){
                responseStr+=line;
            }
            //拿出相应出来的代码
            status = new Integer(httpURLConnection.getResponseCode()).toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //关闭输入输出流
                try {
                    if(br!=null){ br.close(); }
                    if(writer!=null){ writer.close(); }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return responseStr;

    }
    /**得到连接机器人所需要的JSon字符串
     ** */
    public static  String getRequestJson(String msg){
        //请求json,包括reqType，perception，userInfo
        JSONObject reqJson = new JSONObject();
        /**
         * 文件类型*/
        int reqType = 0;
        reqJson.put("reqType",reqType);
        JSONObject perception = new JSONObject();
        JSONObject inputText = new JSONObject();
        inputText.put("text",msg);
        perception.put("inputText",inputText.toJSONString());
        JSONObject userInfo = new JSONObject();
        userInfo.put("apiKey",APPKEY);
        userInfo.put("userId",user_id);
        reqJson.put("userInfo",userInfo);
        reqJson.put("perception",perception);
        reqJson.put("inputText",inputText);
        return reqJson.toJSONString();
    }
    /**处理得到的json字符串
     * */
    public static String getResponseString(String responseJson){
    JSONObject jsonObject = JSONObject.parseObject(responseJson);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        JSONObject text = jsonArray.getJSONObject(0).getJSONObject("values");
        String handleString = text.getString("text");
        return handleString;
    }

}
