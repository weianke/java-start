package com.example.helloworld.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // 固定密钥（生产环境建议配置到文件中，不要硬编码）
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 2天有效期 (毫秒)
    private static final long EXPIRE = 1000L * 60 * 60 * 24 * 2;

    /**
     * 生成 Token
     */
    public static String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(KEY)
                .compact();
    }

    /**
     * 刷新 Token（延长有效期）
     */
    public static String refreshToken(String oldToken) {
        try {
            if (!validateToken(oldToken)) {
                return null;
            }
            String username = getUsername(oldToken);
            return createToken(username);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从 Token 获取用户名
     */
    public static String getUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    /**
     * 校验 Token 是否合法
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // 捕获所有JWT相关异常（过期、签名错误等）
            return false;
        }
    }
}