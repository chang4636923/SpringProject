package com.bittech.javaweb.dao;

import com.bittech.javaweb.entiy.Student;
import com.bittech.javaweb.model.PageBean;

import java.util.List;

public interface StudentDao {
    
    List<Student> studentList(PageBean pageBean, Student student, String bbirthday, String ebirthday);
    
    int studentCount(Student student, String bbirthday, String ebirthday);
    
    int studentDelete(String delIds);
    
    int studentAdd(Student student);
    
    int studentModify(Student student);
    
    boolean getStudentByGradeId(String gradeId);
}
