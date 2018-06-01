package com.gqz.news.factory;

import com.gqz.news.DAO.NewsAdminDAO;

public class NewsAdminDAOFactory {
	public static NewsAdminDAO getNewsAdminDAOInstance(){
		return new NewsAdminDAO();
	}
}
