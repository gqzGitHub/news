package com.gqz.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gqz.news.DAO.NewsAdminDAO;
import com.gqz.news.util.MD5Util;

/**
 * 
* @ClassName: AdminLogin
* @Description: TODO(login登陆页面中的服务器程序,完成登录的逻辑操作)
* @author ganquanzhong
* @date 2017-11-22 上午09:38:02
 */
public class AdminLogin extends HttpServlet {

	/**
	 * Constructor of the object.
	 * 构造方法
	 */
	public AdminLogin() {
		super();
	}
	
	
	/*	一般 Servlet 只初始化一次(只有一个对象)，
		当 Server 不再需要 Servlet 时（一般当 Server 关闭时），
		Server 调用 Servlet 的 destroy() 方法。*/
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	//一般情况下  goGet和doPost方法均实现，如果是doGet请求则执行doPost请求
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理get请求
		System.out.println("service方法中的get请求");
		doPost(request, response);//如果是get请求，则执行doPost方法
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理post请求
		System.out.println("service方法中的post请求");
		/**
		 0.逻辑过程
		 1.获取用户在login.jsp页面输入的用户名和密码
		 2.判断用户名和密码是否正确
		 3.用户名和密码正确，跳转到index.jsp
		 4.用户名和密码不正确，提示用户不正确，停留在login.jsp页面
		 */
		//设置编码方式  服务器响应的编码与方式
		//response.setContentType("text/html;charset=utf-8"); 在编码过滤器中已经设置完成
		/**
		  response对象的部分方法
		  1.getOutputStream()：通过这个方法可以拿到一个字节流，然后可以向Response容器中写入字节数据，
		  			最后客户机向Response容器中拿去数据进行显示
		  2.getWriter()：通过这个方法可以拿到一个字符流(PrintWriter),然后可以向Response容器中写入字符数据，
		  			最后客户机向Response容器中拿去数据进行显示
  	 	  3.setContentLength()：通过这个方法设置服务器向用户返回的数据长度，
  	 	  			我们在HTTP协议详解这篇blog中的那个压缩数据的返回的例子中有说到
		  4.setContentType()：方法可以直接设置响应头content-type的内容
		 */
		
		/**
		  request对象部分方法
		  1.request.setCharacterEncoding（）：是设置从request中取得的值或从数据库中取出的值。
		 */
		//在编码过滤器中已经设置完成
		//request.setCharacterEncoding("utf-8");
		
		//response对象：服务器对用户响应的对象   
		//response对象调用getWriter方法获取PrintWriter对象
		PrintWriter out = response.getWriter();
		//1.获取用户在login.jsp页面输入的用户名与密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//2.判断用户名和密码是否正确
		//System.out.println(adminDAO.isExist(username,password));
		NewsAdminDAO newsAdminDAO =new NewsAdminDAO();
		
		if(username!=null&&!username.equals("")&&password!=null&&!password.equals("")){
			//将密码加密
			String passwordMD5=MD5Util.encoder(password);
			if(newsAdminDAO.isExist(username, passwordMD5) ){
				//存入登录信息。存入到HttpSession中
				System.out.println("*******登录成功！********");
				/**
				    当用户名和密码正确时，保存session 保存会话
				  1.创建session对象,通过request对象调用getSession方法
				  2.存入信息username信息到username对象中去
				 */
				HttpSession session = request.getSession();
				session.setAttribute("adminUsername", username);
				session.setAttribute("adminPwdMD5", passwordMD5);
				
				//console控制台输出
				System.err.println(username+"  "+password);

				
				//3.用户名和密码正确，跳转到index.jsp
				//方式一：弹出对话框，提示用户登录成功，确定后跳转到index.jsp页面
//				out.print("<script>alert('登录成功');" +
//						"window.location.href='index.jsp';</script>");
				
				/**
				  request 和  response的跳转方式的区别
				  	1.request.getRequestDispatcher("index.jsp").forward(request, response);
				  	页面的请求转发，转发之后，地址栏(不变)为先前的页面的URL，先前在request和response存入的信息会传递给跳转后的页面
				  	
				  	2.response.sendRedirect("index.jsp");
				  	页面的重定向，重定向之后，地址栏（更新）为跳转页面的URL，先前在request和response存入的信息不会传递给跳转后的页面
				 */
				
				//方式二：用户名和密码正确直接跳转到index.jsp页面
				out.print("<script>alert('登录成功！');</script>");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
				//方式三：使用response.sendRedirect()跳转
				//response.sendRedirect("index.jsp");
			}else {
				
				//4.用户名和密码不正确，提示用户不正确，停留在login.jsp页面
				//方式一：弹出对话框，提示用户登录失败，点击确定按钮后，从新跳转到login.jsp页面
//				out.print("<script>alert('登录失败！你输入的用户名或密码不正确！');" +
//						"window.location.href='login.jsp';</script>");
				
				//方式二：用户名和密码不正确。显示一个li标签提示用户"你输入的用户名和密码不正确！"跳转到login.jsp页面
				/**
				 	getRequestDispatcher()方法浅谈
				 	request.getRequestDispatcher("/路径（可以是jsp路径也可以是servlet）") .forward(request, response);
					如：
					request.getRequestDispatcher("/2.jsp）") .forward(request, response);
					request.getRequestDispatcher("/servlet/HomeServlet）") .forward(request, response);
	　　				Servlet页面跳转的路径是相对路径。forward方式只能跳转到本web应用中的页面上。
	　　				跳转后浏览器地址栏不会变化。
	　　				使用这种方式跳转，传值可以使用三种方法：url中带parameter，session，request.setAttribute
				 */
				//console控制台输出
				System.err.println(username+"  "+password);
				
				request.setAttribute("msg", "你输入的用户名或密码不正确！");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				out.print("<script>window.location.href='login.jsp';</script>");
			}
		}else {
			out.print("<script>alert('请输入用户名和密码！');window.location.href='Mlogin.jsp';</script>");
		}
		
	}

	
	//实例化一个servlet
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
