package com.example.platform.pojo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Rent {
    @NotEmpty
    private String student_num;
    private String car_id;
    private String car_name;
    private String phone;
    private String renter;
    private String answer;
    public Rent(){}

    public Rent(String student_num,String car_id,String car_name,String phone,String renter,String answer){
        this.student_num=student_num;
        this.car_id=car_id;
        this.car_name=car_name;
        this.phone=phone;
        this.renter=renter;
        this.answer=answer;
    }
}
