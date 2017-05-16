package C3Boot.code.controller;

import C3Boot.code.config.AuthorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class RestfulController {
    @Autowired
    AuthorSettings authorSettings;

    @RequestMapping("/author")
    public String getAuthorInfo() {
        return "author name is " + authorSettings.getName() + " and qq is " + authorSettings.getQq();
    }
}
