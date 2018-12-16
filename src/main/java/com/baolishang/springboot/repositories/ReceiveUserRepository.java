package com.baolishang.springboot.repositories;

import com.baolishang.springboot.models.ReceiveUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hello on 2016/9/12.
 */
@Transactional
public interface ReceiveUserRepository
        extends JpaRepository<ReceiveUser, Long>
{

    @Query(value="select u.id,r.Name,r.Phone,r.OpenId,mt.WeChatTemplateId,mt.ShortMessageId,mt.WechatTopColor,mt.Url,u.WeChatId from t_notice_user u ,t_local_message_template  lmt, t_receiver r,t_message_template mt   where u.LocalMessageTemplateId=lmt.id and lmt.code =?1 and lmt.status=1  and r.ID=u.UserId and r.Status=1 and u.WeChatId=mt.ID",nativeQuery=true)
    List<ReceiveUser> getReceiveUser(@Param("code") String code);
;
}
