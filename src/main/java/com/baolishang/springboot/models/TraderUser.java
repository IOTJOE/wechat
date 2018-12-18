package com.baolishang.springboot.models;

import com.baolishang.springboot.bases.BaseModel;

import javax.persistence.*;

/**
 * Created by joe on 2017/8/29.
 */
@Entity
@Table(name="t_trader_user")
public class TraderUser extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",unique = true, nullable = false)
    private Long id;
    @Column(name="Name")
    private String name;
    @Column(name="Broker")
    private String broker;
    @Column(name="UserAccount")
    private String userAccount;
    @Column(name="FileName")
    private String fileName;
    @Column(name="ServerIp")
    private String serverIp;
    @Column(name="AvailableAmount")
    private String availableAmount;
    @Column(name="TotalAmount")
    private String totalAmount;
    @Column(name="TransIp")
    private String transIp;
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

    public TraderUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getBroker() {
        return broker;
    }

    public TraderUser setBroker(String broker) {
        this.broker = broker;
        return this;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public TraderUser setUserAccount(String userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public TraderUser setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getServerIp() {
        return serverIp;
    }

    public TraderUser setServerIp(String serverIp) {
        this.serverIp = serverIp;
        return this;
    }

    public String getAvailableAmount() {
        return availableAmount;
    }

    public TraderUser setAvailableAmount(String availableAmount) {
        this.availableAmount = availableAmount;
        return this;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public TraderUser setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public String getTransIp() {
        return transIp;
    }

    public TraderUser setTransIp(String transIp) {
        this.transIp = transIp;
        return this;
    }
}
