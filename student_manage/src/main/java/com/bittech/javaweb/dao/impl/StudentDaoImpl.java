package com.bittech.javaweb.dao.impl;

import com.bittech.javaweb.dao.StudentDao;
import com.bittech.javaweb.entiy.Student;
import com.bittech.javaweb.model.PageBean;
import com.bittech.javaweb.util.DateUtil;
import com.bittech.javaweb.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<Student> studentList(PageBean pageBean, Student student, String bbirthday, String ebirthday) {
        StringBuffer sb = new StringBuffer("select * from t_student s,t_grade g where s.grade_id=g.id");
        if (StringUtil.isNotEmpty(student.getStuNo())) {
            sb.append(" and s.no like '%" + student.getStuNo() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getStuName())) {
            sb.append(" and s.name like '%" + student.getStuName() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getSex())) {
            sb.append(" and s.sex ='" + student.getSex() + "'");
        }
        if (student.getGradeId() != -1) {
            sb.append(" and s.grade_id ='" + student.getGradeId() + "'");
        }
        if (StringUtil.isNotEmpty(bbirthday)) {
            sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('" + bbirthday + "')");
        }
        if (StringUtil.isNotEmpty(ebirthday)) {
            sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('" + ebirthday + "')");
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        
        String sql = sb.toString();
        return jdbcTemplate.query(sql, new StudentRowMapper());
        
    }
    
    public int studentCount(Student student, String bbirthday, String ebirthday) {
        StringBuffer sb = new StringBuffer(
                "select count(*) as total from t_student s,t_grade g where s.grade_id=g.id");
        if (StringUtil.isNotEmpty(student.getStuNo())) {
            sb.append(" and s.no like '%" + student.getStuNo() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getStuName())) {
            sb.append(" and s.name like '%" + student.getStuName() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getSex())) {
            sb.append(" and s.sex ='" + student.getSex() + "'");
        }
        if (student.getGradeId() != -1) {
            sb.append(" and s.grade_id ='" + student.getGradeId() + "'");
        }
        if (StringUtil.isNotEmpty(bbirthday)) {
            sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('" + bbirthday + "')");
        }
        if (StringUtil.isNotEmpty(ebirthday)) {
            sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('" + ebirthday + "')");
        }
        String sql = sb.toString();
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    public int studentDelete(String delIds) {
        String sql = "delete from t_student where stuId in(" + delIds + ")";
        return jdbcTemplate.update(sql);
    }
    
    public int studentAdd(Student student) {
        String sql = "insert into t_student (no,name,sex, birthday,grade_id,email,`desc`) values(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                student.getStuNo(),
                student.getStuName(),
                student.getSex(),
                DateUtil.formatDate(student.getBirthday(), "yyyy-MM-dd"),
                student.getGradeId(),
                student.getEmail(),
                student.getStuDesc()
        );
    }
    
    public int studentModify(Student student) {
        String sql = "update t_student set no=?, name=?, sex=?, birthday=?, grade_id=?,email=?,`desc`=? where id=?";
        return jdbcTemplate.update(sql,
                student.getStuNo(),
                student.getStuName(),
                student.getSex(),
                DateUtil.formatDate(student.getBirthday(), "yyyy-MM-dd"),
                student.getGradeId(),
                student.getEmail(),
                student.getStuDesc(),
                student.getGradeId());
    }
    
    public boolean getStudentByGradeId(String gradeId) {
        String sql = "select * from t_student where grade_id=?";
        List<Student> students = jdbcTemplate.query(sql, new Object[]{gradeId}, new StudentRowMapper());
        return !students.isEmpty();
    }
    
    public static class StudentRowMapper implements RowMapper<Student> {
        
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setStuId(rs.getInt("id"));
            student.setStuNo(rs.getString("no"));
            student.setStuName(rs.getString("name"));
            student.setSex(rs.getString("sex"));
            student.setBirthday(rs.getDate("birthday"));
            student.setGradeId(rs.getInt("grade_id"));
            student.setEmail(rs.getString("email"));
            student.setStuDesc(rs.getString("desc"));
            return student;
        }
    }
}
