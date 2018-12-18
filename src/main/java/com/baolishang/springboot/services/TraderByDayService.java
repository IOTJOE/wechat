package com.baolishang.springboot.services;

import com.baolishang.springboot.models.TraderByDay;

import java.util.List;

/**
 * Created by hello on 2016/9/12.
 */
public interface TraderByDayService {

    TraderByDay save(TraderByDay traderByDay);
    List<TraderByDay> selectByUserAccountAndTransDate(String userAccount, String transDate);
//    Receiver findReceiverByWeixinAcct(String weixinAcct);
//    Receiver findReceiverByPhone(String phone);
}
