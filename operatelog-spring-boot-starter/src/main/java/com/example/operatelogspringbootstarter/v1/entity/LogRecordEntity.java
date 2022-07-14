package com.example.operatelogspringbootstarter.v1.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class LogRecordEntity {

    /*单据id*/
    private String businessId;

    /*业务分类*/
    private String bizType;

    /*日志内容*/
    private String context;

    /*操作人Id*/
    private String operatorId;

    /*操作人姓名*/
    private String operatorName;

    /*调用的接口方法名*/
    private String interfaceName;

    /*调用的服务名*/
    private String applicationName;



}
