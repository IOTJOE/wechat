package com.autotrans.springboot.models;

import com.autotrans.springboot.bases.BaseModel;

import javax.persistence.*;

/**
 * Created by joe on 2017/8/29.
 */
@Entity
@Table(name="t_yh_match")
public class YHMatch extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",unique = true, nullable = false)
    private Long id;
    private int uniqueid;
    private String ordersno;
    private String fundid;
    private String symbol;
    private String exchange;
    private String side;
    private String matchprice;
    private String matchqty;
    private String matchdate;
    private String status;

    private String extraparam_mo;
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
