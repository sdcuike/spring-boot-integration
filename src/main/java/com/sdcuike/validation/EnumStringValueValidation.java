package com.sdcuike.validation;

/**
 * Created by beaver on 2017/6/5.
 */
public @interface EnumStringValueValidation {
    
    public abstract String message() default "Invalid value. This is not permitted.";
    
    
    public abstract Class<? extends IEnumStringValue> enumClass();
}
