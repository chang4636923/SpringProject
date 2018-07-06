package com.chang.myCache;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReadWriteLock;

public class MyCache implements Cache {

    private final Logger logger = LoggerFactory.getLogger(MyCache.class);
    private final ConcurrentMap<Object, Object> cache = new ConcurrentHashMap<>(512);
    private final String id;
    private Integer maxSize;//最大数量
    public MyCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("MyCache Cache instances require an ID.");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if(cache.size()>=this.maxSize){
            File file = new File("D://cache//cache.txt");
            OutputStream outputStream;
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            
            Properties properties = new Properties();


            cache.clear();

        }
        cache.put(key,value);
    }

    @Override
    public Object getObject(Object key) {
        //...
        return cache.get(key);
    }

    @Override
    public Object removeObject(Object key) {
        return cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int getSize() {
        return cache.size();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
