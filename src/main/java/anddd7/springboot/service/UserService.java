package anddd7.springboot.service;

import anddd7.springboot.bean.User;

/**
 * Created by AnDong on 2017/2/8.
 */
public interface UserService {
    User selectUser(Long id);
}
