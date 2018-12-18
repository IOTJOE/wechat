package com.baolishang.springboot.services.impl;

import com.baolishang.springboot.models.TraderLog;
import com.baolishang.springboot.repositories.TraderLogRepository;
import com.baolishang.springboot.services.TraderLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by will on 16/9/12.
 */
@Service
public class TraderLogServiceImpl implements TraderLogService {

    private static final Logger logger = LoggerFactory.getLogger(TraderLogServiceImpl.class);

    @Autowired
    private TraderLogRepository traderLogRepository;

    public TraderLog save(TraderLog traderLog){
        return traderLogRepository.save(traderLog);
    }

    public List<TraderLog> selectAllTraderLog(){
    return traderLogRepository.findAll();
}
    public List<TraderLog> selectByUserAccountAndTransDate(String userAccount, String transDate){
        return traderLogRepository.selectByUserAccountAndTransDate(userAccount,transDate);
    }
    public void updateStatus(String status,String transid){
        traderLogRepository.updateStatus(status,transid);
    }
}
