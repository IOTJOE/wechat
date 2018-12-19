package com.autotrans.springboot.controllers;

import com.autotrans.springboot.models.MessageTemplate;
import com.autotrans.springboot.models.ReceiveUser;
import com.autotrans.springboot.models.WeChatTemplateDetail;
import com.autotrans.springboot.services.CoreService;
import com.autotrans.springboot.services.WechatModelService;
import com.autotrans.springboot.utils.SafeUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by joe on 2017/8/31.
 */
@Controller
public class AutoMessageSendController extends GenericController {
    @Autowired
    protected WxMpConfigStorage configStorage;
    @Autowired
    protected WxMpService wxMpService;
    @Autowired
    protected CoreService coreService;
    @Autowired
    private WechatModelService wechatModelService;
    @RequestMapping(value = "SendMessage")
    public void sendMessage(HttpServletResponse response,
                                               HttpServletRequest request) {


        List<ReceiveUser> receiveUsersList = wechatModelService.getUser(request.getParameter("template"));
        for (ReceiveUser receiveUser : receiveUsersList) {
            WxMpTemplateMessage wxTemplate = new WxMpTemplateMessage();
            wxTemplate.setToUser(receiveUser.getOpenId());
            wxTemplate.setTemplateId(receiveUser.getWeChatTemplateId());
            wxTemplate.setUrl(request.getParameter(receiveUser.getUrl()));
            wxTemplate.setTopColor(receiveUser.getWechatTopColor());
            logger.info("receiveUser="+receiveUser.getOpenId()+";getWeChatTemplateId="+receiveUser.getWeChatTemplateId()+";receiveUser.getUrl()="+receiveUser.getUrl()+";receiveUser.getWechatTopColor()="+receiveUser.getWechatTopColor());
            List<WeChatTemplateDetail> weChatTemplateDetailList = wechatModelService.getTemplateDetail(SafeUtils.getLong(receiveUser.getWeChatId()));
            for (WeChatTemplateDetail weChatTemplateDetail : weChatTemplateDetailList) {
                WxMpTemplateData wxMpTemplateData = new WxMpTemplateData(weChatTemplateDetail.getWeChatTemplateKey(), request.getParameter(weChatTemplateDetail.getLocalReceiveKey()), weChatTemplateDetail.getWeChatMessageColor());
                logger.info("weChatTemplateDetail.getWeChatTemplateKey()="+weChatTemplateDetail.getWeChatTemplateKey()+"; weChatTemplateDetail.getLocalReceiveKey()="+ weChatTemplateDetail.getLocalReceiveKey()+"; request.getParameter(weChatTemplateDetail.getLocalReceiveKey()="+request.getParameter(weChatTemplateDetail.getLocalReceiveKey())+"; weChatTemplateDetail.getWeChatMessageColor()="+weChatTemplateDetail.getWeChatMessageColor());
                wxTemplate.addWxMpTemplateData(wxMpTemplateData);
            }
            try {
                wxMpService.getTemplateMsgService()
                        .sendTemplateMsg(wxTemplate);
            } catch (WxErrorException e) {
                logger.error(e.getMessage().toString());
            }
        }



    }

    /**
     * 第三方接口消息
     * @param response
     * @param request
     */
    @RequestMapping(value = "SendWechatMessage")
    public void sendWechatMessage(HttpServletResponse response,
                            HttpServletRequest request) {
        logger.info("template_name="+request.getParameter("template_name")+"--openid="+request.getParameter("openid"));
        MessageTemplate messageTemplate = wechatModelService.getMessageTemplate(request.getParameter("template_name"));
        WxMpTemplateMessage wxTemplate = new WxMpTemplateMessage();
        wxTemplate.setToUser(request.getParameter("openid"));
        wxTemplate.setTemplateId(messageTemplate.getWeChatTemplateId());
        wxTemplate.setUrl(messageTemplate.getUrl());
        wxTemplate.setTopColor(messageTemplate.getWechatTopColor());
        List<WeChatTemplateDetail> weChatTemplateDetailList = wechatModelService.getTemplateDetail(messageTemplate.getId());
        for (WeChatTemplateDetail weChatTemplateDetail : weChatTemplateDetailList) {
            WxMpTemplateData wxMpTemplateData = new WxMpTemplateData(weChatTemplateDetail.getWeChatTemplateKey(), request.getParameter(weChatTemplateDetail.getLocalReceiveKey()), weChatTemplateDetail.getWeChatMessageColor());
            logger.info("weChatTemplateDetail.getWeChatTemplateKey()="+weChatTemplateDetail.getWeChatTemplateKey()+"; weChatTemplateDetail.getLocalReceiveKey()="+ weChatTemplateDetail.getLocalReceiveKey()+"; request.getParameter(weChatTemplateDetail.getLocalReceiveKey()="+request.getParameter(weChatTemplateDetail.getLocalReceiveKey())+"; weChatTemplateDetail.getWeChatMessageColor()="+weChatTemplateDetail.getWeChatMessageColor());
            wxTemplate.addWxMpTemplateData(wxMpTemplateData);
        }
        try {
            wxMpService.getTemplateMsgService()
                    .sendTemplateMsg(wxTemplate);
        } catch (WxErrorException e) {
            logger.error(e.getMessage().toString());
        }


    }
}
