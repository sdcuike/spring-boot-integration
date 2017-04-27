package com.sdcuike.mybatis.auditor;

/**
 * Created by beaver on 2017/4/25.
 */
public class AuditorServiceUtis {
    
    protected static InheritableThreadLocal<String> userIds = new InheritableThreadLocal<>();
    
    public static void setUserId(String userId) {
        userIds.set(userId);
    }
    
    public static String getUserId() {
        return userIds.get();
    }
    
    public static void remove() {
        userIds.remove();
    }
}
