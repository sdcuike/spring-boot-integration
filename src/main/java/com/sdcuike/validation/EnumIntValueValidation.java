package com.sdcuike.validation;

/**
 * Created by beaver on 2017/6/5.
 */
public @interface EnumIntValueValidation {
    
    public abstract String message() default "Invalid value. This is not permitted.";
    
    
    public abstract Class<? extends IEnumIntValue> enumClass();
    
}
