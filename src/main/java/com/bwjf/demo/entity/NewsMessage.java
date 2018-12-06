package com.bwjf.demo.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;
import java.util.Map;

@XStreamAlias("xml")
public class NewsMessage extends BaseMessage {
    private String ArticleCount;
    private List<Article> Articles;

    public NewsMessage(Map<String, String> respMap, String articleCount, List<Article> articles) {
        super(respMap);
        setMsgType("news");
        ArticleCount = articleCount;
        Articles = articles;
    }
}
