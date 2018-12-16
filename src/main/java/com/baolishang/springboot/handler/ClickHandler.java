package com.baolishang.springboot.handler;

import com.baolishang.springboot.services.CoreService;
import com.baolishang.springboot.utils.SMSHttpRequest;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户关注公众号Handler
 * <p>
 * Created by FirenzesEagle on 2016/7/27 0027.
 * Email:liumingbo2008@gmail.com
 */
@Component
public class ClickHandler extends AbstractHandler {

    @Autowired
    protected WxMpConfigStorage configStorage;
    @Autowired
    protected WxMpService wxMpService;
    @Autowired
    protected CoreService coreService;

    @Value("${baojinsuo_url}")
    private String baojinsuoUrl;

    //定义servername==>目标地址
    //点击事件转发
    //事件点击       编号 名称  事件     转发地址
    //t_click_event id  name eventKey url

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        logger.info("openId:"+wxMessage.getFromUser()+"===serverName=="+ wxMessage.getEventKey()+"==url=="+baojinsuoUrl+"wxuser/notify" );

        Map<String, String> map = new HashMap<String, String>();
        map.put("openId", wxMessage.getFromUser());
        map.put("serverName", wxMessage.getEventKey());
        //发送 POST 请求
        String sr= SMSHttpRequest.sendPost(baojinsuoUrl+"wxuser/notify",map);
        logger.info("ResponseMessage:"+sr);
        return null;
    }
};
