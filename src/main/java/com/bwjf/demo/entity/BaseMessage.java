package com.bwjf.demo.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;
@XStreamAlias("xml")
public class BaseMessage {
     String ToUserName;
     String FromUserName;
     String CreateTime;
     String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }


    public BaseMessage(Map<String,String> respMap)  {
        ToUserName = respMap.get("FromUserName");
        FromUserName = respMap.get("ToUserName");
        CreateTime = System.currentTimeMillis()/1000+"";
    }
}
