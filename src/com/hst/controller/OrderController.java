package com.hst.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hst.pojo.Order;
import com.hst.pojo.User;
import com.hst.service.OrderService;
import com.hst.service.UserService;

@Controller
public class OrderController {
	@Autowired
    private OrderService orderService;
	@Autowired
    private UserService userService;
	
	@RequestMapping("getOrderByUsername.do")
	@ResponseBody
	public List<Map<String, Object>> getOrderByUId(String username,int pno,int psize) {
		User user = userService.selectUserByUsername(username);
		return orderService.getOrderByUIdAndPage(user.getuId(),pno,psize);
	}
	
	@RequestMapping("getOrderByUsernameAndPage.do")
	@ResponseBody
	public List<Map<String, Object>> getCommentByGIdAndPage(String username,int pno,int psize) throws Exception {
		User user = userService.selectUserByUsername(username);
		return orderService.getOrderByUIdAndPage(user.getuId(),pno,psize);
	}
	
	@RequestMapping("addCommentByOId.do")
	@ResponseBody
	public Map<String, Object> addCommentByOId(int oId,String content) throws Exception {
		Map<String, Object> rt = new HashMap<String,Object>();
		int rs = orderService.addCommentByOId(oId, content);
		return rt;
	}
}
