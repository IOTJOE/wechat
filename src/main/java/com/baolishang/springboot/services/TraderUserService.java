package com.baolishang.springboot.services;

import com.baolishang.springboot.models.TraderUser;

import java.util.List;

/**
 * Created by hello on 2016/9/12.
 */
public interface TraderUserService {

    TraderUser save(TraderUser traderUser);
    List<TraderUser> selectAllTraderUser();
//    Receiver findReceiverByWeixinAcct(String weixinAcct);
//    Receiver findReceiverByPhone(String phone);
}
