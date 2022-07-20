package com.example.operatelogspringbootstarter.v2.service;

public interface TestService {

    String saveOrder(String newAddress, String newPhone, String businessId);

    String saveOrder(String oldAddress, String newAddress, String businessId, String bizType);

    String call(String businessId, String bizType);
}
