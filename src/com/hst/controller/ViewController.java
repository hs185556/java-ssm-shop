package com.hst.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hst.mapper.UserMapper;
import com.hst.pojo.Comment;
import com.hst.pojo.Good;
import com.hst.pojo.Order;
import com.hst.pojo.User;
import com.hst.service.GoodService;
import com.hst.service.OrderService;
import com.hst.service.UserService;

@Controller
public class ViewController {
	@Autowired
    private GoodService goodService;
	@Autowired
    private OrderService orderService;
	@Autowired
	private UserService userService; 
	
	@RequestMapping("index.do")
	public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/indexp.do?pno=1&psize=9");
		return mv;
    }
	@RequestMapping("indexp.do")
	public ModelAndView login(int pno,int psize){
        ModelAndView mv = new ModelAndView();
        if(pno<1)pno=1;
        List<Good> goodlist = goodService.getGoodByPage(pno,psize);
        mv.addObject("goodlist", goodlist);
        mv.addObject("pno",pno);
        mv.addObject("psize",psize);
        mv.setViewName("/view/index.jsp");
        return mv;
    }
	
	@RequestMapping("zone.do")
	public ModelAndView zone(HttpServletRequest request,int pno,int psize){
        ModelAndView mv = new ModelAndView();
        Cookie[] cookies  = request.getCookies();
        String username = "";
        for(Cookie c:cookies) {
        	if("_username".equals(c.getName())) {
        		username = c.getValue();
        		break;
        	}
        }
        if(!"".equals(username)) {
        	User user = userService.selectUserByUsername(username);
        	if(user != null) {
        		user.setPassword("");
        		mv.addObject("user",user);
        		List<Map<String, Object>> orderlist =  orderService.getOrderByUIdAndPage(user.getuId(), pno, psize);
        		mv.addObject("orderlist",orderlist);
        		mv.setViewName("/view/zone.jsp");
        	}
        }else {
        	mv.setViewName("redirect:/index.do");
        }
        return mv;
    }
	
	@RequestMapping("goodDetail.do")
	public ModelAndView goodDetail(@RequestParam(value="pcik") int gId){
        ModelAndView mv = new ModelAndView();
        Good good = goodService.selectByPrimaryKey(gId);
        List<Map<String, Object>> commentlist = goodService.getCommentByGIdAndPage(gId, 1, 10);
        mv.addObject("good",good);
        mv.addObject("commentlist",commentlist);
        mv.setViewName("/view/goodDetail.jsp");
        return mv;
    }
	
	@RequestMapping("manage.do")
	public ModelAndView manage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/view/manage.jsp");
        return mv;
    }
}
