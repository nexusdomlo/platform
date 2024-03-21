package com.example.platform.mapper;

import com.example.platform.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Mapper
public interface UserMapper {
    @Select("select * from user where student_num=#{student_num}")
    User findByStudentNum(String student_num);
    @Select("select * from user where name=#{username}")
    User findByUserName(String username);
    @Insert("insert into user(student_num,password,identity)"+"values(#{student_num},#{password},#{identity})")
    void add(String student_num,String password,Boolean identity);
    @Update("update user set name=#{name},email=#{email},phone=#{phone}where student_num=#{student_num}")
    void update(User user);
    @Update("update user set picture=#{picture} where student_num=#{student_num}")
    void updatePic(String picture,String student_num);
    @Update("update user set password=#{password} where student_num=#{student_num}")
    void updatePassword(User Loginuser);
}
