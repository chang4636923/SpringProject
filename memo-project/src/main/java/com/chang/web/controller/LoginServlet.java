package com.chang.web.controller;

import com.chang.entity.User;
import com.chang.util.FormUserBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        FormUserBean formUserBean = new FormUserBean(req);
        User user = formUserBean.getUser();
        Map<String,String> map = formUserBean.getUserAttributeMap();

        if("chang".equals(user.getName())&&"123456".equals(user.getPassword())){
            session.setAttribute("map",map);
            session.setAttribute("user",user);
            req.getRequestDispatcher("WEB-INF/pages/index.jsp").forward(req,resp);
        }else{
            writer.append("No Data!");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

}
