package com.chang.dao.impl;

import com.chang.dao.MemoGroupDao;
import com.chang.entity.MemoGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class MemoGroupDaoImpl implements MemoGroupDao {

    private final Logger logger = LoggerFactory.getLogger(MemoGroupDaoImpl.class);

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public MemoGroupDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addMemoGroup(String name) {
        int flag = queryMemoGroupByNameCount(name);
        int effect = 0;
        if(flag==0){
            effect = this.jdbcTemplate.update(
                    "insert into memo_group( name, created_time) value (?,?)",
                    name,
                    new Date()
            );
        }
        logger.debug("addMemoGroup name={} effect={}",name,effect);
        return effect;
    }

    public int queryMemoGroupByNameCount(String name) {
        String sql = "select count(id) from memo_group where name=?";
        int effect = this.jdbcTemplate.queryForObject(sql,new Object[]{name},Integer.class);
        return effect;
    }

    public int modifyMemoGroupById(String name, int id) {
        List<MemoGroup> memoGroups = queryById(id);
        if(memoGroups.isEmpty()){
            throw new IllegalArgumentException("the id "+id+" not found a MemoGroup");
        }
        if(memoGroups.size()>1){
            throw new IllegalArgumentException("the id "+id+" more than one");
        }
        int effect = this.jdbcTemplate.update(
                "update memo_group set name=? where id=?",
                name,id
        );
        return effect;
    }

    public List<MemoGroup> queryById(int id) {
        String sql = "select id,name,created_time,modify_time from memo_group where id=?";
        List<MemoGroup> memoGroupList = this.jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<MemoGroup>() {
            public MemoGroup mapRow(ResultSet resultSet, int i) throws SQLException {
                MemoGroup memoGroup = new MemoGroup();
                memoGroup.setId(resultSet.getInt("id"));
                memoGroup.setName(resultSet.getString("name"));
                memoGroup.setCreatedTime(resultSet.getTime("created_time"));
                memoGroup.setModifyTime(resultSet.getTime("modify_time"));
                return memoGroup;
            }
        });
        logger.debug("query id={} result={}",id,memoGroupList);
        return memoGroupList;
    }

    public int deleteById(int id) {
       int effect =  this.jdbcTemplate.update("delete from memo_group where id=?", id);
       return effect;
    }

    public List<MemoGroup> queryAll() {
        String sql = "select id,name,created_time,modify_time from memo_group";
        List<MemoGroup> memoGroups = this.jdbcTemplate.query(
                sql, new RowMapper<MemoGroup>() {
                    public MemoGroup mapRow(ResultSet resultSet, int i) throws SQLException {
                        MemoGroup memoGroup = new MemoGroup();
                        memoGroup.setId(resultSet.getInt("id"));
                        memoGroup.setName(resultSet.getString("name"));
                        memoGroup.setCreatedTime(new Date(resultSet.getTimestamp("created_time").getTime()));
                        memoGroup.setModifyTime(new Date(resultSet.getTimestamp("modify_time").getTime()));
                        return memoGroup;
                    }
                }
        );
        logger.debug("queryAll result={}",memoGroups);
        return memoGroups;
    }
}
