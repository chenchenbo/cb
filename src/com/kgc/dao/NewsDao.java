package com.kgc.dao;

import java.util.List;

import com.kgc.pojo.News;
import com.kgc.pojo.NewsCategory;
import com.kgc.util.Page;

public interface NewsDao {
	// 根据数据源获取新闻信息
	public void getNewsListByDS();

	// 查询新闻信息
	public List<News> getNewsList();

	// 增加新闻信息
	public boolean add(News news);

	// 修改新闻标题
	public void update(News news);

	// 删除新闻信息
	public void delete(News news);

	// 查找特定标题的新闻信息
	public void getNewsByTitle(News news);

	// 获取某类别下新闻总数量
	public int getCountByCategory(NewsCategory category);

	// 根据id查询新闻
	public News getNewById(Integer id);

	// 修改新闻
	public int updateNewsById(News news);

	// 根据id排序后的数据总数
	public int getNewsTotalCountById();

	// 根据id排序后的分页新闻集合
	public List<News> getNewsListByPage(Page page);
}
