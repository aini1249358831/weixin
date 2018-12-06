package com.bwjf.demo.entity;

import java.util.Map;

public class LinkMessage extends  BaseMessage {
    private String Title;
    private String Description;
    private String Url;
    private String MsgId;

    public LinkMessage(Map<String, String> respMap, String title, String description, String url, String msgId) {
        super(respMap);
        setMsgType("link");
        Title = title;
        Description = description;
        Url = url;
        MsgId = msgId;
    }
}
