package com.hst.service;

import java.util.List;

import com.hst.pojo.Order;
import com.hst.pojo.User;

public interface UserService {
	
	//�����û������������user�������
	public User selectUserByUsername(String username);
	
	//����id��ȡuser�������
    public User selectByPrimaryKey(Integer uId);
    
    //����uid��ȡorder�������
    public List<Order> selctOrderByUId(Integer uId);
    
    //�޸�user�������
    public boolean updateUser(User user);
}
