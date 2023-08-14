package com.fizzyi;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    public void testUuid() {
        for (int i = 0; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

    /**
     * 加密 Jwt
     */
    @Test
    public void testGenJwt() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", 1);
        data.put("name", "Fizzyi");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "itheima") //签名算法
                .setClaims(data) // 自定义内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置 jwt 的有效期为 1h
                .compact();
        System.out.println(jwt);
    }

    /**
     * 解密 Jwt
     * 如果解析报错，要么令牌被修改要么就是过期了
     */
    @Test
    public void testParseJwt() {
        Claims claims = Jwts.parser().setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiRml6enlpIiwiaWQiOjEsImV4cCI6MTY5MTc0NDE1M30.bSjSfG0bogcrwPX_zMvJ4ZU8idehSbgZyxArpdNRbRc")
                .getBody();
        System.out.println(claims);
    }
}
