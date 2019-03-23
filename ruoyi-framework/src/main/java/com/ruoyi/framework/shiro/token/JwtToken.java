package com.ruoyi.framework.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/* *
 * @Author tomsun28
 * @Description JWT token
 * @Date 19:37 2018/2/10
 */
public class JwtToken implements AuthenticationToken{

    private static final long serialVersionUID = 1L;

    private final String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}