## SpringMvc 快速搭建

* 依赖 : 这里直接使用SpringBoot的快速搭建
```xml
<!-- 包含常用的web/mvc等依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
```xml
<!-- 用于管理spring相关的依赖版本 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>${spring-boot.version}</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```
* 日志 : Spring4推荐使用logback
> 简历一个logback.xml文件进行日志配置 ,内容与log4j差不多

* 页面 : SpringBoot习惯把页面放置在resources下面

## 快速配置
用SpringBoot(注解风格)配置代替web.xml和spring-mvc.xml

* web.xml
```java
/**
 * @WebApplicationInitializer 用来配置Servlet3.0的接口 ,也就代替了web.xml ,里面配置的内容和xml配置基本一致 ,部署在tomcat时容器会自动寻找并加载这个实现
 * <p>
 * SpringBoot方式启动的话 ,可以通过配置类(类里定义servlet bean ,然后import到Application)
 */
public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MvcConfig.class);
        context.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
```
* SpringMvc
```java
@Configuration
@EnableWebMvc //开启一些默认配置MessageConverters,ViewResolvers等
@ComponentScan("demo2.springboot.mvc")
public class MvcConfig {
    /**
     * 注册视图转换器 ,为mvc返回的页面路径添加前后缀
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
}
```

## 页面和Controller
```java
@Controller
public class HelloController {

    @RequestMapping("/index")
    public String hello() {
        System.out.println("进入controller");
        return "index";
    }
}
```
省略`index.jsp`

## 部署
SpringBoot(-web)有自带的Tomcat ,先排除再关联Servlet3.0需要的包
用Maven打包成war ,部署在tomcat即可
```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
 </dependencies>
```

## SpringMvc常用注解
* `@Controller` 声明控制器Bean ,容器的DispatcherServlet会把控制器和url绑定
* `@RequestMapping` 指定访问路径
* `@ResponseBody` 支持返回值放在response内 ,用于AJAX返回数据而非页面
* `@RequestBody` 允许参数在request内 ,通常处理POST体
* `@PathVarible` 接受路径中的参数 ,例如: `/user/add/10002` -> ( `/user/add/{id}` ) -> id=10002
* `@RestController` 等效 `@Controller + @ResponseBody`


