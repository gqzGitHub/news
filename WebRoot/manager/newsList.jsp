<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%-- 引用jstl标签库 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>ForFuture新闻网站后台管理</title>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
		<script type="text/javascript" src="js/index.js"></script>

		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/main.css" />
		<script type="text/javascript" src="js/modernizr.min.js"></script>

		<link rel="icon" href="images/icon.jpg" type="x-images/icon"/>
		<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="js/jquery.jsort.0.4.js"></script>
		<script type="text/javascript">
			/*更新排列函数  UpdateOrd()*/
			function updateOrd() {
				$(".result-tab  tbody").jSort({
					sort_by: 'td.title',
					item: 'tr',
					order: 'desc'
				});
			};

			/*Ajax 服务器的异步请求 */
			function deleteNews(id) {
				$.post(
					"NewsList?op=delete", //请求的URL地址
					{
						newsId: id
					}, //向服务器提交的数据
					function(data) { //从服务器返回的数据
						alert(data);
						window.location.reload(true);
					}
				);
			};

			/*将复选框选中的新闻，批量删除 */
			function batchDel() {
				//定义一个数组，用于存放需要删除新闻的NewsId
				var delIds = new Array();
				//通过循环（选中的复选框）， 将newsId存放到数组中去
				$("input:checked").each(function() {
					delIds.push($(this).val());
				});
				//Ajax 的Post请求
				$.post(
					"NewsList?op=batchDel", //请求的URL地址
					{
						"NewsDels": delIds
					}, //向服务器提交的数据
					function(data) {
						alert(data);
						window.location.reload(true);
					}
				);
			};
		</script>
	</head>

	<body>
		<!--头部 top start--->
		<jsp:include page="generalTop.jsp"></jsp:include>
		<!--头部 top end--->

		<div class="nav-down footer-down" >
			<!-- 菜单导航栏 start -->
			<jsp:include page="Navigate.jsp"></jsp:include>
			<!-- 菜单导航栏 end -->

			<!----main start---->
			<div class="main-wrap">

				<div class="crumb-wrap">
					<div class="crumb-list">
						<i class="icon-font"></i>
						<a href="index.jsp">首页</a>
						<span class="crumb-step">&gt;</span>
						<a href="Back?op=newsList">
							<span class="crumb-name">新闻管理</span>
						</a>
					</div>
				</div>

				<div class="search-wrap">
					<div class="search-content">
						<form action="#" method="post" style="margin-left:180px;">
							<table class="search-tab">
								<tr>
									<th width="120">选择分类:</th>
									<td>
										<select name="search-sort" id="">
											<c:forEach items="${newsclassList}" var="c">
												<option value="${c.classId}">${c.content }</option>
											</c:forEach>
										</select>
									</td>

									<th width="90">新闻标题:</th>
									<td><input class="common-text" placeholder="关键字" name="keywords" value="" id="" type="text"></td>
									<td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<!-- 新闻显示部分 start -->
				<div class="result-wrap">
					<form name="myform" id="myform" method="post">
						<div class="result-title">
							<div class="result-list">
								<a href="Back?op=preAdd"><i class="icon-font"></i>新增新闻</a>
								<a id="batchDel" href="javascript:batchDel();"><i class="icon-font"></i>批量删除</a>
								<a id="updateOrd" href="javascript:updateOrd();"><i class="icon-font"></i>更新排序</a>
							</div>
						</div>
						<div class="result-content">
							<table class="result-tab" width="100%">
								<!-- 表头信息  start-->
								<tr>
									<th class="tc" width="5%">
										<input class="allChoose" name="" type="checkbox">
									</th>
									<th>排序</th>
									<th>ID</th>
									<th>标题</th>
									<th>审核状态</th>
									<th>点击</th>
									<th>作者</th>
									<th>编辑</th>
									<th>更新时间</th>
									<th>新闻来源</th>
									<th clospan="2">操作</th>
								</tr>
								<!-- 表头信息  end-->

								<tbody>
									<!-- 表的正文部分，使用forEach循环从数据库中取出新闻信息  start -->
									<c:forEach items="${page.records }" var="news" varStatus="status">
										<tr>
											<td class="tc"><input name="id[]" value="${news.newsId }" type="checkbox"></td>
											<td class="title">${status.index+1}</td>
											<td>${news.classId}</td>
											<td>${news.headTitle }</td>
											<td>${news.state }</td>
											<td>${news.hits }</td>
											<td>${news.author }</td>
											<td>${news.editor }</td>
											<td>${news.newsTime }</td>
											<td>${news.newsFrom }</td>
											
											<td>
												<a class="link-update" href="Back?op=preEdit&newsId=${news.newsId }">修改</a>
												<a class="link-update" href="addNewsPic.jsp">添加照片</a>
												<a class="link-del" href="javascript:deleteNews(${news.newsId })">删除</a>
												<a class="link-del" href="Back?op=preVerify&newsId=${news.newsId }">审核新闻</a>
												<a class="link-del" href="Back?op=noteList&newsId=${news.newsId }">查看评论</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>

							</table>
							<div class="list-page">
								<jsp:include page="../common/page.jsp"></jsp:include>
							</div>
						</div>
					</form>
				</div>
				<!-- 新闻显示部分end -->
			</div>
			<!--/main-->
			<!----main end------>

		</div>

		<div class="clear"></div>
		<div class="footer_location ">
			<div >
				<p class="padding-bottom">
					Copyright &copy; 2017-2020
					<a href="http://www.ganquanzhong.top/mynews/" target="_blank">
						ForFuture News
					</a>
					</span>新闻网 版权所有&copy;
					<br/> <br/>
					<a id="ganquanzhong" href="http://baidu.com/" target="_blank">
						ForFuture 
					</a> mike zhong <img alt="公网安备" src="images/icp.png" width="20px" height="20px">	
				  	<a href="http://www.beian.gov.cn/portal/registerSystemInfo" target="_blank">鄂ICP备18005830号</a>
				</p>
			</div>
		</div>
		<!--底部版权信息 end-->
	</body>

</html>