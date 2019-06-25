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

import com.gqz.news.DAO.ForumDAO;
import com.gqz.news.DAO.NewsAdminDAO;
import com.gqz.news.DAO.NewsDAO;
import com.gqz.news.DAO.NewsUserDAO;
import com.gqz.news.DAO.NewsclassDAO;
import com.gqz.news.DAO.NoteDAO;
import com.gqz.news.common.BaseServlet;
import com.gqz.news.common.Page;
import com.gqz.news.common.WebUtil;
import com.gqz.news.factory.ForumDAOFactory;
import com.gqz.news.factory.NewsAdminDAOFactory;
import com.gqz.news.factory.NewsDAOFactory;
import com.gqz.news.factory.NewsUserDAOFactory;
import com.gqz.news.factory.NewsclassDAOFactory;
import com.gqz.news.factory.NoteDAOFactory;
import com.gqz.news.model.Forum;
import com.gqz.news.model.News;
import com.gqz.news.model.NewsAdmin;
import com.gqz.news.model.NewsUser;
import com.gqz.news.model.Newsclass;
import com.gqz.news.model.Note;
import com.gqz.news.util.MD5Util;

/**
 * 
 * @ClassName: Back
 * @Description: TODO(后台的所有servlet)
 * @author ganquanzhong
 * @date 2017年12月30日 下午11:32:02
 */
public class Back extends BaseServlet {

