package com.hst.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hst.mapper.GoodMapper;
import com.hst.mapper.OrderMapper;
import com.hst.mapper.UserMapper;
import com.hst.pojo.Comment;
import com.hst.pojo.Good;
import com.hst.pojo.Order;
import com.hst.pojo.User;

public class TestDAO {
	ApplicationContext context = null;
	@Before
    public void init(){
        context = new ClassPathXmlApplicationContext("classpath:spring/springDao.xml");
    }
	
	//======================================good
	@Test
    public void testSelectGoodAllDao(){
		GoodMapper goodMapper = (GoodMapper) context.getBean("goodMapper");
		List<Good> goodList = goodMapper.selectGoodAll();
        System.out.println(goodList);
    }
	
	@Test
	public void testSelectByPrimaryKey() {
		GoodMapper goodMapper = (GoodMapper) context.getBean("goodMapper");
		Good good = goodMapper.selectByPrimaryKey(7);
        System.out.println(good);
	}
	
	@Test
	public void testUpdateGoodRepertoryMinusOne() {
		GoodMapper goodMapper = (GoodMapper) context.getBean("goodMapper");
		Good good = goodMapper.selectByPrimaryKey(7);
		good.setRepertory(good.getRepertory()-1);
		goodMapper.updateByPrimaryKeySelective(good);
	}
	
	@Test
	public void testSelectGoodnameBygId() {
		GoodMapper goodMapper = (GoodMapper) context.getBean("goodMapper");
		Good g = goodMapper.selectByPrimaryKey(5);
		System.out.println(g.getGoodname());
	}
	
	@Test
	public void testSelectCommentBygId() {
		GoodMapper goodMapper = (GoodMapper) context.getBean("goodMapper");
		List<Comment> c = goodMapper.seletCommentByGId(7);
		for(Comment item:c) {
			System.out.println(item);
		}
	}
	
	//======================================user
	@Test
	public void testSelectUserByUsernameAndPassword() {
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		User u = new User();
//		u.setUsername("h");
		u.setPassword("18");
		User user = userMapper.selectUserByUsername(u.getUsername());
		System.out.println(user);
	}
	
	@Test
	public void testSelectUsernameByuId() {
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		User u = userMapper.selectByPrimaryKey(1);
		System.out.println(u.getUsername());
	}
	
	@Test
	public void testInsert() {
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		User user = new User("name","password","nickname","link");
		userMapper.insert(user);
	}
	
	@Test
	public void testDelete() {
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		userMapper.deleteByPrimaryKey(2);
	}
	
	@Test
	public void testUpdate() {
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		User user = new User(1,"h","18","Á¹±¡","link");
		userMapper.updateByPrimaryKey(user);
	}
	
	@Test
	public void testSelectOrderByuId() {
		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
		List<Order> o = userMapper.seletOrderByUId(1);
		for(Order item:o) {
			System.out.println(item);
		}
	}
	//======================================order
	@Test
	public void testSelectOrderAll() {
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");
		List<Order> orderList = orderMapper.selectOrderAll();
		System.out.println(orderList);
	}
	
	@Test
	public void testInsertOrder() {
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");
		Order o = new Order();
		o.setoId(2);
		o.setgId(2);
		o.setuId(2);
		o.setNumber(1);
		int num = orderMapper.insertSelective(o);
		System.out.println(num);
	}
	
	@Test
	public void testSelectOrder() {
		OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");
		System.out.println(orderMapper.selectByPrimaryKey(16));
	}
	
	//======================================comment
}

