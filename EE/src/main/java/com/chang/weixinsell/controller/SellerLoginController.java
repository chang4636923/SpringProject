package com.chang.weixinsell.controller;

import com.chang.weixinsell.entity.SellerInfo;
import com.chang.weixinsell.service.ISellerInfoService;
import com.chang.weixinsell.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerLoginController {
    public static final int EXPIRE_TIME = 7200;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ISellerInfoService sellerInfoService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        HttpServletResponse response) {
        // 用户信息校验
        SellerInfo sellerInfo = sellerInfoService.findByUserName(username);
        if (sellerInfo == null) {
            log.error("用户不存在，请重新输入");
            return "404";
        }
        if (!sellerInfo.getPassword().equals(password)) {
            log.error("密码不正确，请重新输入");
            return "404";
        }
//        // token写入Redis
        String token = UUID.randomUUID().toString();
//        stringRedisTemplate.opsForValue().set(username,token,EXPIRE_TIME, TimeUnit.SECONDS);
        // token写入cookie
        CookieUtil.set(username, token, response, EXPIRE_TIME);
        return "200";
    }
}
