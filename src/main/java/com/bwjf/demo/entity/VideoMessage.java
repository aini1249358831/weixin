package com.bwjf.demo.entity;

import java.util.Map;

public class VideoMessage extends BaseMessage {

    private String MediaId;
    private String ThumbMediaId;
    private String MsgId;

    public VideoMessage(Map<String, String> respMap, String mediaId, String thumbMediaId, String msgId) {
        super(respMap);
        setMsgType("video");
        MediaId = mediaId;
        ThumbMediaId = thumbMediaId;
        MsgId = msgId;
    }
}
