package com.autotrans.springboot.repositories;

import com.autotrans.springboot.models.TraderByDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hello on 2016/9/12.
 */
@Transactional
public interface TraderByDayRepository extends JpaRepository<TraderByDay, Long> {

    TraderByDay save(TraderByDay traderByDay);
//    @Query("select st from Receiver st where st.openId= :openId ")
//    Receiver getReceiverByWeixinAcct(@Param("openId") String openId);
    @Query("select st from TraderByDay st where st.userAccount= :userAccount and st.transDate= :transDate ")
    List<TraderByDay> selectByUserAccountAndTransDate(@Param("userAccount") String userAccount,@Param("transDate") String transDate);
//    @Query("select st from Receiver st where st.phone= :phone ")
//    Receiver getReceiverByPhone(@Param("phone") String phone);
}
