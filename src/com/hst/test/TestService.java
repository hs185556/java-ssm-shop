package com.hst.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hst.pojo.Good;
import com.hst.pojo.Order;
import com.hst.pojo.User;
import com.hst.service.GoodService;
import com.hst.service.OrderService;
import com.hst.service.UserService;

public class TestService {
	ApplicationContext context = null;
	@Before
    public void init(){
        context = new ClassPathXmlApplicationContext("classpath:spring/springDao.xml");
    }
	
	//good
    @Test
    public void testSelectGoodAllService(){
        GoodService goodServiceImpl = (GoodService) context.getBean("goodServiceImpl");
        List<Good> goodList = goodServiceImpl.selectGoodAll();
        System.out.println(goodList);
    }
    
    @Test
	public void testSelectByPrimaryKey() {
    	GoodService goodServiceImpl = (GoodService) context.getBean("goodServiceImpl");
		Good good = goodServiceImpl.selectByPrimaryKey(7);
        System.out.println(good);
	}
    
    @Test
	public void testUpdateGoodRepertoryMinusOne() {
    	GoodService goodServiceImpl = (GoodService) context.getBean("goodServiceImpl");
        System.out.println(goodServiceImpl.reduceGoodRepertory(7,1));
	}
    
    //user
    @Test
    public void testSelectUserByUsernameAndPassword() {
    	UserService userService = (UserService) context.getBean("userServiceImpl");
    	User user = new User();
		user.setUsername("h");
		user.setPassword("18");
		User u = userService.selectUserByUsername(user.getUsername());
		System.out.println(u);
    }
    
    //order
    @Test
    public void testInsertIntoOrder() {
    	OrderService orderService = (OrderService) context.getBean("orderServiceImpl");
    	Order order= new Order();
    	order.setgId(2);
    	order.setoId(2);
    	order.setuId(2);
    	order.setNumber(1);
		System.out.println(orderService.insertSelective(order));
    }
    
    @Test
    public void testSelectOrderAll() {
    	OrderService orderService = (OrderService) context.getBean("orderServiceImpl");
    	List<Map<String, String>> orderList = orderService.selectOrderAll();
    	System.out.println(orderList);
    }
}
