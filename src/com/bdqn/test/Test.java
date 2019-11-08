package com.bdqn.test;

import java.util.Scanner;

import com.kgc.service.impl.NewsServiceImpl;
import com.kgc.util.Page;

public class Test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		NewsServiceImpl serviceImpl = new NewsServiceImpl();
		Page page = new Page();
		System.out.println("请输入当前显示的每页条数：");
		int pageSize = sc.nextInt();
		System.out.println("请输入当前显示的页码：");
		int currPageNo = sc.nextInt();
		page.setCurrPageNo(currPageNo);
		page.setPageSize(pageSize);
		serviceImpl.getNewsListById(page);
	}
}
