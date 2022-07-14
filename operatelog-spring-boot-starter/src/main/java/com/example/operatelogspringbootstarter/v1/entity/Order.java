package com.example.operatelogspringbootstarter.v1.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order {

    private String code;

    private String purchaseName;
}
