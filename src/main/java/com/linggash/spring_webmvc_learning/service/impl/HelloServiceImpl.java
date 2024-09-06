package com.linggash.spring_webmvc_learning.service.impl;

import com.linggash.spring_webmvc_learning.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        if (name == null) {
            return "Hello Guest";
        } else {
            return "Hello " + name;
        }
    }
}
