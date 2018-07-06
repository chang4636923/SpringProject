package com.chang;

import com.chang.entity.MemoGroup;
import com.chang.mapper.MemoGroupMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MemoGroupMapperTest {
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
    public void testQueryAll(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession();
            MemoGroupMapper memoGroupMapper = sqlSession.getMapper(MemoGroupMapper.class);
            List<MemoGroup> list = memoGroupMapper.queryAll();
            Iterator<MemoGroup> iterator = list.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void test_query_memoGroup(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession();
            MemoGroupMapper memoGroupMapper = sqlSession.getMapper(MemoGroupMapper.class);
            MemoGroup memoGroup = memoGroupMapper.query_memoGroup(1);


        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void testInsetMemoGroup(){
        SqlSession sqlSession = null;
        try{
            MemoGroup memoGroup = new MemoGroup();
            memoGroup.setName("DD");
            memoGroup.setCratedDate(new Date());
            sqlSession = sqlSessionFactory.openSession();
            MemoGroupMapper memoGroupMapper = sqlSession.getMapper(MemoGroupMapper.class);
            int effect = memoGroupMapper.insetMemoGroup(memoGroup);
            Assert.assertEquals(1,effect);
            sqlSession.commit();
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void testUpdateMemoGroup(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession();
            MemoGroupMapper memoGroupMapper = sqlSession.getMapper(MemoGroupMapper.class);
            int effect = memoGroupMapper.updateMemoGroup(5,"CC");
            Assert.assertEquals(1,effect);
            sqlSession.commit();
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void testDeleteMemoGroup(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession();
            MemoGroupMapper memoGroupMapper = sqlSession.getMapper(MemoGroupMapper.class);
            int effect = memoGroupMapper.deleteMemoGroup(7);
            Assert.assertEquals(1,effect);
            sqlSession.commit();
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

}
