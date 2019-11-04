package com.hst.controller;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hst.mapper.UserMapper;
import com.hst.pojo.User;

@Controller
public class UserController {
	
	@Autowired
	private UserMapper userMapper; 
	
	@RequestMapping(value="regUser.do",headers= "content-type=multipart/*")
	@ResponseBody
	public Map<String, Integer> regUser(User user,HttpServletRequest request) throws IllegalStateException, IOException {
		Map<String, Integer> m = new HashMap<String, Integer>();
		int rs = 0;
		User temp = userMapper.selectUserByUsername(user.getUsername());
		if (temp == null) {
			// 保存数据库的路径
			String sqlPath = null;
			String localPath = "D:/code/06-server/01-java/04-java-springMVC" + request.getContextPath();
			// 定义 文件名
			String filename = null;
			if (!user.getFile().isEmpty()) {
//				// 生成uuid作为文件名称
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//				// 获得文件类型（可以判断如果不是图片，禁止上传）
				String contentType = user.getFile().getContentType();
//				// 获得文件后缀名
				String suffixName = contentType.substring(contentType.indexOf("/") + 1);
//				// 得到 文件名
				filename = uuid + "." + suffixName;
				// 使用transferTo（dest）方法将上传文件写到服务器上指定的文件。
				user.getFile().transferTo(new File(localPath + "\\WebContent\\view\\lib\\img\\" + filename));
//				// 把图片的相对路径保存至数据库
				sqlPath = "/view/lib/img/" + filename;
				user.setPiclink(sqlPath);
			}
			rs = userMapper.insertSelective(user);
		}
		m.put("rs", rs);
		return m;
	}
	
	@RequestMapping("loginUser.do")
	@ResponseBody
	public User loginUser(@RequestBody User user,HttpServletRequest request) {
		return userMapper.selectUserByUsername(user.getUsername());
	}
	
	@RequestMapping("logoutUser.do")
	@ResponseBody
	public Map<String, Integer> logoutUser(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Integer> m = new HashMap<String, Integer>();
		int rs = 0;
		Cookie[] cs = request.getCookies();
		for(Cookie c : cs) {
			if("_username".equals(c.getName())) {
				c.setMaxAge(0);
				response.addCookie(c);
				rs = 1;
			}
		}
		m.put("rs", rs);
		return m;
	}
	
	@RequestMapping("updateUser.do")
	@ResponseBody
	public User updateUser(User user,HttpServletRequest request) throws IllegalStateException, IOException {//昵称，头像
		if(user.getFile() == null && user.getNickname() == null) {
			return null;
		}
		User temp = userMapper.selectByPrimaryKey(user.getuId());
		User u = new User();	
		String sqlPath = null;
		String localPath = "D:/code/06-server/01-java/04-java-springMVC" + request.getContextPath();
		String filename = null;
		if (temp != null) {
			if (user.getFile() != null) {//修改头像
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				String contentType = user.getFile().getContentType();
				String suffixName = contentType.substring(contentType.indexOf("/") + 1);
				filename = uuid + "." + suffixName;
				user.getFile().transferTo(new File(localPath + "\\WebContent\\view\\lib\\img\\" + filename));
				File file = new File(localPath + "\\WebContent" + temp.getPiclink());
				file.delete();
				sqlPath = "/view/lib/img/" + filename;
				u.setPiclink(sqlPath);
			}
			if(user.getNickname() != null && user.getNickname().length() != 0) {//修改昵称
				u.setNickname(user.getNickname());
			}
			u.setuId(temp.getuId());
			userMapper.updateByPrimaryKeySelective(u);
		}
		return u;
	}
}

