package anddd7.springboot.dao;

import anddd7.springboot.domain.SysUser;

import java.math.BigDecimal;

public interface SysUserMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}