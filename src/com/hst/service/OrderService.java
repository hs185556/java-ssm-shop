package com.hst.service;

import java.util.List;
import java.util.Map;

import com.hst.pojo.Order;

public interface OrderService {
	
	//获取good表的全部数据
    public List<Map<String, String>> selectOrderAll();
    
    //向order表添加一条数据
    public boolean insertSelective(Order order);
    
    //根据uId和页数获取订单
	public List<Map<String, Object>> getOrderByUIdAndPage(int uId, int pno, int psize);
	
	//根据oId添加评论
	public int addCommentByOId(int oId, String content);

    
}
