package com.bwjf.demo.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckUtil {
    public static String token ="token123";
    public static  boolean check(String signature,String timestamp,String nonce ){
        String[] str = new String[]{token,timestamp,nonce};
        Arrays.sort(str);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : str) {
            stringBuilder.append(s);
        }
        /**进行sha1加密*/
        StringBuilder sb = null;
        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            byte[] bytes = md.digest(new String(stringBuilder).getBytes());
            /**
             * 将加密后的字节数组转换成16进制处理*/
            char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return   signature.equals(new String(sb)) ;
    }


}
