package com.sdcuike.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by beaver on 2017/6/5.
 */
public class EnumStringValueValidator implements ConstraintValidator<EnumStringValueValidation, String> {
    
    private EnumStringValueValidation enumIntValueValidation;
    
    @Override
    public void initialize(EnumStringValueValidation constraintAnnotation) {
        this.enumIntValueValidation = constraintAnnotation;
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        IEnumStringValue enumIntValue = IEnumStringValue.of(this.enumIntValueValidation.enumClass(), value);
        
        return enumIntValue != null;
    }
}
