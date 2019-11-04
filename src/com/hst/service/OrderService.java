package com.hst.service;

import java.util.List;
import java.util.Map;

import com.hst.pojo.Order;

public interface OrderService {
	
	//��ȡgood���ȫ������
    public List<Map<String, String>> selectOrderAll();
    
    //��order�����һ������
    public boolean insertSelective(Order order);
    
    //����uId��ҳ����ȡ����
	public List<Map<String, Object>> getOrderByUIdAndPage(int uId, int pno, int psize);
	
	//����oId�������
	public int addCommentByOId(int oId, String content);

    
}
