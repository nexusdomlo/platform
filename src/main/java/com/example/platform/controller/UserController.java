package com.example.platform.controller;

import com.example.platform.pojo.Result;
import com.example.platform.pojo.User;
import com.example.platform.service.UserService;
import com.example.platform.utils.JwtUtil;
import com.example.platform.utils.MD5Util;
import com.example.platform.utils.ThreadLocalUtil;
import com.mysql.cj.log.Log;
import com.mysql.cj.util.StringUtils;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;//redis的模板
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{8,16}$") String student_num, @Pattern(regexp = "^\\S{8,16}$")String password,Boolean identity)
    {
        User u=userService.findByStudentNum(student_num);
        if(u==null)
        {
            //register a new user
            userService.register(student_num,password,identity);
        }else{
            return Result.error("该用户已被注册");
        }
        return Result.success();
    }
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{8,16}$") String student_num, @Pattern(regexp = "^\\S{8,16}$")String password)
    {
        User u=userService.findByStudentNum(student_num);
        if(u==null)
        {
            return  Result.error("不存在该用户,请检查用户名");
        }
        else if(!Objects.equals(MD5Util.change(u.getPassword()), password)){
                return Result.error("密码不正确");
        }
        else{
            //密码正确的情况
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",u.getStudent_num());
            claims.put("username",u.getName());
            String token = JwtUtil.getToken(claims);
            ValueOperations<String,String>operations=stringRedisTemplate.opsForValue();
            operations.set(token,token,1, TimeUnit.HOURS);//把token存储到redis中，设置1小时作为超时时间
            return Result.success(token);
            //生成一个token令牌
        }
    }
    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        Map<String,Object>map = ThreadLocalUtil.get();//从线程局部变量中获取相应的值
        String userid = (String)map.get("id");
        User user = userService.findByStudentNum(userid);
        return Result.success(user);
    }
    @PostMapping("/update")//更新多个信息
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map<String,String>params){
        //@RequestBody形成json格式
        String oldPwd=params.get("old_pwd");
        String newPwd= params.get("new_pwd");
        String rePwd=params.get("re_pwd");
        Map<String,Object>map = ThreadLocalUtil.get();
        User Loginuser = userService.findByUserName((String) map.get("username"));
        if(oldPwd.length()==0||newPwd.length()==0||rePwd.length()==0)
        {
            return Result.error("缺少必要的参数");
        }
        if(!newPwd.equals(rePwd))
        {
            return Result.error("两次输入的密码不一样");
        }
        if(!oldPwd.equals(Loginuser.getPassword()))
        {
            return Result.error("原密码输入错误");
        }
        Loginuser.setPassword(newPwd);
        userService.updatePassword(Loginuser);
        //删除对应的token
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",(String) map.get("username"));
        claims.put("username",(String) map.get("username"));
        String token = JwtUtil.getToken(claims);
        ValueOperations<String,String>operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);//operations.getOperations能够获得更多操作，以此来delete对应的token
        //更新密码后，要删除redis中旧的token
        return Result.success();
    }
    @PatchMapping("/updatePic")
    public  Result updatePic(@RequestParam @URL String picture){
        //@URL是检验这个字符串是否是URL
        userService.updatePic(picture);
        return Result.success();
    }

}
