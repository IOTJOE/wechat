package com.autotrans.springboot.services;


import com.autotrans.springboot.configs.WechatRspResult;

/**
 * @Author XXL mailTo:xiaoxuelei@baojinsuo.com
 * @Date 2016/12/22 17:39
 * @Desc
 */
public interface WxService {
    WechatRspResult getOAuth2UserInfo(String code);
}
