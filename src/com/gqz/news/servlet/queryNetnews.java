package com.gqz.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gqz.news.DAO.NetnewsDAO;
import com.gqz.news.common.NetNewsJson;
import com.gqz.news.factory.NetnewsDAOFactory;
import com.gqz.news.model.NewsPo;
import com.gqz.news.util.DateUtil;

public class queryNetnews extends HttpServlet {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// 获取需要搜索的内容 ，将input中输入的关键字进行搜索
		String queryContent = request.getParameter("query");
		System.out.println("获取内容    "+queryContent);
		List<NewsPo> sinaPutList = new NetNewsJson().getJsonPut(1, 10,queryContent, "sina", DateUtil.toHtmlTime("20分钟前"));
		for (NewsPo newsPo : sinaPutList) {
			NetnewsDAO netnewsDAO = NetnewsDAOFactory.getNetnewsDAOInstance();
			netnewsDAO.insert(newsPo);
		}
		// 百度新闻
		List<NewsPo> baiduPutList = new NetNewsJson().getJsonPut(1, 10,	queryContent, "baidu", DateUtil.toHtmlTime("50分钟前"));
		for (NewsPo newsPo : baiduPutList) {
			NetnewsDAO netnewsDAO = NetnewsDAOFactory.getNetnewsDAOInstance();
			netnewsDAO.insert(newsPo);
		}	
		out.print("<script>window.location.href='Front?op=getNetnewsList';</script>");
	}

}
