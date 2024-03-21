package com.example.platform.anno;

import com.example.platform.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.text.Format;

//自定义校验
@Documented//元注解
@Target({ElementType.FIELD})//表明这是用到属性上的
@Retention(RetentionPolicy.RUNTIME)//什么时候使用
@Constraint(validatedBy = {StateValidation.class})//用StateValidation来进行校验
public @interface State {
    String message() default "参数值错误";//规定错误后返回的信息
    //指定分组
    Class<?>[]groups() default { };
    //负载获取到State注解的附加信息
    Class<? extends Payload>[] payload() default {};
    //在你需要进行校验的参数上面填写@State，就能实现自定义校验

}
