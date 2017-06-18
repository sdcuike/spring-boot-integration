package com.sdcuike.spring.security;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by beaver on 2017/6/12.
 */
public class RichUserPrincipalExtractor implements PrincipalExtractor {
    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        RichUserDetails richUserDetails = new RichUserDetails();
        try {
            BeanUtils.copyProperties(richUserDetails,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return richUserDetails;
    }
}
