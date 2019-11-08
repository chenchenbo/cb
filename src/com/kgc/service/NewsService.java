package com.kgc.service;

import java.util.List;

import com.kgc.pojo.News;
import com.kgc.util.Page;

public interface NewsService {
	// ��ѯ������Ϣ
	public List<News> getNewsList();
	
	// ����������Ϣ
	public boolean add(News news) ;
	
	//����id��ѯ������Ϣ
	public News getNewById(Integer id);
	
	//����id�޸�������Ϣ
	public boolean updateNewsById(News news);
	
	//����id�����ķ�ҳ��ѯ
	public List<News> getNewsListById(Page page);
}
