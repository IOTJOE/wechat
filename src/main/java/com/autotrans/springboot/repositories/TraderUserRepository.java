package com.autotrans.springboot.repositories;

import com.autotrans.springboot.models.TraderUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hello on 2016/9/12.
 */
@Transactional
public interface TraderUserRepository extends JpaRepository<TraderUser, Long> {

    TraderUser save(TraderUser traderUser);
//    @Query("select u from TraderUser st where  u.AutoBuyStatus= :autoBuyStatus and u.Status=1 ")
//    TraderUser getReceiverByWeixinAcct(@Param("autoBuyStatus") int autoBuyStatus);
//    @Query("select st from Receiver st where st.phone= :phone ")
//    Receiver getReceiverByPhone(@Param("phone") String phone);
}
