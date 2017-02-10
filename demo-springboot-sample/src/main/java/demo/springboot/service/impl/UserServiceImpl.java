package demo.springboot.service.impl;


import demo.springboot.dao.domain.User;
import demo.springboot.dao.mapper.UserMapper;
import demo.springboot.exception.Exception4UI;
import demo.springboot.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by AnDong on 2016/11/20.
 */
@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserMapper userMapper;


    private final int default_isAdmin = 0;//默认创建非管理员用户
    private final int default_isEffective = 1;//默认用户注册即有效

    @Override
    public boolean insertUser(User user) {
        user.setCreateTime(new Date(System.currentTimeMillis()));
        user.setIsAdmin(default_isAdmin);
        user.setIsEffective(default_isEffective);
        int result = userMapper.insert(user);
        return result == 1 ? true : false;
    }

    @Override
    public boolean deleteUser(User user) {
        int result = userMapper.deleteByPrimaryKey(user.getId());
        return result == 1 ? true : false;
    }

    @Override
    public boolean updateUser(User user) {
        int result = userMapper.updateByPrimaryKey(user);
        return result == 1 ? true : false;
    }

    @Override
    public List<User> queryUserByExample(User user) {
        return userMapper.selectByExample(user);
    }

    @Override
    public List<User> queryUserByCode(String code) {
        User cond = new User();
        cond.setCode(code);
        return queryUserByExample(cond);
    }


    /**
     * 测试回滚
     */
    //@Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception4UI.class)
    public void updateTestTx(int age) throws Exception {
        //测试事务处理
        //对用户 age 数据进行修改
        //如果i<0 修改后抛出异常到AOP
        //如果i>0 成功修改
        User cond = new User();
        cond.setCode("liaoad");

        User user = queryUserByExample(cond).get(0);
        log.info("原年龄为:{} -> 修改为:{}", user.getAge(), age);
        user.setAge(age);
        userMapper.updateByPrimaryKey(user);

        if (age < 0) {
            log.error("异常回退");
            throw new Exception4UI("", "");
        }
        log.info("成功通过");
    }
}
