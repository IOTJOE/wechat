package com.autotrans.springboot.services.impl;

import com.autotrans.springboot.models.Receiver;
import com.autotrans.springboot.repositories.ReceiverRepository;
import com.autotrans.springboot.services.ReceiverService;
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
