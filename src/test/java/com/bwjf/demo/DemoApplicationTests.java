package com.bwjf.demo;

import com.bwjf.demo.util.GetAccessTokenUtil;
import com.bwjf.demo.util.GetTwoDimensionalUtil;
import com.bwjf.demo.util.GetUserListUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
          String appID  = "wxfecc5fedc62a1e42";
         String appsecret = "f5dff1cfe24b2601a62a4b730b2ab51a";
          String url =  "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"
                .replace("APPID",appID).replace("APPSECRET",appsecret);
         /* while(true){
              String token = GetAccessTokenUtil.get(url);
              System.out.println(token);
          }*/
         /**
          * 测试获取用户列表*/
        String userList = GetUserListUtil.getUserList(url);
        System.out.println(userList);
    }

}
