package com.bittech.javaweb.web.servlet.grade;

import com.alibaba.fastjson.JSONObject;
import com.bittech.javaweb.entiy.Grade;
import com.bittech.javaweb.service.GradeService;
import com.bittech.javaweb.util.StringUtil;
import com.bittech.javaweb.web.servlet.ApplicationLoadServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "GradeSaveServlet", urlPatterns = "{/gradeSave}")
public class GradeSaveServlet extends ApplicationLoadServlet {

    private GradeService gradeService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.gradeService = getBean(GradeService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
//        String gradeName = new String(request.getParameter("gradeName"));
//        String gradeDesc = new String(request.getParameter("gradeDesc"));
        String gradeName = new String(request.getParameter("gradeName").getBytes(),"UTF-8");
        String gradeDesc = new String(request.getParameter("gradeDesc").getBytes(),"UTF-8");

        String id = request.getParameter("id");
        Grade grade = new Grade();
        grade.setGradeName(gradeName);
        grade.setGradeDesc(gradeDesc);
        System.out.println(grade);
        if (StringUtil.isNotEmpty(id)) {
            grade.setId(Integer.parseInt(id));
        }
        try {
            int saveNums;
            JSONObject result = new JSONObject();
            if (StringUtil.isNotEmpty(id)) {
                saveNums = gradeService.modifyGrade(grade);
            } else {
                saveNums = gradeService.saveGrade(grade);
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
