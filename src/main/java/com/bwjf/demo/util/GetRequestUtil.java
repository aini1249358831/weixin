package com.bwjf.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetRequestUtil {
    public static StringBuilder get(String requestUrl){
        URL url = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            url = new URL(requestUrl);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpURLConnection  = (HttpURLConnection) connection;
            if(httpURLConnection.getResponseCode()==200){
                byte[] bytes = new byte[1024];
                int len = 0;
                InputStream inputStream = httpURLConnection.getInputStream();
                while((len=inputStream.read(bytes))!=-1){
                    stringBuilder.append(new String(bytes,0,len));
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(stringBuilder!=null){
            return stringBuilder;
        }
        return null;
    }


}
