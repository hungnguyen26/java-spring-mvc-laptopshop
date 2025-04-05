package com.hungnguyen.laptop_shop.service.validator;

import com.hungnguyen.laptop_shop.domain.DTO.RegisterDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {
    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context){
        boolean valid = true;

        //kiểm tra xem các trường mật khẩu có khớp không
        if (!user.getPassword().equals(user.getComfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Passwords nhập sai")
                   .addPropertyNode("confirmPassword")
                   .addConstraintViolation()
                   .disableDefaultConstraintViolation();
            valid = false;
        }
        return valid;
        
    }
}
