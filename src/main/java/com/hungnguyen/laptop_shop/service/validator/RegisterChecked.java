package com.hungnguyen.laptop_shop.service.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = RegisterValidator.class)
@Target({ElementType.TYPE})  // phạm vi truy cập là 1  class
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RegisterChecked {
    String message() default "User register validator failed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
