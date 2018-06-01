package com.gqz.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminQuit extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);//调用doPost
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		HttpSession session =request.getSession();
		//取出session中的信息
		String username=(String)session.getAttribute("adminUsername");
		if(username!=null){
			//删除session中的信息
			session.removeAttribute("adminUsername");
			System.out.println(username+"退出后台管理系统------seesion删除成功！！");
		}
		//页面重定向到登录页面
		response.sendRedirect("login.jsp");
	}

}
