package com.sdcuike.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Created by beaver on 2017/6/5.
 */

public class EnumIntValueListValidator implements ConstraintValidator<EnumIntValueListValidation, List<Integer>> {
    
    private EnumIntValueListValidation enumIntValueValidation;
    
    @Override
    public void initialize(EnumIntValueListValidation constraintAnnotation) {
        this.enumIntValueValidation = constraintAnnotation;
    }
    
    @Override
    public boolean isValid(List<Integer> value, ConstraintValidatorContext context) {
        
        for (Integer v : value) {
            IEnumIntValue enumIntValue = IEnumIntValue.of(this.enumIntValueValidation.enumClass(), v);
            
            if (enumIntValue == null) {
                return false;
            }
            
        }
        return true;
    }
}
