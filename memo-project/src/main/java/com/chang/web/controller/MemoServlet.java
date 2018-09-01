package com.chang.web.controller;

import com.chang.entity.MemoGroup;
import com.chang.service.MemoGroupService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MemoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        MemoGroupService memoGroupService = context.getBean(MemoGroupService.class);
        List<MemoGroup> memoGroups = memoGroupService.queryAll();
        if(memoGroups!=null){
            session.setAttribute("memoGroups",memoGroups);
            req.getRequestDispatcher("WEB-INF/pages/memo.jsp").forward(req,resp);
        }else{
            resp.getWriter().append("Not Data");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
