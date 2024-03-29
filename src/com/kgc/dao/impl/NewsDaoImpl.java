package com.kgc.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kgc.dao.BaseDao;
import com.kgc.dao.NewsDao;
import com.kgc.pojo.News;
import com.kgc.pojo.NewsCategory;
import com.kgc.util.Page;

public class NewsDaoImpl extends BaseDao implements NewsDao {
	// 查询特定新闻类别下新闻的数目
	public int getCountByCategory(NewsCategory category) {
		int count = 0;
		String sql = "select count(1) from news_detail where categoryId=?";
		Object[] params = { category.getId() };
		if (this.getConnection()) {
			try {
				ResultSet rs = this.executeSQL(sql, params);
				while (rs.next()) {
					count = rs.getInt(1);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				this.closeResource();
			}
		}
		return count;
	}

	// 根据数据源获取新闻信息
	public void getNewsListByDS() {
		try {
			// 3 获取Statement对象，执行sql语句
			String sql = "select * from news_detail";
			Object[] params = {};
			rs = this.executeSQL2(sql, params);
			// 4 处理执行结果集ResultSet
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String summary = rs.getString("summary");
				String content = rs.getString("content");
				String author = rs.getString("author");
				Timestamp createDate = rs.getTimestamp("createDate");
				System.out.println(
						id + "\t" + title + "\t" + summary + "\t" + content + "\t" + author + "\t" + createDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResource();
		}
	}

	// 查询新闻信息
	public List<News> getNewsList() {
		List<News> list = new ArrayList<News>();
		try {
			//3 获取Statement对象，执行sql语句
			String sql = "select * from news_detail";
			Object[] params = {};
			rs = this.executeSQL(sql, params);
			// 4 处理执行结果集ResultSet
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String summary = rs.getString("summary");
				String content = rs.getString("content");
				String author = rs.getString("author");
				Timestamp createDate = rs.getTimestamp("createDate");
//				System.out.println(id + "\t" + title + "\t" + summary + "\t"
//						+ content + "\t" + author + "\t" + createDate);

				News news = new News();
				news.setId(id);
				news.setTitle(title);
				news.setSummary(summary);
				news.setContent(content);
				news.setAuthor(author);
				news.setCreateDate(createDate);

				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResource();
		}
		return list;
	}

	// 增加新闻信息
	public boolean add(News news) {
		boolean flag = false;
		try {
			String sql = "insert into news_detail(categoryId,title,summary,content,createDate,author,picPath) values(?,?,?,?,?,?,?)";
			Object[] params = { news.getCategoryId(), news.getTitle(), news.getSummary(), news.getContent(),
					news.getCreateDate(), news.getAuthor(),news.getPicPath() };
			int i = this.executeUpdate(sql, params);
			// 4 处理执行结果
			if (i > 0) {
				System.out.println("插入新闻成功！");
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource();
		}
		return flag;
	}

	// 修改新闻标题
	public void update(News news) {
		try {
			String sql = "update news_detail set title=? where id=?";
			Object[] params = { news.getTitle(), news.getId() };
			int i = this.executeUpdate(sql, params);
			if (i > 0) {
				System.out.println("修改新闻标题成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource();
		}

	}

	// 删除新闻信息
	public void delete(News news) {
		try {
			String sql = "delete from news_detail where id=?";
			Object[] params = { news.getId() };
			int i = this.executeUpdate(sql, params);
			if (i > 0) {
				System.out.println("删除新闻成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查找特定标题的新闻信息
	public void getNewsByTitle(News news) {
		try {
			String sql = "select id,title from news_detail where title like ?";
			Object[] params = { "%" + news.getTitle() + "%" };
			rs = this.executeSQL(sql, params);
			while (rs.next()) {
				int id = rs.getInt("id"); // rs.getInt(1);
				String newsTitle = rs.getString("title");

				System.out.println(id + "\t" + newsTitle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResource();
		}
	}

	public static void main(String[] args) {
		NewsDao newsDao = new NewsDaoImpl();
		/*
		 * News news = new News(); news.setId(5); news.setCategoryId(2);
		 * news.setTitle("test"); news.setSummary("test"); news.setContent("test");
		 * news.setAuthor("admin"); news.setCreateDate(new Date()); newsDao.add(news);
		 */

		/*
		 * News news = new News(); news.setId(5); news.setTitle("testnew");
		 * newsDao.update(news);
		 */

		/*
		 * News news = new News(); news.setTitle("课工场"); newsDao.getNewsByTitle(news);
		 */

		News news = new News();
		news.setId(5);
		newsDao.delete(news);
		newsDao.getNewsList();

	}

	public News getNewById(Integer ids) {
		News newsInfo = new News();
		// 3 获取Statement对象，执行sql语句
		try {
			String sql = "select * from news_detail where id=?";
			Object[] params = { ids };
			rs = this.executeSQL(sql, params);
			// 4 处理执行结果集ResultSet

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String summary = rs.getString("summary");
				String content = rs.getString("content");
				String author = rs.getString("author");
				Timestamp createDate = rs.getTimestamp("createDate");
//						System.out.println(id + "\t" + title + "\t" + summary + "\t"
//								+ content + "\t" + author + "\t" + createDate);
				newsInfo.setId(id);
				newsInfo.setTitle(title);
				newsInfo.setSummary(summary);
				newsInfo.setContent(content);
				newsInfo.setAuthor(author);
				newsInfo.setCreateDate(createDate);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResource();
		}
		return newsInfo;
	}

	public int updateNewsById(News news) {
		int i = 0;
		try {
			String sql = "update news_detail set title=?,author=? where id=?";
			Object[] params = { news.getTitle(), news.getAuthor(), news.getId() };
			i = this.executeUpdate(sql, params);
			if (i > 0) {
				System.out.println("修改新闻标题成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource();
		}
		return i;
	}

	public int getNewsTotalCountById() {
		int count = 0;
		String sql = "select count(id) from news_detail order by id";
		Object[] params = {};
		if (this.getConnection()) {
			try {
				ResultSet rs = this.executeSQL(sql, params);
				while (rs.next()) {
					count = rs.getInt(1);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				this.closeResource();
			}
		}
		return count;
	}

	public List<News> getNewsListByPage(Page page) {
		List<News> newList = new ArrayList<News>();
		String sql = "select * from news_detail order by id limit ?,?";
		Object[] params = { (page.getCurrPageNo() - 1) * page.getPageSize(), page.getPageSize() };
		if (this.getConnection()) {
			try {
				ResultSet rs = this.executeSQL(sql, params);
				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String summary = rs.getString("summary");
					String content = rs.getString("content");
					String author = rs.getString("author");

					News news = new News();
					news.setId(id);
					news.setTitle(title);
					news.setSummary(summary);
					news.setContent(content);
					news.setAuthor(author);
					newList.add(news);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				this.closeResource();
			}
		}

		return newList;
	}

}
