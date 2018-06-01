<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<!-- Your Basic Site Informations -->
	<title>ForFuture News</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<meta name="description"
		content="ForFuture News新闻网站,Servlet ,Tomcat ,JSP项目开发" />
	<meta name="keywords"
		content="Site Template, News, Magazine, Portofolio, HTML, CSS, jQuery, Newsletter, PHP Contact, Subscription, Responsive, Marketing, Clean, SEO" />
	<meta name="author" content="mike zhong" />
	
	<!-- Mobile Specific Meta -->
	<meta name="viewport"	content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<!-- Stylesheets -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap-responsive.min.css">
	<link rel="stylesheet" href="css/flexslider.css">
	<link rel="stylesheet" href="css/prettyPhoto.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/theme-layout.css">
	
	<noscript>
		<link rel="stylesheet" href="css/no-js.css">
	</noscript>
	
	<!-- Favicons -->
	<link rel="shortcut icon" href="images/favicon.ico">
	
	<!-- JavaScript -->
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type='text/javascript' src='js/bootstrap.min.js'></script>
	<script type='text/javascript' src='js/jquery.easing.js'></script>
	<script type='text/javascript' src='js/jquery.flexslider-min.js'></script>
	<script type='text/javascript' src='js/jflickrfeed.min.js'></script>
	<script type='text/javascript' src='js/jquery.fitvids.min.js'></script>
	<script type='text/javascript' src='js/jquery.lazyload.mini.js'></script>
	<script type='text/javascript' src='js/jquery.prettyPhoto.js'></script>
	<script type='text/javascript' src='js/jquery.placeholder.min.js'></script>
	<script type='text/javascript' src='js/jquery.jticker.js'></script>
	<script type='text/javascript' src='js/jquery.mobilemenu.js'></script>
	<script type='text/javascript' src='js/jquery.isotope.min.js'></script>
	<script type='text/javascript' src='js/jquery.hoverdir.js'></script>
	<script type='text/javascript' src='js/modernizr.custom.js'></script>
	<script type="text/javascript" src="js/main.js"></script>
</head>

<body>
	<!---中间主模块main start---->
	<div class="theme-layout">
		<!-- Stretched/Boxed Layout -->

		<!---顶部导航  ---->
		<jsp:include page="top-navigation.jsp"></jsp:include>

		<!---container主要容器 start---->
		<div class="container">
			<!--头部 start-->
			<header id="header" class="clearfix">

				<!-- Logo -->
				<div class="logo pull-left">
					<a href="#"> <img src="images/logo.png" alt="ForFuture NEWS" />
					</a>
				</div>

				<!-- Ads -->
				<div class="ads pull-right">
					<!--<img src="images/ads/480x80.png" alt="广告" />-->
					<p
						style="float: left;font-size: 26px;color: #ea1c1c;font-weight: 700;
							text-shadow: 55px -17px #f15620;">
						ForFuture News</p>
					<!-- 本网络Search Form start-->
					<form name="form-search" method="post"
						action="Front?op=query&search=thissite">
						<input type="text" name="q" placeholder="本网络......" /> <input
							type="submit" name="submit" value="Search" />
					</form>
				</div>

			</header>
			<!-- End Header -->
			<!--头部 end-->

			<!---导航 start---->
			<nav id="main-navigation" class="clearfix">
				<ul>
					<li><a href="Front?op=main" class="current">主页</a></li>
					<c:forEach items="${classList }" var="c">
						<li><a href="Front?op=listByClass&classId=${c.classId }">${c.content }</a></li>
					</c:forEach>
				</ul>
			</nav>
			<!-- End Main-Navigation -->
			<!---导航 end---->


			<!---headlines动态显示  start---->
			<div class="headlines clearfix">
				<!--固定时间 start--->
				<span class="base"> <script type="text/javascript">
					var currentTime = new Date();
					var date = currentTime.getDate();
					document.write(date);
				</script> <i> <script type="text/javascript">
					var d_names = new Array("周日", "周一", "周二", "周三", "周四", "周五",
							"周六");
					var currentTime = new Date();
					var day = currentTime.getDay();
					document.write(d_names[day]);
				</script>
				</i>
				</span>				
				<!--时间 end--->
				<!---动态通知 start--->
				<div class="text-rotator">
					<div>
						<a href="http://ganquanzhong.top" title="ForFuture Share">ForFuture Share</a>
					</div>
					<div>
						<a href="http://ganquanzhong.top" title="ForFuture NEWS">ForFuture NEWS</a>
					</div>
					<div>
						<a href="http://ganquanzhong.top" title="Web full stack engineer">Web full stack
							engineer</a>
					</div>
					<div>
						<a href="http://ganquanzhong.top/web" title="ForFuture More">ForFuture More</a>
					</div>
					<div>
						<a href="http://ganquanzhong.top/web"
							title="Big data  Cloud computing  Artificial intelligence">Big data Cloud computing Artificial intelligence</a>
					</div>
				</div>
				<!---动态通知 end--->
			</div>
			<!-- End Headlines -->
			<!---headlines动态显示  end---->

			<!---dispaly:none--->
			<div class="copyrights">
				Collect from <a href="index.jsp">甘全中</a>
			</div>

			<!---######新闻Content内容  start---->
			<div class="row-fluid">
				<!---左侧Main start--->
				<div id="main" class="span8 image-preloader">
					<!-- Start Row-Fluid -->
					<div class="row-fluid">
						<!-- 新闻列表 start -->
						<div id="NewsList">
				                   <a href="#"><h3 style="color:#f15620;font-weight:500;">新闻列表：</h3></a>
				                   <hr />
				                   <c:forEach items="${page.records }" var="news">
				                   		<a href="Front?op=displayNews&newsId=${news.newsId }">
				                    		<h4 style="color:#f15620;font-weight:normal;">${news.headTitle }</h4>
				                    	</a>
				                    	<p> ${news.content } </p>
				                  		<br />
				                  		<hr />
				                  </c:forEach> 
				                
				                  <a href="#">首页</a>
				                  <jsp:include page="common/page.jsp"></jsp:include>
						</div>
				    	<!-- 新闻列表 end-->
						
						<div class="clearfix ie-sep"></div>
						<!-- Clearfix 换行-->
					</div>
					<!-- End Row-Fluid -->
				</div>
				<!-- End Main -->
				<!---左侧Main end--->

				<!---右侧SideBar start--->
				<jsp:include page="right.jsp"></jsp:include>
				<!---右侧SideBar end--->

			</div>
			<!-- End Row-Fluid -->
			<!---######新闻Content内容  end---->
		</div>
		<!-- End Container -->
		<!---container主要容器 end---->

		<!-- bootom and foot start-->
		<jsp:include page="bottom-foot.jsp"></jsp:include>
		<!-- bootom and foot end-->

	</div>
	<!-- End Theme-Layout -->
	<!---中间主模块main end---->

	<!-- 主题设置 -->
	<jsp:include page="global-conf.jsp"></jsp:include>

</body>

</html>