package com.autotrans.springboot.models;

import com.autotrans.springboot.bases.BaseModel;

import javax.persistence.*;

/**
 * Created by joe on 2017/8/29.
 */
@Entity
@Table(name="t_yh_parentorder_ans")
public class YHParentorderAns extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",unique = true, nullable = false)
    private Long id;
    private int uniqueid;
    private String ansid;
    private String parentid;
    private String comboid;
    private String errcode;
    private String errmsg;
    private String extraparam;
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
