package com.hst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hst.mapper.UserMapper;
import com.hst.pojo.Good;
import com.hst.pojo.Order;
import com.hst.pojo.User;
import com.hst.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public User selectUserByUsername(String username) {
		User u = userMapper.selectUserByUsername(username);
		return u;
	}

	@Override
	public User selectByPrimaryKey(Integer uId) {
		User user = userMapper.selectByPrimaryKey(uId);
		return user;
	}

	@Override
	public List<Order> selctOrderByUId(Integer uId) {
		List<Order> orders = userMapper.seletOrderByUId(uId);
		return orders;
	}

	@Override
	public boolean updateUser(User user) {
		int f = userMapper.updateByPrimaryKey(user);
		return f!=0?true:false;
	}

}
