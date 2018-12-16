package com.baolishang.springboot.daos.impl;

import com.baolishang.springboot.models.MessageTemplate;
import com.baolishang.springboot.models.ReceiveUser;
import com.baolishang.springboot.repositories.ReceiveUserRepository;
import com.baolishang.springboot.daos.WechatModelDao;
import com.baolishang.springboot.models.WeChatTemplateDetail;
import com.baolishang.springboot.repositories.MessageTemplateRepository;
import com.baolishang.springboot.repositories.WeChatTemplateDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hello on 2016/9/12.
 */
@Repository
public class WechatModelDaoImpl implements WechatModelDao {
    private static final Logger logger = LoggerFactory.getLogger(WechatModelDaoImpl.class);

    @Autowired
    private WeChatTemplateDetailRepository weChatTemplateDetailRepository;
    @Autowired
    private ReceiveUserRepository receiveUserRepository;
    @Autowired
    private MessageTemplateRepository messageTemplateRepository;
    @Override
    public List<ReceiveUser> getUser(String code){
        return receiveUserRepository.getReceiveUser(code);
    }
    @Override
    public List<WeChatTemplateDetail> getTemplateDetail(Long templateId){
        return weChatTemplateDetailRepository.getWeChatTemplateDetail(templateId);
    }
    @Override
    public MessageTemplate getMessageTemplate(String templateName){
        return messageTemplateRepository.getMessageTemplate(templateName);
    }

}
