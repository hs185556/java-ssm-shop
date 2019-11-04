package com.hst.pojo;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Good implements Serializable {
	private Integer gId;
	private String goodname;
	private Float price;
	private Integer repertory;
	private String piclink;
	private MultipartFile file;
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Integer getgId() {
		return gId;
	}

	public void setgId(Integer gId) {
		this.gId = gId;
	}

	public String getGoodname() {
		return goodname;
	}

	public void setGoodname(String goodname) {
		this.goodname = goodname == null ? null : goodname.trim();
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getRepertory() {
		return repertory;
	}

	public void setRepertory(Integer repertory) {
		this.repertory = repertory;
	}

	public String getPiclink() {
		return piclink;
	}

	public void setPiclink(String piclink) {
		this.piclink = piclink == null ? null : piclink.trim();
	}
	
	public Good() {
		super();
	}

	public Good(String goodname, Float price, Integer repertory, String piclink) {
		super();
		this.goodname = goodname;
		this.price = price;
		this.repertory = repertory;
		this.piclink = piclink;
	}

	public Good(Integer gId, String goodname, Float price, Integer repertory, String piclink) {
		super();
		this.gId = gId;
		this.goodname = goodname;
		this.price = price;
		this.repertory = repertory;
		this.piclink = piclink;
	}

	@Override
	public String toString() {
		return "Good [gId=" + gId + ", goodname=" + goodname + ", price=" + price + ", repertory=" + repertory
				+ ", piclink=" + piclink + ", file=" + file + "]";
	}

}