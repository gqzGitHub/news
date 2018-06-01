package com.gqz.news.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.gqz.news.DAO.NewsDAO;
import com.gqz.news.DAO.NewsclassDAO;
import com.gqz.news.DAO.NoteDAO;
import com.gqz.news.common.Page;
import com.gqz.news.factory.NewsDAOFactory;
import com.gqz.news.factory.NewsclassDAOFactory;
import com.gqz.news.factory.NoteDAOFactory;
import com.gqz.news.model.News;
import com.gqz.news.model.Newsclass;
import com.gqz.news.model.Note;

/**
 * 
* @ClassName: NewsList
* @Description: TODO(新闻列表 管理)
* @author ganquanzhong
* @date 2017-12-4 上午10:32:54
 */
public class NewsList extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 为方便管理Servlet,避免定义太多的Servlet（消耗内存），
		//所以，在调用NewsList时，传递一个参数op，代表要进行的操作，方便调用NewsList中不同的方法	
		String op=request.getParameter("op");//代表要进行的操作
		if (op.equals("newsList")) {
			newsList(request,response);
		}else if (op.equals("preEdit")) {
			preEdit(request,response);
		}else if (op.equals("edit")) {
			edit(request,response);
		}else if (op.equals("add")) {
			add(request,response);
		}else if (op.equals("preAdd")) {
			preAdd(request,response);
		}else if (op.equals("delete")) {
			delete(request,response);
		}else if (op.equals("batchDel")) {
			batchDel(request,response);
		}else if (op.equals("noteList")) {
			noteList(request,response);
		}else if (op.equals("delNote")) {
			delNote(request,response);
		}
	}
	/**
	 * 
	* @Title: delNote
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author ganquanzhong
	* @date  2017-12-29 上午10:26:09
	* @param request
	* @param response
	* @throws IOException
	 */
	private void delNote(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		//获取需要删除的评论的noteId
		String noteId=request.getParameter("noteId");
		//实例化一个noteDAO对象  ，使用工厂类的静态方法实现
		NoteDAO noteDAO=NoteDAOFactory.getNoteDAOInstance();
		//调用NoteDAO的数据库操作方法		
		int result=noteDAO.delete(noteId);
		//删除成功，提示用户修改成功，反之提示修改失败
		if(result==0){
			out.print("评论删除失败!");
		}else{
			out.print("评论删除成功!");
		}
	}

	/**
	 * 
	* @Title: noteList
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author ganquanzhong
	* @date  2017-12-29 上午10:26:14
	* @param request
	* @param response
	* @throws ServletException
	* @throws IOException
	 */
	private void noteList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//2.分页显示  所有已审核的新闻
		String num=request.getParameter("num");//获取需要显示的页码
		//第一次查询，没有设置查询的页码，pageNum的就是1
		int pageNum=1;
		//如果不是一次查询，用户点击上一页和下一页 超链接时，就有num页码
		if (num!=null && !num.equals("")) {
			pageNum=Integer.parseInt(num);
		}		
		
		//2.1.查询评论--即note表中的信息
		String newsId = request.getParameter("newsId");//获取需要显示的新闻的评论
		
		//实例化一个NoteDAO对象  ，使用工厂类的静态方法实现
		NoteDAO noteDAO=NoteDAOFactory.getNoteDAOInstance();
		int totalRecordNum=noteDAO.getTotalRecordNumById(newsId);//获取 总的记录数
		
		if (totalRecordNum!=0) {
			//如果评论数不为零时
			Page page=new Page(pageNum, totalRecordNum);//构建分页对象
			
			//访问NoteDAO getNoteByPage方法，将note表中的信息存入到request中去
			List<Note> noteList=noteDAO.getNoteByPage(newsId,page.getStartIndex(), page.getPageSize());//分页查询
			
			page.setRecords(noteList);//保存  --设置每页显示的信息
			page.setUrl("/manager/NewsList?op=noteList&newsId="+newsId); //点击超链接跳转的url		
			request.setAttribute("page", page);	
			//只能用request的方法跳转，引文request中存放这数据
			request.getRequestDispatcher("noteList.jsp").forward(request, response);
		}else {
			//评论数为零时，不显示noteList.jsp页面
			PrintWriter out = response.getWriter();
			out.print("<script>alert('该新闻暂无评论！!');window.location.href='NewsList?op=newsList'</script>");
		}
	}

	/**
	 * 
	* @Title: batchDel
	* @Description: TODO(批量删除新闻)
	* @author ganquanzhong
	* @date  2017-12-12 上午11:40:45
	* @param request
	* @param response
	* @throws IOException
	 */
	private void batchDel(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取需要删除的新闻的newsId
		String[] newsDels = request.getParameterValues("NewsDels[]");
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();
		int result=0;
		for (String id : newsDels) {
			result = newsDAO.delete(id);
			System.out.println("classId为"+id+"新闻删除！");
		}
		if (result==0) {
			out.print("批量删除失败！");
		}else {
			out.print("批量删除成功！");
		}
	}

	/**
	 * 
	* @Title: delete
	* @Description: TODO(删除新闻  delete)
	* @author ganquanzhong
	* @date  2017-12-7 上午09:23:59
	* @param request
	* @param response
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException,IOException{
		PrintWriter out=response.getWriter();
		//获取需要修改的新闻的 newsId
		String newsId=request.getParameter("newsId");
		//实例化一个newsDAO对象  ，使用工厂类的静态方法实现
		NewsDAO newsDAO=NewsDAOFactory.getNewsDAOInstance();
		//从news表中获取对应的newsId的新闻信息
		//News news=newsDAO.getNewsById(newsId);
		int result=newsDAO.delete(newsId);
		//删除成功，提示用户修改成功，反之提示修改失败
		if(result==0){
			out.print("新闻删除失败!");
		}else{
			out.print("新闻删除成功!");
		}
	}

	/**
	 * 
	* @Title: newsList
	* @Description: TODO(新闻列表显示 管理)
	* @author ganquanzhong
	* @date  2017-12-6 上午11:20:11
	* @param request
	* @param response
	* @throws ServletException
	* @throws IOException
	 */
	private void newsList(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		// TODO Auto-generated method stub
		String num=request.getParameter("num");//获取需要显示的页码
		//第一次查询，没有设置查询的页码，pageNum的就是1
		int pageNum=1;
		//如果不是一次查询，用户点击上一页和下一页 超链接时，就有num页码
		if (num!=null && !num.equals("")) {
			pageNum=Integer.parseInt(num);
		}
	
		//1.查询新闻分类--即newclass表中的信息
		//实例化一个NewsClassclassDAO对象，使用工厂类的静态方法实现
		NewsclassDAO newsclassDAO=NewsclassDAOFactory.getNewsclassDAOInstance();
		//访问NewsclassDAO getList方法，将newsclass表中的新闻分类信息存入到request中去
		List<Newsclass> newsclassList = newsclassDAO.getList();
		request.setAttribute("newsclassList", newsclassList);
		
		//2.查询新闻--即news表中的信息
		//实例化一个newsDAO对象  ，使用工厂类的静态方法实现
		NewsDAO newsDAO=NewsDAOFactory.getNewsDAOInstance();
		
		int totalRecordNum=newsDAO.getTotalRecordNum();//获取总的记录数
		Page page=new Page(pageNum, totalRecordNum);//构建分页对象
		
		//访问NewsDAO getList方法，将news表中的新闻分类信息存入到request中去
		List<News> newList=newsDAO.getListByPage(page.getStartIndex(), page.getPageSize());//分页查询
		
		page.setRecords(newList);//设置每页显示的信息
		page.setUrl("/manager/NewsList?op=newsList"); //点击超链接跳转的url
		
		request.setAttribute("page", page);	
		//只能用request的方法跳转，引文request中存放这数据
		request.getRequestDispatcher("newsList.jsp").forward(request, response);
	}
	
	
	/**
	 * 
	* @Title: preEdit
	* @Description: TODO(编辑新闻的前期工作 从news表中获取对应的newsId的新闻信息  将修改的页面显示出来)
	* @author ganquanzhong
	* @date  2017-12-6 上午11:24:27
	* @param request
	* @param response
	 */
	private void preEdit(HttpServletRequest request,HttpServletResponse response) 
		throws ServletException, IOException   {
		// TODO Auto-generated method stub
		//获取需要修改的新闻的 newsId
		String newsId=request.getParameter("newsId");
		
		//实例化一个newsDAO对象  ，使用工厂类的静态方法实现
		NewsDAO newsDAO=NewsDAOFactory.getNewsDAOInstance();
		
		//从news表中获取对应的newsId的新闻信息
		News news=newsDAO.getNewsById(newsId);
		
		//将该新闻信息存放到request对象中，方便在页面显示
		request.setAttribute("news", news);
		
		//实例化一个newsclassDAO对象  ，使用工厂类的静态方法实现
		NewsclassDAO newsclassDAO=NewsclassDAOFactory.getNewsclassDAOInstance();
		//访问NewsDAO getList方法，将news表中的新闻分类信息存入到request中去
		List<Newsclass> classList =newsclassDAO.getList();
		request.setAttribute("classList", classList);
		//新闻分类表中的信息存入到request中去后，页面跳转到addNews.jsp页面中去，
		//只能用request的方法跳转，引文request中存放这数据
		request.getRequestDispatcher("editNews.jsp").forward(request, response);
	}

	
	/**
	 * 
	* @Title: edit
	* @Description: TODO(编辑新闻  更新新闻的各个内容)
	* @author ganquanzhong
	* @date  2017-12-6 上午11:24:13
	* @param request
	* @param response
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response)
	 	throws ServletException, IOException   {
		// TODO Auto-generated method stub
		//在编码过滤器中已经完成设置
		//设置请求编码
		request.setCharacterEncoding("utf-8");
		//设置服务器响应编码
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		
		//1.判断是否支持文件上传，底层判断是否使用了enctype="multipart/form-data"
		boolean multipartContent = ServletFileUpload.isMultipartContent(request);
		if(!multipartContent)
		{
			throw new RuntimeException("the form is not multipart/form-data");
		}
		//2.创建工厂--基于硬盘的文件列表工厂FileItemFactory factory = new DiskFileItemFactory();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload suf = new ServletFileUpload(factory);
		//3.解决上传文件的中文乱码问题
		suf.setHeaderEncoding("utf-8");
		//4.解析request，获得上传所有内容，每一个内容都封装到一个对象（FileItem）中
		List<FileItem> items = new ArrayList<FileItem>();
		try{
			items=suf.parseRequest(request);
		}catch(FileUploadException e){
			e.printStackTrace();
		}
		//5.判断是否是普通字段  item.isFormField()
		//6.如果是普通字段 字段名item.getFormField();字段值 item.igetString(encoding)；
		//7.如果不是普通字段，则是上传的文件, 获取上传文件名称item.getName()、上传内容item.getInputStream
		//8.上传文件，将文件写入到服务器的指定位置下
		News news = new News();
		for(FileItem item:items){
			if(item.isFormField()){
				processFormFiled(item, news);
			}else{
				processUploadFiled(item, news);
			}
		}
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();
		//调用newsDAO的update更新方法
		int result=newsDAO.update(news);
		
		//3.修改成功，提示用户修改成功，反之提示修改失败
		if(result==0)
		{
			out.print("<script>alert('新闻修改失败!');" + 
					"window.location.href='NewsList?op=newsList'</script>");
		}
		else
		{
			out.print("<script>alert('新闻修改成功!');" + 
			"window.location.href='NewsList?op=newsList'</script>");
		}
	}
	
	
	/**
	 * 
	* @Title: add
	* @Description: TODO( 添加新闻 )
	* @author ganquanzhong
	* @date  2017-12-6 上午11:21:27
	* @param request
	* @param response
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException  {
		// TODO Auto-generated method stub
		//在编码过滤器中已经完成设置
		//设置请求编码
		request.setCharacterEncoding("utf-8");
		//设置服务器响应编码
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		
		//1.判断是否支持文件上传，底层判断是否使用了enctype="multipart/form-data"
		boolean multipartContent = ServletFileUpload.isMultipartContent(request);
		if(!multipartContent)
		{
			throw new RuntimeException("the form is not multipart/form-data");
		}
		//2.创建工厂--基于硬盘的文件列表工厂FileItemFactory factory = new DiskFileItemFactory();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload suf = new ServletFileUpload(factory);
		//3.解决上传文件的中文乱码问题
		suf.setHeaderEncoding("utf-8");
		//4.解析request，获得上传所有内容，每一个内容都封装到一个对象（FileItem）中
		List<FileItem> items = new ArrayList<FileItem>();
		try{
			items=suf.parseRequest(request);
		}catch(FileUploadException e){
			e.printStackTrace();
		}
		//5.判断是否是普通字段  item.isFormField()
		//6.如果是普通字段 字段名item.getFormField();字段值 item.igetString(encoding)；
		//7.如果不是普通字段，则是上传的文件, 获取上传文件名称item.getName()、上传内容item.getInputStream
		//8.上传文件，将文件写入到服务器的指定位置下
		News news = new News();
		for(FileItem item:items){
			if(item.isFormField()){
				processFormFiled(item, news);
			}else{
				processUploadFiled(item, news);
			}
		}
		NewsDAO newsDAO = NewsDAOFactory.getNewsDAOInstance();
		int result=newsDAO.insert(news);
		
		//3.存储成功，提示用户存储成功，反之提示添加失败
		if(result==0)
		{
			out.print("<script>alert('新闻添加失败!');" + 
					"window.location.href='NewsList?op=preAdd'</script>");
		}
		else
		{
			out.print("<script>alert('新闻添加成功!');" + 
			"window.location.href='NewsList?op=newsList'</script>");
		}
	}
	
	
	/**
	 * 
	* @Title: preAdd
	* @Description: TODO(添加新闻的前期操作)
	* @author ganquanzhong
	* @date  2017-12-6 上午11:23:45
	* @param request
	* @param response
	 */
	private void preAdd(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException  {
		// TODO Auto-generated method stub
		//实例化一个newsclassDAO对象  ，使用工厂类的静态方法实现
		NewsclassDAO newsclassDAO=NewsclassDAOFactory.getNewsclassDAOInstance();
		//访问NewsclassDAO getList方法，将newsclass表中的新闻分类信息存入到request中去
		List<Newsclass> classList =newsclassDAO.getList();
		request.setAttribute("classList", classList);
		//新闻分类表中的信息存入到request中去后，页面跳转到addNews.jsp页面中去，
		//只能用request的方法跳转，引文request中存放这数据
		request.getRequestDispatcher("addNews.jsp").forward(request, response);
	}
	
	
	///文件上传使用的方法 processUploadFiled
	private void processUploadFiled(FileItem item, News news) {
		// TODO Auto-generated method stub
		String storeDirectory = getServletContext().getRealPath("/images");
		File rootDirectory = new File(storeDirectory);
		if(!rootDirectory.exists())
		{
			rootDirectory.mkdirs();
		}
		//改文件名
		String filename = item.getName();
		if(filename != null)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");
			filename = sdf.format(new Date())+(int)(Math.random()*100)
						+ "." + FilenameUtils.getExtension(filename);
			news.setPictures(filename);
		}
		//文件上传
		try {
			item.write(new File(rootDirectory, filename));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	///文件上传使用的方法 processFormFiled
	private void processFormFiled(FileItem item, News news) {
		// TODO Auto-generated method stub
		
		try {
			String fieldName = item.getFieldName();
			String fieldValue = item.getString("utf-8");
			BeanUtils.setProperty(news, fieldName, fieldValue);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}

