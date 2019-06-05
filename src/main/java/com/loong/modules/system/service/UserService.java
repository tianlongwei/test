package com.loong.modules.system.service;

import com.loong.commons.security.Digest;
import com.loong.modules.system.dao.UserDao;
import com.loong.modules.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public List<User> getAll(){
        return userDao.getAll();
    }

    /**
     * 根据用户名查找用户
     * @param loginName
     * @return
     */
    public User getUserByLoginName(String loginName){
        return userDao.getUserByLoginName(loginName);
    }

    /**
     * 保存修改密码
     * @param user
     */
    public void savePassword(User user){
        userDao.savePassword(user);
    }
}
