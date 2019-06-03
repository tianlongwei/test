package com.loong.commons.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.realm.Realm;

/**
 * @program: ssm
 * @description:
 * @AUTHOR: tlw
 * @create: 2019-06-02 10:50
 */
public class MyRealm implements Realm {
    private CredentialsMatcher credentialsMatcher;

    public CredentialsMatcher getCredentialsMatcher() {
        return credentialsMatcher;
    }

    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        this.credentialsMatcher = credentialsMatcher;
    }

    public MyRealm() {
        this(new SimpleCredentialsMatcher());
    }

    public MyRealm(CredentialsMatcher credentialsMatcher) {
        this.credentialsMatcher = credentialsMatcher;
    }

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
        /*
        *1、 这里写的方法并没有去与浏览器传入的密码进行匹配
        * 会直接通过检查，当前realm也没有设置密码匹配器
        * 2、在AuthenticatingRealm中是在getAuthenticationInfo方法中进行密码匹配比较的
        * 2、如果需要增加密码匹配
        */
        if (!assertCredentialsMatch(token,info)){
            throw new IncorrectCredentialsException("密码错误");
        }
        return info;
    }

    /**
     * 密码比较方法
     * @param token
     * @param info
     * @return
     */
    private boolean assertCredentialsMatch(AuthenticationToken token,AuthenticationInfo info){
        String pass = (String) token.getCredentials();
        String credentials1 = (String) info.getCredentials();
        if (pass.equals(credentials1)){
            return true;
        }
        return false;
    }

}