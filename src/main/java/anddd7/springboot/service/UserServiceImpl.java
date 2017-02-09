package anddd7.springboot.service;

import anddd7.springboot.dao.SysUserMapper;
import anddd7.springboot.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by AnDong on 2017/2/8.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser selectUser(Long id) {
        return sysUserMapper.selectByPrimaryKey(BigDecimal.valueOf(id));
    }
}
