package com.loong.modules.system.dao;


import com.loong.commons.mybatis.annotation.MybatisDao;
import com.loong.modules.system.entity.User;

import java.util.List;

@MybatisDao
public interface UserDao {
    List<User> getAll();
    void savePassword(User user);
    User getUserByLoginName(String loginName);
}
