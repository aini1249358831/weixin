package com.bwjf.demo.controller;
import com.bwjf.demo.util.CheckUtil;
import com.bwjf.demo.util.GetRespXml;
import com.bwjf.demo.util.MapWithXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    static Logger logger = LoggerFactory.getLogger(WeXinController.class);
    public static  String appID  = "wxfecc5fedc62a1e42";
    public static  String appsecret = "f5dff1cfe24b2601a62a4b730b2ab51a";
    public static  String url =  "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"
            .replace("appid",appID).replace("secret",appsecret);
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
              /**准备回复的数据包*/
              /**注解 @Xstream*/
          String respXml =  GetRespXml.getResponse(map);
          logger.info("响应的xml信息"+respXml);
          response.setContentType("application/xml; charset=utf-8");
          PrintWriter writer = response.getWriter();
          writer.print(respXml);
          writer.flush();
          writer.close();
          return null;
    }
}
