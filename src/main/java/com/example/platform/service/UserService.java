package com.example.platform.service;

import com.example.platform.pojo.User;

public interface UserService {
    User findByStudentNum(String student_num);
    User findByUserName(String name);
    void register(String student_num, String password,Boolean identity);

    void update(User user);

    void updatePic(String picture);

    void updatePassword(User Loginuser);
}
