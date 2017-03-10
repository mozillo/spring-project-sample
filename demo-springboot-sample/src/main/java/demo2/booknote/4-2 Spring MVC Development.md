## 文件上传

* 引入commons-fileupload包
* MVC配置对媒体资源的处理
* Controller处理 ,保存收到的文件
```java
public class MvcConfig extends WebMvcConfigurerAdapter {
    
    //...
    
    /**
     * 对multipart类型 (文件)的默认处理设置
     * 由commons-upload实现 需要引入
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }
}

@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(MultipartFile file) {
        try {
            File newFile = new File("D:/upload/" + file.getOriginalFilename());
            FileUtils.writeByteArrayToFile(newFile, file.getBytes());
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败 : " + e.getMessage();
        }
    }
}
```

## MessageCovertor

* Spring内置了很多 ,默认Jackson
* 如果需要自定义 ,继承 AbstractHttpMessageCovertor

## 服务端推送

* Ajax心跳 : 频率不好控制 ,服务器压力
* WebSocket
* 异步等待 ,服务器抓住请求 ,等待推送时再返回(Server Send Event)
```java

```




