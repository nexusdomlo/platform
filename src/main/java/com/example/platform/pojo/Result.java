package com.example.platform.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data //一定记得要添加
public class Result<T> {
    private Integer code;//业务状态码
    private String message;//提示信息
    private T data;//响应数据
    public static <E> Result<E> success(E data){
        return new Result<>(0,"success",data);
    }
    public static Result success(){
        return new Result<>(0,"success",null);
    }
    public static Result error(String message){
        return new Result<>(1,message,null);
    }
}
