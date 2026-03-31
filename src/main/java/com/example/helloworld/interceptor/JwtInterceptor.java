package com.example.helloworld.interceptor;

import com.example.helloworld.util.JwtUtil;
import lombok.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

        // 前端约定把 token 放在请求头：token: xxx
        String token = request.getHeader("token");

        if (token == null || !JwtUtil.validateToken(token)) {
            response.setStatus(401);
            response.getWriter().write("未登录或token无效");
            return false;
        }

        return true;
    }
}