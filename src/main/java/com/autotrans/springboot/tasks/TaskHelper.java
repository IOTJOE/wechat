package com.autotrans.springboot.tasks;

import com.autotrans.springboot.services.TransService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskHelper {

    private static final Logger logger = LoggerFactory.getLogger(TaskHelper.class);
    @Autowired
    private TransService transService;
    //每天7点,自动下单
    @Scheduled(cron = "0 16 8 ? * MON-FRI")
    public void autoTrans(){
        logger.info("定时任务---自动下单");
        transService.autoTrans(1);

    }
//    @Scheduled(cron = "0 26 9 ? * MON-FRI")
    public void autoTrans2(){
        logger.info("定时任务---自动下单");   
        transService.autoTrans(1);

    }
    //每天下午9点,检查早盘挂单结果
    @Scheduled(cron = "0 20 9  ? * MON-FRI")
    public void checkTrans(){
        logger.info("定时任务---检查早盘挂单结果");
    }
    //每天下午2点45,撤单自动卖出,连续检查3次
    @Scheduled(cron = "0 40 14  ? * MON-FRI")
    public void autoSold(){
        logger.info("定时任务---自动卖出1");
        transService.autoSold("LIMIT",1);
    }
    //每天下午2点46,撤单自动卖出
//    @Scheduled(cron = "0 46 14 ? * MON-FRI")
    public void autoSold2(){
        logger.info("定时任务---自动卖出2");
        transService.autoSold("LIMIT",1);
    }
    //每天下午2点47,撤单自动卖出,跌停价卖出??
//    @Scheduled(cron = "0 47 14  ? * MON-FRI")
    public void autoSold3(){
        logger.info("定时任务---自动卖出3");
        transService.autoSold("LIMIT",1);
    }
    //每天下午2点48,检查卖出情况
    @Scheduled(cron = "0 23 15  ? * MON-FRI")
    public void checkSold(){
        logger.info("定时任务---检查尾盘结果");
    }
//    @Scheduled(cron = "* * * * * MON-FRI")
//    public void autoXintiao(){
//        logger.info("定时任务---自动下单");
////        transService.autoTrans(1);
//
//    }
}
