package demo.springboot.annotation.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {
    public void output() {
        System.out.println("hello");
    }
}
