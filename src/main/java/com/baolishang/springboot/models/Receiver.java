package com.baolishang.springboot.models;

import com.baolishang.springboot.bases.BaseModel;

import javax.persistence.*;

/**
 * Created by joe on 2017/8/29.
 */
@Entity
@Table(name="t_receiver")
public class Receiver extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",unique = true, nullable = false)
    private Long id;


    @Column(name="Name")
    private String name;

    @Column(name="Phone")
    private String phone;

    @Column(name="OpenId")
    private String openId;

    @Column(name="Status")
    private boolean status;

    public Receiver() {
    }

    public Receiver(String name, String phone, String openId, boolean status) {
        this.name = name;
        this.phone = phone;
        this.openId = openId;
        this.status = status;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", openId='" + openId + '\'' +
                ", status=" + status +
                '}';
    }
}
