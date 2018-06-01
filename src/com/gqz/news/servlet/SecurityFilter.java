package com.gqz.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 
* @ClassName: SecurityFilter
* @Description: TODO(过滤器类)
* @author ganquanzhong
* @date 2017-11-23 上午09:25:24
 */
public class SecurityFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		//销毁的方法
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//过滤器的核心方法，起到过滤的作用
		
		//转换为 对象
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		
		//设置 请求编码  响应编码 
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		
		//创建session对象
		HttpSession session = req.getSession();
		//获取session会话，当登录成功时，session不为空值
		String username=(String)session.getAttribute("adminUsername");
		
		/** 《逻辑过程》
		  	拦截manager路劲下的JSP、Servlet	除去login.jsp ,AdminLogin页面
		  	已登录：判断URL 如果URL以login.jsp或AdminLogin结尾的话，就不拦截
		  	未登录：访问manager/···下面其他的JSP页面时，拦截跳转到login.jsp页面
		 */
		//获得URI路劲
		String uri=req.getRequestURI();
		if(uri.endsWith("login.jsp")||uri.endsWith("Mlogin.jsp")||uri.endsWith("AdminLogin")||
				uri.endsWith("css")||uri.endsWith("jpg")||uri.endsWith("gif")||uri.endsWith("js")){
			//本身就在login页面，合法操作，则不拦截
			//FilterChain：doFilter是导致过滤器链的下一个过滤器执行
			//如果当前过滤器是最后一个过滤器，直接访问请求的资源
			chain.doFilter(req, res);
		}else  if (username!=null) {
			//已登录时，不拦截可以继续访问manager/····下面的JSP页面
			chain.doFilter(req, res);
		}else{
			//未登录时，想访问manger目录下面的其他页面则拦截至登录页面，必须完成登录操作
			PrintWriter out = res.getWriter();
			out.print("<script>alert('你的操作不合法，请完成登录操作！！');window.location.href='../manager/login.jsp';</script>");
			//res.sendRedirect("login.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
