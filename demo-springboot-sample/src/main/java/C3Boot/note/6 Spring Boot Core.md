## SpringBoot 核心

###  基本配置
* @SpringBootApplication：核心注解，组合了常用的三大注解
    * @Configuration：通过@ImportResource引入xml文件,@Value读取键值 ,@Bean读取配置的bean实例
    * @ComponentScan：同xml中的自动扫描组件
    * @EnableAutoConfiguration：下包含了一系列自动配置类的清单 ,并按顺序执行 ,在Spring3时代就有@EnableWebMvc注解
    * 通过注解的exclude属性，可以关闭特定的配置
* SpringApplication是内置容器的启动器
    * 定制Banner：在resources下新建banner.txt，里面放上显示的banner字符图案
    * 关闭Banner：获取SpringApplication对象，有一些设置函数
* 默认配置文件application.yaml：可以自定义设置Tomcat容器和Spring
* 通过stater引入其他模块
* @ImportResource：引入必要的xml或其他类型的配置文件

### 外部配置
* 命令行：java -jar xx.jar --server.port=9090（等效于加在application.yaml里）
* 常规配置：application.yaml里添加属性，入口类（配置类）中使用@Value可以直接引入
* 类型安全配置：通过@ConfigurationProperties的prefix（属性前缀）、locations（配置文件位置），可以批量引入配置到类中
