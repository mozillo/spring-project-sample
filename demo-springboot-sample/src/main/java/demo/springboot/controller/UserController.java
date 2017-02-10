package demo.springboot.controller;

import demo.springboot.common.CommonParams;
import demo.springboot.controller.bean.Result4UI;
import demo.springboot.dao.domain.User;
import demo.springboot.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by AnDong on 2016/11/30.
 */
@Controller
public class UserController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    HttpSession httpSession;

    /**
     * StringHttpMessageConverter 是 <mvc:annotation-driven />触发的默认的字符串转换工作类。
     * 比较不幸的是，StringHttpMessageConverter所使用的默认字符集是ISO-8859-1
     * <p>
     * 1.替换默认的StringHttpMessageConverter ,在xml中自定义
     * 2.request指定返回的数据形式和编码
     */

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String login(String code, String paasword) {

        List<User> userList = userService.queryUserByCode(code);
        if (userList.size() > 0) return Result4UI.toFailedJsonString("登录失败,账号未注册!");

        User target = userList.get(0);
        if (!paasword.equals(target.getPasswd())) return Result4UI.toFailedJsonString("登录失败,密码错误!");

        httpSession.setAttribute(CommonParams.USER_SESSION_KEY, target);

        return Result4UI.toSuccessJsonString("登陆成功", target);
    }

    /**
     * GET（SELECT）：从服务器取出资源（一项或多项）。
     * POST（CREATE）：在服务器新建一个资源。
     * PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）。
     * PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）。
     * DELETE（DELETE）：从服务器删除资源。
     */

    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String register(@RequestBody User userInfo) {
        userService.insertUser(userInfo);
        return Result4UI.toSuccessJsonString("注册成功");
    }

    @RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(@PathVariable("id") int id) {
        return "";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(@RequestBody User userInfo) {
        boolean result = userService.updateUser(userInfo);
        return result ? Result4UI.toSuccessJsonString() : Result4UI.toFailedJsonString();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String query(User user) {
        List<User> target = userService.queryUserByExample(user);
        return Result4UI.toSuccessJsonString(String.format("查询成功,共有%d条记录", target.size()), target);
    }


    @RequestMapping(value = "/isCodeUsed", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String isCodeUsed(String code) {
        User cond = new User();
        cond.setCode(code);

        List<User> target = userService.queryUserByCode(code);
        if (target.size() > 0) {
            return Result4UI.toFailedJsonString("用户名已注册 ,请重新输入!", true);
        }
        return Result4UI.toSuccessJsonString("可以注册", false);
    }


    @RequestMapping(value = "/testTx", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String testTx(int age) {
        try {
            userService.updateTestTx(age);
        } catch (Exception e) {

        }
        return Result4UI.toSuccessJsonString("可以注册", false);
    }

}
