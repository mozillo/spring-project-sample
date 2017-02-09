package anddd7.springboot.service;

import anddd7.springboot.domain.SysUser;

/**
 * Created by AnDong on 2017/2/8.
 */
public interface UserService {
    SysUser selectUser(Long id);
}
