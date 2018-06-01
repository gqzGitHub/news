package com.gqz.news.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gqz.news.DAO.ForumDAO;
import com.gqz.news.DAO.NewsDAO;
import com.gqz.news.DAO.NewsUserDAO;
import com.gqz.news.DAO.NewsclassDAO;
import com.gqz.news.DAO.NoteDAO;
import com.gqz.news.DAO.ReplyDAO;
import com.gqz.news.common.BaseServlet;
import com.gqz.news.common.Page;
import com.gqz.news.common.WebUtil;
import com.gqz.news.factory.ForumDAOFactory;
import com.gqz.news.factory.NewsDAOFactory;
import com.gqz.news.factory.NewsUserDAOFactory;
import com.gqz.news.factory.NewsclassDAOFactory;
import com.gqz.news.factory.NoteDAOFactory;
import com.gqz.news.factory.ReplyDAOFactory;
import com.gqz.news.model.Forum;
import com.gqz.news.model.News;
import com.gqz.news.model.NewsUser;
import com.gqz.news.model.Newsclass;
import com.gqz.news.model.Note;
import com.gqz.news.model.Reply;
import com.gqz.news.util.MD5Util;

/**
 * 
 * @ClassName: Front
 * @Description: TODO(前台的所有servlet)
 * @author ganquanzhong
 * @date 2017年12月30日 下午11:32:37
 */
public class Front extends BaseServlet {
	/**
	 * 
	 * @Title: main
	 * @Description: TODO(访问前台首页)
	 * @author ganquanzhong
	 * @date 2017-12-15 上午10:51:43
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	/* http://localhost:8080/news/Front?op=main */
	public static String main(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// 1.显示新闻分类信息
		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		List<Newsclass> classList = newsclassDAO.getList();
		request.setAttribute("classList", classList);

		// 2.首页显示头条新闻
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();
		News topNews = newsDAO.getTopNews();
		WebUtil.removeHtml(topNews);// 移除html标签
		topNews.setTime(WebUtil.formateTime(topNews.getNewsTime()));// 将日期格式化
		//查询新闻的评论总数
		topNews.setNoteCount(NoteDAOFactory.getNoteDAOInstance().
				getTotalRecordNumById((String.valueOf(topNews.getNewsId()))));
		
		request.setAttribute("topNews", topNews);

		// 3.最新新闻部分
		List<News> recentNews = newsDAO.getRecentNews(7);
		WebUtil.removeHtml(recentNews.get(0));
		// 将日期格式化
		for (News news : recentNews) {
			news.setTime(WebUtil.formateTime(news.getNewsTime()));// 将日期格式化
			//查询新闻的评论总数
			news.setNoteCount(NoteDAOFactory.getNoteDAOInstance().
					getTotalRecordNumById((String.valueOf(news.getNewsId()))));
		}
		
		request.setAttribute("recentNews", recentNews);

		// 4.javaWeb分类（classId为1）新闻部分
		List<News> newsList1 = newsDAO.getNewsByClassId(classList.get(0)
				.getClassId(), 7);
		WebUtil.removeHtml(newsList1.get(0));
		// 将日期格式化
		for (News news : newsList1) {
			news.setTime(WebUtil.formateTime(news.getNewsTime()));// 将日期格式化
			//查询新闻的评论总数
			news.setNoteCount(NoteDAOFactory.getNoteDAOInstance().
					getTotalRecordNumById((String.valueOf(news.getNewsId()))));
		}
		request.setAttribute("newsList1", newsList1);

		// 大数据分类（classId为8）分类新闻部分
		List<News> newsList_bigData = newsDAO.getNewsByClassId(classList.get(7)
				.getClassId(), 7);
		WebUtil.removeHtml(newsList_bigData.get(0));
		// 将日期格式化
		for (News news : newsList_bigData) {
			news.setTime(WebUtil.formateTime(news.getNewsTime()));// 将日期格式化
			//查询新闻的评论总数
			news.setNoteCount(NoteDAOFactory.getNoteDAOInstance().
					getTotalRecordNumById((String.valueOf(news.getNewsId()))));
		}
		request.setAttribute("newsList_bigData", newsList_bigData);

		// 4.云计算分类（classId为9）分类新闻部分
		List<News> newsList_cloud = newsDAO.getNewsByClassId(classList.get(8)
				.getClassId(), 7);
		WebUtil.removeHtml(newsList_cloud.get(0));
		for (News news : newsList_cloud) {
			news.setTime(WebUtil.formateTime(news.getNewsTime()));// 将日期格式化
			//查询新闻的评论总数
			news.setNoteCount(NoteDAOFactory.getNoteDAOInstance().
					getTotalRecordNumById((String.valueOf(news.getNewsId()))));
		}
		request.setAttribute("newsList_cloud", newsList_cloud);

		// 5.其他分类新闻部分
		List<List<News>> allNewsList = new ArrayList<List<News>>();
		int length = 9;
		if (classList.size() < 9) {
			length = classList.size() - 1;
		}

//		System.out.println("-allNewsList中-显示的新闻类别是--" + length + "条");

		for (int i = 1; i <= length; i++) {
			// 根据新闻的分类classId，将classId为2（下标为i=1的）的后9个新闻的前8（1Top+7newsList）条新闻保存到每一个newsByClassId中
			List<News> newsByClassId = newsDAO.getNewsByClassId(classList
					.get(i).getClassId(), 8);
			for (News news : newsByClassId) {
				WebUtil.removeHtml(news);
				news.setTime(WebUtil.formateTime(news.getNewsTime()));// 将日期格式化
				//查询新闻的评论总数
				news.setNoteCount(NoteDAOFactory.getNoteDAOInstance().
						getTotalRecordNumById((String.valueOf(news.getNewsId()))));
			}
			allNewsList.add(newsByClassId);
		}
		request.setAttribute("newsList2", allNewsList);

		// 6.获取论坛列表
		ForumDAO forumDAO = ForumDAOFactory.getForumDAOInstance();
		// 获取论坛列表
		List<Forum> forumList = forumDAO.getForum();
		request.setAttribute("forumList", forumList);
//		System.out.println("论坛消息成功存储到forumList中！");

		// 在BaseServlet中，根据返回的值，页面跳转或者重定向
		return "/index.jsp";
	}

