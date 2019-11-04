package com.hst.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hst.pojo.Comment;
import com.hst.pojo.Good;

public interface GoodService {
    
    //��ȡgood���ȫ������
    public List<Good> selectGoodAll();
    
    //����id��ȡgood�������
    public Good selectByPrimaryKey(Integer gId);
    
    //������Ʒ�Ŀ��
    public boolean reduceGoodRepertory(Integer gId,int number);
    
    //����id������Ʒ
    public boolean buyGoodById(int gId,int uId,int number);

	//����id��ҳ����ȡ��Ʒ������
	public List<Map<String, Object>> getCommentByGIdAndPage(int gId, int pno, int psize);

	//����ҳ����ȡ��Ʒ
	public List<Good> getGoodByPage(int pno, int psize);

	//�����Ʒ
	public int addGood(Good good);
	
	//������Ʒ����ȡ��Ʒ
	public Good selectGoodByGoodname(String goodname);
}
