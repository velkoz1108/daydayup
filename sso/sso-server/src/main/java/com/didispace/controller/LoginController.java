package com.didispace.controller;

import com.didispace.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request,@RequestParam("userName") String userName, @RequestParam("passWord") Integer age) {
        HttpSession session = request.getSession();
        User user=new User(userName,age);
        String sid = generateSid(userName);

        session.setAttribute("loginUserId", sid);

        redisTemplate.opsForValue().set("loginUser:" + sid,
                session.getId());

//       redisTemplate.opsForValue().set(sid, userName);

       //跳转到首页
        return "redirect:http://localhost:8082/index1";
    }

    public String generateSid(String userName) {

        StringBuilder sb = new StringBuilder();
        sb.append('1')
                .append(new Random().nextInt(100))
                .append('_')
                .append(userName);
        return sb.toString();
    }


}
