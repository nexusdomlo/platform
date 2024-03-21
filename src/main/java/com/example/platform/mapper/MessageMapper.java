package com.example.platform.mapper;

import com.example.platform.pojo.Message;
import com.example.platform.pojo.Result;
import org.apache.ibatis.annotations.*;
import org.springframework.jmx.export.annotation.ManagedNotification;

import java.sql.Date;
import java.util.List;

@Mapper
public interface MessageMapper {
/*    @Select("select * from vehiclemessage where id=#{id}")
    public Message findById(Integer id);*/
    @Select("select * from vehiclemessage")
    List<Message> list();
//    @Select("select * from vehiclemessage where student_num=#{student_num}")
//    List<M> selectAll()
    @Insert("insert into vehiclemessage(student_num,car_id,name,car_name,car_picture,phone,create_time,expire_time,position,state)"
            +"values(#{student_num},#{car_id},#{name},#{car_name},#{car_picture},#{phone},#{create_time},#{expire_time},#{position},#{state})")
    void add(Message vehicle_message);
    @Update("update vehiclemessage set name=#{name},car_name=#{car_name},car_picture=#{car_picture},phone=#{phone},create_time=#{create_time},expire_time=#{expire_time},position=#{position},state=#{state} where car_id=#{car_id}")
    void update(Message vehicle_message);
    @Select("select * from vehiclemessage where car_id=#{car_id}")
    Message findById(String car_id);
    @Delete("delete from vehiclemessage where car_id=#{car_id}")
    void remove(String car_id);
    @Update("update vehiclemessage set state=#{state} where car_id=#{car_id}")
    void updateVehicleState(String car_id, String state);
    @Update("update vehiclemessage set car_picture=#{car_picture} where car_id=#{car_id}")
    void updateVehiclePic(String car_picture,String car_id);
    List<Message> listContent(String student_num, String car_id, String name, String car_name, String phone,String position,String state);
    @Select("select * from vehiclemessage where student_num=#{student_num}")
    Message findByStudent_num(String student_num);
    @Delete("delete from vehiclemessage where expire_time<#{sDate}")
    void refresh(Date sDate);
}
