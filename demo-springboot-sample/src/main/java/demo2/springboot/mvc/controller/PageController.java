package demo2.springboot.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/index")
    public String getIndex() {
        return "index";
    }

    @RequestMapping("/pages/{pageName}")
    public String getPages(@PathVariable String pageName) {
        return pageName;
    }
}
