package com.hst.service;

import java.util.List;

import com.hst.pojo.Order;
import com.hst.pojo.User;

public interface UserService {
	
	//根据用户名或密码查找user表的数据
	public User selectUserByUsername(String username);
	
	//根据id获取user表的数据
    public User selectByPrimaryKey(Integer uId);
    
    //根据uid获取order表的数据
    public List<Order> selctOrderByUId(Integer uId);
    
    //修改user表的数据
    public boolean updateUser(User user);
}
