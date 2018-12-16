package com.baolishang.springboot.models;

import com.baolishang.springboot.bases.BaseModel;

import javax.persistence.*;

/**
 * Created by joe on 2017/8/29.
 */
@Entity
public class ReceiveUser extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",unique = true, nullable = false)
    private Long id;

    @Column(name="Name")
    private String name;

    @Column(name="Phone")
    private String phone;

    @Column(name="OpenId")
    private String openId;

    @Column(name="WeChatTemplateId")
    private String weChatTemplateId;

    @Column(name="ShortMessageId")
    private Long shortMessageId;

    @Column(name="WechatTopColor")
    private String wechatTopColor;

    @Column(name="WeChatId")
    private String weChatId;
    @Column(name="Url")
    private String url;
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWeChatTemplateId() {
        return weChatTemplateId;
    }

    public void setWeChatTemplateId(String weChatTemplateId) {
        this.weChatTemplateId = weChatTemplateId;
    }

    public Long getShortMessageId() {
        return shortMessageId;
    }

    public void setShortMessageId(Long shortMessageId) {
        this.shortMessageId = shortMessageId;
    }

    public String getWechatTopColor() {
        return wechatTopColor;
    }

    public void setWechatTopColor(String wechatTopColor) {
        this.wechatTopColor = wechatTopColor;
    }

    public String getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(String weChatId) {
        this.weChatId = weChatId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ReceiveUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", openId='" + openId + '\'' +
                ", weChatTemplateId='" + weChatTemplateId + '\'' +
                ", shortMessageId=" + shortMessageId +
                ", wechatTopColor='" + wechatTopColor + '\'' +
                ", weChatId='" + weChatId + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
