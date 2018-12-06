package com.bwjf.demo.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapWithXml {

    public static Map<String,String> parseRequest(InputStream is){
        SAXReader reader = new SAXReader();
        Map<String,String> map = new HashMap<>();
        try {
            Document document = reader.read(new BufferedReader( new InputStreamReader(is,"utf-8")));
            Element rootElement = document.getRootElement();
            List<Element> list = rootElement.elements();
            for (int i = 0; i < list.size(); i++) {
                map.put(list.get(i).getName(),list.get(i).getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return map;
    }
}
