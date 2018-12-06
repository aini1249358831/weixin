package com.bwjf.demo.entity;

import java.util.Map;

public class VoiceMessage extends BaseMessage {
    private String MediaId;
    private String Format;
    private String MsgID;

    public VoiceMessage(Map<String, String> respMap, String mediaId, String format, String msgID) {
        super(respMap);
        setMsgType("voice");
        MediaId = mediaId;
        Format = format;
        MsgID = msgID;
    }
}
