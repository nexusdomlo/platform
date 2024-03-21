package com.example.platform.controller;

import com.example.platform.pojo.Message;
import com.example.platform.pojo.PageBean;
import com.example.platform.pojo.Result;
import com.example.platform.service.MessageService;
import com.example.platform.utils.CarIdUtil;
import com.example.platform.utils.JwtUtil;
import com.example.platform.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Vehicle")
@Validated
@CrossOrigin
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/list")
    public Result<List> list() {
        List<Message> list = messageService.list();
        messageService.refresh();
        return Result.success(list);
    }
    @PostMapping("/updateVehiclePic")
    public Result updateVehiclePic(@RequestParam @URL String car_picture, @RequestParam String car_id) {
        messageService.updateVehiclePic(car_picture, car_id);
        return Result.success();
    }
    @PostMapping("/updateAll")
    public Result updateAll(@RequestParam String student_num, @RequestParam(required = false) String name,@RequestParam(required = false) String phone)
    {
        messageService.updateAll(student_num,name,phone);
        return Result.success();
    }
    @PostMapping("/addVehicleMessage")
    public Result addVehicleMessage(@RequestBody @Validated Message vehicle_message) {
        Message message = messageService.findByStudent_num(vehicle_message.getStudent_num());
        if(message==null)
            messageService.add(vehicle_message);
        else{
            return Result.error("抱歉，一个用户只能持有一个车辆");
        }
        return Result.success();
    }
    @PutMapping("/updateVehicleMessage")
    public Result updateVehicleMessage(@RequestBody @Validated Message vehicle_message) {
        if (messageService.findById(vehicle_message.getCar_id()) == null)
            return Result.error("不存在该车辆");
        messageService.update(vehicle_message);
        return Result.success();
    }
    @PostMapping("/updateVehicleState")
    public Result updateVehicleState(@RequestParam String car_id,@RequestParam String state)
    {
        messageService.updateVehicleState(car_id,state);
        return Result.success();
    }
    @DeleteMapping("/removeVehicleMessage")
    public Result removeVehicleMessage(@RequestParam @Validated String car_id)
    {
        if(messageService.findById(car_id)==null)
        {
            return Result.error("不存在该车辆");
        }
        messageService.remove(car_id);
        return Result.success();
    }
    @GetMapping()
    public Result<PageBean<Message>> listContent(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String student_num,
            @RequestParam(required = false) String car_id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String car_name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String state
    )
    { //@RequestParam说明可以添加或者不添加
        PageBean<Message>content=messageService.listContent(pageNum,pageSize,student_num,car_id,name,car_name,phone,position,state);
        return Result.success(content);
    }
}
