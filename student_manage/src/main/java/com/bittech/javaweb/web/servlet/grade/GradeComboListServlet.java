package com.bittech.javaweb.web.servlet.grade;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bittech.javaweb.entiy.Grade;
import com.bittech.javaweb.service.GradeService;
import com.bittech.javaweb.web.servlet.ApplicationLoadServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//@WebServlet(name = "GradeComboListServlet",urlPatterns = {"/gradeComboList"})
public class GradeComboListServlet extends ApplicationLoadServlet {
    
    private GradeService gradeService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        this.gradeService = getBean(GradeService.class);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            JSONArray jsonArray = new JSONArray();
            
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", "");
            jsonObject.put("gradeName", "----");
            jsonArray.add(jsonObject);
            
            List<Grade> gradeList = gradeService.gradeList(null, null);
            for (Grade grade : gradeList) {
                JSONObject object = new JSONObject();
                object.put("id", grade.getId());
                object.put("gradeName", grade.getGradeName());
                jsonArray.add(object);
            }
            write(response, jsonArray.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
