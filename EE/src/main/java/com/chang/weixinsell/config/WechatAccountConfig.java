package com.chang.weixinsell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    /**
     * 微信公众号appId
     */
    private String mpAppId;
    /**
     * 微信公众号appSecret
     */
    private String mpAppSecret;
}
