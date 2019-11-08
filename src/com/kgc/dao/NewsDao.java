package com.kgc.dao;

import java.util.List;

import com.kgc.pojo.News;
import com.kgc.pojo.NewsCategory;
import com.kgc.util.Page;

public interface NewsDao {
	// ��������Դ��ȡ������Ϣ
	public void getNewsListByDS();

	// ��ѯ������Ϣ
	public List<News> getNewsList();

	// ����������Ϣ
	public boolean add(News news);

	// �޸����ű���
	public void update(News news);

	// ɾ��������Ϣ
	public void delete(News news);

	// �����ض������������Ϣ
	public void getNewsByTitle(News news);

	// ��ȡĳ���������������
	public int getCountByCategory(NewsCategory category);

	// ����id��ѯ����
	public News getNewById(Integer id);

	// �޸�����
	public int updateNewsById(News news);

	// ����id��������������
	public int getNewsTotalCountById();

	// ����id�����ķ�ҳ���ż���
	public List<News> getNewsListByPage(Page page);
}
