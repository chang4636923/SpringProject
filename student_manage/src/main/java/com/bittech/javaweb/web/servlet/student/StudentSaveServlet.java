package com.bittech.javaweb.web.servlet.student;

import com.alibaba.fastjson.JSONObject;
import com.bittech.javaweb.entiy.Student;
import com.bittech.javaweb.service.StudentService;
import com.bittech.javaweb.util.DateUtil;
import com.bittech.javaweb.util.StringUtil;
import com.bittech.javaweb.web.servlet.ApplicationLoadServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "StudentSaveServlet",urlPatterns = {"/studentSave"} )
public class StudentSaveServlet extends ApplicationLoadServlet {
    
    private StudentService studentService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        studentService = getBean(StudentService.class);
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String gradeId = request.getParameter("gradeId");
        String email = request.getParameter("email");
        String stuDesc = request.getParameter("stuDesc");
        String stuId = request.getParameter("stuId");
        Student student = new Student();
        try {
            student.setStuNo(stuNo);
            student.setStuName(stuName);
            student.setSex(sex);
            student.setBirthday(DateUtil.formatString(birthday, "yyyy-MM-dd"));
            student.setGradeId(Integer.valueOf(gradeId));
            student.setEmail(email);
            student.setStuDesc(stuDesc);
            System.out.println(student);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (StringUtil.isNotEmpty(stuId)) {
            student.setStuId(Integer.parseInt(stuId));
        }
        try {
            int saveNums;
            JSONObject result = new JSONObject();
            if (StringUtil.isNotEmpty(stuId)) {
                saveNums = studentService.modifyStudent(student);
                System.out.println(student);
            } else {
                saveNums = studentService.saveStudent(student);
                System.out.println(student);
            }
            if (saveNums > 0) {
                result.put("success", "true");
            } else {
                result.put("success", "true");
                result.put("errorMsg", "更新失败");
            }
            write(response, result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
