package demo.springboot.annotation;

import demo.springboot.annotation.service.DemoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@CommonConfiguration("demo.springboot.annotation")
public class DemoConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        DemoService demoService = context.getBean(DemoService.class);
        demoService.output();
    }
}
