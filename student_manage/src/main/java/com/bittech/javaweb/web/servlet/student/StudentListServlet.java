package com.bittech.javaweb.web.servlet.student;

import com.bittech.javaweb.entiy.Student;
import com.bittech.javaweb.model.PageBean;
import com.bittech.javaweb.service.StudentService;
import com.bittech.javaweb.util.StringUtil;
import com.bittech.javaweb.web.servlet.ApplicationLoadServlet;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "StudentListServlet",urlPatterns = {"/studentList"} )
public class StudentListServlet extends ApplicationLoadServlet {
    
    private StudentService studentService;
    
    private Gson gson;
    
    @Override
    public void init() throws ServletException {
        super.init();
        studentService = getBean(StudentService.class);
        gson = getBean(Gson.class);
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String sex = request.getParameter("sex");
        String bbirthday = request.getParameter("bbirthday");
        String ebirthday = request.getParameter("ebirthday");
        String gradeId = request.getParameter("gradeId");
        Student student = new Student();
        if (stuNo != null) {
            student.setStuNo(stuNo);
            student.setStuName(stuName);
            student.setSex(sex);
            if (StringUtil.isNotEmpty(gradeId)) {
                student.setGradeId(Integer.parseInt(gradeId));
            }
        }
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        try {
            JsonObject result = new JsonObject();
            List<Student> studentList = studentService.queryStudent(pageBean, student, bbirthday, ebirthday);
            int total = studentService.queryCount(student, bbirthday, ebirthday);
            System.out.println(studentList);
            result.add("rows", gson.toJsonTree(studentList));
            result.addProperty("total", total);
            write(response, result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}