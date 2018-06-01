<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%-- 引用jstl标签库 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!---导航菜单(1) start--->
<div class="leftmenu1">
	<div class="menu-oc">
		<img src="images/menu-all.png" />
	</div>
	<ul>
		<li><a class="a_list a_list1">全局设置</a>
			<div class="menu_list menu_list_first">
				<a href="NewsList?op=preAdd">添加新闻</a> 
				<a href="NewsList?op=preEdit&newsId=${news.newsId }">修改新闻</a> 
				<a href="javascript:deleteNews(${news.newsId })">删除新闻</a> 
				<a href="NewsList?op=newsList">管理新闻</a>
				<a href="addNewsclass.jsp">新增新闻分类</a> 
				<a href="Back?op=newsClassList">新闻分类管理</a> 
			</div>
		</li>
		
		<li><a class="a_list a_list2">权限管理</a>
			<div class="menu_list">
				<a href="Back?op=userList">基本权限</a> 
				<a href="#">分配权限</a> 
				<a href="#">权限管理</a> 
				<a href="Back?op=userList">成员管理</a>
			</div>
		</li>
		
		<li><a class="a_list a_list3">新闻管理</a>
			<div class="menu_list">
				<a href="NewsList?op=newsList">新闻中心</a> 
				<a href="NewsList?op=preAdd">添加新闻</a>
				<a href="NewsList?op=preEdit&newsId=${news.newsId }">修改新闻</a>
				<a href="javascript:deleteNews(${news.newsId })">删除新闻</a>
			</div>
			</li>
		<li><a class="a_list a_list3">新闻管理</a>
			<div class="menu_list">
				<a href="NewsList?op=newsList">新闻中心</a> 
				<a href="NewsList?op=preAdd">添加新闻</a>
				<a href="NewsList?op=preEdit&newsId=${news.newsId }">修改新闻</a> 
				<a href="javascript:deleteNews(${news.newsId })">删除新闻</a>
			</div>
		</li>
	</ul>
</div>
<!---导航菜单(1) end--->

<!---导航菜单(2) start--->
<div class="leftmenu2">
	<div class="menu-oc1">
		<img src="images/menu-all.png" />
	</div>
	<ul>
		<li><a class="j_a_list j_a_list1"></a>
			<div class="j_menu_list j_menu_list_first">
				<span class="sp1"><i></i>常用操作</span> <a href="NewsList?op=preAdd">添加新闻</a>
				<a href="NewsList?op=preEdit&newsId=${news.newsId }">修改新闻</a> 
				<a href="javascript:deleteNews(${news.newsId })">删除新闻</a> 
				<a href="NewsList?op=newsList">管理新闻</a>
				<a href="addNewsclass.jsp">新增新闻分类</a> 
				<a href="Back?op=newsClassList">新闻分类管理</a> 
			</div></li>
		<li><a class="j_a_list j_a_list2"></a>
			<div class="j_menu_list">
				<span class="sp2"><i></i>权限管理</span> 
				<a href="changePassword.jsp">修改密码</a> 
				<a href="editAdmin.jsp">修改用户名</a> 
				<a href="addAdmin.jsp">新增管理员</a>
				<a href="Back?op=userList">成员管理</a>
				<a href="AdminQuit">退出系统</a>
			</div></li>
		<li><a class="j_a_list j_a_list3"></a>
			<div class="j_menu_list">
				<span class="sp3"><i></i>权限管理</span> 
				<a href="Back?op=adminList">管理员列表</a> 
				<a href="Back?op=userList">成员管理</a>
				<a href="#">分配权限</a> 
				<a href="#">权限管理</a> 
			</div></li>
	</ul>
</div>
<!---导航菜单(2) end--->
