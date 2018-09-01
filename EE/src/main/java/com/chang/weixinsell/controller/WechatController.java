package com.chang.weixinsell.controller;

import com.chang.weixinsell.config.ProjectUrlConfig;
import com.chang.weixinsell.enums.ExceptionEnum;
import com.chang.weixinsell.exception.WechatException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        // 1.配置
        // 2.调用方法
        String url = projectUrlConfig.getWechatMpAuthorize() + "/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(
                url, WxConsts.OAuth2Scope.SNSAPI_BASE, returnUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信授权】{}", e);
            throw new WechatException(ExceptionEnum.WECHAT_MP_ERROR);
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.info("返回信息为:" + "redirect:" + returnUrl + "?openid=" + openId);
        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
