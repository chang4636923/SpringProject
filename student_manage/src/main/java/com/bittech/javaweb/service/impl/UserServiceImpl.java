package com.bittech.javaweb.service.impl;

import com.bittech.javaweb.dao.UserDao;
import com.bittech.javaweb.entiy.User;
import com.bittech.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Author: secondriver
 * Created: 2018/7/29
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public boolean login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return false;
        }
        User user = new User();
        user.setPassword(password);
        user.setUserName(username);
        return userDao.login(user) != null;
    }

    @Override
    public boolean register(User user){
        return 1==userDao.insertUser(user);
    }
    
}
