package com.chang.weixinsell.repository;

import com.chang.weixinsell.entity.SellerInfo;
import com.chang.weixinsell.util.GenerateKeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoReposityTest {
    @Autowired
    private SellerInfoReposity sellerInfoReposity;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(GenerateKeyUtil.getUniqueKey());
        sellerInfo.setUsername("yuisama");
        sellerInfo.setPassword("123456");
        sellerInfoReposity.save(sellerInfo);
    }


}