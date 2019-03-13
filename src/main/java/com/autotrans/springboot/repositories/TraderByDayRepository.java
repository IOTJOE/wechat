package com.autotrans.springboot.repositories;

import com.autotrans.springboot.models.TraderByDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    @Query("select st from TraderByDay st where st.userAccount= :userAccount and st.transDate >= :transDate ")
    List<TraderByDay> selectByUserAccountAndTransDate(@Param("userAccount") String userAccount,@Param("transDate") String transDate);
    @Modifying
    @Query("update  TraderByDay st set st.status= :status, st.successAmount= :successAmount,st.revokeAmount= :revokeAmount,st.transId = :transId,st.source= :source,st.message= :message,st.des = :des  where st.id= :id ")
    int update(@Param("status") int status, @Param("successAmount") int  successAmount,@Param("revokeAmount") int  revokeAmount,@Param("transId") String transId,@Param("source") String source,@Param("message") String message,@Param("des") String des,@Param("id") long id);
    //    @Query("select st from Receiver st where st.phone= :phone ")
//    Receiver getReceiverByPhone(@Param("phone") String phone);
}
