package com.baolishang.springboot.repositories;

import com.baolishang.springboot.models.WeChatTemplateDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hello on 2016/9/12.
 */
@Transactional
public interface WeChatTemplateDetailRepository
        extends JpaRepository<WeChatTemplateDetail, Long>
{

    @Query(value="select * from t_wechat_template_detail where WeChatTemplateID= ?1 ",nativeQuery=true)
    List<WeChatTemplateDetail> getWeChatTemplateDetail(@Param("weChatTemplateID") Long weChatTemplateID);
//    @Query("select st from WeChatTemplateDetail st where st.WeChatTemplateID= :weChatTemplateId ")
//    List<WeChatTemplateDetail> getWeChatTemplateDetail(@Param("weChatTemplateId") Long weChatTemplateId);
}
