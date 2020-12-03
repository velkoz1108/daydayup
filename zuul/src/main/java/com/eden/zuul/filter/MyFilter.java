package com.eden.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        System.out.println("MyFilter  filterType ->  " + FilterConstants.PRE_TYPE);
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("MyFilter  run ->  " + System.currentTimeMillis());
        return null;
    }
}
