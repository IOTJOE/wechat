package com.autotrans.springboot.models;

import com.autotrans.springboot.bases.BaseModel;

import javax.persistence.*;

/**
 * Created by joe on 2017/8/29.
 */
@Entity
@Table(name="t_yh_order_rec")
public class YHOrderRec extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",unique = true, nullable = false)
    private Long id;
    private int uniqueid;
    private String parentid;
    private String comboid;
    private String fundid;
    private String symbol;
    private String exchange;
    private String side;
    private String orderqty;

    private String orderprice;
    private String ordertype;
    private String ordersno;
    private String cancelordersno;
    private String extraparam_oc;
    private String extraparam_ec;
    private String extraparam_em;
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
