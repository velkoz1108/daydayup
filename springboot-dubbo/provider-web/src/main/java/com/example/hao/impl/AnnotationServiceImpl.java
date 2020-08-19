package com.example.hao.impl;


import org.apache.dubbo.config.annotation.Service;
import org.example.hao.AnnotationService;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceName = "annotationService")
public class AnnotationServiceImpl implements AnnotationService {
    @Override
    public String sayHello(String name) {
        return "annotation: hello, " + name;
    }
}
