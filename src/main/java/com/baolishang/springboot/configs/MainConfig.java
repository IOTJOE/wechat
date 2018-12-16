package com.baolishang.springboot.configs;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by FirenzesEagle on 2016/5/30 0030.
 * Email:liumingbo2008@gmail.com
 * 
 * @author FirenzesEagle
 * @author BinaryWang
 */
@Configuration
public class MainConfig {

    @Value("${appid}")
    private String appid;

    @Value("${appsecret}")
    private String appsecret;

    @Value("${token}")
    private String token;

    @Value("${aeskey}")
    private String aesKey;

    @Value("${partener_id}")
    private String partenerId;

    @Value("${partener_key}")
    private String partenerKey;

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(this.appid);
        configStorage.setSecret(this.appsecret);
        configStorage.setToken(this.token);
        configStorage.setAesKey(this.aesKey);
        configStorage.setPartnerId(this.partenerId);
        configStorage.setPartnerKey(this.partenerKey);
        return configStorage;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public void setPartenerId(String partenerId) {
        this.partenerId = partenerId;
    }

    public void setPartenerKey(String partenerKey) {
        this.partenerKey = partenerKey;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Override
    public String toString() {
        return "MainConfig{" +
                "appid='" + appid + '\'' +
                ", appsecret='" + appsecret + '\'' +
                ", token='" + token + '\'' +
                ", aesKey='" + aesKey + '\'' +
                ", partenerId='" + partenerId + '\'' +
                ", partenerKey='" + partenerKey + '\'' +
                '}';
    }
}
