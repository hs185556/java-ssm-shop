package com.hst.mapper;

import java.util.List;
import java.util.Map;

import com.hst.pojo.Comment;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(Comment comment);

    int insertSelective(Comment comment);

    Comment selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(Comment comment);

    int updateByPrimaryKey(Comment comment);
    
    //根据id和页数获取商品的评论
    List<Comment> selectCommentByGIdAndPage(Map<Object,Object> map);
    
    //根据订单Id和页数获取评论
	Comment selectCommentByOId(int oId);
    
}