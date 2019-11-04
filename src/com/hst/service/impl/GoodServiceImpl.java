package com.hst.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hst.mapper.CommentMapper;
import com.hst.mapper.GoodMapper;
import com.hst.mapper.OrderMapper;
import com.hst.mapper.UserMapper;
import com.hst.pojo.Comment;
import com.hst.pojo.Good;
import com.hst.pojo.Order;
import com.hst.pojo.User;
import com.hst.service.GoodService;

@Service("goodServiceImpl")
public class GoodServiceImpl implements GoodService{
	 
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private GoodMapper goodMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public List<Good> selectGoodAll() {
		List<Good> g = goodMapper.selectGoodAll();
		return g;
	}

	@Override
	public Good selectByPrimaryKey(Integer gId) {
		Good good = goodMapper.selectByPrimaryKey(gId);
		return good;
	}

	@Override
	public boolean reduceGoodRepertory(Integer gId,int number) {
		Good good = goodMapper.selectByPrimaryKey(gId);
		if(good.getRepertory()>=1 && good.getRepertory()>=number) {
			good.setRepertory(good.getRepertory()-number);
			goodMapper.updateByPrimaryKeySelective(good);
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	@Transactional
	public boolean buyGoodById(int gId, int uId,int number) {
		if(userMapper.selectByPrimaryKey(uId) != null && goodMapper.selectByPrimaryKey(gId) != null) {
			if(reduceGoodRepertory(gId,number)) {
				Order order = new Order();
//				int i=1/0;
				order.setgId(gId);
				order.setuId(uId);
				order.setNumber(number);
				orderMapper.insertSelective(order);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> getCommentByGIdAndPage(int gId, int pno, int psize) {
		List<Map<String, Object>> rmaplist = new ArrayList<Map<String, Object>>();
		Map<String, Object> rmap = null; 
		Good good = goodMapper.selectByPrimaryKey(gId);
		if(pno<1)pno=1;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		if(good != null) {
			Map<Object, Object> map = new HashMap<Object,Object>();
			int pstart = (pno-1)*psize;
			map.put("gId", gId);
			map.put("pstart", pstart);
			map.put("psize", psize);
			List<Comment> commentlist = commentMapper.selectCommentByGIdAndPage(map);
			for(Comment c:commentlist) {
				rmap = new HashMap<String, Object>();
				User user = userMapper.selectByPrimaryKey(c.getuId());
				rmap.put("nickname", user.getNickname());
				rmap.put("content", c.getContent());
				rmap.put("time", sdf.format(c.getTime()));
				rmap.put("piclink", user.getPiclink());
				rmaplist.add(rmap);
			}
		}
		return rmaplist;
	}

	@Override
	public List<Good> getGoodByPage(int pno, int psize) {
		Map<Object, Object> map = new HashMap<Object,Object>();
		int pstart = (pno-1)*psize;
		map.put("pstart", pstart);
		map.put("psize", psize);
		List<Good> goodlist = goodMapper.selectGoodByPage(map);
		return goodlist;
	}

	@Override
	public int addGood(Good good) {
		return goodMapper.insertSelective(good);
	}

	@Override
	public Good selectGoodByGoodname(String goodname) {
		return goodMapper.selectGoodByGoodname(goodname);
	}

}