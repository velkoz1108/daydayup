package com.example.exceldemo.service;

public interface TestService {

    String saveOrder(String newAddress, String newPhone, String businessId);

    String saveOrder(String oldAddress, String newAddress, String businessId, String bizType);

    String call(String businessId, String bizType);
}
