package com.bittech.javaweb.service;

import com.bittech.javaweb.entiy.Grade;
import com.bittech.javaweb.model.PageBean;

import java.util.List;

/**
 * Author: secondriver
 * Created: 2018/7/29
 */
public interface GradeService {
    
    List<Grade> gradeList(PageBean pageBean, Grade grade);
    
    int gradeCount(Grade grade);
    
    int saveGrade(Grade grade);
    
    int modifyGrade(Grade grade);
    
    int removeGrade(String delIds);
}
