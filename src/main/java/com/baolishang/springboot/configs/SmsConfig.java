package com.baolishang.springboot.configs;

import com.baolishang.springboot.storage.SmsConfigStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Name
 * @Author Joe mailTo:jiangzhongke@baojinsuo.com
 * @Date 2016/10/31 下午4:12
 * @Desc
 */
@Configuration
public class SmsConfig {
    @Value("${cl_tz_uri_old}")
    private String tzUriOld;
    @Value("${cl_tz_account_old}")
    private String tzAccountOld;
    @Value("${cl_tz_password_old}")
    private String tzPasswordOld;
    @Value("${cl_tz_access_key_old}")
    private String tzAccessKeyOld;

    @Value("${cl_tz_uri_new}")
    private String tzUriNew;
    @Value("${cl_tz_account_new}")
    private String tzAccountNew;
    @Value("${cl_tz_password_new}")
    private String tzPasswordNew;
    @Value("${cl_tz_access_key_new}")
    private String tzAccessKeyNew;

    @Value("${cl_yx_uri_new}")
    private String yxUriNew;
    @Value("${cl_yx_account_new}")
    private String yxAccountNew;
    @Value("${cl_yx_password_new}")
    private String yxPasswordNew;
    @Value("${cl_yx_access_key_new}")
    private String yxAccessKeyNew;



    @Bean
    public SmsConfigStorage clTzOldConfigStorage(){
        SmsConfigStorage configStorage = new SmsConfigStorage(this.tzUriOld,this.tzAccountOld,this.tzPasswordOld,this.tzAccessKeyOld);
        return configStorage;
    }
    @Bean
    public SmsConfigStorage clTzNewConfigStorage(){
        SmsConfigStorage configStorage = new SmsConfigStorage(this.tzUriNew,this.tzAccountNew,this.tzPasswordNew,this.tzAccessKeyNew);
        return configStorage;
    }
    @Bean
    public SmsConfigStorage clYxNewConfigStorage(){
        SmsConfigStorage configStorage = new SmsConfigStorage(this.yxUriNew,this.yxAccountNew,this.yxPasswordNew,this.yxAccessKeyNew);
        return configStorage;
    }

    public String getTzUriOld() {
        return tzUriOld;
    }

    public void setTzUriOld(String tzUriOld) {
        this.tzUriOld = tzUriOld;
    }

    public String getTzAccountOld() {
        return tzAccountOld;
    }

    public void setTzAccountOld(String tzAccountOld) {
        this.tzAccountOld = tzAccountOld;
    }

    public String getTzPasswordOld() {
        return tzPasswordOld;
    }

    public void setTzPasswordOld(String tzPasswordOld) {
        this.tzPasswordOld = tzPasswordOld;
    }

    public String getTzUriNew() {
        return tzUriNew;
    }

    public void setTzUriNew(String tzUriNew) {
        this.tzUriNew = tzUriNew;
    }

    public String getTzAccountNew() {
        return tzAccountNew;
    }

    public void setTzAccountNew(String tzAccountNew) {
        this.tzAccountNew = tzAccountNew;
    }

    public String getTzPasswordNew() {
        return tzPasswordNew;
    }

    public void setTzPasswordNew(String tzPasswordNew) {
        this.tzPasswordNew = tzPasswordNew;
    }

    public String getYxUriNew() {
        return yxUriNew;
    }

    public void setYxUriNew(String yxUriNew) {
        this.yxUriNew = yxUriNew;
    }

    public String getYxAccountNew() {
        return yxAccountNew;
    }

    public void setYxAccountNew(String yxAccountNew) {
        this.yxAccountNew = yxAccountNew;
    }

    public String getYxPasswordNew() {
        return yxPasswordNew;
    }

    public void setYxPasswordNew(String yxPasswordNew) {
        this.yxPasswordNew = yxPasswordNew;
    }

    @Override
    public String toString() {
        return "SmsConfig{" +
                "tzUriOld='" + this.tzUriOld + '\'' +
                ", tzAccountOld='" + this.tzAccountOld + '\'' +
                ", tzPasswordOld='" + this.tzPasswordOld + '\'' +
                ", tzAccessKeyOld='" + tzAccessKeyOld + '\'' +
                ", tzUriNew='" + this.tzUriNew + '\'' +
                ", tzAccountNew='" + this.tzAccountNew + '\'' +
                ", tzPasswordNew='" + this.tzPasswordNew + '\'' +
                ", tzAccessKeyNew='" + tzAccessKeyNew + '\'' +
                ", yxUriNew='" + this.yxUriNew + '\'' +
                ", yxAccountNew='" + this.yxAccountNew + '\'' +
                ", yxPasswordNew='" + this.yxPasswordNew + '\'' +
                ", yxAccessKeyNew='" + this.yxAccessKeyNew + '\'' +
                '}';
    }
}
