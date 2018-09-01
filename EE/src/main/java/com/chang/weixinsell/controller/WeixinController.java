package com.chang.weixinsell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth...");
        log.info("code={}", code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxcbac1a7db0ec3899" +
                "&secret=b89562aced06608763c09615d0264a8d" +
                "&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
    }
}
