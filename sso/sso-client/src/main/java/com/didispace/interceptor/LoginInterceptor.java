package com.didispace.interceptor;

import com.didispace.Const;
import com.didispace.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * 基于URL实现的拦截器
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if (path.matches(Const.NO_INTERCEPTOR_PATH)) {
            //不需要的拦截直接过
            return true;
        } else {
            // 这写你拦截需要干的事儿，比如取缓存，SESSION，权限判断等
            HttpSession session = request.getSession();
            if (session.getAttribute("loginUserId") != null) {
                try {
                    //验证当前请求的session是否是已登录的session
                    String loginSessionId = redisTemplate.opsForValue().get("loginUser:" + session.getAttribute("loginUserId"));
                    if (loginSessionId != null && loginSessionId.equals(session.getId())) {
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        response401(response);
        return false;
    }

    private void response401(HttpServletResponse response)
    {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        try
        {
            response.getWriter().print("用户未登录!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
