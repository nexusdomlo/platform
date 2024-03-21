package com.example.platform.service.impl;

import com.example.platform.mapper.UserMapper;
import com.example.platform.pojo.User;
import com.example.platform.service.UserService;
import com.example.platform.utils.MD5Util;
import com.example.platform.utils.ThreadLocalUtil;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByStudentNum(String student_num) {
        return userMapper.findByStudentNum(student_num);
    }
    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String student_num, String password,Boolean identity) {
        String MD5password= MD5Util.change(password);
        userMapper.add(student_num,MD5password,identity);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updatePic(String picture) {
        Map<String,Object>map= ThreadLocalUtil.get();
        userMapper.updatePic(picture, (String) map.get("id"));
    }

    @Override
    public void updatePassword(User Loginuser) {
        userMapper.updatePassword(Loginuser);
    }
}
