package com.chang.dao.impl;

import com.chang.dao.MemoInfoDao;
import com.chang.entity.BackGround;
import com.chang.entity.MemoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MemoInfoDaoImpl implements MemoInfoDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public MemoInfoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int removeMemoInfo(int oldId, int newId) {
        String sql = "update memo_info set group_id=? where group_id=?";
        int effect = this.jdbcTemplate.update(sql, newId, oldId);
        return effect;
    }

    public List<MemoInfo> queryMemoInfoByGroupId(int gId) {
        String sql = "select id,group_id,title, content,is_protected, background, is_remind,remind_time,created_time,modify_time \n" +
                "from memo_info where group_id=?";
        List<MemoInfo> memoInfoList = this.jdbcTemplate.query(sql, new Object[]{gId}, new RowMapper<MemoInfo>() {
            public MemoInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                MemoInfo memoInfo = new MemoInfo();
                memoInfo.setId(rs.getInt(1));
                memoInfo.setGId(rs.getInt(2));
                memoInfo.setTitle(rs.getString(3));
                memoInfo.setContent(rs.getString(4));
                if (rs.getString(5) != null) {
                    memoInfo.setIsProtected(rs.getString(5).charAt(0));
                }
                memoInfo.setBackGround(Enum.valueOf(BackGround.class, rs.getString(6)));
                if (rs.getString(7) != null) {
                    memoInfo.setIsRemind(rs.getString(7).charAt(0));
                }
                if (rs.getTimestamp(8) != null) {
                    memoInfo.setRemindTime(new Date(rs.getTimestamp(8).getTime()));
                }
                memoInfo.setCreatedTime(new Date(rs.getTimestamp(9).getTime()));
                memoInfo.setModifyTime(new Date(rs.getTimestamp(10).getTime()));
                return memoInfo;
            }
        });
        return memoInfoList;
    }
}