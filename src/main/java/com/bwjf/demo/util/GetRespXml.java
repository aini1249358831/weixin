package com.bwjf.demo.util;

import com.bwjf.demo.entity.BaseMessage;
import com.bwjf.demo.entity.TextMessage;
import com.thoughtworks.xstream.XStream;

import java.util.Map;

public class GetRespXml {
    public static String getResponse(Map<String, String> requestMap) {
        BaseMessage textMessage = null;
        String MsgType = requestMap.get("MsgType");
        switch (MsgType) {
            case "text":
                textMessage = dealTextMessage(requestMap);
                break;
            case "image":

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

    private static String beanToXml(BaseMessage textMessage) {
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        xStream.processAnnotations(BaseMessage.class);
        String xml = xStream.toXML(textMessage);
        return xml;
    }

    private static BaseMessage dealTextMessage(Map<String, String> requestMap) {
        TextMessage tm = new TextMessage(requestMap, requestMap.get("Content"));
        return tm;
    }
}
