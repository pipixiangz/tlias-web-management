package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class TliasWebManagementApplicationTests {

    /*@Test
    public void testUuid(){
        for (int i = 0; i < 1000; i++) {
            String string = UUID.randomUUID().toString();
            System.out.println(string);
        }
    }*/

    /**
     * 生成JWT
     */
    @Test
    public void testGenJwt(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","Tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "itheima") //数字签名算法
                .setClaims(claims) //自定义内容(载荷)
                .setExpiration(new Date(System.currentTimeMillis() + 24*3600*1000)) //有效期(比如24h)
                .compact();

        System.out.println(jwt);
    }

    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")//指定签名密钥（必须保证和生成令牌时使用相同的签名密钥）
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzA1Mzk3NDI5LCJ1c2VybmFtZSI6IlRvbSJ9.wjTG463Cv4xkvR_wu9AgNIpq9Kk0g4mcRMhAMoFfrzs")
                .getBody();

        System.out.println(claims);
    }

}
