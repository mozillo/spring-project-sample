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

