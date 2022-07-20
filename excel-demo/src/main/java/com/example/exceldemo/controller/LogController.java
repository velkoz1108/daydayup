package com.example.exceldemo.controller;

import com.example.exceldemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/log")
public class LogController {

    @Autowired
    private TestService testService;

    @GetMapping("/saveOrder")
    public String saveOrder(String oldAddress, String newAddress, String businessId, String bizType){
        return testService.saveOrder(oldAddress,newAddress,businessId,bizType);
    }

    @GetMapping("/newSaveOrder")
    public String newSaveOrder(String newAddress, String newPhone, String businessId){
        return testService.saveOrder(newAddress,newPhone,businessId);
    }

    @GetMapping("/call")
    public String call(String businessId, String bizType){
        return testService.call(businessId,bizType);
    }
}
