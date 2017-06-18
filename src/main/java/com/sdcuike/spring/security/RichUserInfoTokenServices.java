package com.sdcuike.spring.security;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;

/**
 * Created by beaver on 2017/6/12.
 * <p>
 * 获取用户信息-> oid
 *
 * @see https://stackoverflow.com/questions/35056169/how-to-get-custom-user-info-from-oauth2-authorization-server-user-endpoint/35092561
 */
public class RichUserInfoTokenServices extends UserInfoTokenServices {
    public RichUserInfoTokenServices(String userInfoEndpointUrl, String clientId) {
        super(userInfoEndpointUrl, clientId);
    }
    
    public RichUserInfoTokenServices(String userInfoEndpointUrl, String clientId, RichUserPrincipalExtractor richUserPrincipalExtractor) {
        super(userInfoEndpointUrl, clientId);
        setPrincipalExtractor(richUserPrincipalExtractor);
    }
    
}
