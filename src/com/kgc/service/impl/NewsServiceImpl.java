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

	// ����������Ϣ
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
		System.out.println("��ǰҳ�ǣ���" + page.getCurrPageNo() + "ҳ");
		System.out.println("��ǰÿҳ��ʾ�����ǣ�" + page.getPageSize() + "��");
		System.out.println("һ���У�" + page.getTotalPageCount() + "ҳ");
		System.out.println("id\t\t\t\t\tauthor\t\t\t\t\ttitle");
		for (News news : newsListByPage) {
			System.out.println(news.getId() + "\t\t\t\t\t" + news.getAuthor() + "\t\t\t\t\t" + news.getTitle());
		}
		return newsListByPage;
	}
}
