package com.kgc.service.impl;

import java.util.List;

import com.kgc.dao.NewsDao;
import com.kgc.dao.impl.NewsDaoImpl;
import com.kgc.pojo.News;
import com.kgc.service.NewsService;
import com.kgc.util.Page;

public class NewsServiceImpl implements NewsService {
	private NewsDao newsDao = null;

	public NewsServiceImpl() {
		newsDao = new NewsDaoImpl();
	}

	public List<News> getNewsList() {
		return newsDao.getNewsList();
	}

	// 增加新闻信息
	public boolean add(News news) {
		return newsDao.add(news);
	}

	public News getNewById(Integer id) {
		return newsDao.getNewById(id);
	}

	public boolean updateNewsById(News news) {
		int index = newsDao.updateNewsById(news);
		if (index > 0) {
			return true;
		}
		return false;
	}

	public List<News> getNewsListById(Page page) {
		page.setTotalCount(newsDao.getNewsTotalCountById());
		List<News> newsListByPage = newsDao.getNewsListByPage(page);
		System.out.println("当前页是：第" + page.getCurrPageNo() + "页");
		System.out.println("当前每页显示条数是：" + page.getPageSize() + "条");
		System.out.println("一共有：" + page.getTotalPageCount() + "页");
		System.out.println("id\t\t\t\t\tauthor\t\t\t\t\ttitle");
		for (News news : newsListByPage) {
			System.out.println(news.getId() + "\t\t\t\t\t" + news.getAuthor() + "\t\t\t\t\t" + news.getTitle());
		}
		return newsListByPage;
	}
}
