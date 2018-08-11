package com.chang.service;

import com.chang.entity.MemoInfo;

import java.util.List;

public interface MemoInfoService {

    List<MemoInfo> queryMemoInfoByGroupId(int gId);

}
