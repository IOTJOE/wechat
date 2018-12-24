package com.autotrans.springboot.services;

import com.autotrans.springboot.models.TraderByDay;

import java.util.List;

/**
 * Created by hello on 2016/9/12.
 */
public interface TraderByDayService {

    TraderByDay save(TraderByDay traderByDay);
    List<TraderByDay> selectByUserAccountAndTransDate(String userAccount, String transDate);
    int updateTransStatus(int status, int  successAmount,int  revokeAmount,String transId, String source,String message,String desc,long id);
//    Receiver findReceiverByWeixinAcct(String weixinAcct);
//    Receiver findReceiverByPhone(String phone);
}
