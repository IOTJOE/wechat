package com.baolishang.springboot.models;

import com.baolishang.springboot.bases.BaseModel;

import javax.persistence.*;

/**
 * Created by joe on 2017/8/29.
 */
@Entity
@Table(name="t_notice_user")
public class NoticeUser extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",unique = true, nullable = false)
    private Long id;


    @Column(name="LocalMessageTemplateId")
    private Long localMessageTemplateId;

    @Column(name="MessageId")
    private Long messageId;

    @Column(name="WeChatId")
    private Long weChatId;

    @Column(name="UserId")
    private Long userId;

    @Column(name="Status")
    private Byte status;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocalMessageTemplateId() {
        return localMessageTemplateId;
    }

    public void setLocalMessageTemplateId(Long localMessageTemplateId) {
        this.localMessageTemplateId = localMessageTemplateId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(Long weChatId) {
        this.weChatId = weChatId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
