package anddd7.springboot.service;

import anddd7.springboot.bean.User;
import anddd7.springboot.common.CommonParameters;
import org.springframework.stereotype.Service;

/**
 * Created by AnDong on 2017/2/8.
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User selectUser(Long id) {
        int index = (int) (id % 10);
        User user = new User(id, "小" + CommonParameters.LAST_NAME[Math.abs(index)]);
        return user;
    }
}
