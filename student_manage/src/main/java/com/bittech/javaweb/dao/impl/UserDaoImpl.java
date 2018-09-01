package com.bittech.javaweb.dao.impl;

import com.bittech.javaweb.dao.UserDao;
import com.bittech.javaweb.entiy.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public User login(User user) {
        String sql = "select id, name,password from t_user where name=? and password=?";
        List<User> userList = jdbcTemplate.query(sql,
                new Object[]{
                        user.getUserName(),
                        user.getPassword()
                },
                new UserRowMapper());
        if (userList.isEmpty() || userList.size() > 1) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @Override
    public int insertUser(User user) {
        String query="select count(id) from t_user where name=?";
        int effect = jdbcTemplate.queryForObject(
                query,new Object[]{user.getUserName()},Integer.class);
        if(effect>0){
            return -1;
        }
        String insert="insert into t_user(name,password) values(?,?)";
        effect = jdbcTemplate.update(insert,user.getUserName(),user.getPassword());
        return effect;
    }

    public static class UserRowMapper implements RowMapper<User> {
        
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
}
