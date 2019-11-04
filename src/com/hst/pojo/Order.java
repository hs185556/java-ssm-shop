package com.hst.pojo;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
	private Integer oId;
	private Integer uId;
	private Integer gId;
	private Integer number;
	private Date createTime;
	private int commented;

	public Integer getoId() {
		return oId;
	}

	public void setoId(Integer oId) {
		this.oId = oId;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public Integer getgId() {
		return gId;
	}

	public void setgId(Integer gId) {
		this.gId = gId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public int getCommented() {
		return commented;
	}

	public void setCommented(int commented) {
		this.commented = commented;
	}

	public Order() {
		super();
	}
	
	public Order(Integer uId, Integer gId, Integer number, Date createTime) {
		super();
		this.uId = uId;
		this.gId = gId;
		this.number = number;
		this.createTime = createTime;
	}
	
	public Order(Integer oId, Integer uId, Integer gId, Integer number, Date createTime) {
		super();
		this.oId = oId;
		this.uId = uId;
		this.gId = gId;
		this.number = number;
		this.createTime = createTime;
	}

	public Order(Integer oId, Integer uId, Integer gId, Integer number, Date createTime, int commented) {
		super();
		this.oId = oId;
		this.uId = uId;
		this.gId = gId;
		this.number = number;
		this.createTime = createTime;
		this.commented = commented;
	}

	@Override
	public String toString() {
		return "Order [oId=" + oId + ", uId=" + uId + ", gId=" + gId + ", number=" + number + ", createTime="
				+ createTime + ", commented=" + commented + "]";
	}

}