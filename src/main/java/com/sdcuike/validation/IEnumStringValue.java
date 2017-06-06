package com.sdcuike.validation;

/**
 * Created by sdcuike on 2017/6/5.
 */
public interface IEnumStringValue {
    
    String getValue();
    
    
    public static <T extends IEnumStringValue> T of(Class<T> classz, String value) {
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
