package com.example.helloworld.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;

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
        Claims claims = parseAllClaims(token);
        return claims != null ? claims.getSubject() : null;
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

    // ====================== 新增：完整解析Token的方法 ======================

    /**
     * 解析Token，获取所有载荷信息(Claims)
     * @param token 前端传入的token
     * @return Claims 包含所有信息，解析失败返回null
     */
    public static Claims parseAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            // 解析失败（签名错误、过期、格式非法）
            return null;
        }
    }

    /**
     * 获取Token签发时间
     */
    public static Date getIssuedAt(String token) {
        Claims claims = parseAllClaims(token);
        return claims != null ? claims.getIssuedAt() : null;
    }

    /**
     * 获取Token过期时间
     */
    public static Date getExpiration(String token) {
        Claims claims = parseAllClaims(token);
        return claims != null ? claims.getExpiration() : null;
    }

    /**
     * 获取Token中自定义字段（通用方法）
     * @param token token
     * @param key 自定义字段名
     * @return 字段值
     */
    public static <T> T getClaim(String token, String key) {
        Claims claims = parseAllClaims(token);
        return claims != null ? (T) claims.get(key) : null;
    }
}