package com.hst.mapper;

import java.util.List;
import java.util.Map;

import com.hst.pojo.Comment;
import com.hst.pojo.Good;

public interface GoodMapper {
    int deleteByPrimaryKey(Integer gId);

    int insert(Good good);

    int insertSelective(Good good);

    Good selectByPrimaryKey(Integer gId);

    int updateByPrimaryKeySelective(Good good);

    int updateByPrimaryKey(Good record);
    
    List<Good> selectGoodAll();
    
    List<Comment> seletCommentByGId(Integer gid);

	List<Good> selectGoodByPage(Map<Object, Object> map);
	
	Good selectGoodByGoodname(String goodname);
}