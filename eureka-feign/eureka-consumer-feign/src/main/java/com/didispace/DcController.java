package com.didispace;

import com.alibaba.fastjson.JSONObject;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author 翟永超
 * @create 2017/4/15.
 * @blog http://blog.didispace.com
 */
@RestController
public class DcController {

    @Autowired
    DcClient dcClient;

    @GetMapping("/consumer")
    public String dc() {
        return dcClient.consumer();
    }


    @GetMapping("/test")
    public String test(){
        LemonAPI lemonAPI = Feign.builder()
                .target(LemonAPI.class, "http://localhost:2001");
        String result = lemonAPI.get("abcnnnn" , "123");
        System.out.println(result);
        return result;
    }

    @GetMapping("/testurl1")
    public String testurl1() throws URISyntaxException {
        LemonAPI lemonAPI = Feign.builder()
                .target(LemonAPI.class, "http://localhost:2000");
        JSONObject q=new JSONObject();
        q.put("name","ooo");

        JSONObject j=new JSONObject();
//        j.put("header1","def;ghi");
        j.put("Content_type","application/json");

        JSONObject k=new JSONObject();
        k.put("age","1");
        k.put("sex","ss");
        k.put("name","hao");
        String result = lemonAPI.post(new URI("http://localhost:2001/testurl1") , q,j,k.toJSONString());

        System.out.println(result);
        return result;
    }

    @GetMapping("/testurl")
    public String testurl() throws URISyntaxException {
        LemonAPI lemonAPI = Feign.builder()
                .target(LemonAPI.class, "http://localhost:2000");
        JSONObject j=new JSONObject();
//        j.put("header1","def;ghi");
        j.put("Content_type","application/json");

        JSONObject k=new JSONObject();
        k.put("age","1");
        k.put("sex","ss");
//        String result = lemonAPI.post(new URI("http://localhost:2001/testurl") , new JSONObject(),j,k.toJSONString());
        String result1 = lemonAPI.post1(new URI("http://localhost:2001/testurl"));

        System.out.println(result1);
        return result1;
    }

}
