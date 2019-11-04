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
    
    //����id��ҳ����ȡ��Ʒ������
    List<Comment> selectCommentByGIdAndPage(Map<Object,Object> map);
    
    //���ݶ���Id��ҳ����ȡ����
	Comment selectCommentByOId(int oId);
    
}