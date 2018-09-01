package com.bittech.javaweb.web.servlet.user;

import com.bittech.javaweb.entiy.User;
import com.bittech.javaweb.service.UserService;
import com.bittech.javaweb.util.StringUtil;
import com.bittech.javaweb.web.servlet.ApplicationLoadServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserLoginServlet extends ApplicationLoadServlet {
    
    private UserService userService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = getBean(UserService.class);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        request.setAttribute("userName", userName);
        request.setAttribute("password", password);

        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
            request.setAttribute("error", "用户名或密码为空！");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        User user = new User();
        user.setPassword(password);
        user.setUserName(userName);
        boolean loginResult = userService.login(userName, password);
        if (loginResult) {
            // 获取Session
            HttpSession session = request.getSession();
            session.setAttribute(CURRENT_USER, user);
            // 客户端跳转
            response.sendRedirect("main.jsp");
        } else {
            request.setAttribute("error", "用户名或密码错误！");
            // 服务器跳转
            request.getRequestDispatcher("index.jsp").forward(request, response);
           
        }
    }
}
