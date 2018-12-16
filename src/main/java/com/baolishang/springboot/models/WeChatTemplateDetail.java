package com.baolishang.springboot.models;

import com.baolishang.springboot.bases.BaseModel;

import javax.persistence.*;

/**
 * Created by joe on 2017/8/29.
 */
@Entity
//@Table(name="t_wechat_template_detail")
public class WeChatTemplateDetail extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",unique = true, nullable = false)
    private Long id;

    @Column(name="Name")
    private String name;

    @Column(name="WeChatTemplateID")
    private Long WeChatTemplateID;

    @Column(name="WeChatTemplateKey")
    private String weChatTemplateKey;

    @Column(name="LocalReceiveKey")
    private String localReceiveKey;

    @Column(name="WeChatMessageColor")
    private String weChatMessageColor;

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

    public Long getWeChatTemplateID() {
        return WeChatTemplateID;
    }

    public void setWeChatTemplateID(Long weChatTemplateID) {
        WeChatTemplateID = weChatTemplateID;
    }

    public String getWeChatTemplateKey() {
        return weChatTemplateKey;
    }

    public void setWeChatTemplateKey(String weChatTemplateKey) {
        this.weChatTemplateKey = weChatTemplateKey;
    }

    public String getLocalReceiveKey() {
        return localReceiveKey;
    }

    public void setLocalReceiveKey(String localReceiveKey) {
        this.localReceiveKey = localReceiveKey;
    }

    public String getWeChatMessageColor() {
        return weChatMessageColor;
    }

    public void setWeChatMessageColor(String weChatMessageColor) {
        this.weChatMessageColor = weChatMessageColor;
    }

    @Override
    public String toString() {
        return "WeChatTemplateDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", WeChatTemplateID=" + WeChatTemplateID +
                ", weChatTemplateKey='" + weChatTemplateKey + '\'' +
                ", localReceiveKey='" + localReceiveKey + '\'' +
                ", weChatMessageColor='" + weChatMessageColor + '\'' +
                '}';
    }
}
