package com.autotrans.springboot.repositories;

import com.autotrans.springboot.models.TraderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hello on 2016/9/12.
 */
@Transactional
public interface TraderLogRepository extends JpaRepository<TraderLog, Long> {

    TraderLog save(TraderLog traderLog);
    @Query("select st from TraderLog st where st.userAccount= :userAccount and st.transDate= :transDate")
    List<TraderLog> selectByUserAccountAndTransDate(@Param("userAccount") String userAccount,@Param("transDate") String transDate);
    @Query("update TraderLog st set st.status= :status where st.transId= :transId")
    void updateStatus(@Param("status")String status,@Param("transId") String transId);
}
