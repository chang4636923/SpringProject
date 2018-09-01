package com.bittech.javaweb.service;

import com.bittech.javaweb.entiy.User;

/**
 * Author: secondriver
 * Created: 2018/7/29
 */
public interface UserService {
    
    boolean login(String username, String password);

    boolean register(User user);
    
}