	/**
	 * 
	 * @Title: listAll
	 * @Description: TODO(分页获取未删除已审核的新闻列表，放入request对象中)
	 * @author ganquanzhong
	 * @date 2017-12-15 上午10:51:26
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public static String listAll(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// 1.显示新闻分类信息
		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		List<Newsclass> classList = newsclassDAO.getList();
		request.setAttribute("classList", classList);

		// 2.分页显示 所有已审核的新闻
		String num = request.getParameter("num");// 获取需要显示的页码
		// 第一次查询，没有设置查询的页码，pageNum的就是1
		int pageNum = 1;
		// 如果不是一次查询，用户点击上一页和下一页 超链接时，就有num页码
		if (num != null && !num.equals("")) {
			pageNum = Integer.parseInt(num);
		}
		// 2.1.查询新闻--即news表中的信息
		// 实例化一个newsDAO对象 ，使用工厂类的静态方法实现
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();

		int totalRecordNum = newsDAO.getVerifiedTotalRecordNum();// 获取
																	// 已审核的（Verified）总的记录数
		if (totalRecordNum != 0) {

			Page page = new Page(pageNum, totalRecordNum);// 构建分页对象

			// 访问NewsDAO getList方法，将 已审核的（Verified） news表中的新闻分类信息存入到request中去
			List<News> newList = newsDAO.getVerifiedListByPage(
					page.getStartIndex(), page.getPageSize());// 分页查询
			// 去除HTML标签
			for (News news : newList) {
				WebUtil.removeHtml(news);
			}

			page.setRecords(newList);// 保存 --设置每页显示的信息
			page.setUrl("/Front?op=listAll"); // 点击超链接跳转的url
			request.setAttribute("page", page);
		} else {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('该新闻分类尚未发布新闻！！');;window.location.href='Front?op=main'</script>");
		}
		return "/allnewsList.jsp";
	}

	/**
	 * 
	 * @Title: listByClass
	 * @Description: TODO(通过classId新闻的分类分页显示新闻)
	 * @author ganquanzhong
	 * @date 2017-12-18 上午10:27:17
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public static String listByClass(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// 1.显示新闻分类信息
		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		List<Newsclass> classList = newsclassDAO.getList();
		request.setAttribute("classList", classList);

		// 2.分页显示 所有已审核的新闻
		String num = request.getParameter("num");// 获取需要显示的页码
		// 第一次查询，没有设置查询的页码，pageNum的就是1
		int pageNum = 1;
		// 如果不是一次查询，用户点击上一页和下一页 超链接时，就有num页码
		if (num != null && !num.equals("")) {
			pageNum = Integer.parseInt(num);
		}
		// 获取需要分类的 分类号，后面的操作都是根据classId来进行的
		String classId = request.getParameter("classId");

		// 2.1.查询新闻--即news表中的信息
		// 实例化一个newsDAO对象 ，使用工厂类的静态方法实现
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();

		int totalRecordNum = newsDAO.getTotalRecordNumByClassId(classId);// 获取当前classId
																			// 已审核的总的记录数

		if (totalRecordNum != 0) {
			Page page = new Page(pageNum, totalRecordNum);// 构建分页对象

			// 访问NewsDAO getList方法，将 已审核的（Verified） news表中的新闻分类信息存入到request中去
			List<News> newsList = newsDAO.getNewsByClassId(classId,
					page.getStartIndex(), page.getPageSize());// 分页查询
			// 去除HTML标签
			for (News news : newsList) {
				WebUtil.removeHtml(news);
			}
			page.setRecords(newsList);// 保存 --设置每页显示的信息
			page.setUrl("/Front?op=listByClass&classId=" + classId); // 点击超链接跳转的url
			request.setAttribute("page", page);
		} else {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('该新闻分类尚未发布新闻！！');;window.location.href='Front?op=main'</script>");
		}
		return "/newsList.jsp";

	}

	/**
	 * 
	 * @Title: query
	 * @Description: TODO(完成搜索栏 的功能)
	 * @author ganquanzhong
	 * @date 2017-12-19 上午08:59:02
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public static String query(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// 1.显示新闻分类信息
		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		List<Newsclass> classList = newsclassDAO.getList();
		request.setAttribute("classList", classList);

		// 2.分页显示 所有已审核的新闻
		String num = request.getParameter("num");// 获取需要显示的页码
		// 第一次查询，没有设置查询的页码，pageNum的就是1
		int pageNum = 1;
		// 如果不是一次查询，用户点击上一页和下一页 超链接时，就有num页码
		if (num != null && !num.equals("")) {
			pageNum = Integer.parseInt(num);
		}

		// 获取需要搜索的方式 1.本网站 2.全网络
		String search = request.getParameter("search");

		// 获取需要搜索的内容 ，将input中输入的关键字进行搜索
		String queryContent = request.getParameter("q");
		// 判断用户选择的搜索方式是？
		if (search.equals("thissite")) {
			// 在本网站搜索
			// 2.1.查询新闻--即news表中的信息
			// 实例化一个newsDAO对象 ，使用工厂类的静态方法实现
			NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();

			// 调用NewsDAO的方法，获取记录的总数目
			int totalRecordNum = newsDAO.getTotalRecordNumByTitle(queryContent); // 获取当前queryContent
																					// 已审核的总的记录数
			Page page = new Page(pageNum, totalRecordNum);// 构建分页对象

			// 访问NewsDAO getList方法，将 已审核的（Verified） news表中的新闻分类信息存入到request中去
			List<News> newsList = newsDAO.getNewsByTitle(queryContent,
					page.getStartIndex(), page.getPageSize());// 分页查询
			// 去除HTML标签
			for (News news : newsList) {
				WebUtil.removeHtml(news);
			}
			page.setRecords(newsList);// 保存 --设置每页显示的信息
			page.setUrl("/Front?op=query&q=" + queryContent
					+ "&search=thissite"); // 点击超链接跳转的url
			request.setAttribute("page", page);
			// 将根据输入的关键字搜索到的的新闻分页显示在allnewsList.jsp中
			return "/allnewsList.jsp";
		} else {
			// 在整个网络中搜索,利用百度的搜索引擎搜索
			PrintWriter out = response.getWriter();
			out.print("<script>window.location.href='https://www.baidu.com/s?wd="
					+ queryContent + "'</script>");
		}
		return "";
	}

	/**
	 * 
	 * @Title: displayNews
	 * @Description: 
	 * TODO(根据newsId查询到新闻，并格式化新闻的时间，将点击率加一，然后将新闻保存到newsDetail的request对象中 )
	 * @author ganquanzhong
	 * @date 2017-12-20 上午08:28:33
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public static String displayNews(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// 1.显示新闻分类信息
		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		List<Newsclass> classList = newsclassDAO.getList();
		request.setAttribute("classList", classList);

		// 2.根据newsId查询到一条新闻的详细信息，并保存到request对象中去
		String newsId = request.getParameter("newsId");
		// 获取newsDAO的实例对象
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();
		// 调用newsDAO的方法，通过传递一个newsId参数，查询返回一个newsList集合(newsDetail)保存该条新闻的信息
		News newsDetail = newsDAO.getNewsDetailById(newsId);
		// 格式化新闻的发布时间
		newsDetail.setTime(WebUtil.formateTime(newsDetail.getNewsTime()));
		// 将新闻的点击率加一
		int hits = newsDetail.getHits();
		hits++;
		newsDetail.setHits(hits);
		newsDAO.updateHits(newsDetail);
		// 将news的对象保存在request对象中，方便在页面中显示
		request.setAttribute("newsDetail", newsDetail);
		return "/newsDetail.jsp";
	}

	/**
	 * 
	 * @Title: getNote
	 * @Description: TODO(服务器响应Ajax的异步请求getNote)
	 * @author ganquanzhong
	 * @date 2017-12-21 上午08:28:33
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String getNotes(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// 获取需要到note的newsId
		String newsId = request.getParameter("newsId");
		// 实例化一个NoteDAO对象
		NoteDAO noteDAO = NoteDAOFactory.getNoteDAOInstance();
		// 调用noteDAO的方法，获取指定newId的note
		List<Note> noteList = noteDAO.getNoteList(newsId);

		// 将list转换为JSONList
		JSONArray array = new JSONArray();
		for (Note note : noteList) {
			JSONObject object = new JSONObject();
			object.put("username", note.getUsername());
			object.put("time", WebUtil.formateTime(note.getNoteTime()));
			object.put("content", note.getContent());
			array.add(object);
		}
		// 使用response对象传递数据库给客户
		PrintWriter out = response.getWriter();
		out.print(array);
		return "";
	}

	/**
	 * 
	 * @Title: login
	 * @Description: TODO(新闻用户的登录)
	 * @author ganquanzhong
	 * @date 2017-12-21 上午11:57:00
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 获取用户登录的username
		String username = request.getParameter("username");
		// 获取用户登录的password
		String password = request.getParameter("password");
		System.out.println("用户名是：" + username + "  密码是：" + password);

		// 获取NewsUser对象实例
		NewsUserDAO newsUserDAO = NewsUserDAOFactory.getNewsUserDAOInstance();
		// 将表单中的密码使用MD5加密
		String encoderpassword = MD5Util.encoder(password);
		// 调用NewsUserDAO的方法，判断用户登录是否成功
		boolean exist = newsUserDAO.isExist(username, encoderpassword);

		// 使用response对象传递数据库给客户
		PrintWriter out = response.getWriter();
		out.print(exist);
		// 如果登录成功，则将用户名保存到会话中
		if (exist) {
			// 获取当前的一个会话
			HttpSession session = request.getSession();
			// 将username保存到session中
			session.setAttribute("newsUsername", username);
			System.out.println("-------" + username + "登录成功，已保存该会话！");
		}
		System.out.println(exist);
		return "";
	}

	/**
	 * 
	 * @Title: exit
	 * @Description: TODO(新闻用户退出登录)
	 * @author ganquanzhong
	 * @date 2017年12月25日 下午4:14:17
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String exit(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		// 取出session中的信息
		String username = (String) session.getAttribute("newsUsername");
		if (username != null) {
			// 删除session中的信息
			session.removeAttribute("newsUsername");
			session.invalidate();
			System.out.println("---" + username + "退出登录---seesion删除成功！！");
			out.print("您即将退出，确定后退出该页面!");
		}
		return "";
	}

	/**
	 * 
	 * @Title: addNote
	 * @Description: TODO(发表新闻的评论)
	 * @author ganquanzhong
	 * @date 2017-12-22 上午11:09:50
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String addNote(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// 获取相应属性的值，保存到note对象中去
		Note note = WebUtil.fillBean(request, Note.class);
		NoteDAO noteDAO = NoteDAOFactory.getNoteDAOInstance();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("newsUsername");
		PrintWriter out = response.getWriter();
		if (username == null) {
			out.print("你还没有登录，请先登录后在评论！");
		} else {
			// 登录成功后，可以发表评论
			note.setUsername(username);
			if (note.getContent() == null || note.getContent() == "") {
				out.print("评论内容不能为空！");
			} else {
				// 当评论不为空时，插入note表中
				int result = noteDAO.insert(note);
				if (result == 0) {
					out.print("发表评论失败！");
				} else {
					out.print("发表评论成功！");
				}
			}
		}
		return "";
	}

	/**
	 * 
	 * @Title: beforeReg
	 * @Description: TODO(跳转到注册页面)
	 * @author ganquanzhong
	 * @date 2017年12月25日 下午4:30:15
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String beforeReg(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// 1.显示新闻分类信息
		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		List<Newsclass> classList = newsclassDAO.getList();
		request.setAttribute("classList", classList);
		return "/regist.jsp";
	}

	/**
	 * 
	 * @Title: checkName
	 * @Description: TODO(注册时，检查是否存在该用户名)
	 * @author ganquanzhong
	 * @date 2017年12月25日 下午5:28:23
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String checkName(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		System.out.println("--------" + username);
		if (username != null && !username.equals("") && username.length() >= 2
				&& username.length() <= 18) {
			// 当输入合法的字符串时，才在数据库中进行判断
			NewsUserDAO userDAO = NewsUserDAOFactory.getNewsUserDAOInstance();
			boolean result = userDAO.existNewsUser(username);
			if (result) {
				out.print("你输入的用户名已存在，请输入其他用户名！");
			} else {
				out.print("         你输入的用户名可以被您使用！");
				System.out.println("注册的用户名为：" + username);
			}
		} else {
			out.print("您的输入不合法，请输入合法的用户名!！");
		}
		return "";
	}

	/**
	 * 
	 * @Title: regist
	 * @Description: TODO(新闻用户注册,需要验证码正确)
	 * @author ganquanzhong
	 * @date 2017年12月25日 下午6:04:25
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String regist(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String verifyCode = request.getParameter("verifyCode");
		// 获取验证码图片中session的vCode的值
		String imgvCode = (String) request.getSession().getAttribute("vCode");
		NewsUser user = WebUtil.fillBean(request, NewsUser.class);
		user.setUserName(username);
		// 如果验证码正确，才能注册
		if (imgvCode.equals(verifyCode)) {
			if (user.getUserName() != null && user.getPassword() != null
					&& !user.getUserName().equals("")
					&& !user.getPassword().equals("")) {
				NewsUserDAO userDAO = NewsUserDAOFactory
						.getNewsUserDAOInstance();
				// 加密保存
				user.setPassword(MD5Util.encoder(user.getPassword()));
				int result = userDAO.insert(user);
				if (result == 0) {
					out.print("<script>alert('注册失败！！');window.location.href='Front?op=beforeReg'</script>");
				} else {
					out.print("<script>alert('注册成功！');window.location.href='Front?op=main'</script>！");
				}
			} else {
				out.print("<script>alert('注册信息有误！！！');window.location.href='Front?op=beforeReg'</script>");
			}
		} else {
			out.print("<script>alert('验证码有误！！');window.location.href='Front?op=beforeReg'</script>");
		}
		return "";
	}

	/**
	 * 
	 * @Title: forum
	 * @Description: TODO(进入论坛页面)
	 * @author ganquanzhong
	 * @date 2018年1月2日 上午12:26:04
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String forum(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 1.显示新闻分类信息
		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		List<Newsclass> classList = newsclassDAO.getList();
		request.setAttribute("classList", classList);

		// 2.获取论坛列表
		// 获取需药查询的num号
		String forumId = request.getParameter("num");
		System.out.println("需要显示的num号为" + forumId);
		ForumDAO forumDAO = ForumDAOFactory.getForumDAOInstance();
		// 获取详细论坛消息
		Forum forumInfo = forumDAO.getForumById(forumId);
		// 格式化时间
		forumInfo.setFormatTime(WebUtil.formateTime(forumInfo.getTime()));
		request.setAttribute("forumInfo", forumInfo);
		System.out.println("论坛消息成功存储到forumInfo中！");

		// 3.获取指定的论坛的回复，评论
		ReplyDAO replyDAO = ReplyDAOFactory.getReplyDAOInstance();
		List<Reply> reply = replyDAO.getReply(forumId);
		// 格式化时间
		for (Reply reply2 : reply) {
			reply2.setFromatTime(WebUtil.formateTime(reply2.getTime()));
		}
		request.setAttribute("replyList", reply);
		return "forum.jsp";
	}

	/**
	 * 
	* @Title: listAllForum
	* @Description: TODO(显示所有的论坛)
	* @author ganquanzhong
	* @date  2018年1月4日 下午3:00:58
	* @param request
	* @param response
	* @return
	* @throws IOException
	* @throws ServletException
	 */
	public static String listAllForum(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// 1.显示新闻分类信息
		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		List<Newsclass> classList = newsclassDAO.getList();
		request.setAttribute("classList", classList);

		
		// 2.分页显示 所有已审核的新闻
		String num = request.getParameter("num");// 获取需要显示的页码
		// 第一次查询，没有设置查询的页码，pageNum的就是1
		int pageNum = 1;
		// 如果不是一次查询，用户点击上一页和下一页 超链接时，就有num页码
		if (num != null && !num.equals("")) {
			pageNum = Integer.parseInt(num);
		}
		
		
		// 2.1.查询新闻--即news表中的信息
		// 实例化一个newsDAO对象 ，使用工厂类的静态方法实现
		ForumDAO forumDAO = ForumDAOFactory.getForumDAOInstance();

		int totalRecordNum = forumDAO.getTotalRecordNum();// 获取
																	// 已审核的（Verified）总的记录数
		if (totalRecordNum != 0) {

			Page page = new Page(pageNum, totalRecordNum);// 构建分页对象

			// 访问NewsDAO getList方法，将 已审核的（Verified） news表中的新闻分类信息存入到request中去
			List<Forum> forumList = forumDAO.getForumByPage(page.getStartIndex(), page.getPageSize());// 分页查询
		
			for (Forum forum : forumList) {
				forum.setFormatTime(WebUtil.formateTime(forum.getTime()));//格式化时间
				WebUtil.removeForumHtml(forum);	// 去除HTML标签
			}

			page.setRecords(forumList);// 保存 --设置每页显示的信息
			page.setUrl("/Front?op=listAllForum"); // 点击超链接跳转的url
			request.setAttribute("page", page);
		} else {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('暂无更多论坛列表！！');;window.location.href='Front?op=main'</script>");
		}
		return "forumList.jsp";
	}

