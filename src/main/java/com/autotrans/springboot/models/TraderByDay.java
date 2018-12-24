package com.autotrans.springboot.models;

import com.autotrans.springboot.bases.BaseModel;

import javax.persistence.*;

/**
 * Created by joe on 2017/8/29.
 */
@Entity
@Table(name="t_trader_by_day")
public class TraderByDay extends BaseModel {
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
    @Column(name="Price")
    private String price;
    @Column(name="Amount")
    private Integer amount;
    @Column(name="Direction")
    private Integer direction;
    @Column(name="PriceType")
    private Integer priceType;
    @Column(name="MarketType")
    private Integer marketType;
    @Column(name="Status")
    private Integer status;
    @Column(name="SuccessAmount")
    private Integer successAmount;
    @Column(name="RevokeAmount")
    private Integer revokeAmount;
    @Column(name="TransId")
    private String transId;
    @Column(name="Source")
    private String source;
    @Column(name="Message")
    private String message;
    @Column(name="Des")
    private String des;
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

    public TraderByDay setUserAccount(String userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public String getTransDate() {
        return transDate;
    }

    public TraderByDay setTransDate(String transDate) {
        this.transDate = transDate;
        return this;
    }

    public String getStockId() {
        return stockId;
    }

    public TraderByDay setStockId(String stockId) {
        this.stockId = stockId;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public TraderByDay setPrice(String price) {
        this.price = price;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public TraderByDay setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Integer getDirection() {
        return direction;
    }

    public TraderByDay setDirection(Integer direction) {
        this.direction = direction;
        return this;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public TraderByDay setPriceType(Integer priceType) {
        this.priceType = priceType;
        return this;
    }

    public Integer getMarketType() {
        return marketType;
    }

    public TraderByDay setMarketType(Integer marketType) {
        this.marketType = marketType;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public TraderByDay setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getSuccessAmount() {
        return successAmount;
    }

    public TraderByDay setSuccessAmount(Integer successAmount) {
        this.successAmount = successAmount;
        return this;
    }

    public Integer getRevokeAmount() {
        return revokeAmount;
    }

    public TraderByDay setRevokeAmount(Integer revokeAmount) {
        this.revokeAmount = revokeAmount;
        return this;
    }

    public String getTransId() {
        return transId;
    }

    public TraderByDay setTransId(String transId) {
        this.transId = transId;
        return this;
    }

    public String getSource() {
        return source;
    }

    public TraderByDay setSource(String source) {
        this.source = source;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public TraderByDay setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDes() {
        return des;
    }

    public TraderByDay setDes(String des) {
        this.des = des;
        return this;
    }

    @Override
    public String toString() {
        return "TraderByDay{" +
                "id=" + id +
                ", userAccount='" + userAccount + '\'' +
                ", transDate='" + transDate + '\'' +
                ", stockId='" + stockId + '\'' +
                ", price='" + price + '\'' +
                ", amount=" + amount +
                ", direction=" + direction +
                ", priceType=" + priceType +
                ", marketType=" + marketType +
                ", status=" + status +
                ", successAmount=" + successAmount +
                ", revokeAmount=" + revokeAmount +
                ", transId='" + transId + '\'' +
                ", source='" + source + '\'' +
                ", message='" + message + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
