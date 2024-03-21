package com.example.platform.pojo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.validator.constraints.URL;

@Data
public class User {
    @NotNull//不能为null且不能为空
    private String student_num;
    private String name;
    @JsonIgnore
    private String password;
    @Email
    private String email;
    @URL
    private String picture;
    private String phone;
    private Boolean identity;
    public User(){}

    public User(String studentNum,String name,String password,String email) {
        this.student_num = studentNum;
        this.name = name;
        this.password = password;
        this.email=email;
    }

    public void setStudent_num(String student_num) {
        this.student_num = student_num;
    }

    public String getStudent_num() {
        return this.student_num;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
}
