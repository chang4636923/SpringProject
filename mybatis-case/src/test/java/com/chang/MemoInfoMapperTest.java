package com.chang;

import com.chang.common.BackGround;
import com.chang.common.Page;
import com.chang.entity.MemoInfo;

import com.chang.mapper.MemoInfoMapper;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class MemoInfoMapperTest {
    private Logger logger;
    private static SqlSessionFactory sqlSessionFactory;

    @Before
    public void beforeTest(){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder =
                new SqlSessionFactoryBuilder();
        try{
            sqlSessionFactory = sqlSessionFactoryBuilder.build(
                    Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @AfterClass
    public static void setAfter() {
        sqlSessionFactory = null;
    }

    @Test
    public void test_insertMemoInfo(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            MemoInfoMapper memoInfoMapper = sqlSession.getMapper(MemoInfoMapper.class);
            MemoInfo memoInfo = new MemoInfo();
            memoInfo.setGroupId(1);
            memoInfo.setTitle("Q");
            memoInfo.setContent("QQ");
            memoInfo.setCreatedTime(new Date());
            memoInfoMapper.insertMemoInfo(memoInfo);
            System.out.println(memoInfo);
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void test_queryMemoInfoById(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            MemoInfoMapper memoInfoMapper = sqlSession.getMapper(MemoInfoMapper.class);
            MemoInfo memoInfo = memoInfoMapper.queryMemoInfoById(4);
            System.out.println(memoInfo);
            memoInfo = memoInfoMapper.queryMemoInfoById(4);
            System.out.println(memoInfo);
            memoInfo = memoInfoMapper.queryMemoInfoById(4);
            System.out.println(memoInfo);
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void test_updateMemoInfo(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            MemoInfoMapper memoInfoMapper = sqlSession.getMapper(MemoInfoMapper.class);
            MemoInfo memoInfo = new MemoInfo();
            memoInfo.setId(3);
            memoInfo.setTitle("R");
            memoInfo.setContent("Mybatis");
            int n = memoInfoMapper.updateMemoInfo(memoInfo);
            System.out.println(n);
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void test_queryByPage(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            MemoInfoMapper memoInfoMapper = sqlSession.getMapper(MemoInfoMapper.class);
            List<MemoInfo> list = memoInfoMapper.queryByPage(
                    2,1,"created_time");
            for (MemoInfo memoInfo:list ) {
                System.out.println(memoInfo);
            }
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void test_deleteMemoInfo(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            MemoInfoMapper memoInfoMapper = sqlSession.getMapper(MemoInfoMapper.class);
            int effect = memoInfoMapper.deleteMemoInfo(6);
            System.out.println(effect);
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void test_queryByGroupId(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            MemoInfoMapper memoInfoMapper = sqlSession.getMapper(MemoInfoMapper.class);
            List<MemoInfo> list = memoInfoMapper.queryByGroupId(1);
            for (MemoInfo memoInfo:list) {
                System.out.println(memoInfo);
            }

        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void test_queryByPageWithObject(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            MemoInfoMapper memoInfoMapper = sqlSession.getMapper(MemoInfoMapper.class);
            Page page = new Page();
            page.setPageSize(2);
            page.setPageOffset(1);
            page.setOrderColumnName("created_time");
            List<MemoInfo> list = memoInfoMapper.queryByPageWithObject(page);
            for (MemoInfo memoInfo:list ) {
                System.out.println(memoInfo);
            }
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void test_queryByTitleContentCreatedTime(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            MemoInfoMapper memoInfoMapper = sqlSession.getMapper(MemoInfoMapper.class);
            List<MemoInfo> list = memoInfoMapper.queryByTitleContentCreatedTime("H","Java",null);
            for (MemoInfo memoInfo:list ) {
                System.out.println(memoInfo);
            }
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void test_updateByMemoInfo(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            MemoInfoMapper memoInfoMapper = sqlSession.getMapper(MemoInfoMapper.class);
            MemoInfo memoInfo = new MemoInfo();
            memoInfo.setId(8);
            memoInfo.setTitle("Haha");
            memoInfo.setContent("This is Haha");
            memoInfo.setPrivacy('1');
            memoInfo.setBackGround(BackGround.BLUE);
            memoInfoMapper.updateByMemoInfo(memoInfo);
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void test_queryByPrivacyOrRemind(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            MemoInfoMapper memoInfoMapper = sqlSession.getMapper(MemoInfoMapper.class);
            List<MemoInfo> list = memoInfoMapper.queryByPrivacyOrRemind(null,'0');
            for (MemoInfo memoInfo:list ) {
                System.out.println(memoInfo);
            }
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void test_queryByIds(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            MemoInfoMapper memoInfoMapper = sqlSession.getMapper(MemoInfoMapper.class);
            List<Integer> ids = new ArrayList<>();
            ids.add(1);
            ids.add(3);
            ids.add(5);
            ids.add(7);
            List<MemoInfo> list = memoInfoMapper.queryByIds(ids);
            for (MemoInfo memoInfo:list ) {
                System.out.println(memoInfo);
            }
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void test_queryByPageTwo(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession(true);
            MemoInfoMapper memoInfoMapper = sqlSession.getMapper(MemoInfoMapper.class);

            //PageHelper.startPage(2,3);
            //PageHelper.offsetPage(2,3);
            //List<MemoInfo> list = memoInfoMapper.queryByPageTwo();

            List<MemoInfo> list = memoInfoMapper.queryByPage3(1,2);
            for (MemoInfo memoInfo:list ) {
                System.out.println(memoInfo);
            }
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

}
