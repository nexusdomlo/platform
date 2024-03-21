package com.example.platform.interceptors;

import com.example.platform.utils.JwtUtil;
import com.example.platform.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
//每次请求都会用到的拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token=request.getHeader("Authorization");//从头中获取Authorization
        try{
            //从redis中获取token
            ValueOperations<String,String>operations = stringRedisTemplate.opsForValue();
            String redisToken=operations.get(token);
            if(redisToken==null)
            {
                //token已经失效
                throw new RuntimeException();
            }
            Map<String,Object> claims= JwtUtil.parseToken(token);
            ThreadLocalUtil.set(claims);//将claims注入ThreadLocalUtil
            //验证通过
            return true;
        }catch (Exception e){
            response.setStatus(401);
            //不放行
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
        //完成请求后移除注入的变量
    }
}
