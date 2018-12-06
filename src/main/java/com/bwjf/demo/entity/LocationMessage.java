package com.bwjf.demo.entity;

import java.util.Map;

public class LocationMessage extends BaseMessage {
    private String Location_X;
    private String Location_Y;
    private String Scale;
    private String Label;
    private String MsgId;

    public LocationMessage(Map<String, String> respMap, String location_X, String location_Y, String scale, String label, String msgId) {
        super(respMap);
        setMsgType("location");
        Location_X = location_X;
        Location_Y = location_Y;
        Scale = scale;
        Label = label;
        MsgId = msgId;
    }
}
