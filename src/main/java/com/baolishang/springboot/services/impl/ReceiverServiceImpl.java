package com.baolishang.springboot.services.impl;

import com.baolishang.springboot.models.Receiver;
import com.baolishang.springboot.repositories.ReceiverRepository;
import com.baolishang.springboot.services.ReceiverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by will on 16/9/12.
 */
@Service
public class ReceiverServiceImpl implements ReceiverService {

    private static final Logger logger = LoggerFactory.getLogger(ReceiverServiceImpl.class);

    @Autowired
    private ReceiverRepository receiverRepository;

    public Receiver findReceiverByWeixinAcct(String weixinAcct){
        Receiver customerProfile = receiverRepository.getReceiverByWeixinAcct(weixinAcct);
            return customerProfile;

    }
    public Receiver save(Receiver receiver){
        return receiverRepository.save(receiver);
    }
    public Receiver findReceiverByPhone(String phone){
        Receiver customerProfile = receiverRepository.getReceiverByPhone(phone);
        return customerProfile;

    }
}