	/**
	 * 
	 * @Title: addAdmin
	 * @Description: TODO(添加管理员)
	 * @author ganquanzhong
	 * @date 2018年1月3日 下午5:34:36
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String addAdmin(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		/**
		 * 获取addAdmin页面上的1.管理员用户名 2.管理员密码 3.管理员类型 将获取的数据存入到数据库中去
		 * 信息存入成功，页面跳转到当前页面
		 */
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		if (username == null || password == null || type == null
				|| username.equals("") || password.equals("")
				|| type.equals(" ")) {
			out.println("<script>alert('你的输入不合法！请重新输入');window.location.href='addAdmin.jsp';</script>");
		} else {
			// 将密码加密
			String passwordMD5 = MD5Util.encoder(password);
			NewsAdminDAO newsAdminDAO = NewsAdminDAOFactory.getNewsAdminDAOInstance();
			int result = newsAdminDAO.Insert(username, passwordMD5, type);
			if (result != 0) {
				out.println("<script>alert('添加成功！！');window.location.href='addAdmin.jsp';</script>");
			} else {
				out.println("<script>alert('添加失败！！');window.location.href='addAdmin.jsp';</script>");
			}
		}
		return "";
	}

	/**
	 * 
	* @Title: editAdminName
	* @Description: TODO(修改管理员用户名)
	* @author ganquanzhong
	* @date  2018年1月3日 下午11:07:41
	* @param request
	* @param response
	* @return
	* @throws IOException
	* @throws ServletException
	 */
	public String editAdminName(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		String oldusername = request.getParameter("name");
		String newusername = request.getParameter("username");
		String type = request.getParameter("type");
		if (newusername == null || newusername.equals("")) {
			out.print("<script>alert('您的输入不合法，请重新输入!');window.location.href='editAdmin.jsp'</script>");
		} else {
			NewsAdminDAO newsAdminDAO = NewsAdminDAOFactory.getNewsAdminDAOInstance();
			int result = newsAdminDAO.UpdateName(newusername, type, oldusername);
			if (result == 0) {
				out.print("<script>alert('用户名修改失败!');window.location.href='editAdmin.jsp'</script>");
			} else {
				out.print("<script>alert('用户名修改成功!,请重新登录');window.location.href='AdminQuit'</script>");
			}
		}
		return "";
	}
	
	
	/**
	 * 
	 * @Title: changePassword
	 * @Description: TODO(修改当前用户的密码)
	 * @author ganquanzhong
	 * @date 2018年1月3日 下午4:21:45
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String changePassword(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		/*
		 * 完成修改密码的功能 1.获取表单中的元素：原密码、新密码、确认新密码 2.将原密码与数据库中的密码对比，若相同则进行下一步操作
		 * 3.两次新密码相同则将新密码存入数据库
		 */
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 前台页面后去参数
		String password = request.getParameter("password");// 原密码
		String password2 = request.getParameter("password2");// 新密码
		String repassword = request.getParameter("repassword");// 确认密码
		// System.out.println(password + "-----" + password2 + "------"+
		// repassword);
		// 获取数据库连接
		// 创建session对象
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("adminUsername");
		String OriginPwdMD5 = (String) session.getAttribute("adminPwdMD5");
		// 将原密码与数据库中的密码对比
		if (password == null || password2 == null || repassword == null
				|| password.equals("") || password2.equals("")
				|| repassword.equals("")) {
			out.print("<script>alert('你的输入不合法！，请重新输入！');window.location.href='changePassword.jsp';</script>");
		} else if (!OriginPwdMD5.equals(MD5Util.encoder(password))) {
			out.print("<script>alert('原始密码错误！');window.location.href='changePassword.jsp';</script>");
		} else if (!password2.equals(repassword)) {
			out.print("<script>alert('两次密码不一致！');window.location.href='changePassword.jsp';</script>");
		} else {
			// 满足修改密码的条件
			NewsAdminDAO newsAdminDAO = NewsAdminDAOFactory
					.getNewsAdminDAOInstance();
			int result = newsAdminDAO.UpdatePwd(MD5Util.encoder(repassword),
					userName);
			if (result == 0) {
				out.print("<script>alert('密码修改失败！');window.location.href='changePassword.jsp';</script>");
			} else {
				out.print("<script>alert('密码修改成功！');window.location.href='Mlogin.jsp';</script>");
			}
		}
		return "";
	}

	/**
	 * 
	 * @Title: addNewsClass
	 * @Description: TODO(添加新闻分类)
	 * @author ganquanzhong
	 * @date 2018年1月3日 下午5:51:14
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String addNewsClass(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String content = request.getParameter("content");
		if (content == null || content.equals("")) {
			out.print("<script>alert('您的输入不合法，请重新输入!');window.location.href='addNewsclass.jsp'</script>");
		} else {
			NewsclassDAO newsclassDAO = NewsclassDAOFactory
					.getNewsclassDAOInstance();
			int result = newsclassDAO.Insert(content);
			if (result == 0) {
				out.print("<script>alert('新闻分类添加失败!');window.location.href='addNewsclass.jsp'</script>");
			} else {
				out.print("<script>alert('新闻分类添加成功!');window.location.href='Back?op=newsClassList'</script>");
			}
		}
		return "";
	}

	/**
	 * 
	 * @Title: newsClassList
	 * @Description: TODO(新闻分类列表)
	 * @author ganquanzhong
	 * @date 2018年1月3日 下午6:08:37
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String newsClassList(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 2.分页显示 所有已审核的新闻
		String num = request.getParameter("num");// 获取需要显示的页码
		// 第一次查询，没有设置查询的页码，pageNum的就是1
		int pageNum = 1;
		// 如果不是一次查询，用户点击上一页和下一页 超链接时，就有num页码
		if (num != null && !num.equals("")) {
			pageNum = Integer.parseInt(num);
		}
		// 实例化一个newsclassDAO对象 ，使用工厂类的静态方法实现
		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		int totalRecordNum = newsclassDAO.getTotalRecordNum();// 获取 总的记录数
		if (totalRecordNum != 0) {
			// 如果评论数不为零时
			Page page = new Page(pageNum, totalRecordNum);// 构建分页对象
			// 访问newsclassDAO getNewsClassByPage方法，将newsclass表中的信息存入到request中去

			List<Newsclass> newsClassList = newsclassDAO.getNewsClassByPage(
					page.getStartIndex(), page.getPageSize());// 分页查询
			page.setRecords(newsClassList);// 保存 --设置每页显示的信息
			page.setUrl("/manager/Back?op=newsClassList"); // 点击超链接跳转的url
			request.setAttribute("page", page);
			// 只能用request的方法跳转，引文request中存放这数据
		} else {
			// 评论数为零时，不显示noteList.jsp页面
			PrintWriter out = response.getWriter();
			out.print("<script>alert('暂无新闻分类！!');window.location.href='index.jsp'</script>");
		}
		return "newsclassList.jsp";
	}

	/**
	 * 
	 * @Title: delNewsClass
	 * @Description: TODO(删除一条新闻分类)
	 * @author ganquanzhong
	 * @date 2018年1月3日 下午6:52:18
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String delNewsClass(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		// 获取需要删除的新闻分类的id
		String classId = request.getParameter("classId");

		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();

		int result = newsclassDAO.delete(classId);
		// 删除成功，提示用户修改成功，反之提示修改失败
		if (result == 0) {
			out.print("新闻分类删除失败!");
		} else {
			out.print("新闻分类删除成功!");
		}
		return "";
	}

	/**
	 * 
	 * @Title: preEditNewsClass
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author ganquanzhong
	 * @date 2018年1月3日 下午7:07:20
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String preEditNewsClass(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String classId = request.getParameter("classId");

		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		Newsclass newsclass = newsclassDAO.getNewsclassById(classId);
		// 将该新闻信息存放到request对象中，方便在页面显示
		request.setAttribute("newsclass", newsclass);
		return "editNewsclass.jsp";
	}

	/**
	 * 
	 * @Title: editNewsClass
	 * @Description: TODO(修改分类新闻)
	 * @author ganquanzhong
	 * @date 2018年1月3日 下午7:17:22
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String editNewsClass(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String classId = request.getParameter("classId");
		String content = request.getParameter("content");
		if (content == null || content.equals("")) {
			out.print("<script>alert('您的输入不合法，请重新输入!');window.location.href='Back?op=newsClassList'</script>");
		} else {
			NewsclassDAO newsclassDAO = NewsclassDAOFactory
					.getNewsclassDAOInstance();
			int result = newsclassDAO.update(classId, content);
			if (result == 0) {
				out.print("<script>alert('新闻分类修改失败!');window.location.href='Back?op=newsClassList'</script>");
			} else {
				out.print("<script>alert('新闻分类修改成功!');window.location.href='Back?op=newsClassList'</script>");
			}
		}
		return "";
	}

	/**
	 * 
	 * @Title: forumList
	 * @Description: TODO(显示论坛列表)
	 * @author ganquanzhong
	 * @date 2018年1月3日 下午7:46:48
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String forumList(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub

		// 2.分页显示 所有已审核的新闻
		String num = request.getParameter("num");// 获取需要显示的页码
		// 第一次查询，没有设置查询的页码，pageNum的就是1
		int pageNum = 1;
		// 如果不是一次查询，用户点击上一页和下一页 超链接时，就有num页码
		if (num != null && !num.equals("")) {
			pageNum = Integer.parseInt(num);
		}

		ForumDAO forumDAO = ForumDAOFactory.getForumDAOInstance();
		int totalRecordNum = forumDAO.getTotalRecordNum();

		if (totalRecordNum != 0) {
			// 如果评论数不为零时
			Page page = new Page(pageNum, totalRecordNum);// 构建分页对象

			List<Forum> forumList = forumDAO.getForumByPage(
					page.getStartIndex(), page.getPageSize());
			// 部分显示
			for (Forum forum : forumList) {
				WebUtil.removeForumHtml(forum);
				forum.setFormatTime(WebUtil.formateTime(forum.getTime()));// 格式化时间
			}
			page.setRecords(forumList);// 保存 --设置每页显示的信息
			page.setUrl("/manager/Back?op=forumList"); // 点击超链接跳转的url
			request.setAttribute("page", page);
		} else {
			// 评论数为零时，不显示noteList.jsp页面
			PrintWriter out = response.getWriter();
			out.print("<script>alert('暂无论坛！!');window.location.href='index.jsp'</script>");
		}
		return "forumList.jsp";
	}

	/**
	 * 
	 * @Title: delForum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author ganquanzhong
	 * @date 2018年1月3日 下午8:51:47
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String delForum(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		// 获取需要删除的评论的noteId
		String forumId = request.getParameter("forumId");

		ForumDAO forumDAO = ForumDAOFactory.getForumDAOInstance();

		int result = forumDAO.delete(forumId);
		// 删除成功，提示用户修改成功，反之提示修改失败
		if (result == 0) {
			out.print("论坛删除失败!");
		} else {
			out.print("论坛删除成功!");
		}
		return "";
	}

	/**
	 * 
	 * @Title: addForum
	 * @Description: TODO(添加论坛)
	 * @author ganquanzhong
	 * @date 2018年1月3日 下午9:28:46
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String addForum(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
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
		Forum forum = new Forum();
		for (FileItem item : items) {
			if (item.isFormField()) {
				processFormFiled(item, forum);
			} else {
				processUploadFiled(item, forum);
			}
		}
		ForumDAO forumDAO = ForumDAOFactory.getForumDAOInstance();
		int result = forumDAO.insert(forum);
		// 3.存储成功，提示用户存储成功，反之提示添加失败
		if (result == 0) {
			out.print("<script>alert('论坛发布失败!');"
					+ "window.location.href='addForum.jsp'</script>");
		} else {
			out.print("<script>alert('论坛发布成功!');"
					+ "window.location.href='Back?op=forumList'</script>");
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

	/**
	 * 
	* @Title: adminList
	* @Description: TODO(管理员列表)
	* @author ganquanzhong
	* @date  2018年1月4日 下午1:41:56
	* @param request
	* @param response
	* @return
	* @throws IOException
	* @throws ServletException
	 */
	public String adminList(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 2.分页显示 所有已审核的新闻
		String num = request.getParameter("num");// 获取需要显示的页码
		// 第一次查询，没有设置查询的页码，pageNum的就是1
		int pageNum = 1;
		// 如果不是一次查询，用户点击上一页和下一页 超链接时，就有num页码
		if (num != null && !num.equals("")) {
			pageNum = Integer.parseInt(num);
		}

		// 实例化一个newsAdminDAO对象 ，使用工厂类的静态方法实现
		NewsAdminDAO newsAdminDAO = NewsAdminDAOFactory.getNewsAdminDAOInstance();
		
		int totalRecordNum = newsAdminDAO.getTotalRecordNum();// 获取 总的记录数

		if (totalRecordNum != 0) {
			// 如果评论数不为零时
			Page page = new Page(pageNum, totalRecordNum);// 构建分页对象
			// 访问newsclassDAO getNewsClassByPage方法，将newsclass表中的信息存入到request中去

			List<NewsAdmin> newsAdminList = newsAdminDAO.getNewsAdminByPage(page.getStartIndex(), page.getPageSize());// 分页查询
			for (NewsAdmin newsAdmin : newsAdminList) {
				newsAdmin.setFormatTime(WebUtil.formateTime(newsAdmin.getLastLogin()));
			}
			page.setRecords(newsAdminList);// 保存 --设置每页显示的信息
			page.setUrl("/manager/Back?op=adminList"); // 点击超链接跳转的url
			request.setAttribute("page", page);
			// 只能用request的方法跳转，引文request中存放这数据
		} else {
			// 评论数为零时，不显示noteList.jsp页面
			PrintWriter out = response.getWriter();
			out.print("<script>alert('暂无管理员用户！!');window.location.href='index.jsp'</script>");
		}
		return "adminList.jsp";
	}
	
	/**
	 * 
	* @Title: delAdmin
	* @Description: TODO(删除管理员)
	* @author ganquanzhong
	* @date  2018年1月4日 下午2:09:06
	* @param request
	* @param response
	* @return
	* @throws IOException
	* @throws ServletException
	 */
	public String delAdmin(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		// 获取需要删除的用户id
		String adminId = request.getParameter("adminId");
		NewsAdminDAO newsAdminDAO = NewsAdminDAOFactory.getNewsAdminDAOInstance();
		int result = newsAdminDAO.delete(adminId);
		// 删除成功，提示用户修改成功，反之提示修改失败
		if (result == 0) {
			out.print("删除失败!");
		} else {
			out.print("删除成功!");
		}
		return "";
	}
	
	/**
	 * 
	 * @Title: userList
	 * @Description: TODO(用户列表)
	 * @author ganquanzhong
	 * @date 2018年1月3日 下午10:03:46
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String userList(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 2.分页显示 所有已审核的新闻
		String num = request.getParameter("num");// 获取需要显示的页码
		// 第一次查询，没有设置查询的页码，pageNum的就是1
		int pageNum = 1;
		// 如果不是一次查询，用户点击上一页和下一页 超链接时，就有num页码
		if (num != null && !num.equals("")) {
			pageNum = Integer.parseInt(num);
		}

		// 实例化一个newsUserDAO对象 ，使用工厂类的静态方法实现
		NewsUserDAO newsUserDAO = NewsUserDAOFactory.getNewsUserDAOInstance();
		int totalRecordNum = newsUserDAO.getTotalRecordNum();// 获取 总的记录数

		if (totalRecordNum != 0) {
			// 如果评论数不为零时
			Page page = new Page(pageNum, totalRecordNum);// 构建分页对象
			// 访问newsclassDAO getNewsClassByPage方法，将newsclass表中的信息存入到request中去

			List<NewsUser> newsUserList = newsUserDAO.getNewsUserByPage(page.getStartIndex(), page.getPageSize());// 分页查询
			page.setRecords(newsUserList);// 保存 --设置每页显示的信息
			page.setUrl("/manager/Back?op=userList"); // 点击超链接跳转的url
			request.setAttribute("page", page);
			// 只能用request的方法跳转，引文request中存放这数据
		} else {
			// 评论数为零时，不显示noteList.jsp页面
			PrintWriter out = response.getWriter();
			out.print("<script>alert('暂无用户！!');window.location.href='index.jsp'</script>");
		}
		return "userList.jsp";
	}

	/**
	 * 
	* @Title: delUser
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author ganquanzhong
	* @date  2018年1月3日 下午10:40:00
	* @param request
	* @param response
	* @return
	* @throws IOException
	* @throws ServletException
	 */
	public String delUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		// 获取需要删除的用户id
		String userId = request.getParameter("userId");
		NewsUserDAO newsUserDAO = NewsUserDAOFactory.getNewsUserDAOInstance();
		int result = newsUserDAO.delete(userId);
		// 删除成功，提示用户修改成功，反之提示修改失败
		if (result == 0) {
			out.print("删除失败!");
		} else {
			out.print("删除成功!");
		}
		return "";
	}

	/**
	 * 
	 * @Title: delNote
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author ganquanzhong
	 * @date 2017-12-29 上午10:26:09
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public String delNote(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		// 获取需要删除的评论的noteId
		String noteId = request.getParameter("noteId");
		// 实例化一个noteDAO对象 ，使用工厂类的静态方法实现
		NoteDAO noteDAO = NoteDAOFactory.getNoteDAOInstance();
		// 调用NoteDAO的数据库操作方法
		int result = noteDAO.delete(noteId);
		// 删除成功，提示用户修改成功，反之提示修改失败
		if (result == 0) {
			out.print("评论删除失败!");
		} else {
			out.print("评论删除成功!");
		}
		return "";
	}

	/**
	 * 
	 * @Title: noteList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author ganquanzhong
	 * @date 2017-12-29 上午10:26:14
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String noteList(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 2.分页显示 所有已审核的新闻
		String num = request.getParameter("num");// 获取需要显示的页码
		// 第一次查询，没有设置查询的页码，pageNum的就是1
		int pageNum = 1;
		// 如果不是一次查询，用户点击上一页和下一页 超链接时，就有num页码
		if (num != null && !num.equals("")) {
			pageNum = Integer.parseInt(num);
		}

		// 2.1.查询评论--即note表中的信息
		String newsId = request.getParameter("newsId");// 获取需要显示的新闻的评论

		// 实例化一个NoteDAO对象 ，使用工厂类的静态方法实现
		NoteDAO noteDAO = NoteDAOFactory.getNoteDAOInstance();
		int totalRecordNum = noteDAO.getTotalRecordNumById(newsId);// 获取 总的记录数

		if (totalRecordNum != 0) {
			// 如果评论数不为零时
			Page page = new Page(pageNum, totalRecordNum);// 构建分页对象

			// 访问NoteDAO getNoteByPage方法，将note表中的信息存入到request中去
			List<Note> noteList = noteDAO.getNoteByPage(newsId,
					page.getStartIndex(), page.getPageSize());// 分页查询

			page.setRecords(noteList);// 保存 --设置每页显示的信息
			page.setUrl("/manager/Back?op=noteList&newsId=" + newsId); // 点击超链接跳转的url
			request.setAttribute("page", page);
			// 只能用request的方法跳转，引文request中存放这数据
			// request.getRequestDispatcher("noteList.jsp").forward(request,
			// response);
		} else {
			// 评论数为零时，不显示noteList.jsp页面
			PrintWriter out = response.getWriter();
			out.print("<script>alert('该新闻暂无评论！!');window.location.href='NewsList?op=newsList'</script>");
		}
		return "noteList.jsp";
	}

	/**
	 * 
	 * @Title: batchDel
	 * @Description: TODO(批量删除新闻)
	 * @author ganquanzhong
	 * @date 2017-12-12 上午11:40:45
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public String batchDel(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		// 获取需要删除的新闻的newsId
		String[] newsDels = request.getParameterValues("NewsDels[]");
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();
		int result = 0;
		for (String id : newsDels) {
			result = newsDAO.delete(id);
			System.out.println("classId为" + id + "新闻删除！");
		}
		if (result == 0) {
			out.print("批量删除失败！");
		} else {
			out.print("批量删除成功！");
		}
		return "";
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: TODO(删除新闻 delete)
	 * @author ganquanzhong
	 * @date 2017-12-7 上午09:23:59
	 * @param request
	 * @param response
	 */
	public String delete(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		// 获取需要修改的新闻的 newsId
		String newsId = request.getParameter("newsId");
		// 实例化一个newsDAO对象 ，使用工厂类的静态方法实现
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();
		// 从news表中获取对应的newsId的新闻信息
		// News news=newsDAO.getNewsById(newsId);
		int result = newsDAO.delete(newsId);
		// 删除成功，提示用户修改成功，反之提示修改失败
		if (result == 0) {
			out.print("新闻删除失败!");
		} else {
			out.print("新闻删除成功!");
		}
		return "";
	}

	/**
	 * 
	 * @Title: newsList
	 * @Description: TODO(新闻列表显示 管理)
	 * @author ganquanzhong
	 * @date 2017-12-6 上午11:20:11
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public static String newsList(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");// 获取需要显示的页码
		// 第一次查询，没有设置查询的页码，pageNum的就是1
		int pageNum = 1;
		// 如果不是一次查询，用户点击上一页和下一页 超链接时，就有num页码
		if (num != null && !num.equals("")) {
			pageNum = Integer.parseInt(num);
		}

		// 1.查询新闻分类--即newclass表中的信息
		// 实例化一个NewsClassclassDAO对象，使用工厂类的静态方法实现
		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		// 访问NewsclassDAO getList方法，将newsclass表中的新闻分类信息存入到request中去
		List<Newsclass> newsclassList = newsclassDAO.getList();
		request.setAttribute("newsclassList", newsclassList);

		// 2.查询新闻--即news表中的信息
		// 实例化一个newsDAO对象 ，使用工厂类的静态方法实现
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();

		int totalRecordNum = newsDAO.getTotalRecordNum();// 获取总的记录数
		Page page = new Page(pageNum, totalRecordNum);// 构建分页对象

		// 访问NewsDAO getList方法，将news表中的新闻分类信息存入到request中去
		List<News> newList = newsDAO.getListByPage(page.getStartIndex(),
				page.getPageSize());// 分页查询

		page.setRecords(newList);// 设置每页显示的信息
		page.setUrl("/manager/Back?op=newsList"); // 点击超链接跳转的url

		request.setAttribute("page", page);
		// 只能用request的方法跳转，引文request中存放这数据
		// request.getRequestDispatcher("newsList.jsp").forward(request,
		// response);
		return "newsList.jsp";
	}

	/**
	 * 
	 * @Title: preEdit
	 * @Description: TODO(编辑新闻的前期工作 从news表中获取对应的newsId的新闻信息 将修改的页面显示出来)
	 * @author ganquanzhong
	 * @date 2017-12-6 上午11:24:27
	 * @param request
	 * @param response
	 */
	public String preEdit(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 获取需要修改的新闻的 newsId
		String newsId = request.getParameter("newsId");

		// 实例化一个newsDAO对象 ，使用工厂类的静态方法实现
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();

		// 从news表中获取对应的newsId的新闻信息
		News news = newsDAO.getNewsById(newsId);

		// 将该新闻信息存放到request对象中，方便在页面显示
		request.setAttribute("news", news);

		// 实例化一个newsclassDAO对象 ，使用工厂类的静态方法实现
		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		// 访问NewsDAO getList方法，将news表中的新闻分类信息存入到request中去
		List<Newsclass> classList = newsclassDAO.getList();
		request.setAttribute("classList", classList);
		// 新闻分类表中的信息存入到request中去后，页面跳转到addNews.jsp页面中去，
		// 只能用request的方法跳转，引文request中存放这数据
		// request.getRequestDispatcher("editNews.jsp").forward(request,
		// response);
		return "editNews.jsp";
	}

	/**
	 * 
	 * @Title: edit
	 * @Description: TODO(编辑新闻 更新新闻的各个内容)
	 * @author ganquanzhong
	 * @date 2017-12-6 上午11:24:13
	 * @param request
	 * @param response
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
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
		News news = new News();
		for (FileItem item : items) {
			if (item.isFormField()) {
				processFormFiled(item, news);
			} else {
				processUploadFiled(item, news);
			}
		}
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();
		// 调用newsDAO的update更新方法
		int result = newsDAO.update(news);

		// 3.修改成功，提示用户修改成功，反之提示修改失败
		if (result == 0) {
			out.print("<script>alert('新闻修改失败!');"
					+ "window.location.href='NewsList?op=newsList'</script>");
		} else {
			out.print("<script>alert('新闻修改成功!');"
					+ "window.location.href='NewsList?op=newsList'</script>");
		}
		return "";
	}

	
	
	/**
	 * 
	* @Title: preVerify
	* @Description: TODO(审核新闻之前的操作)
	* @author ganquanzhong
	* @date  2018年1月3日 下午11:36:30
	* @param request
	* @param response
	* @return
	* @throws IOException
	* @throws ServletException
	 */
	public String preVerify(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 获取需要修改的新闻的 newsId
		String newsId = request.getParameter("newsId");

		// 实例化一个newsDAO对象 ，使用工厂类的静态方法实现
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();

		// 从news表中获取对应的newsId的新闻信息
		News news = newsDAO.getNewsById(newsId);

		// 将该新闻信息存放到request对象中，方便在页面显示
		request.setAttribute("news", news);

		// 实例化一个newsclassDAO对象 ，使用工厂类的静态方法实现
		NewsclassDAO newsclassDAO = NewsclassDAOFactory.getNewsclassDAOInstance();
		// 访问NewsDAO getList方法，将news表中的新闻分类信息存入到request中去
		List<Newsclass> classList = newsclassDAO.getList();
		request.setAttribute("classList", classList);
		// 新闻分类表中的信息存入到request中去后，页面跳转到addNews.jsp页面中去，
		// 只能用request的方法跳转，引文request中存放这数据
		// request.getRequestDispatcher("editNews.jsp").forward(request,
		// response);
		return "verifyNews.jsp";
	}
	
	/**
	 * 
	* @Title: Verify
	* @Description: TODO(审核新闻)
	* @author ganquanzhong
	* @date  2018年1月3日 下午11:51:49
	* @param request
	* @param response
	* @return
	* @throws IOException
	* @throws ServletException
	 */
	public String Verify(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		// 获取需要修改的新闻的 newsId
		String newsId = request.getParameter("newsId");
		// 实例化一个newsDAO对象 ，使用工厂类的静态方法实现
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();
		int result = newsDAO.verifyNews(newsId);
		if (result == 0) {
			out.print("<script>alert('审核失败!');window.location.href='NewsList?op=newsList'</script>");
		} else {
			out.print("<script>alert('审核成功!');window.location.href='NewsList?op=newsList'</script>");
		}
		return "";
	}
	
	
	/**
	 * 
	 * @Title: add
	 * @Description: TODO( 添加新闻 )
	 * @author ganquanzhong
	 * @date 2017-12-6 上午11:21:27
	 * @param request
	 * @param response
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
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
		News news = new News();
		for (FileItem item : items) {
			if (item.isFormField()) {
				processFormFiled(item, news);
			} else {
				processUploadFiled(item, news);
			}
		}
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();
		int result = newsDAO.insert(news);

		// 3.存储成功，提示用户存储成功，反之提示添加失败
		if (result == 0) {
			out.print("<script>alert('新闻添加失败!');"
					+ "window.location.href='NewsList?op=preAdd'</script>");
		} else {
			out.print("<script>alert('新闻添加成功!');"
					+ "window.location.href='NewsList?op=newsList'</script>");
		}
		return "";
	}

	/**
	 * 
	 * @Title: preAdd
	 * @Description: TODO(添加新闻的前期操作)
	 * @author ganquanzhong
	 * @date 2017-12-6 上午11:23:45
	 * @param request
	 * @param response
	 */
	public static String preAdd(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 实例化一个newsclassDAO对象 ，使用工厂类的静态方法实现
		NewsclassDAO newsclassDAO = NewsclassDAOFactory
				.getNewsclassDAOInstance();
		// 访问NewsclassDAO getList方法，将newsclass表中的新闻分类信息存入到request中去
		List<Newsclass> classList = newsclassDAO.getList();
		request.setAttribute("classList", classList);

		// 新闻分类表中的信息存入到request中去后，页面跳转到addNews.jsp页面中去，
		// 只能用request的方法跳转，引文request中存放这数据
		// request.getRequestDispatcher("addNews.jsp").forward(request,
		// response);
		return "addNews.jsp";
	}

	// /文件上传使用的方法 processUploadFiled
	private void processUploadFiled(FileItem item, News news) {
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
			news.setPictures(filename);
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
	private void processFormFiled(FileItem item, News news) {
		// TODO Auto-generated method stub

		try {
			String fieldName = item.getFieldName();
			String fieldValue = item.getString("utf-8");
			BeanUtils.setProperty(news, fieldName, fieldValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
