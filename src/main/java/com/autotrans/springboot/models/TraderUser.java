package com.autotrans.springboot.models;

import com.autotrans.springboot.bases.BaseModel;

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
    @Column(name="Status")
    private int status;
    @Column(name="AutoBuyStatus")
    private int autoBuyStatus;
    @Column(name="AutoSoldStatus")
    private int autoSoldStatus;
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

    public int getStatus() {
        return status;
    }

    public TraderUser setStatus(int status) {
        this.status = status;
        return this;
    }

    public int getAutoBuyStatus() {
        return autoBuyStatus;
    }

    public TraderUser setAutoBuyStatus(int autoBuyStatus) {
        this.autoBuyStatus = autoBuyStatus;
        return this;
    }

    public int getAutoSoldStatus() {
        return autoSoldStatus;
    }

    public TraderUser setAutoSoldStatus(int autoSoldStatus) {
        this.autoSoldStatus = autoSoldStatus;
        return this;
    }
}
