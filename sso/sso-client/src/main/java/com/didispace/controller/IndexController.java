package com.didispace.controller;

import com.didispace.anno.LoginRequired;
import com.didispace.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;
import java.util.UUID;

@Controller
public class IndexController {

   /* @Autowired
    private RedisTemplate<String, String > redisTemplate;*/


    @LoginRequired
    @RequestMapping("/index1")
    public String index() {
//        redisTemplate.opsForValue().get(token).getAge().longValue();
        System.out.println("------------------index1-------------------------");
        return "index";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }




}
