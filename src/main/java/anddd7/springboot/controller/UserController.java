package anddd7.springboot.controller;

import anddd7.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by AnDong on 2017/2/8.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/welcome")
    @ResponseBody
    String welcomeUser(Long id) {
        return "Welcome , " + userService.selectUser(id).getName() + " !";
    }

}
