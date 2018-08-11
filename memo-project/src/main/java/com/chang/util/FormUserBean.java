package com.chang.util;

import com.chang.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class FormUserBean {
    private User user;
    private HttpServletRequest request;

    public FormUserBean(HttpServletRequest request){
        this.request = request;
        user = new User();
        user.setName(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setAge(Integer.parseInt(request.getParameter("age")));
        user.setSex(request.getParameter("sex"));
    }

    public User getUser(){
        return user;
    }

    public Map<String,String> getUserAttributeMap(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("username",user.getName());
        map.put("password",user.getPassword());
        map.put("age",String.valueOf(user.getAge()));
        map.put("sex",user.getSex());
        return map;
    }

}
