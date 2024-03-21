package com.example.platform.controller;

import com.example.platform.pojo.Rent;
import com.example.platform.pojo.Result;
import com.example.platform.service.RenterService;
import com.example.platform.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/Renter")
@Validated
@CrossOrigin
public class RenterController {
    @Autowired
    private RenterService renterService;

    @GetMapping("/renterMessageList")
    public Result<Rent> renterMessageList(){
        Map<String,Object> map = ThreadLocalUtil.get();
        Rent rent = renterService.renterMessageList((String)map.get("id"));
        return Result.success(rent);
    }
    @GetMapping("/findRenter")
    public Result<Rent> findRenter(){
        Map<String,Object> map = ThreadLocalUtil.get();
        Rent rent = renterService.findRenter((String)map.get("id"));
        return Result.success(rent);
    }
    @PostMapping("/renterAdd")
    public Result renterAdd(@RequestBody @Validated Rent rent){
        renterService.renterAdd(rent);
        return Result.success();
    }
    @PutMapping("/updateRenter")
    public Result updateRenter(@RequestBody @Validated Rent rent)
    {
        renterService.updateRenter(rent);
        return Result.success();
    }
    @DeleteMapping("/removeRenter")
    public Result removeRenter(@RequestParam @Validated String car_id)
    {
        renterService.removeRenter(car_id);
        return Result.success();
    }


}
