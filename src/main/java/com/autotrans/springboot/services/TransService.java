package com.autotrans.springboot.services;

public interface TransService {
    void autoTrans(int transType);
    void autoSold(String type,int transType);
}
