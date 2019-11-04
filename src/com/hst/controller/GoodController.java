package com.hst.controller;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hst.mapper.GoodMapper;
import com.hst.mapper.UserMapper;
import com.hst.pojo.Comment;
import com.hst.pojo.Good;
import com.hst.pojo.User;
import com.hst.service.GoodService;
import com.hst.service.UserService;

@Controller
public class GoodController {
	@Autowired
    private GoodService goodService;
	@Autowired
    private UserService userService;
	
	@RequestMapping("getGoodByGId.do")
	@ResponseBody
	public Good getGoodByGId(int gId){
		return goodService.selectByPrimaryKey(gId);
	}
	
	@RequestMapping("buyGoodById.do")
	@ResponseBody
	public Map<String, Integer> buyGoodById(Integer gId,String username,Integer number){
		Map<String, Integer> m = new HashMap<String, Integer>();
		User user = userService.selectUserByUsername(username);
		int rs = 0;
		if(user != null) {
			rs = goodService.buyGoodById(gId, user.getuId(),number)?1:0;
		}
		m.put("rs", rs);
		return m; 
	}
	
	@RequestMapping("getCommentByGIdAndPage.do")
	@ResponseBody
	public List<Map<String, Object>> getCommentByGIdAndPage(int gId,int pno,int psize) throws Exception {
		return goodService.getCommentByGIdAndPage(gId,pno,psize);
	}
	
	@RequestMapping("getGoodByPage.do")
	@ResponseBody
	public List<Good> getGoodByPage(int pno,int psize) throws Exception {
		return goodService.getGoodByPage(pno,psize);
	}
	
	@RequestMapping(value="addGood.do",headers= "content-type=multipart/*")
	@ResponseBody
	public Map<String, Integer> addGood(Good good,HttpServletRequest request) throws Exception {
		Map<String, Integer> m = new HashMap<String, Integer>();
		int rs = 0;
		Good g = goodService.selectGoodByGoodname(good.getGoodname());
		if(g == null && good != null) {
			String sqlPath = null;
			String localPath = "D:/code/06-server/01-java/04-java-springMVC" + request.getContextPath();
			String filename = null;
			if (!good.getFile().isEmpty()) {
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				String contentType = good.getFile().getContentType();
				String suffixName = contentType.substring(contentType.indexOf("/") + 1);
				filename = uuid + "." + suffixName;
				good.getFile().transferTo(new File(localPath + "\\WebContent\\view\\lib\\img\\" + filename));
				sqlPath = "/view/lib/img/" + filename;
				good.setPiclink(sqlPath);
			}
			rs = goodService.addGood(good);
		}
		m.put("rs", rs);
		return m;
	}
	
}
