package com.bwjf.demo.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("item")
public class Article {
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;

    public Article(String title, String description, String picUrl, String url) {
        Title = title;
        Description = description;
        PicUrl = picUrl;
        Url = url;
    }
}
