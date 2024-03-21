package com.example.platform.pojo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import java.sql.Date;

@Data
public class Message {
    private String student_num;
    private String car_id;
    private String name;
    @NotEmpty
    private String car_name;
    private String phone;
    private Date create_time;
    private Date expire_time;
    @NotEmpty
    private String position;
    @NotEmpty
    private String state;
    @URL
    private String car_picture;

    public Message(){

    }
    public Message(String student_num,String car_id,String name,String car_name,String car_picture,String phone){
        this.student_num=student_num;
        this.car_id=car_id;
        this.name=name;
        this.car_name=car_name;
        this.car_picture=car_picture;
        this.phone=phone;

    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setCarId(String car_id){
        this.car_id=car_id;
    }

    public String getCarId(){
        return car_id;
    }
    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setStudent_num(String student_num) {
        this.student_num = student_num;
    }

    public String getStudent_num() {
        return student_num;
    }
    public void setCreateTime(Date date)
    {
        this.create_time=date;
    }
    public Date getCreateTime()
    {
        return create_time;
    }
    public void setExpireTime(Date date)
    {
        this.expire_time=date;
    }
    public Date getExpireTime()
    {
        return expire_time;
    }
}
