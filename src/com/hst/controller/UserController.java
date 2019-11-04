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
			// �������ݿ��·��
			String sqlPath = null;
			String localPath = "D:/code/06-server/01-java/04-java-springMVC" + request.getContextPath();
			// ���� �ļ���
			String filename = null;
			if (!user.getFile().isEmpty()) {
//				// ����uuid��Ϊ�ļ�����
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//				// ����ļ����ͣ������ж��������ͼƬ����ֹ�ϴ���
				String contentType = user.getFile().getContentType();
//				// ����ļ���׺��
				String suffixName = contentType.substring(contentType.indexOf("/") + 1);
//				// �õ� �ļ���
				filename = uuid + "." + suffixName;
				// ʹ��transferTo��dest���������ϴ��ļ�д����������ָ�����ļ���
				user.getFile().transferTo(new File(localPath + "\\WebContent\\view\\lib\\img\\" + filename));
//				// ��ͼƬ�����·�����������ݿ�
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
	public User updateUser(User user,HttpServletRequest request) throws IllegalStateException, IOException {//�ǳƣ�ͷ��
		if(user.getFile() == null && user.getNickname() == null) {
			return null;
		}
		User temp = userMapper.selectByPrimaryKey(user.getuId());
		User u = new User();	
		String sqlPath = null;
		String localPath = "D:/code/06-server/01-java/04-java-springMVC" + request.getContextPath();
		String filename = null;
		if (temp != null) {
			if (user.getFile() != null) {//�޸�ͷ��
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
			if(user.getNickname() != null && user.getNickname().length() != 0) {//�޸��ǳ�
				u.setNickname(user.getNickname());
			}
			u.setuId(temp.getuId());
			userMapper.updateByPrimaryKeySelective(u);
		}
		return u;
	}
}

