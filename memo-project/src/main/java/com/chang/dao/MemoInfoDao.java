package com.chang.dao;

import com.chang.entity.MemoInfo;

import java.util.List;

public interface MemoInfoDao {
    int removeMemoInfo(int oldId, int newId);
    List<MemoInfo> queryMemoInfoByGroupId(int gId);
}
