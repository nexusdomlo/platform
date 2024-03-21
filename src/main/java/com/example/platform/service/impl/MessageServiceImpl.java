package com.example.platform.service.impl;

import com.example.platform.mapper.MessageMapper;
import com.example.platform.pojo.Message;
import com.example.platform.pojo.PageBean;
import com.example.platform.service.MessageService;
import com.example.platform.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public List<Message> list() {
        return messageMapper.list();
    }
    @Override
    public void updateAll(String student_num, String name, String phone){
        Message vehicle = messageMapper.findByStudent_num(student_num);
        if(vehicle==null)
        {
            return;
        }
        vehicle.setName(name);
        vehicle.setPhone(phone);
        messageMapper.update(vehicle);
    }
    @Override
    public void add(Message vehicle_message){
        Date date =new Date();
        long longtime = date.getTime();
        java.sql.Date sDate=new java.sql.Date(longtime);
        Map<String,Object> map= ThreadLocalUtil.get();
        vehicle_message.setStudent_num((String) map.get("id"));
        vehicle_message.setName((String) map.get("username"));
        vehicle_message.setCreate_time(sDate);
        messageMapper.add(vehicle_message);
    }
    @Override
    public void update(Message vehicle_message) {
        messageMapper.update(vehicle_message);
    }
    @Override
    public Message findById(String car_id) {
        return messageMapper.findById(car_id);
    }
    @Override
    public void remove(String car_id) {
        messageMapper.remove(car_id);
    }
    @Override
    public PageBean<Message> listContent(Integer pageNum, Integer pageSize, String student_num, String car_id, String name, String car_name, String phone,String position,String state) {
        PageBean<Message>content=new PageBean<>();
        //开启分页查询pageHelper
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object>map=ThreadLocalUtil.get();
        List<Message>messages=messageMapper.listContent(student_num,car_id,name,car_name,phone,position,state);
        //page类中提供了方法，可以获得某一页的数据和总记录条数
        Page<Message>page=(Page<Message>)messages;
        content.setTotal(page.getTotal());
        content.setItems(page.getResult());
        return content;
    }
    @Override
    public void updateVehiclePic(String car_picture, String car_id) {
        messageMapper.updateVehiclePic(car_picture,car_id);
    }
    @Override
    public void updateVehicleState(String car_id, String state) {
        messageMapper.updateVehicleState(car_id,state);
    }
    @Override
    public Message findByStudent_num(String student_num) {
        return messageMapper.findByStudent_num(student_num);

    }

    @Override
    public void refresh() {
        Date date =new Date();
        long longtime = date.getTime();
        java.sql.Date sDate=new java.sql.Date(longtime);
        messageMapper.refresh(sDate);
    }

}
