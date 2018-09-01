package com.chang.service.impl;

import com.chang.dao.MemoGroupDao;
import com.chang.dao.MemoInfoDao;
import com.chang.entity.MemoGroup;
import com.chang.service.MemoGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@Service
@Transactional
public class MemoGroupServiceImpl implements MemoGroupService {

    @Autowired
    private final MemoGroupDao memoGroupDao;
    @Autowired
    private final MemoInfoDao memoInfoDao;
    private final static int DEFAULT_MEMO_GROUP_ID = 1;

    public MemoGroupServiceImpl(MemoGroupDao memoGroupDao, MemoInfoDao memoInfoDao) {
        this.memoGroupDao = memoGroupDao;
        this.memoInfoDao = memoInfoDao;
    }

    private Scanner scanner = new Scanner(System.in);

    public int addMemoGroup() {
        //this.memoGroupDao.addMemoGroup("456");    //测试回滚是否成功
        System.out.println("Input name:");
        String name = scanner.next();

        int effect = this.memoGroupDao.addMemoGroup(name);
        if(effect==0){
            throw new IllegalArgumentException("The name " + name + " is not effective");
        }
        return effect;
    }

    public MemoGroup queryMemoGroupById() {
        System.out.println("Input id:");
        int id = scanner.nextInt();
        List<MemoGroup> memoGroups = this.memoGroupDao.queryById(id);
        if(memoGroups.isEmpty()){
            throw new IllegalArgumentException("id " + id + " not found a MemoGroup");
        }
        if(memoGroups.size()>1){
            throw new IllegalArgumentException("The id " + id + " more than one");
        }
        System.out.println(memoGroups.get(0));
        return memoGroups.get(0);
    }

    public int updateMemoGroupName() {
        System.out.println("Input name:");
        String name = scanner.next();
        System.out.println("Input id:");
        int id = scanner.nextInt();

        int effect = this.memoGroupDao.modifyMemoGroupById(name,id);
        if(effect==0){
            throw new IllegalArgumentException("id "+id + "not found a MemoGroup");
        }
        return effect;
    }

    public int deleteMemoGroupById() {
        System.out.println("Input id:");
        int id = scanner.nextInt();
        int effect = this.memoGroupDao.deleteById(id);
        this.memoInfoDao.removeMemoInfo(id,DEFAULT_MEMO_GROUP_ID);
        if(effect==0){
            throw new IllegalArgumentException("id "+id + "not found a MemoGroup");
        }
        return effect;
    }

    @Transactional(transactionManager = "transactionManager",readOnly = true)
    public List<MemoGroup> queryAll() {
        List<MemoGroup> memoGroupList =this.memoGroupDao.queryAll();
        Iterator<MemoGroup> iterator = memoGroupList.iterator();
        while (iterator.hasNext()){
            MemoGroup memoGroup = iterator.next();
            System.out.println(memoGroup);
        }
        return memoGroupList;
    }
}
