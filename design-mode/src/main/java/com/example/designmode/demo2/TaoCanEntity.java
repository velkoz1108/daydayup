package com.example.designmode.demo2;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@Setter
@Getter
public class TaoCanEntity {

    /**
     * 套餐id
     * */
    private Long id;

    /**
     * 套餐名称
     * */
    private String name;

    /**
     * 备注
     * */
    private String remark;


}
