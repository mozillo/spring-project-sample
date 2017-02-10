## 概述

###Spring配置方式
* 配置文件`xml`（Spring 1.x） 》》 
* 注解`@annotation`（Spring 2.x+Jdk1.5）》》
* Java配置（Spring 3/4）

**`SpringBoot`就是基于Java配置（注解的应用）**

###Spring组件
* 核心组件（Core）
    * -Core：核心工具类，Spring其他模块的主要依赖
    * -Bean：对`bean`的支持
    * -Context：运行时容器
    * -Context-Support：容器对第三方包的集成支持
    * -Expression：使用表达式语言查询操作运行时的对象
* AOP
    * -AOP：基于代理的AOP支持
    * -Aspects：基于AspectJ的AOP支持
* Messaging
    * -Messaging：对消息架构和协议的支持
* Web
    * -Web：基础的集成功能，为Web项目提供Spring容器
    * -Webmvc：基于Servlet的Spring MVC
    * -WebSocket：提供WebSocket功能
    * -Webmvc-Portlet：支持Portlet环境
* Data Access/Integration
    * -JDBC：以JDBC方式访问数据库
    * -TX：事务支持
    * -ORM：对象关系映射支持
    * -OXM：对象xml映射支持
    * -JMS：对JMS的支持

### Spring生态圈（独立项目）
* -Boot：使用默认配置快速开发
* -XD：简化大数据应用开发
* -Cloud：分布式系统开发工具集
* -Data：对主流关系型/NoSql数据库的支持
* -Integration：通过消息机制对企业集成模式的支持
* -Batch：简化优化大数据批处理
* -Security：认证授权
* -Social：与社交网络API的继承
* -Mobile：对手机设备的识别
* -Web Services：基于协议有限的SOAP/Web服务
* -Session：提供API管理用户会话
* ...

### Spring原则
* 使用POJO进行轻量级和最小侵入式开发
* 通过依赖注入和基于接口编程降低耦合
* 通过AOP和默认习惯进行声明式编程
* 使用AOP和模板减少模式化（重复的）代码

### 依赖注入 （IOC+DI）
即由Spring容器创建管理Bean（POJO类），通过注解/XML/JAVA告诉容器应该将Bean何时放在何地。

声明Bean：
* @Component 通用
* @Service 在业务逻辑层使用
* @Repository 数据访问层使用
* @Controller 展现层使用

引入Bean：
* @Autowired Spring提供的注解
* @Inject/@Resource JSR-330/JSR-250提供的注解

### JAVA配置
@Configuration 表示这个类是一个配置类，等效于一个xml文件
@Bean 注解一个方法，将方法返回值注册为一个bean

* 配置注解化
```xml
<context:component-scan base-package="com.controller"></context:component-scan>
```
```java
@ComponentScan("com.controller") 
```

* 配置Java化
```xml
<bean class="com.util.MySwaggerConfig"/>
```
```
@Configuration
public class JavaConfig｛
    @Bean
    public MySwaggerConfig mySwaggerConfig(){
        return new MySwaggerConfig();
    } 
｝
```

* 配置AOP





