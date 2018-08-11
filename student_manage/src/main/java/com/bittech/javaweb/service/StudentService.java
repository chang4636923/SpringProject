package com.bittech.javaweb.service;

import com.bittech.javaweb.entiy.Student;
import com.bittech.javaweb.model.PageBean;

import java.util.List;

/**
 * Author: secondriver
 * Created: 2018/7/29
 */
public interface StudentService {
    
    int saveStudent(Student student);
    
    int modifyStudent(Student student);
    
    int removeStudent(String delIds);
    
    List<Student> queryStudent(PageBean pageBean, Student student, String bbirthday, String ebirthday);
    
    int queryCount(Student student, String bbirthday, String ebirthday);
    
    boolean queryStudentByGradeId(String s);
}
