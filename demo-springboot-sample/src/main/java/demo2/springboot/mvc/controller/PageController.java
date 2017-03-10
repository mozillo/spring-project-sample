package demo2.springboot.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/upload")
    public String upload() {
        return "upload";
    }

}
