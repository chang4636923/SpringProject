package com.chang.mapper;

import com.chang.entity.MemoGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author:
 * Created: 2018/6/24
 */
public interface MemoGroupMapper {

    /**
     * 查询所有便签组
     *
     * @return
     */
    List<MemoGroup> queryAll();

    MemoGroup query_memoGroup(int id);
    /**
     * 插入一个便签组
     *
     * @param memoGroup
     * @return
     */
    int insetMemoGroup(MemoGroup memoGroup);

    /**
     * 修改便签组的名称
     *
     * @param id
     * @param name
     * @return
     */
    int updateMemoGroup(@Param("id") int id, @Param("name") String name);

    /**
     * 根据Id删除便签组
     *
     * @param id
     * @return
     */
    int deleteMemoGroup(int id);
}
