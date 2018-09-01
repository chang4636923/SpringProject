package com.chang.weixinsell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "project-url")
public class ProjectUrlConfig {
    /**
     * 微信公众号授权回调路径
     */
    private String wechatMpAuthorize;
}
