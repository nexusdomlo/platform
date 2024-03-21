package com.example.platform.validation;

import com.example.platform.anno.State;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> //表明为哪个类提供校验，为哪个数据类型提供校验
{
    //编写校验规则
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null)
            return false;
        if(s.equals("你要求的条件"))
            return true;
        return false;
    }
}
