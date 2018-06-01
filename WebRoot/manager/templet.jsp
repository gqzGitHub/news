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

		<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="js/jquery.jsort.0.4.js"></script>
		<script type="text/javascript">
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

			<!----main end------>

		</div>

		<div class="clear"></div>
		<!--底部版权信息 start-->
		<div class="footer footer_location">
			<div>
				<p class="padding-bottom">
					Copyright &copy; 2017-2020
					<a href="http://runoob.com/" target="_blank">
						ForFuture Military
					</a>
					</span>军事新闻网 版权所有
					<br/> <br/>
					<a id="ganquanzhong" href="http://baidu.com/" target="_blank">
						甘全中
					</a> mike zhong 湖北理工学院腾龙公寓
				</p>
			</div>
		</div>
		<!--底部版权信息 end-->
	</body>

</html>