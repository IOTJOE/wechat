package com.baolishang.springboot.repositories;

import com.baolishang.springboot.models.TraderUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hello on 2016/9/12.
 */
@Transactional
public interface TraderUserRepository extends JpaRepository<TraderUser, Long> {

    TraderUser save(TraderUser traderUser);
//    @Query("select st from Receiver st where st.openId= :openId ")
//    Receiver getReceiverByWeixinAcct(@Param("openId") String openId);
//    @Query("select st from Receiver st where st.phone= :phone ")
//    Receiver getReceiverByPhone(@Param("phone") String phone);
}
