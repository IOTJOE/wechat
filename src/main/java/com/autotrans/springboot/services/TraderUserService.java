package com.autotrans.springboot.services;

import com.autotrans.springboot.models.TraderUser;

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
