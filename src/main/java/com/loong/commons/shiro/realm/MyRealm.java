package com.loong.commons.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @program: ssm
 * @description:
 * @AUTHOR: tlw
 * @create: 2019-06-02 10:50
 */
public class MyRealm implements Realm {
    @Override
    public String getName() {
        return "myrealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return true;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("进入myrealm");
        UsernamePasswordToken uptoken= (UsernamePasswordToken) token;
        String username = uptoken.getUsername();
        if ("admin".equals(username)){
        }else if (username.equals("aa")){

            throw new UnknownAccountException("账户不存在");
        }else {
            throw new AccountException("账户异常");
        }
        AuthenticationInfo info=null;
        String principal=username;
        String credentials="123";
        String realmName=getName();
        System.out.println("principal:"+principal+"=="+"credentials:"+credentials+"=="+"realmName:"+realmName);
        info=new SimpleAuthenticationInfo(principal,credentials,realmName);
        return info;
    }
}