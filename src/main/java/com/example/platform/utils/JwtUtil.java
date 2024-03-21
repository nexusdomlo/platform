package com.example.platform.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final String KEY = "huangshunhao";
    @Test
    public static String getToken(Map<String,Object> claims){
//        Map<String,Object> claims = new HashMap<>();
//        claims.put("id",1);
//        claims.put("username","张三");
        //生产jwt的代码
        String token = JWT.create()
                .withClaim("user",claims)//添加载荷,就是将claims与user配对起来
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))//添加过期时间
                .sign(Algorithm.HMAC256(KEY));//指定算法添加密钥
        return token;
    }
    @Test
    public static Map<String,Object> parseToken(String token){
/*        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJleHAiOjE3MDg2MzM4MTgsInVzZXIiOnsiaWQiOjEsInVzZXJuYW1lIjoi5byg5LiJIn19" +
                ".6-TxF2IcB46kKSFhYEbmYQ3qmwIyncraGTOgZ1UGOWk";*/
        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(KEY)).build();//创建一个jwt的验证器
        DecodedJWT decodedJWT=jwtVerifier.verify(token);//解析，然后获得一个解析后的的对象
        Map<String, Object>Claim = decodedJWT.getClaim("user").asMap();
        return Claim;
        //如果头部和载荷部分数据被篡改，那么验证失效
        //如果密钥改了，验证失效
        //如果超时也失效
    }

}
