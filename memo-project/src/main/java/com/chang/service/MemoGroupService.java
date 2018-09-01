package com.chang.service;

import com.chang.entity.MemoGroup;

import java.util.List;

/**
 * Service
 */

public interface MemoGroupService {
    /**
     * 通过名字添加分组
     * @return
     */
    int addMemoGroup();

    /**
     * 查询一个分组
     * @return
     */
    MemoGroup queryMemoGroupById();

    /**
     * 通过id修改名字

     * @return
     */
    int updateMemoGroupName();

    /**
     * 通过id删除分组
     * @return
     */
    int deleteMemoGroupById();

    List<MemoGroup> queryAll();

}
