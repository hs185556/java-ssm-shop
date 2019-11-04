package com.hst.pojo;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class User implements Serializable {
	private Integer uId;
	private String username;
	private String password;
	private String nickname;
	private String piclink;
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPiclink() {
		return piclink;
	}

	public void setPiclink(String piclink) {
		this.piclink = piclink;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public User() {
		super();
	}

	public User(String username, String password, String nickname, String piclink) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.piclink = piclink;
	}
	
	public User(Integer uId, String username, String password, String nickname, String piclink) {
		super();
		this.uId = uId;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.piclink = piclink;
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", piclink=" + piclink + ", file=" + file + "]";
	}

}