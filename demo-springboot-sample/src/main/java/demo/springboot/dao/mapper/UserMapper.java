package demo.springboot.dao.mapper;



import demo.springboot.dao.domain.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    //
    List<User> selectByExample(User example);
}