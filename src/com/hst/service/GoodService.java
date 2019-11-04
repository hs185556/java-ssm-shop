package com.hst.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hst.pojo.Comment;
import com.hst.pojo.Good;

public interface GoodService {
    
    //获取good表的全部数据
    public List<Good> selectGoodAll();
    
    //根据id获取good表的数据
    public Good selectByPrimaryKey(Integer gId);
    
    //减少商品的库存
    public boolean reduceGoodRepertory(Integer gId,int number);
    
    //根据id购买商品
    public boolean buyGoodById(int gId,int uId,int number);

	//根据id和页数获取商品的评论
	public List<Map<String, Object>> getCommentByGIdAndPage(int gId, int pno, int psize);

	//根据页数获取商品
	public List<Good> getGoodByPage(int pno, int psize);

	//添加商品
	public int addGood(Good good);
	
	//根据商品名获取商品
	public Good selectGoodByGoodname(String goodname);
}
