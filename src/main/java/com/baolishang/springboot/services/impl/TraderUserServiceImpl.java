package com.baolishang.springboot.services.impl;

import com.baolishang.springboot.models.TraderUser;
import com.baolishang.springboot.repositories.TraderUserRepository;
import com.baolishang.springboot.services.TraderUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by will on 16/9/12.
 */
@Service
public class TraderUserServiceImpl implements TraderUserService {

    private static final Logger logger = LoggerFactory.getLogger(TraderUserServiceImpl.class);

    @Autowired
    private TraderUserRepository traderUserRepository;

    public TraderUser save(TraderUser traderUser){
        return traderUserRepository.save(traderUser);
    }

    public List<TraderUser> selectAllTraderUser(){
    return traderUserRepository.findAll();
}
}
