package com.sdcuike.validation;

/**
 * Created by beaver on 2017/6/5.
 */
public interface IEnumIntValue {
    
    Integer getValue();
    
    String description();
    
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
