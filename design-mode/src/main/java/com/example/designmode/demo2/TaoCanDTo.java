package com.example.designmode.demo2;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TaoCanDTo extends TaoCanEntity{

    private List<ZengPinEntity> zengPinEntityList;
}
