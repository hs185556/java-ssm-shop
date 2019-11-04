package com.hst.mapper;

import java.util.List;
import java.util.Map;

import com.hst.pojo.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer oId);

    int insert(Order record);

    int insertSelective(Order order);

    Order selectByPrimaryKey(Integer oId);

    int updateByPrimaryKeySelective(Order order);

    int updateByPrimaryKey(Order order);

	List<Order> selectOrderAll();

	List<Order> selectOrderByUId(Map<Object, Object> map);
}