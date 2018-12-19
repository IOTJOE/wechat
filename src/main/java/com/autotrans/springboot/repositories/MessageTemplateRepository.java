package com.autotrans.springboot.repositories;

import com.autotrans.springboot.models.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hello on 2016/9/12.
 */
@Transactional
public interface MessageTemplateRepository
        extends JpaRepository<MessageTemplate, Long>
{

    @Query("select st from MessageTemplate st where st.name= :name ")
    MessageTemplate getMessageTemplate(@Param("name") String name);
//    @Query("select st from WeChatTemplateDetail st where st.WeChatTemplateID= :weChatTemplateId ")
//    List<WeChatTemplateDetail> getWeChatTemplateDetail(@Param("weChatTemplateId") Long weChatTemplateId);
}
