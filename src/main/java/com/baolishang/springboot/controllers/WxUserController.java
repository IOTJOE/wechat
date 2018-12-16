package com.baolishang.springboot.controllers;

import com.baolishang.springboot.beans.ResponseCode;
import com.baolishang.springboot.exceptions.BaseException;
import com.baolishang.springboot.models.Receiver;
import com.baolishang.springboot.services.ReceiverService;
import com.baolishang.springboot.storage.SmsConfigStorage;
import com.baolishang.springboot.utils.HttpSender;
import com.baolishang.springboot.utils.ResponseUtils;
import com.baolishang.springboot.utils.SafeUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/wx")
public class WxUserController  extends GenericController {

    protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    @Autowired
    protected WxMpService wxMpService;
    @Autowired
    protected ReceiverService receiverService;
    @Autowired
    protected SmsConfigStorage clTzOldConfigStorage;
    @Scope("prototype")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        String code = request.getParameter("code");
        WxMpOAuth2AccessToken accessToken;
        WxMpUser wxMpUser;
        String weixinAcct = "";
        try {
            accessToken = this.wxMpService.oauth2getAccessToken(code);
            wxMpUser = this.wxMpService.getUserService()
                    .userInfo(accessToken.getOpenId(), "zh_CN");
            weixinAcct= wxMpUser.getOpenId();
            logger.info("weixinAcct="+weixinAcct);
        }catch(Exception e){
        }
        Receiver customer = receiverService.findReceiverByWeixinAcct(weixinAcct);
        if(null != customer){
            //该微信账号已经绑定了账户
            modelAndView.setViewName("login/bind_success");
            return modelAndView;
        }
        try {
            modelAndView.setViewName("login/login");
            request.getSession().setAttribute("open_id",weixinAcct);
//            modelAndView.addObject("open_id",weixinAcct);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }
    @RequestMapping(value = "/sms", method = RequestMethod.POST)
    public ResponseEntity getWeixinSms(@RequestParam(value = "mobile", required = true) String mobile,
                                         @RequestParam(value = "real_name", required = true) String real_name,
                                         HttpServletRequest request)  throws Exception{
        String openid = SafeUtils.getString(request.getSession().getAttribute("open_id"));
        logger.info("open_id="+openid);
        if(openid.isEmpty()){
            throw new BaseException(ResponseCode.openid_error, HttpStatus.BAD_REQUEST);
        }
        if(mobile.length()!=11){
            throw new BaseException(ResponseCode.mobile_error, HttpStatus.BAD_REQUEST);
        }
        if(real_name.length()<2||real_name.length()>10){
            throw new BaseException(ResponseCode.name_error, HttpStatus.BAD_REQUEST);
        }
        Receiver customerWechat = receiverService.findReceiverByWeixinAcct(openid);
        if(null != customerWechat){
            throw new BaseException(ResponseCode.bind_wechat_already, HttpStatus.BAD_REQUEST);
        }
        Receiver customerPhone = receiverService.findReceiverByPhone(mobile);
        if(null != customerPhone){
            throw new BaseException(ResponseCode.bind_phone_already, HttpStatus.BAD_REQUEST);
        }
        String vaildcode = RandomStringUtils.randomNumeric(4);
        clTzOldConfigStorage.setMobile(mobile);
        clTzOldConfigStorage.setMessage("您的验证码是"+vaildcode);
        logger.info("vaildcode="+vaildcode);
        String sendStatus = HttpSender.batchSend(clTzOldConfigStorage);

        request.getSession().setAttribute("mobile",mobile);
        request.getSession().setAttribute("real_name",real_name);
        request.getSession().setAttribute("code_sms",vaildcode);
        String code_sms = SafeUtils.getString(request.getSession().getAttribute("code_sms"));
        logger.info("validcode="+code_sms);
        logger.info("sendStatus="+sendStatus);
//        request.getSession().removeAttribute("open_id");
        return ResponseUtils.restResponse("send_sms_success", "已经发送4位数手机验证码", HttpStatus.OK);
    }
    @RequestMapping(value = "/bindWexin", method = RequestMethod.POST)
    public ResponseEntity bindWeChatAccount(
                                            @RequestParam(value = "validcode", required = true) String validcode,
                                         HttpServletRequest request)  throws Exception{
        String openid = SafeUtils.getString(request.getSession().getAttribute("open_id"));
        String mobile = SafeUtils.getString(request.getSession().getAttribute("mobile"));
        String real_name =  SafeUtils.getString(request.getSession().getAttribute("real_name"));
        String code_sms = SafeUtils.getString(request.getSession().getAttribute("code_sms"));
        logger.info("open_id="+openid);
        logger.info("mobile="+mobile);
        logger.info("real_name="+real_name);
        logger.info("validcode="+code_sms);
        if(code_sms.isEmpty()){
            throw new BaseException(ResponseCode.validation_code_not_found, HttpStatus.BAD_REQUEST);
        }
        if(!code_sms.equals(validcode)){
            throw new BaseException(ResponseCode.validation_code_not_correct, HttpStatus.BAD_REQUEST);
        }
        if(openid.isEmpty()){
            throw new BaseException(ResponseCode.openid_error, HttpStatus.BAD_REQUEST);
        }
        if(real_name.length()<2||real_name.length()>10){
            throw new BaseException(ResponseCode.name_error, HttpStatus.BAD_REQUEST);
        }
        Receiver customerWechat = receiverService.findReceiverByWeixinAcct(openid);
        if(null != customerWechat){
            throw new BaseException(ResponseCode.bind_wechat_already, HttpStatus.BAD_REQUEST);
        }
        Receiver customerPhone = receiverService.findReceiverByPhone(mobile);
        if(null != customerPhone){
            throw new BaseException(ResponseCode.bind_phone_already, HttpStatus.BAD_REQUEST);
        }
        Receiver newReceiver = new Receiver(real_name,mobile,openid,false);
        Receiver resultSave =  receiverService.save(newReceiver);
        request.removeAttribute("open_id");
        request.removeAttribute("mobile");
        request.removeAttribute("real_name");
        request.removeAttribute("code_sms");
        if(resultSave!=null)
        return ResponseUtils.restResponse("bind_success", "绑定成功", HttpStatus.OK);
        else
            throw new BaseException(ResponseCode.unknown_error, HttpStatus.BAD_REQUEST);
    }
    /**
     * 微信绑定成功
     * @param request
     * @return
     */
    @Scope("prototype")
    @RequestMapping(value = "/bindSuccess", method = RequestMethod.GET)
    public ModelAndView bindSuccess(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/bind_success");
        modelAndView.addObject("g_pagename", "用户登录");
        modelAndView.addObject("g_naviidx", 0);
        modelAndView.addObject("g_baseservlet", 0);
        return modelAndView;
    }
}
