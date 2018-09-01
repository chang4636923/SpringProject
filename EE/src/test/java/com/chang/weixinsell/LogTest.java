package com.chang.weixinsell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogTest {
    @Test
    public void test() {
        String name = "chang";
        String password = "123456";
        log.info("name:{},password:{}", name, password);
        log.info("info...");
        log.debug("debug...");
        log.error("error...");
    }
}
