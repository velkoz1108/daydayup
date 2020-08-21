package com.didispace;

import com.netflix.client.http.HttpHeaders;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
public class TestController {

    @GetMapping("/user/formGet")
    public String dc(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println(username+","+password);
        return username+","+password;
    }

    @PostMapping("/testurl")
    public String ff() {
        return "success";
    }

    @PostMapping("/testurl1")
    public String ff1(@RequestParam(value = "name",required = false) String name, @RequestHeader(required = false) HttpHeaders httpHeaders, @RequestBody(required = false) String body) throws UnsupportedEncodingException {
        return URLDecoder.decode("success,"+name+","+httpHeaders+","+body,"UTF-8");
    }
}
