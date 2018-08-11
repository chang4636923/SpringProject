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


public class UserRegisterServlet extends ApplicationLoadServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = getBean(UserService.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");

        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)||StringUtil.isEmpty(rePassword)) {
            req.setAttribute("error", "用户名或密码为空！");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
            return;
        }
        if(!password.equals(rePassword)){
            req.setAttribute("error", "两次密码不一致！");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
            return;
        }
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        boolean regResult = userService.register(user);
        if(regResult){
            HttpSession session = req.getSession();
            session.setAttribute(CURRENT_USER, user);
            // 客户端跳转
            resp.sendRedirect("main.jsp");
        }else {
            req.setAttribute("error", "用户已存在！");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
            return;
        }

    }
}
