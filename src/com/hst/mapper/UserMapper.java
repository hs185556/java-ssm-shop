package com.hst.mapper;

import java.util.List;

import com.hst.pojo.Order;
import com.hst.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);
    
    User selectUserByUsername(String username);
    
    List<Order> seletOrderByUId(Integer uId);
}