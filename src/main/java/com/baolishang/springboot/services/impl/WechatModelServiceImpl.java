package com.baolishang.springboot.services.impl;

import com.baolishang.springboot.daos.WechatModelDao;
import com.baolishang.springboot.models.MessageTemplate;
import com.baolishang.springboot.models.ReceiveUser;
import com.baolishang.springboot.models.WeChatTemplateDetail;
import com.baolishang.springboot.services.WechatModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hello on 2016/9/12.
 */
@Service
public class WechatModelServiceImpl implements WechatModelService {
    private static final Logger logger = LoggerFactory.getLogger(WechatModelServiceImpl.class);

    @Autowired
    private WechatModelDao wechatModelDao;
    @Override
    public List<ReceiveUser> getUser(String code){
        return wechatModelDao.getUser(code);
    }
    @Override
    public List<WeChatTemplateDetail> getTemplateDetail(Long templateId){
        return wechatModelDao.getTemplateDetail(templateId);
    }
    @Override
    public MessageTemplate getMessageTemplate(String templateName){
        return wechatModelDao.getMessageTemplate(templateName);
    }
}
