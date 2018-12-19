package com.autotrans.springboot.models;

import com.autotrans.springboot.bases.BaseModel;

import javax.persistence.*;

/**
 * Created by joe on 2017/8/29.
 */
@Entity
@Table(name="t_message_template")
public class MessageTemplate extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",unique = true, nullable = false)
    private Long id;

    @Column(name="Name")
    private String name;

    @Column(name="WeChatTemplateId")
    private String weChatTemplateId;

    @Column(name="ShortMessageId")
    private String shortMessageId;

    @Column(name="WechatTopColor")
    private String wechatTopColor;
    @Column(name="Url")
    private String url;
    @Column(name="Status")
    private Byte status;

    @Override
    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getWeChatTemplateId() {
        return weChatTemplateId;
    }

    public void setWeChatTemplateId(String weChatTemplateId) {
        this.weChatTemplateId = weChatTemplateId;
    }

    public String getShortMessageId() {
        return shortMessageId;
    }

    public void setShortMessageId(String shortMessageId) {
        this.shortMessageId = shortMessageId;
    }

    public String getWechatTopColor() {
        return wechatTopColor;
    }

    public void setWechatTopColor(String wechatTopColor) {
        this.wechatTopColor = wechatTopColor;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
