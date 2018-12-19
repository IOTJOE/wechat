package com.autotrans.springboot.repositories;

import com.autotrans.springboot.models.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hello on 2016/9/12.
 */
@Transactional
public interface ReceiverRepository extends JpaRepository<Receiver, Long> {

    Receiver save(Receiver seller);
    @Query("select st from Receiver st where st.openId= :openId ")
    Receiver getReceiverByWeixinAcct(@Param("openId") String openId);
    @Query("select st from Receiver st where st.phone= :phone ")
    Receiver getReceiverByPhone(@Param("phone") String phone);
}
