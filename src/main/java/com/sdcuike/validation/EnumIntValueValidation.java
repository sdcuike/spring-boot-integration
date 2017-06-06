package com.sdcuike.validation;

/**
 * Created by sdcuike on 2017/6/5.
 */
public @interface EnumIntValueValidation {
    
    public abstract String message() default "Invalid value. This is not permitted.";
    
    
    public abstract Class<? extends IEnumIntValue> enumClass();
    
}
