package demo.springboot.aop.impl;

import org.springframework.stereotype.Service;

@Service
public class DemoMethodAopService {
    public String say() {
        return "Hello, Normal World.";
    }
}
