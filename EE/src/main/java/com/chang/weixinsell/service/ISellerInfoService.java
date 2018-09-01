package com.chang.weixinsell.service;

import com.chang.weixinsell.entity.SellerInfo;

/**
 * 卖家登录Service
 */
public interface ISellerInfoService {
    SellerInfo findByUserName(String username);
}
