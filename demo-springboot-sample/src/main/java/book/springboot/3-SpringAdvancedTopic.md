## Spring Aware
通常@Component标识的Bean由容器所管理，但它自身是不能和容器交互的（解耦），你只能通过@Autowired引入并使用。而Aware接口为Bean提供了与容器交互的能力。
* BeanNameAware：获取容器中Bean的名称
* BeanFactoryAware：获取当前Bean的Factory，从而调用容器的服务
* MessagerSourceAware：获取文本信息
* ApplicationEventPublisherAware：应用事件发布器，也可以通过AbstractApplicationContext的publish方法发布
* ResourceLoaderAware：获取资源加载器，可以获取外部资源
* ApplicationContextAware：获取上下文，包含了MessagerSource、EventPublisher、ResourceLoader

```java
@Service
public class AwareService implements BeanNameAware, ResourceLoaderAware {
    String beanName;
    ResourceLoader resourceLoader;

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void outputInfo() {
        System.out.println("bean在容器中的名称是："+beanName);
        Resource resource = resourceLoader.getResource("classpath:aware/test.txt");
        try {
            System.out.println("资源文件的内容是："+IOUtils.toString(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## 多线程
* 注解需要异步执行(多线程)的方法
```java
@Service
@Async //注解表明该方法是一个异步方法,注解在类上则表示类中所有方法都是异步的.
public class AsyncTaskService {
    public void executeAsyncTask(Integer i) {
        System.out.println("执行异步任务：" + i);
    }

    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("执行异步任务+1：" + (i + 1));
    }
}
```
* 开启异步执行的方法,并定义异步执行器
```java
@Configuration
@ComponentScan("demo.springboot.taskexecutor")
@EnableAsync //使用@EnableAsync开启异步任务,实现异步配置类返回一个基于线程池的执行器
public class ExecutorConfig implements AsyncConfigurer {
    //重写替换系统获取的执行器
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExecutorConfig.class);
        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
        /**
         * 调用异步方法时,Spring会自动获取内部的Async执行器来执行
         * 执行的结果是异步的(通过多线程执行),而不是0-9顺序的执行
         */
        for (int i=0;i<10;i++){
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
        context.close();
    }
}
```
**结果展示**
```
执行异步任务+1：1
执行异步任务：1
执行异步任务+1：2
执行异步任务：3
执行异步任务+1：3
执行异步任务：0
执行异步任务：2
执行异步任务：5
执行异步任务+1：5
执行异步任务：4
执行异步任务+1：4
执行异步任务：7
执行异步任务+1：7
执行异步任务：6
执行异步任务+1：6
执行异步任务：9
执行异步任务+1：9
执行异步任务：8
执行异步任务+1：8
执行异步任务+1：10
```

## 计划任务
现在使用注解就可以使用Spring的计划任务模块, 代替之前复杂繁琐的quartz整合
```java
@Service
public class SchedulerService {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){System.out.println("每5秒执行,当前时间:"+sdf.format(new Date()));}

    @Scheduled(cron = "0 08 17 ? * *")
    public void fixedTimeExecution(){System.out.println("定时执行:"+sdf.format(new Date()));}
}
```
```java
@Configuration
@ComponentScan("demo.springboot.taskscheduler")
@EnableScheduling //注解开启计划任务
public class SchedulerConfig {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SchedulerConfig.class);
    }
}
```

## 条件注解
根据条件操控Bean, 比profile更通用, 可以定义在程序内方便理解
* 设置条件, 实现条件实现的函数
```java
public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("os.name").contains("Windows");
    }
}

public class LinuxCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("os.name").contains("Linux");
    }
}
```
* 设置Bean的不同实现
```java
public class WindowsServiceImpl implements ListService {
    @Override
    public String showListCmd() { return "dir"; }
}
public class LinuxServiceImpl implements ListService {
    @Override
    public String showListCmd() { return "ls"; }
}
```
* 设置Bean的加载条件
```java
@Configuration
public class ConditionalConfig {

    @Bean
    @Conditional(WindowsCondition.class)
    public ListService windowsList() { return new WindowsServiceImpl(); }

    @Bean
    @Conditional(LinuxCondition.class)
    public ListService linuxList() { return new LinuxServiceImpl(); }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionalConfig.class);
        ListService listService = context.getBean(ListService.class);
        System.out.println("查看系统列表的命令是:" + listService.showListCmd());
    }
}
```

## 组合注解和元注解