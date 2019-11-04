package com.hst.pojo;

import java.util.Date;

public class Comment {
    private Integer cId;
    private Integer gId;
    private Integer oId;
    private Integer uId;
    private String content;
    private Date time;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
	public Comment() {
		super();
	}

	public Comment(Integer gId, Integer oId, Integer uId, String content) {
		super();
		this.gId = gId;
		this.oId = oId;
		this.uId = uId;
		this.content = content;
	}

	public Comment(Integer cId, Integer gId, Integer oId, Integer uId, String content) {
		super();
		this.cId = cId;
		this.gId = gId;
		this.oId = oId;
		this.uId = uId;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [cId=" + cId + ", gId=" + gId + ", oId=" + oId + ", uId=" + uId + ", content=" + content
				+ ", time=" + time + "]";
	}
}