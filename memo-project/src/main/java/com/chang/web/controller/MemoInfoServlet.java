package com.chang.web.controller;

import com.chang.entity.MemoInfo;
import com.chang.service.MemoInfoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MemoInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        MemoInfoService memoInfoService;
        HttpSession session = req.getSession();
        resp.setContentType("text/html;charset=UTF-8");
        Object attribute = req.getParameter("id");

        if(attribute!=null){
            Integer id = Integer.parseInt(String.valueOf(attribute));   //这里的id为组id
            memoInfoService=context.getBean(MemoInfoService.class);
            List<MemoInfo> memoInfoList= memoInfoService.queryMemoInfoByGroupId(id);
            System.out.println(memoInfoList);
            session.setAttribute("memoInfoList",memoInfoList);
            req.getRequestDispatcher("WEB-INF/pages/memoInfo.jsp").forward(req,resp);
        }else {
            resp.getWriter().append("Not Data!!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
