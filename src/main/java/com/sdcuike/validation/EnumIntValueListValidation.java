package com.sdcuike.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by beaver on 2017/6/5.
 */
@Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {EnumIntValueListValidator.class})
public @interface EnumIntValueListValidation {
    
    String message() default "Invalid value. This is not permitted.";
    
    
    Class<? extends IEnumIntValue> enumClass();
    
    //分组
    Class<?>[] groups() default {};
    
    //负载
    Class<? extends Payload>[] payload() default {};
    
}
