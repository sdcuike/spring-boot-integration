package com.sdcuike.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by beaver on 2017/6/5.
 */
public class EnumIntValueValidator implements ConstraintValidator<EnumIntValueValidation, Integer> {
    
    private EnumIntValueValidation enumIntValueValidation;
    
    @Override
    public void initialize(EnumIntValueValidation constraintAnnotation) {
        this.enumIntValueValidation = constraintAnnotation;
    }
    
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        
        IEnumIntValue enumIntValue = IEnumIntValue.of(this.enumIntValueValidation.enumClass(), value);
        
        return enumIntValue != null;
    }
}
