package com.bwjf.demo.controller;
import com.bwjf.demo.entity.BaseMessage;
import com.bwjf.demo.entity.TextMessage;
import com.bwjf.demo.util.CheckUtil;
import com.bwjf.demo.util.GetRespXml;
import com.bwjf.demo.util.MapWithXml;
import com.thoughtworks.xstream.XStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
@RestController
@RequestMapping("/api")
public class WeXinController {
    @GetMapping
    public String get(HttpServletRequest request, HttpServletResponse response){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if(CheckUtil.check(signature,timestamp,nonce)){
            try {
                PrintWriter responseWriter = response.getWriter();
                responseWriter.print(echostr);
                responseWriter.flush();
                responseWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("接入成功");
        }
        else{
            System.out.println("接入失败");
        }
        return null;
    }
      @PostMapping
    public String post(HttpServletRequest request,HttpServletResponse response) throws IOException {

              Map<String, String> map = MapWithXml.parseRequest(request.getInputStream());
              System.out.println(map);
              /**准备回复的数据包*/
              /**注解 @Xstream*/
          String respXml =  GetRespXml.getResponse(map);
          System.out.println(respXml);
          PrintWriter writer = response.getWriter();
          writer.print(respXml);
          writer.flush();
          writer.close();
          return null;
    }
}
