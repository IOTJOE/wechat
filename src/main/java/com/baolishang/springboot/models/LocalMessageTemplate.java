package com.baolishang.springboot.models;

import com.baolishang.springboot.bases.BaseModel;

import javax.persistence.*;

/**
 * Created by joe on 2017/8/29.
 */
@Entity
@Table(name="t_local_message_template")
public class LocalMessageTemplate extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",unique = true, nullable = false)
    private Long id;

    @Column(name="Code")
    private String code;

    @Column(name="Remark")
    private String remark;

    @Column(name="Status")
    private Byte status;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
