package com.baolishang.springboot.services;

import com.baolishang.springboot.models.Receiver;

/**
 * Created by hello on 2016/9/12.
 */
public interface ReceiverService {

    Receiver save(Receiver receiver);
    Receiver findReceiverByWeixinAcct(String weixinAcct);
    Receiver findReceiverByPhone(String phone);
}
