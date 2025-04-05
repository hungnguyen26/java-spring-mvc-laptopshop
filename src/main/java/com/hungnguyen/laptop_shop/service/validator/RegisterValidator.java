package com.hungnguyen.laptop_shop.service.validator;

import org.springframework.stereotype.Service;

import com.hungnguyen.laptop_shop.domain.DTO.RegisterDTO;
import com.hungnguyen.laptop_shop.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {
    private final UserService userService;
    

    public RegisterValidator(UserService userService) {
        this.userService = userService;
    }



    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context){
        boolean valid = true;

        //kiểm tra xem các trường mật khẩu có khớp không
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Passwords không khớp")
                   .addPropertyNode("confirmPassword")
                   .addConstraintViolation()
                   .disableDefaultConstraintViolation();
            valid = false;
        }

        if(this.userService.checkEmailExist(user.getEmail())){
            context.buildConstraintViolationWithTemplate("Email đã tồn tại")
                   .addPropertyNode("email")
                   .addConstraintViolation()
                   .disableDefaultConstraintViolation();
            valid = false;
        }
        return valid;
        
    }
}
