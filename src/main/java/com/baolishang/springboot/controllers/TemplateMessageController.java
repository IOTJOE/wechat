package com.baolishang.springboot.controllers;

import com.baolishang.springboot.services.CoreService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 模板消息Controller
 * <p>
 * Created by FirenzesEagle on 2016/7/11 0011.
 * Email:liumingbo2008@gmail.com
 */
@Controller
@RequestMapping(value = "templateMessage")
public class TemplateMessageController extends GenericController {

    // 模板消息字体颜色
    private static final String TEMPLATE_FRONT_COLOR = "#000000";
    @Autowired
    protected WxMpConfigStorage configStorage;
    @Autowired
    protected WxMpService wxMpService;
    @Autowired
    protected CoreService coreService;

    @RequestMapping(value = "notifyRepaymentArrivedTemplate")
    public void notifyRepaymentArrivedTemplate(HttpServletResponse response,
                                              HttpServletRequest request) {
        WxMpTemplateMessage orderPaySuccessTemplate = new WxMpTemplateMessage();
        orderPaySuccessTemplate.setToUser(request.getParameter("openid"));
        orderPaySuccessTemplate.setTemplateId("8ws2u9gi7_hIxLDA-ogRpJCRC14rCqIJwx0u7RfBBwg");
        orderPaySuccessTemplate.setUrl(request.getParameter("url"));
        orderPaySuccessTemplate.setTopColor(TEMPLATE_FRONT_COLOR);
        WxMpTemplateData firstData = new WxMpTemplateData("first",request.getParameter("first"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData productName = new WxMpTemplateData("keyword1", request.getParameter("productName"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData repaymentPrincipal = new WxMpTemplateData("keyword2", request.getParameter("repaymentPrincipal"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData repaymentInterest = new WxMpTemplateData("keyword3", request.getParameter("repaymentInterest"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData repaymentTime = new WxMpTemplateData("keyword4", request.getParameter("repaymentTime"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData remarkData = new WxMpTemplateData("remark", request.getParameter("remark"), TEMPLATE_FRONT_COLOR);
        orderPaySuccessTemplate.addWxMpTemplateData(firstData);
        orderPaySuccessTemplate.addWxMpTemplateData(productName);
        orderPaySuccessTemplate.addWxMpTemplateData(repaymentPrincipal);
        orderPaySuccessTemplate.addWxMpTemplateData(repaymentInterest);
        orderPaySuccessTemplate.addWxMpTemplateData(repaymentTime);
        orderPaySuccessTemplate.addWxMpTemplateData(remarkData);
        try {
            wxMpService.getTemplateMsgService()
                    .sendTemplateMsg(orderPaySuccessTemplate);
        } catch (WxErrorException e) {
            logger.error(e.getMessage().toString());
        }
    }
    @RequestMapping(value = "notifyInvestSuccessTemplate")
    public void notifyInvestSuccessTemplate(HttpServletResponse response,
                                               HttpServletRequest request) {
        WxMpTemplateMessage orderPaySuccessTemplate = new WxMpTemplateMessage();
        orderPaySuccessTemplate.setToUser(request.getParameter("openid"));
        orderPaySuccessTemplate.setTemplateId("5uIxB9K-emXYGq1Ux5yJjHsAEDAokgX_O7GeoyrS4qU");
        orderPaySuccessTemplate.setUrl(request.getParameter("url"));
        orderPaySuccessTemplate.setTopColor(TEMPLATE_FRONT_COLOR);
        WxMpTemplateData firstData = new WxMpTemplateData("first",request.getParameter("first"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData productName = new WxMpTemplateData("keyword1", request.getParameter("productName"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData annualRate = new WxMpTemplateData("keyword2", request.getParameter("annualRate"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData repaymentTime = new WxMpTemplateData("keyword3", request.getParameter("repaymentTime"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData repaymentPrincipal = new WxMpTemplateData("keyword4", request.getParameter("repaymentPrincipal"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData repaymentInterest = new WxMpTemplateData("keyword5", request.getParameter("repaymentInterest"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData remarkData = new WxMpTemplateData("remark", request.getParameter("remark"), TEMPLATE_FRONT_COLOR);
        orderPaySuccessTemplate.addWxMpTemplateData(firstData);
        orderPaySuccessTemplate.addWxMpTemplateData(productName);
        orderPaySuccessTemplate.addWxMpTemplateData(annualRate);
        orderPaySuccessTemplate.addWxMpTemplateData(repaymentPrincipal);
        orderPaySuccessTemplate.addWxMpTemplateData(repaymentInterest);
        orderPaySuccessTemplate.addWxMpTemplateData(repaymentTime);
        orderPaySuccessTemplate.addWxMpTemplateData(remarkData);
        try {
            wxMpService.getTemplateMsgService()
                    .sendTemplateMsg(orderPaySuccessTemplate);
        } catch (WxErrorException e) {
            logger.error(e.getMessage().toString());
        }
    }
    @RequestMapping(value = "notifyWithdrawTemplate")
    public void notifyWithdrawTemplate(HttpServletResponse response,
                                            HttpServletRequest request) {
        WxMpTemplateMessage orderPaySuccessTemplate = new WxMpTemplateMessage();
        orderPaySuccessTemplate.setToUser(request.getParameter("openid"));
        orderPaySuccessTemplate.setTemplateId("ucpFJirhBo5J7PI7y243o2JbVPBXp2mKF1BX1h9nrHc");
        orderPaySuccessTemplate.setUrl(request.getParameter("url"));
        orderPaySuccessTemplate.setTopColor(TEMPLATE_FRONT_COLOR);
        WxMpTemplateData firstData = new WxMpTemplateData("first",request.getParameter("first"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData withdrawTime = new WxMpTemplateData("keyword1", request.getParameter("withdrawTime"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData amount = new WxMpTemplateData("keyword2", request.getParameter("amount"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData userFee = new WxMpTemplateData("keyword3", request.getParameter("userFee"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData remarkData = new WxMpTemplateData("remark", request.getParameter("remark"), TEMPLATE_FRONT_COLOR);
        orderPaySuccessTemplate.addWxMpTemplateData(firstData);
        orderPaySuccessTemplate.addWxMpTemplateData(withdrawTime);
        orderPaySuccessTemplate.addWxMpTemplateData(amount);
        orderPaySuccessTemplate.addWxMpTemplateData(userFee);
        orderPaySuccessTemplate.addWxMpTemplateData(remarkData);
        try {
            wxMpService.getTemplateMsgService()
                    .sendTemplateMsg(orderPaySuccessTemplate);
        } catch (WxErrorException e) {
            logger.error(e.getMessage().toString());
        }
    }
    @RequestMapping(value = "notifyDepositTemplate")
    public void notifyDepositTemplate(HttpServletResponse response,
                                       HttpServletRequest request) {
        WxMpTemplateMessage orderPaySuccessTemplate = new WxMpTemplateMessage();
        orderPaySuccessTemplate.setToUser(request.getParameter("openid"));
        orderPaySuccessTemplate.setTemplateId("NNNMFowOoo6LkyhNJ_jSBS9PfswyoEuHVnNMDFXWO9g");
        orderPaySuccessTemplate.setUrl(request.getParameter("url"));
        orderPaySuccessTemplate.setTopColor(TEMPLATE_FRONT_COLOR);
        WxMpTemplateData firstData = new WxMpTemplateData("first",request.getParameter("first"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData depositTime = new WxMpTemplateData("keyword1", request.getParameter("depositTime"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData amount = new WxMpTemplateData("keyword2", request.getParameter("amount"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData balance = new WxMpTemplateData("keyword3", request.getParameter("balance"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData remarkData = new WxMpTemplateData("remark", request.getParameter("remark"), TEMPLATE_FRONT_COLOR);
        orderPaySuccessTemplate.addWxMpTemplateData(firstData);
        orderPaySuccessTemplate.addWxMpTemplateData(depositTime);
        orderPaySuccessTemplate.addWxMpTemplateData(amount);
        orderPaySuccessTemplate.addWxMpTemplateData(balance);
        orderPaySuccessTemplate.addWxMpTemplateData(remarkData);
        try {
            wxMpService.getTemplateMsgService()
                    .sendTemplateMsg(orderPaySuccessTemplate);
        } catch (WxErrorException e) {
            logger.error(e.getMessage().toString());
        }
    }
    @RequestMapping(value = "notifyBindSuccessTemplate")
    public void notifyBindSuccessTemplate(HttpServletResponse response,
                                      HttpServletRequest request) {
        WxMpTemplateMessage orderPaySuccessTemplate = new WxMpTemplateMessage();
        orderPaySuccessTemplate.setToUser(request.getParameter("openid"));
        orderPaySuccessTemplate.setTemplateId("9JXXf200jNZohsCjYwbmqIh3uZPgKAv-YENncPAHmy8");
        orderPaySuccessTemplate.setUrl(request.getParameter("url"));
        orderPaySuccessTemplate.setTopColor(TEMPLATE_FRONT_COLOR);
        WxMpTemplateData firstData = new WxMpTemplateData("first",request.getParameter("first"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData name1 = new WxMpTemplateData("name1", request.getParameter("name1"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData name2 = new WxMpTemplateData("name2", request.getParameter("name2"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData time = new WxMpTemplateData("time", request.getParameter("time"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData remarkData = new WxMpTemplateData("remark", request.getParameter("remark"), TEMPLATE_FRONT_COLOR);
        orderPaySuccessTemplate.addWxMpTemplateData(firstData);
        orderPaySuccessTemplate.addWxMpTemplateData(name1);
        orderPaySuccessTemplate.addWxMpTemplateData(name2);
        orderPaySuccessTemplate.addWxMpTemplateData(time);
        orderPaySuccessTemplate.addWxMpTemplateData(remarkData);
        try {
            wxMpService.getTemplateMsgService()
                    .sendTemplateMsg(orderPaySuccessTemplate);
        } catch (WxErrorException e) {
            logger.error(e.getMessage().toString());
        }
    }
    @RequestMapping(value = "notifyAccountDetailTemplate")
    public void notifyAccountDetailTemplate(HttpServletResponse response,
                                          HttpServletRequest request) {
        WxMpTemplateMessage orderPaySuccessTemplate = new WxMpTemplateMessage();
        orderPaySuccessTemplate.setToUser(request.getParameter("openid"));
        orderPaySuccessTemplate.setTemplateId("7mElOo_pA1iBQIo9gbnJO58-3F1ZfwD4r4dYJsGtNpw");
        orderPaySuccessTemplate.setUrl(request.getParameter("url"));
        orderPaySuccessTemplate.setTopColor(TEMPLATE_FRONT_COLOR);
        WxMpTemplateData firstData = new WxMpTemplateData("first",request.getParameter("first"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData keyword1 = new WxMpTemplateData("keyword1", request.getParameter("keyword1"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData keyword2 = new WxMpTemplateData("keyword2", request.getParameter("keyword2"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData keyword3 = new WxMpTemplateData("keyword3", request.getParameter("keyword3"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData keyword4 = new WxMpTemplateData("keyword4", request.getParameter("keyword4"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData keyword5 = new WxMpTemplateData("keyword5", request.getParameter("keyword5"), TEMPLATE_FRONT_COLOR);
        WxMpTemplateData remarkData = new WxMpTemplateData("remark", request.getParameter("remark"), TEMPLATE_FRONT_COLOR);
        orderPaySuccessTemplate.addWxMpTemplateData(firstData);
        orderPaySuccessTemplate.addWxMpTemplateData(keyword1);
        orderPaySuccessTemplate.addWxMpTemplateData(keyword2);
        orderPaySuccessTemplate.addWxMpTemplateData(keyword3);
        orderPaySuccessTemplate.addWxMpTemplateData(keyword4);
        orderPaySuccessTemplate.addWxMpTemplateData(keyword5);
        orderPaySuccessTemplate.addWxMpTemplateData(remarkData);
        try {
            wxMpService.getTemplateMsgService()
                    .sendTemplateMsg(orderPaySuccessTemplate);
        } catch (WxErrorException e) {
            logger.error(e.getMessage().toString());
        }
    }
    //运营人员通知还款状态
    @RequestMapping({"notifyRepaymentStatusTemplate"})
    public void notifyRepaymentStatusTemplate(HttpServletResponse response, HttpServletRequest request) {
        WxMpTemplateMessage orderPaySuccessTemplate = new WxMpTemplateMessage();
        orderPaySuccessTemplate.setToUser(request.getParameter("openid"));
        orderPaySuccessTemplate.setTemplateId("pCn-kmZQYMIoMSAAuldMGSylzwkw3QNuiKNDgIhOaHI");
        orderPaySuccessTemplate.setUrl(request.getParameter("url"));
        orderPaySuccessTemplate.setTopColor("#000000");
        WxMpTemplateData firstData = new WxMpTemplateData("first", request.getParameter("first"), "#000000");
        WxMpTemplateData keyword1 = new WxMpTemplateData("keyword1", request.getParameter("keyword1"), "#000000");
        WxMpTemplateData keyword2 = new WxMpTemplateData("keyword2", request.getParameter("keyword2"), "#000000");
        WxMpTemplateData keyword3 = new WxMpTemplateData("keyword3", request.getParameter("keyword3"), "#000000");
        WxMpTemplateData keyword4 = new WxMpTemplateData("keyword4", request.getParameter("keyword4"), "#000000");
        WxMpTemplateData keyword5 = new WxMpTemplateData("keyword5", request.getParameter("keyword5"), "#000000");
        WxMpTemplateData remarkData = new WxMpTemplateData("remark", request.getParameter("remark"), "#000000");
        orderPaySuccessTemplate.addWxMpTemplateData(firstData);
        orderPaySuccessTemplate.addWxMpTemplateData(keyword1);
        orderPaySuccessTemplate.addWxMpTemplateData(keyword2);
        orderPaySuccessTemplate.addWxMpTemplateData(keyword3);
        orderPaySuccessTemplate.addWxMpTemplateData(keyword4);
        orderPaySuccessTemplate.addWxMpTemplateData(keyword5);
        orderPaySuccessTemplate.addWxMpTemplateData(remarkData);

        try {
            this.wxMpService.getTemplateMsgService().sendTemplateMsg(orderPaySuccessTemplate);
        } catch (WxErrorException var12) {
            this.logger.error(var12.getMessage().toString());
        }

    }
    //赎回成功通知
    @RequestMapping({"notifyRedemptionTemplate"})
    public void notifyRedemptionTemplate(HttpServletResponse response, HttpServletRequest request) {
        WxMpTemplateMessage orderPaySuccessTemplate = new WxMpTemplateMessage();
        orderPaySuccessTemplate.setToUser(request.getParameter("openid"));
        orderPaySuccessTemplate.setTemplateId("BV6VysKnaQVZqzA6OxDTukKoR8UwPxoFGT98T4_BeUw");
        orderPaySuccessTemplate.setUrl(request.getParameter("url"));
        orderPaySuccessTemplate.setTopColor("#000000");
        WxMpTemplateData firstData = new WxMpTemplateData("first", request.getParameter("first"), "#000000");
        WxMpTemplateData keyword1 = new WxMpTemplateData("keyword1", request.getParameter("keyword1"), "#000000");
        WxMpTemplateData keyword2 = new WxMpTemplateData("keyword2", request.getParameter("keyword2"), "#000000");
        WxMpTemplateData remarkData = new WxMpTemplateData("remark", request.getParameter("remark"), "#000000");
        orderPaySuccessTemplate.addWxMpTemplateData(firstData);
        orderPaySuccessTemplate.addWxMpTemplateData(keyword1);
        orderPaySuccessTemplate.addWxMpTemplateData(keyword2);
        orderPaySuccessTemplate.addWxMpTemplateData(remarkData);

        try {
            this.wxMpService.getTemplateMsgService().sendTemplateMsg(orderPaySuccessTemplate);
        } catch (WxErrorException var12) {
            this.logger.error(var12.getMessage().toString());
        }

    }
    //消息推送通知
    @RequestMapping({"messagePush"})

    public void messagePush(HttpServletResponse response, HttpServletRequest request)  throws WxErrorException {
        WxMpCustomMessage message = WxMpCustomMessage
                .TEXT()
                .content(request.getParameter("message"))
                .toUser(request.getParameter("openid"))
                .build();
        try {
            this.wxMpService.getKefuService().customMessageSend(message);
        } catch (WxErrorException var12) {
            this.logger.error(var12.getMessage().toString());
        }
        this.logger.info("SubscribeMessageHandler:" + message.getContent());

    }
}
