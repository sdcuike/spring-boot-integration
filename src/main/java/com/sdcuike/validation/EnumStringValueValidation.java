package com.sdcuike.validation;

/**
 * Created by cuikexiang on 2017/6/5.
 */
public @interface EnumStringValueValidation {
    
    public abstract String message() default "Invalid value. This is not permitted.";
    
    
    public abstract Class<? extends com.handchina.validation.IEnumStringValue> enumClass();
}
