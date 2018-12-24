package com.autotrans.springboot.models;

import com.autotrans.springboot.bases.BaseModel;

import javax.persistence.*;

/**
 * Created by joe on 2017/8/29.
 */
@Entity
@Table(name="t_trader_log")
public class TraderLog extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",unique = true, nullable = false)
    private Long id;
    @Column(name="UserAccount")
    private String userAccount;
    @Column(name="TransDate")
    private String transDate;
    @Column(name="StockId")
    private String stockId;
    @Column(name="Action")
    private String action;
    @Column(name="Type")
    private String type;
    @Column(name="PriceType")
    private Integer priceType;
    @Column(name="Price")
    private String price;
    @Column(name="Amount")
    private Integer amount;
    @Column(name="SuccessAmount")
    private Integer successAmount;
    @Column(name="RevokeAmount")
    private Integer revokeAmount;
    @Column(name="TransId")
    private String transId;
    @Column(name="Direction")
    private Integer direction;
    @Column(name="Source")
    private String source;
    @Column(name="MarketType")
    private Integer marketType;
    @Column(name="Message")
    private String message;
    @Column(name="Status")
    private String status;

    @Column(name="DayId")
    private long dayId;
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserAccount() {
        return userAccount;
    }

    public Integer getDirection() {
        return direction;
    }

    public TraderLog setDirection(Integer direction) {
        this.direction = direction;
        return this;
    }

    public TraderLog setUserAccount(String userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public String getTransDate() {
        return transDate;
    }

    public TraderLog setTransDate(String transDate) {
        this.transDate = transDate;
        return this;
    }

    public String getStockId() {
        return stockId;
    }

    public String getType() {
        return type;
    }

    public TraderLog setType(String type) {
        this.type = type;
        return this;
    }

    public TraderLog setStockId(String stockId) {
        this.stockId = stockId;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public TraderLog setPrice(String price) {
        this.price = price;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public TraderLog setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public String getAction() {
        return action;
    }

    public TraderLog setAction(String action) {
        this.action = action;
        return this;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public TraderLog setPriceType(Integer priceType) {
        this.priceType = priceType;
        return this;
    }

    public Integer getMarketType() {
        return marketType;
    }

    public TraderLog setMarketType(Integer marketType) {
        this.marketType = marketType;
        return this;
    }

    public String getTransId() {
        return transId;
    }

    public TraderLog setTransId(String transId) {
        this.transId = transId;
        return this;
    }

    public String getSource() {
        return source;
    }

    public TraderLog setSource(String source) {
        this.source = source;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public TraderLog setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TraderLog setStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getSuccessAmount() {
        return successAmount;
    }

    public TraderLog setSuccessAmount(Integer successAmount) {
        this.successAmount = successAmount;
        return this;
    }

    public Integer getRevokeAmount() {
        return revokeAmount;
    }

    public TraderLog setRevokeAmount(Integer revokeAmount) {
        this.revokeAmount = revokeAmount;
        return this;
    }

    public long getDayId() {
        return dayId;
    }

    public TraderLog setDayId(long dayId) {
        this.dayId = dayId;
        return this;
    }
}
