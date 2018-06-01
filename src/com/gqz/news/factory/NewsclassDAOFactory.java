package com.gqz.news.factory;

import com.gqz.news.DAO.NewsclassDAO;

public class NewsclassDAOFactory {
	public static NewsclassDAO getNewsclassDAOInstance(){
		return new NewsclassDAO();
	}
}
