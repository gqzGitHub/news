package com.gqz.news.factory;

import com.gqz.news.DAO.NoteDAO;

public class NoteDAOFactory {
	/*
	  设计模式之工厂模式
	  由工厂产生对象：用户不用操心对象如何产生，只要从工厂获取对象
	  Spring 
	 */
	public static NoteDAO getNoteDAOInstance(){
		return new NoteDAO();
	}
}
