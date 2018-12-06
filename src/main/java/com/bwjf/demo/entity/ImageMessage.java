package com.bwjf.demo.entity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Map;
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {
    /**图片链接*/
  /*  private String PicUrl;*/
    /**
     * 图片消息媒体id*/
    private Image image = new Image();


    public ImageMessage(Map<String, String> respMap) {
        super(respMap);
        setMsgType("image");
        image.setMediaId(respMap.get("MediaId"));
    }
}
