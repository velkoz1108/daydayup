package com.didispace;

import com.alibaba.fastjson.JSONObject;
import feign.*;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.net.URI;

@FeignClient("eureka-client-hao")
public interface LemonAPI {
    @RequestLine("GET /user/formGet?username={username}&password={password}")
    String get(@Param("username") String username, @Param("password") String password);

    @RequestLine("POST")
    @Body("{body}")
    String post(URI url, @QueryMap JSONObject queryMap, @HeaderMap JSONObject header,@Param("body") String body);

    @RequestLine("POST")
    String post1(URI url);
}