	/**
	 * 
	* @Title: addReply
	* @Description: TODO(添加论坛回复)
	* @author ganquanzhong
	* @date  2018年1月2日 下午11:29:07
	* @param request
	* @param response
	* @return
	* @throws IOException
	* @throws ServletException
	 */
	public String addReply(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		// 获取相应属性的值，保存到note对象中去
		Reply reply = WebUtil.fillBean(request, Reply.class);
		System.out.println("后台中显示content为"+reply.getContent());
		String forumId = request.getParameter("discussId");
		
		System.out.println("回复的论坛号为" + forumId);
		ReplyDAO replyDAO = ReplyDAOFactory.getReplyDAOInstance();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("newsUsername");
		PrintWriter out = response.getWriter();
		
		if (username == null) {
			out.print("你还没有登录，请先登录后在评论！");
		} else {
			// 登录成功后，可以发表评论
			reply.setName(username);
			if (reply.getContent() == null || reply.getContent() == "") {
				out.print("回复内容不能为空！！");
			} else {
				// 当评论不为空时，插入note表中
				int result = replyDAO.insert(reply,forumId);
				if (result == 0) {
					out.print("回复失败！！");
				} else {
					out.print("回复成功！！");
				}
			}
		}
		return "";
	}
	
	/**
	 * 
	* @Title: beforeForum
	* @Description: TODO(发表论坛之前的操作)
	* @author ganquanzhong
	* @date  2018年1月2日 下午11:33:02
	* @param request
	* @param response
	* @return
	* @throws IOException
	* @throws ServletException
	 */
	public String beforeForum(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// 1.显示新闻分类信息
		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		List<Newsclass> classList = newsclassDAO.getList();
		request.setAttribute("classList", classList);
		return "sendForum.jsp";
	}

