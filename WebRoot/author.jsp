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
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

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
			<!--main的头部 start-->
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
			<!--main的头部 end-->

			<!---post帖子显示  start---->
			<div id="highlight-posts" class="clearfix">
				<ul>
					<li class="masked masked-big" style="width:100%;">
						<!-- One -->
						<div class="flexslider highlight-one loading">
							<ul class="slides">
								<!-- Images -->
								<li>
									<figure
										style="background-image:url(https://ganquanzhong.top/img/bg-1.jpg);"></figure>
								</li>
								<li>
									<figure
										style="background-image:url(https://ganquanzhong.top/img/bg-3.jpg);"></figure>
								</li>
								<li>
									<figure
										style="background-image:url(https://ganquanzhong.top/img/bg-4.jpg);"></figure>
								</li>
							</ul>
						</div> <a href="#" title="ForFuture news picture">
							<div class="masked-hover"></div>
					</a> <!-- Masked Hover -->
						<div class="text">
							<h2>Alvear Art Black and White Theme</h2>
							<span class="meta">By mdkiwol on Jan. 14, 2013</span>
							<p></p>
						</div>
					</li>
				</ul>
			</div>
			<!-- End Highlight Posts -->
			<!---post帖子显示  end	---->



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
						<a href="http://ganquanzhong.top" title="ForFuture Share">ForFuture
							Share</a>
					</div>
					<div>
						<a href="http://ganquanzhong.top" title="ForFuture NEWS">ForFuture
							NEWS</a>
					</div>
					<div>
						<a href="http://ganquanzhong.top" title="Web full stack engineer">Web
							full stack engineer</a>
					</div>
					<div>
						<a href="http://ganquanzhong.top/web" title="ForFuture More">ForFuture
							More</a>
					</div>
					<div>
						<a href="http://ganquanzhong.top/web"
							title="Big data  Cloud computing  Artificial intelligence">Big
							data Cloud computing Artificial intelligence</a>
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
				
				<div class="row-fluid">

					<div class="author-bio clearfix">
						<!-- <figure>
							<img src="http://ganquanzhong.top/Web/images/14%20%E8%B6%85%E8%83%BD%E9%99%86%E6%88%98%E9%98%9F.jpg" alt="qzhong" />
						</figure> -->
						<div class="content">
							<h2 style="text-align:center;font-weight:bold;color:#ef470c">ForFuture</h3>
							<p>just for a better future!</p>
							<p class="add-info">
								<i class="icon-share"></i> Email: <a href="http://ganquanzhong.top" title="Send a private message via email">ganquanzhong@qq.com</a>
							</p>
						</div>
					</div>
					<!-- End Author-Bio -->

					<div class="header">
						<h4>My first Site.Thank you!</h4>
					</div>

					<!-- One -->
					<div class="span6 post no-margin-left">
						<div class="text">
							<h2>
								<a href="http://ganquanzhong.top/Web/Photo.html" target="_Blank"
									title="View permalink Camerette - Your Time to Dream">Dream</a>
							</h2>
							<!-- Title -->
							<p>梦想和青春一样，是一种信仰。 
								有梦想的生命，就是总有雨露浇灌的花草，由内而外散发出的生命力，如此的鲜活，颜色是最明亮的鲜艳。 
								追逐梦想的人是一贯地坚定，哪怕路上有太多的艰难险阻，荆棘坎坷。 
								追梦的人，却总被那些不相信梦想的，入世的庸人评头论足。 
								没关系，因为梦想永远是梦想，不会凋零。</p>
							<!-- Content -->
							<div class="meta">
								By <a href="author.jsp">mike zhong</a>&nbsp;&nbsp;|&nbsp;&nbsp;2018-12-12 12:00&nbsp;&nbsp;|&nbsp;&nbsp;
								<a href="single_post.html">66666666 comments</a>
							</div>
							<!-- Meta -->
						</div>
					</div>

					<!-- Two -->
					<div class="span6 post">
						<div class="text">
							<h2 >
								<a href="http://ganquanzhong.top/top/"
									title="View permalink Glass House Below The Dark of Moon Light">Share</a>
							</h2>
							<!-- Title -->
							<p >
								<h4 style="text-align:center;font-weight:bold;color:#ef470c">
								这一路感谢你们的支持,以下排名不分先后</h4>
								qq1140093781   辉夜<br/>
								201540410101  会飞的鱼<br/>
								辛好有你<br/>
								<br/>
							</p>
							<div class="meta">
								By <a href="author.html">甘全中</a>
							</div>
							<!-- Meta -->
						</div>
					</div>

					<div class="clearfix ie-sep"></div>
					<!-- Clearfix -->

				</div>
				<!-- End Row-Fluid -->

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