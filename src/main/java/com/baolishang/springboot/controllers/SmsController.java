package com.baolishang.springboot.controllers;

import com.baolishang.springboot.models.SmsModel;
import com.baolishang.springboot.storage.SmsConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Name 短信消息发送接口
 * @Author Joe mailTo:jiangzhongke@baojinsuo.com
 * @Date 2016/10/31 下午4:07
 * @Desc
 */
@Controller

public class SmsController  extends GenericController {
    @Autowired
    protected SmsConfigStorage clTzOldConfigStorage;
    @Autowired
    protected SmsConfigStorage clTzNewConfigStorage;
    @Autowired
    protected SmsConfigStorage clYxNewConfigStorage;
    @RequestMapping(value = "sendSms")
    public void sendSms(HttpServletResponse response, HttpServletRequest request) throws IOException {

        String result="";
        try {
            switch (request.getParameter("tunnel")){
                case "cltzold":
                    result = SmsModel.sendSms(clTzOldConfigStorage,request);
                    break;
                case "cltznew":
                    result = SmsModel.sendSms(clTzNewConfigStorage,request);
                    break;
                case "clyxnew":
                    result = SmsModel.sendSms(clYxNewConfigStorage,request);
                    break;
            }
            response.getWriter().println(result);
        }  catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("系统内部错误!");
        }
    }
}
