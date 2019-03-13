package com.autotrans.springboot.models;

import com.autotrans.springboot.bases.BaseModel;

import javax.persistence.*;

/**
 * Created by joe on 2017/8/29.
 */
@Entity
@Table(name="t_yh_fund")
public class YHFund extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",unique = true, nullable = false)
    private Long id;
    private int uniqueid;
    private String fundid;
    private String currency;
    private String fundbal;
    private String fundavl;

    private String extraparam_fl;
    private String extraparam_ml;
    private String extraparam_sl;
    private String extraparam_fs;
    private String extraparam_ty;
    private String extraparam_ts;
    private String extraparam_ue;
    private String extraparam_fe;
    private String extraparam_se;
    private String extraparam_fv;
    private String extraparam_dv;
    private String extraparam_gl;
    private String extraparam_ms;
    private String extraparam_re;
    private String times;
    private String date;
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


}
