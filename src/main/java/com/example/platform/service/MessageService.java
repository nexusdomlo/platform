package com.example.platform.service;

import com.example.platform.pojo.Message;
import com.example.platform.pojo.PageBean;

import java.util.List;

public interface MessageService {
/*    public Message findById(Integer id);*/
    List<Message> list();

    void updateAll(String student_num, String name, String phone);

    void add(Message vehicle_message);

    void update(Message vehicle_message);
    Message findById(String car_id);

    void remove(String car_id);
    //条件分页列表查询
    PageBean<Message> listContent(Integer pageNum, Integer pageSize, String student_num, String car_id, String name, String car_name, String phone,String position,String state);

    void updateVehiclePic(String car_picture,String car_id);

    void updateVehicleState(String car_id, String state);

    Message findByStudent_num(String student_num);

    void refresh();
}
