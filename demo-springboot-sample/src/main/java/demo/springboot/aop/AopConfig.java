package demo.springboot.aop;

import demo.springboot.aop.service.DemoAnnotationAopService;
import demo.springboot.aop.service.DemoMethodAopService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Aop的配置
 */
@Configuration
@ComponentScan("demo.springboot.aop")
@EnableAspectJAutoProxy//开启Spring对AspectJ的支持
public class AopConfig {
    /**
     * 测试两种Aop
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        DemoAnnotationAopService annotationAopService = context.getBean(DemoAnnotationAopService.class);
        DemoMethodAopService methodAopService = context.getBean(DemoMethodAopService.class);

        System.out.println(annotationAopService.say());
        System.out.println(methodAopService.say());
        context.close();
    }
}
