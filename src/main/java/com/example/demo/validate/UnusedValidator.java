package com.example.demo.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.MUser;
import com.example.demo.repository.UserMapper;

public class UnusedValidator implements ConstraintValidator<Unused, String> {

    @Autowired
    private UserMapper mapper;//各自で設定が必要

    public void initialize(Unused constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
    	//各自で設定が必要
    	MUser email = mapper.findByEmail(value).orElse(null); // ここのvalueは入力値
        if(email == null){
        	return true;
        } 

        return false;
    }
}
