package demo2.springboot.mvc.controller;

import demo2.springboot.mvc.domain.UserBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest")
public class DemoRestController {

    @RequestMapping(value = "/getJson", produces = "application/json;charset=UTF-8")
    public UserBean getJson(HttpServletRequest request) {
        return new UserBean(2, "我是JSON");
    }

    @RequestMapping(value = "/getXml", produces = "application/xml;charset=UTF-8")
    public UserBean getXml(HttpServletRequest request) {
        return new UserBean(5, "我是XML");
    }

}
