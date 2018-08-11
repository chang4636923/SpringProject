package com.bittech.javaweb.dao;

import com.bittech.javaweb.entiy.User;


public interface UserDao {

    User login(User user);

    int insertUser(User user);

}


