package com.chang.mapper;

import com.chang.common.Page;
import com.chang.entity.MemoInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MemoInfoMapper {

    //插入便签信息
    int insertMemoInfo(MemoInfo memoInfo);

    //根据ID查询
    MemoInfo queryMemoInfoById(int id);

    //更新
    int updateMemoInfo(MemoInfo memoInfo);

    //分页查询
    //select * from memo_info limit pageSize offset (pageNumber-1)*pageSize order by columnName
    List<MemoInfo> queryByPage(
            @Param("pageSize") Integer pageSize,
            @Param("pageOffset") Integer pageOffset,
            @Param("columnName") String columnName
    );

    List<MemoInfo> queryByPageWithObject(Page page);


    //根据ID删除便签信息
    int deleteMemoInfo(@Param("memoInfoId") int id);

    //通过组查询
    List<MemoInfo> queryByGroupId(int groupId);

    List<MemoInfo> queryByTitleContentCreatedTime(
            @Param("title") String title,
            @Param("content") String content,
            @Param("createdTime") Date createdTime);

    int updateByMemoInfo(MemoInfo memoInfo);


    List<MemoInfo> queryByPrivacyOrRemind(
            @Param("privacy") Character privacy,
            @Param("remind") Character remind
    );

    List<MemoInfo> queryByIds(List<Integer> ids);

    List<MemoInfo> queryByPageTwo();

    List<MemoInfo> queryByPage3(
            @Param("pageNum") Integer pageNum,
            @Param("pageSize") Integer pageSize
    );

}
