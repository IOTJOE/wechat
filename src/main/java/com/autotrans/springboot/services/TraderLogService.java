package com.autotrans.springboot.services;

import com.autotrans.springboot.models.TraderLog;

import java.util.List;

/**
 * Created by hello on 2016/9/12.
 */
public interface TraderLogService {

    TraderLog save(TraderLog traderLog);
    List<TraderLog> selectAllTraderLog();
    List<TraderLog> selectByUserAccountAndTransDate(String userAccount, String transDate);
    void updateStatus(String status,String transid);
//    Receiver findReceiverByWeixinAcct(String weixinAcct);
//    Receiver findReceiverByPhone(String phone);
}
