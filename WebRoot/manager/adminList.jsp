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
		<title>新闻网站后台管理</title>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
		<script type="text/javascript" src="js/index.js"></script>

		<link rel="icon" href="images/icon.jpg" type="x-images/icon"/>
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/main.css" />
		<script type="text/javascript" src="js/modernizr.min.js"></script>

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
			function deleteAdmin(id) {
				$.post("Back?op=delAdmin", //请求的URL地址
				{adminId : id}, //向服务器提交的数据
				function(data) { //从服务器返回的数据
					alert(data);
					window.location.reload(true);
				});
			};
		</script>
	</head>

	<body>
		<!--头部 top start--->
		<jsp:include page="generalTop.jsp"></jsp:include>
		<!--头部 top end--->

		<div class="nav-down">
			<!-- 菜单导航栏 start -->
			<jsp:include page="Navigate.jsp"></jsp:include>
			<!-- 菜单导航栏 end -->

			<!----main start---->
			<div class="main-wrap">
				<div class="crumb-wrap">
					<div class="crumb-list"><i class="icon-font"></i>
						<a href="index.jsp">首页</a><span class="crumb-step">&gt;</span>
						<span class="crumb-name">管理员信息管理</span></div>
				</div>
				<div class="search-wrap">
					<div class="search-content">
						<form action="" method="post">
							<table class="search-tab">
								<tr>
									<th width="70">用户名:</th>
									<td><input class="common-text" placeholder="关键字" name="keywords" type="text"></td>
									<td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="result-wrap">
					<form name="myform" id="myform" method="post">
						<div class="result-title">
							<div class="result-list">
								<a href="addAdmin.jsp"><i class="icon-font"></i>新增管理员</a>
								<a id="batchDel" href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
								<a id="updateOrd" href="javascript:updateOrd()"><i class="icon-font"></i>更新排序</a>
							</div>
						</div>
						<div class="result-content">
							<table class="result-tab" width="100%">
								<tr>
									<th class="tc" width="5%"><input class="allChoose" name="" type="checkbox"></th>
									<th>排序</th>
									<th>管理员名称</th>
									<th>上次登录时间</th>
									<th>上次登录IP</th>
									<th>管理员类型</th>
									<th>操作</th>
								</tr>
								<tbody>
									<!-- 表的正文部分，使用forEach循环从数据库中取出新闻信息  start -->
									<c:forEach items="${page.records }" var="admin" varStatus="status">
										<tr>
											<td class="tc"><input name="id[]" value="59" type="checkbox"></td>
											<td>
												<input name="ids[]" value="59" type="hidden">
												<input class="common-input sort-input" name="ord[]" value="${admin.id }" type="text">
											</td>
		
											<td>${admin.username }</td>
											<td>${admin.formatTime }</td>
											<td>${admin.lastLoginIp }</td>
											<td>${admin.type }</td>
											<td>
												<a class="link-update" href="#">修改</a>
												<a class="link-del" href="javascript:deleteAdmin(${admin.id })">删除</a>
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
			</div>
			<!--/main-->
			<!----main end------>

		</div>

		<div class="clear"></div>
		<!--底部版权信息 start-->
		<div class="footer footer_location">
			<div>
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