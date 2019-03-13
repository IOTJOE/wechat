package com.autotrans.springboot.models;

import com.autotrans.springboot.bases.BaseModel;

import javax.persistence.*;

/**
 * Created by joe on 2017/8/29.
 */
@Entity
@Table(name="t_yh_stock")
public class YHStock extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",unique = true, nullable = false)
    private Long id;
    private int uniqueid;
    private String fundid;
    private String symbol;
    private String exchange;
    private String stkavl;
    private String stkbal;
    private String costprice;
    private String curprice;

    private String extraparam_py;
    private String extraparam_by;
    private String extraparam_ba;
    private String extraparam_sy;
    private String extraparam_sa;
    private String extraparam_sc;
    private String extraparam_bi;
    private String extraparam_sd;
    private String extraparam_sl;

    private String extraparam_fz;
    private String extraparam_bz;
    private String extraparam_sz;
    private String extraparam_pr;
    private String extraparam_ml;
    private String extraparam_pl;
    private String extraparam_te;
    private String extraparam_al;
    private String extraparam_vl;
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
