package com.gqz.news.factory;

import com.gqz.news.DAO.NewsDAO;
import com.gqz.news.DAO.NewsUserDAO;

public class NewsUserDAOFactory {
	/*
	  设计模式之工厂模式
	  由工厂产生对象：用户不用操心对象如何产生，只要从工厂获取对象
	  Spring 
	 */
	public static NewsUserDAO getNewsUserDAOInstance(){
		return new NewsUserDAO();
	}
}