	/**
	 * 
	 * @Title: addForum
	 * @Description: TODO(添加论坛)
	 * @author ganquanzhong
	 * @date 2018年1月2日 下午9:40:32
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String addForum(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// 1.显示新闻分类信息
		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		List<Newsclass> classList = newsclassDAO.getList();
		request.setAttribute("classList", classList);

		// 2.添加论坛
		// 在编码过滤器中已经完成设置
		// 设置请求编码
		request.setCharacterEncoding("utf-8");
		// 设置服务器响应编码
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// 1.判断是否支持文件上传，底层判断是否使用了enctype="multipart/form-data"
		boolean multipartContent = ServletFileUpload
				.isMultipartContent(request);
		if (!multipartContent) {
			throw new RuntimeException("the form is not multipart/form-data");
		}
		// 2.创建工厂--基于硬盘的文件列表工厂FileItemFactory factory = new
		// DiskFileItemFactory();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload suf = new ServletFileUpload(factory);
		// 3.解决上传文件的中文乱码问题
		suf.setHeaderEncoding("utf-8");
		// 4.解析request，获得上传所有内容，每一个内容都封装到一个对象（FileItem）中
		List<FileItem> items = new ArrayList<FileItem>();
		try {
			items = suf.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		// 5.判断是否是普通字段 item.isFormField()
		// 6.如果是普通字段 字段名item.getFormField();字段值 item.igetString(encoding)；
		// 7.如果不是普通字段，则是上传的文件, 获取上传文件名称item.getName()、上传内容item.getInputStream
		// 8.上传文件，将文件写入到服务器的指定位置下
		Forum forum=new Forum();
		//News news = new News();
		for (FileItem item : items) {
			if (item.isFormField()) {
				processFormFiled(item, forum);
			} else {
				processUploadFiled(item, forum);
			}
		}
		//NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();
		//int result = newsDAO.insert(news);
		ForumDAO forumDAO = ForumDAOFactory.getForumDAOInstance();
		int result = forumDAO.insert(forum);
		// 3.存储成功，提示用户存储成功，反之提示添加失败
		if (result == 0) {
			out.print("<script>alert('论坛发布失败!');"
					+ "window.location.href='Front?op=beforeForum'</script>");
		} else {
			out.print("<script>alert('论坛发布成功!');"
					+ "window.location.href='Front?op=main'</script>");
		}
		return "";
	}

	// /文件上传使用的方法 processUploadFiled
	private void processUploadFiled(FileItem item, Forum forum) {
		// TODO Auto-generated method stub
		String storeDirectory = getServletContext().getRealPath("/images");
		File rootDirectory = new File(storeDirectory);
		if (!rootDirectory.exists()) {
			rootDirectory.mkdirs();
		}
		// 改文件名
		String filename = item.getName();
		if (filename != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");
			filename = sdf.format(new Date()) + (int) (Math.random() * 100)
					+ "." + FilenameUtils.getExtension(filename);
			forum.setPictures(filename);
		}
		// 文件上传
		try {
			item.write(new File(rootDirectory, filename));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// /文件上传使用的方法 processFormFiled
	private void processFormFiled(FileItem item, Forum forum) {
		// TODO Auto-generated method stub

		try {
			String fieldName = item.getFieldName();
			String fieldValue = item.getString("utf-8");
			BeanUtils.setProperty(forum, fieldName, fieldValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
