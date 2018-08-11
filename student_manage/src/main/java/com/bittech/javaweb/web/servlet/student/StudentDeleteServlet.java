package com.bittech.javaweb.web.servlet.student;

import com.alibaba.fastjson.JSONObject;
import com.bittech.javaweb.service.StudentService;
import com.bittech.javaweb.web.servlet.ApplicationLoadServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet(name = "StudentDeleteServlet",urlPatterns = {"/studentDelete"} )
public class StudentDeleteServlet extends ApplicationLoadServlet {
    
    private StudentService studentService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        studentService = getBean(StudentService.class);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String delIds = request.getParameter("delIds");
        try {
            JSONObject result = new JSONObject();
            int delNums = studentService.removeStudent(delIds);
            if (delNums > 0) {
                result.put("success", "true");
                result.put("delNums", delNums);
            } else {
                result.put("errorMsg", "删除失败");
            }
            write(response, result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
