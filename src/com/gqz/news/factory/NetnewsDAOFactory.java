package com.gqz.news.factory;

import com.gqz.news.DAO.NetnewsDAO;
/**
 * 
* @ClassName: ForumDAOFactory
* @Description: TODO(ForumDAO的工厂类)
* @author ganquanzhong
* @date 2018年1月4日 下午11:30:50
 */
public class NetnewsDAOFactory {
	/*
	  设计模式之工厂模式
	  由工厂产生对象：用户不用操心对象如何产生，只要从工厂获取对象  Spring 
	 */
	public static NetnewsDAO getNetnewsDAOInstance(){
		return new NetnewsDAO();
	}
}
