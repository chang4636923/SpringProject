package com.bittech.javaweb.dao;

import com.bittech.javaweb.entiy.Grade;
import com.bittech.javaweb.model.PageBean;

import java.util.List;

public interface GradeDao {
    
    List<Grade> gradeList(PageBean pageBean, Grade grade) throws Exception;
    
    int gradeCount(Grade grade) throws Exception;
    /**
     * delete from tableName where field in (1,3,5)
     *
     * @param delIds
     * @return
     * @throws Exception
     */
    int gradeDelete(String delIds) throws Exception;
    
    int gradeAdd(Grade grade) throws Exception;
    
    int gradeModify(Grade grade) throws Exception;

    Grade queryById(int id) throws Exception;
    
}
