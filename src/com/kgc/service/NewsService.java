package com.kgc.service;

import java.util.List;

import com.kgc.pojo.News;
import com.kgc.util.Page;

public interface NewsService {
	// 查询新闻信息
	public List<News> getNewsList();
	
	// 增加新闻信息
	public boolean add(News news) ;
	
	//根据id查询新闻信息
	public News getNewById(Integer id);
	
	//根据id修改新闻信息
	public boolean updateNewsById(News news);
	
	//根据id排序后的分页查询
	public List<News> getNewsListById(Page page);
}
