package com.chang.service.impl;

import com.chang.dao.MemoInfoDao;
import com.chang.entity.MemoInfo;
import com.chang.service.MemoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemoInfoServiceImpl implements MemoInfoService {

    @Autowired
    private final MemoInfoDao memoInfoDao;

    public MemoInfoServiceImpl(MemoInfoDao memoInfoDao) {
        this.memoInfoDao = memoInfoDao;
    }

    public List<MemoInfo> queryMemoInfoByGroupId(int gId) {
        List<MemoInfo> memoInfoList = memoInfoDao.queryMemoInfoByGroupId(gId);
        return memoInfoList;
    }
}
