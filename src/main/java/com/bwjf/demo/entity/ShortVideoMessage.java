package com.bwjf.demo.entity;

import java.util.Map;

public class ShortVideoMessage extends BaseMessage {
    private String MediaId;
    private String ThumbMediaId;
    private String MsgId;

    public ShortVideoMessage(Map<String, String> respMap, String mediaId, String thumbMediaId, String msgId) {
        super(respMap);
        setMsgType("shortvideo");
        MediaId = mediaId;
        ThumbMediaId = thumbMediaId;
        MsgId = msgId;
    }
}
