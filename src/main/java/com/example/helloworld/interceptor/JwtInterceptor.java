package com.example.helloworld.interceptor;

import com.example.helloworld.common.result.Result;
import com.example.helloworld.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT 拦截器：统一校验 token，适配前端 X-Token 请求头
 */
public class JwtInterceptor implements HandlerInterceptor {

    // 定义前端传的 token 请求头名称（统一常量，方便维护）
    private static final String TOKEN_HEADER = "X-Token";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 1. 放行 OPTIONS 预检请求（解决跨域问题，前端必带）
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        // 2. 从请求头获取 token（严格匹配前端的 X-Token）
        String token = request.getHeader(TOKEN_HEADER);
        System.out.println("✅ 拦截器拿到的 X-Token：" + token);

        // 3. token 为空：直接返回 401
        if (token == null || token.isEmpty()) {
            System.out.println("❌ X-Token 为空，拦截请求");
            return writeError(response, 401, "未登录，请先登录");
        }

        // 4. 校验 token 有效性
        boolean isValid = JwtUtil.validateToken(token);
        System.out.println("✅ X-Token 校验结果：" + isValid);

        if (!isValid) {
            System.out.println("❌ X-Token 无效/已过期，拦截请求");
            return writeError(response, 401, "token无效或已过期，请重新登录");
        }

        // 5. 解析用户名，存入 request 上下文（后续接口可直接获取）
        String username = JwtUtil.getUsername(token);
        if (username != null) {
            request.setAttribute("username", username);
            System.out.println("✅ 解析用户名：" + username + "，放行请求");
        }

        // 6. 校验通过，放行
        return true;
    }

    /**
     * 统一返回 JSON 格式的错误响应
     */
    private boolean writeError(HttpServletResponse response, int code, String msg) throws Exception {
        response.setStatus(code);
        response.setContentType("application/json;charset=UTF-8");
        Result<String> result = Result.error(code, msg);
        response.getWriter().write(objectMapper.writeValueAsString(result));
        return false;
    }
}