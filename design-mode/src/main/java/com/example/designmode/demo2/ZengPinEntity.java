package com.example.designmode.demo2;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@Setter
@Getter
public class ZengPinEntity {

    /**
     * 赠品id
     * */
    private Long id;


    /**
     * 赠品code
     * */
    private String code;

    /**
     * 赠品名称
     * */
    private String name;

    /**
     * 赠品备注
     * */
    private String remark;
}
