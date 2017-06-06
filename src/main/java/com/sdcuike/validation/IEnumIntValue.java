package com.sdcuike.validation;

/**
 * Created by sdcuike on 2017/6/5.
 */
public interface IEnumIntValue {
    
    Integer getValue();
    
    
    public static <T extends IEnumIntValue> T of(Class<T> classz, Integer value) {
        T[] enumConstants = classz.getEnumConstants();
        if (enumConstants == null) {
            return null;
        }
        
        for (T t : enumConstants) {
            if (t.getValue().equals(value)) {
                return t;
            }
        }
        
        return null;
    }
}
