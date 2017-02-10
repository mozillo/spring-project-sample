package demo.springboot.service.interfaces;


import demo.springboot.dao.domain.User;

import java.util.List;

/**
 * Created by AnDong on 2016/11/20.
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    boolean insertUser(User user);

    /**
     * @param user
     * @return
     */
    boolean deleteUser(User user);

    /**
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 根据条件查询用户
     *
     * @param user
     * @return
     */
    List<User> queryUserByExample(User user);

    List<User> queryUserByCode(String code);

    void updateTestTx(int age) throws Exception;

}
