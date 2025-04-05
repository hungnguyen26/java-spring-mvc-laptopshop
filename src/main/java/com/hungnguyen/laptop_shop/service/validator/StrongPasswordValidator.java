package com.hungnguyen.laptop_shop.service.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String>{
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return value.matches("(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+~`\\-=\\[\\]{};':\"\\\\|,.<>?/]).+");
    }
}
