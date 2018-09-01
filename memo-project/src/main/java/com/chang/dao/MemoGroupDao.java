package com.chang.dao;

import com.chang.entity.MemoGroup;

import java.util.List;

public interface MemoGroupDao {

    /**
     * 添加一个便签组
     * @param name
     * @return
     */
    int addMemoGroup(String name);

    /**
     * 查询指定名称的便签组
     * @param name
     * @return
     */
    int queryMemoGroupByNameCount(String name);

    /**
     * 通过id修改便签组名
     * @param name
     * @param id
     * @return
     */
    int modifyMemoGroupById(String name, int id);

    /**
     * 通过id查询便签组
     * @param id
     * @return
     */
    List<MemoGroup> queryById(int id);

    /**
     * 删除便签组
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 查询所有的便签组
     * @return
     */
    List<MemoGroup> queryAll();

}
