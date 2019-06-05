package com.loong.commons.shiro.realm;

import com.loong.modules.system.entity.User;
import com.loong.modules.system.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealmDemo extends AuthenticatingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken uptoken= (UsernamePasswordToken) authenticationToken;
        String username = uptoken.getUsername();
        User user = userService.getUserByLoginName(username);
        if (user==null){
            throw new AccountException("用户不存在");
        }
        SimpleAuthenticationInfo info=null;
        String principal=username;
        String credentials=user.getPassword().substring(16);//
        ByteSource credentialsSalt=ByteSource.Util.bytes(user.getPassword().substring(0,16));
        System.out.println(user.getPassword().substring(16));
        System.out.println(user.getPassword().substring(0,16));
        String realmName=getName();
        info=new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);
        return info;
    }
}
