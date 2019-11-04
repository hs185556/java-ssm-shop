package com.hst.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hst.mapper.CommentMapper;
import com.hst.mapper.GoodMapper;
import com.hst.mapper.OrderMapper;
import com.hst.mapper.UserMapper;
import com.hst.pojo.Comment;
import com.hst.pojo.Good;
import com.hst.pojo.Order;
import com.hst.pojo.User;
import com.hst.service.OrderService;

@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService  {
	@Autowired
	private OrderMapper orderMapper; 
	@Autowired
	private GoodMapper goodMapper; 
	@Autowired
	private UserMapper userMapper; 
	@Autowired
	private CommentMapper commentMapper; 
	
	@Override
	public List<Map<String, String>> selectOrderAll() {
		List<Order> orderList= orderMapper.selectOrderAll();
		List<Map<String, String>> orderMapList = new ArrayList<Map<String, String>>();
		DateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Order o : orderList) {
			Map<String, String> map = new HashMap<String, String>();
			int oId = o.getoId();
			int gId = o.getgId();
			String goodname = goodMapper.selectByPrimaryKey(gId).getGoodname();
			int uId = o.getuId();
			String username = userMapper.selectByPrimaryKey(uId).getUsername();
			int number = o.getNumber();
			String createTime = sdft.format(o.getCreateTime());
			map.put("oId", String.valueOf(oId));
			map.put("goodname", goodname);
			map.put("username", username);
			map.put("number", String.valueOf(number));
			map.put("createTime", createTime);
			orderMapList.add(map);
		}
		return orderMapList;
	}

	@Override
	public boolean insertSelective(Order order) {
		int i = orderMapper.insertSelective(order);
		if(i != 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> getOrderByUIdAndPage(int uId,int pno,int psize) {
		List<Map<String, Object>> rt = new ArrayList<Map<String, Object>>();
		Map<String, Object> temp = null;
		User user = userMapper.selectByPrimaryKey(uId);
		DateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(user != null) {
			Map<Object, Object> map = new HashMap<Object,Object>();
			int pstart = (pno-1)*psize;
			map.put("uId", uId);
			map.put("pstart", pstart);
			map.put("psize", psize);
			List<Order> orderlist = orderMapper.selectOrderByUId(map);
			for(Order o : orderlist) {
				temp = new HashMap<String, Object>(); 
				Good good = goodMapper.selectByPrimaryKey(o.getgId());
				temp.put("goodname", good.getGoodname());
				temp.put("number", o.getNumber());
				temp.put("oId",o.getoId());
				temp.put("commented", o.getCommented());
				temp.put("createTime", sdft.format(o.getCreateTime()));
				temp.put("price", good.getPrice());
				if(o.getCommented() == 1) {
					Comment comment = commentMapper.selectCommentByOId(o.getoId());
					temp.put("content", comment.getContent());
				}
				rt.add(temp);
			}
		}
		return rt;
	}

	@Override
	public int addCommentByOId(int oId, String content) {
		int rs = 0;
		Order order = orderMapper.selectByPrimaryKey(oId);
		if(order.getCommented() == 0) {
			Comment comment = new Comment(order.getgId(),oId,order.getuId(),content);
			rs = commentMapper.insertSelective(comment);
			Order o = new Order();
			o.setoId(order.getoId());
			o.setCommented(1);
			orderMapper.updateByPrimaryKeySelective(o);
		}
		return rs;
	}

}
