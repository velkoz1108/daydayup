package com.example.hao.impl;

import org.apache.dubbo.config.annotation.Service;
import org.example.hao.IQueryService;
import org.springframework.stereotype.Component;

@org.springframework.stereotype.Service
@Service(interfaceName = "queryService")
public class QueryServiceImpl implements IQueryService {
    @Override
    public String queryPayList() {
        return null;
    }
}
