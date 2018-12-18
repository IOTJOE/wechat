package com.baolishang.springboot.services.impl;

import com.baolishang.springboot.models.TraderByDay;
import com.baolishang.springboot.repositories.TraderByDayRepository;
import com.baolishang.springboot.services.TraderByDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by will on 16/9/12.
 */
@Service
public class TraderByDayServiceImpl implements TraderByDayService {

    private static final Logger logger = LoggerFactory.getLogger(TraderByDayServiceImpl.class);

    @Autowired
    private TraderByDayRepository traderByDayRepository;
    public TraderByDay save(TraderByDay traderByDay){
        return traderByDayRepository.save(traderByDay);
    }

    public List<TraderByDay> selectByUserAccountAndTransDate(String userAccount, String transDate){
    return traderByDayRepository.selectByUserAccountAndTransDate(userAccount,transDate);
}
}
