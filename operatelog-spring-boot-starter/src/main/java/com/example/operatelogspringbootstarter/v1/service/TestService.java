package com.example.operatelogspringbootstarter.v1.service;

public interface TestService {

    String saveOrder(String oldAddress, String newAddress, String businessId, String bizType);

    String call(String businessId, String bizType);
}
