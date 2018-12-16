package com.baolishang.springboot.models;


import com.baolishang.springboot.storage.SmsConfigStorage;
import com.baolishang.springboot.utils.HttpSender;

import javax.servlet.http.HttpServletRequest;

/**
 * @Name
 * @Author Joe mailTo:jiangzhongke@baojinsuo.com
 * @Date 2016/10/31 下午5:30
 * SmsRequestBean{tunnel='cltzold', modelName='103', user='baojinsuo', mobile='18516550566', param={key2=3332, key1=1122}, key='XAVFzHSsj8MjOxSPxxR4zI0tOvheW0QRi7FDT6YEaFV'}
 * @Desc
 */
public class SmsModel {
    public static String sendSms(SmsConfigStorage configStorage,HttpServletRequest param) throws Exception {
        StringBuffer str = new StringBuffer();
        if (!param.getParameter("accesskey").equals(configStorage.getKey()))
            return "accesskey错误!无权调用!";
        if(!param.getParameter("tunnel").equals("cltzold")){
        switch (param.getParameter("user")){
            case "baojinsuo":
                str.append("【保金所】");
                break;
            case "mingpin":
                str.append("【名品消费宝】");
                break;
            default:
                return "头部名称不存在!当前存在参数:baojinsuo,mingpin,对应头部【保金所】,【名品消费宝】.请检查是否参数正确!";

        }}
        switch (param.getParameter("modelName")){
            case "buy_success":
                str.append("您成功购买了"+param.getParameter("key1")+"元"+param.getParameter("key2")+"，年化利率"+param.getParameter("key3")+"%，理财周期"+param.getParameter("key4")+"到期日"+param.getParameter("key5")+"。");
                break;
            case "repayment_success":
                str.append("您购买的"+param.getParameter("key1")+"已到期，还款本金"+param.getParameter("key2")+"元，利息"+param.getParameter("key3")+"元。本息已返还到您的账户余额。");
                break;
            case "verify_code":
                str.append("您的验证码是"+param.getParameter("key1"));
                break;
            case "user_deposit":
                str.append("您账户于"+param.getParameter("key1")+"发生一笔"+param.getParameter("key2")+"元的充值操作，已到账请查账户余额。");
                break;
            case "user_chash":
                str.append("您账户于"+param.getParameter("key1")+"发生一笔"+param.getParameter("key2")+"元的取现操作，预计1-3个工作日到账，请注意银行通知。");
                break;
            case "repayment_product":
                str.append(param.getParameter("first") +  "还款状态:" + param.getParameter("keyword1") + "已还本息:"+param.getParameter("keyword2") +",待还本息:"+param.getParameter("keyword3")+",还款笔数:"+param.getParameter("keyword4")+",还款人次:"+param.getParameter("keyword5")+","+param.getParameter("remark"));
                break;
            default:
                str.append(param.getParameter("message"));
                break;
        }
        if(param.getParameter("tunnel").equals("clyxnew")){
            str.append("退订回复TD");
        }
        configStorage.setMobile(param.getParameter("mobile"));
        configStorage.setMessage(str.toString());
        String sendStatus = HttpSender.batchSend(configStorage);
        return sendStatus;

    }
}
