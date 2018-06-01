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

			<!---main导航 start---->
			<nav id="main-navigation" class="clearfix">
				<ul>
					<li><a href="Front?op=main" class="current">主页</a></li>
					<c:forEach items="${classList }" var="c">
						<li><a href="Front?op=listByClass&classId=${c.classId }">${c.content }</a></li>
					</c:forEach>
				</ul>
			</nav>
			<!-- End Main-Navigation -->
			<!---main导航 end---->


			<!---post帖子显示  start---->
			<div id="highlight-posts" class="clearfix" >
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
						</div> 
						<a href="#" title="ForFuture news picture">
							<div class="masked-hover"></div>
						</a> 
						<!-- Masked Hover -->
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

						<!---**第一行  start--->
						<!-- One NewsClass 头条新闻-->
						<div class="FImg span6 post no-margin-left">
							<figure>
								<!---新闻图片TopNews --->
								<img src="images/${topNews.pictures }" alt="头条新闻" />
								<!---新闻分类 --->
								<div class="cat-name">
									<span class="base">头条新闻</span> <span class="arrow"></span>
								</div>
							</figure>
							<div class="text">
								<!---新闻标题--->
								<h4 style="text-align:center;font-weight:bold;color:#f15620;">
									<a href="Front?op=displayNews&newsId=${topNews.newsId }">
										${topNews.headTitle } </a>
								</h4>
								<!---新闻正文--->
								<p>${topNews.content }</p>
								<p>
									<a href="Front?op=displayNews&newsId=${topNews.newsId }">查看全文</a>
								</p>

								<div class="meta">
									By <a href="author.jsp">${topNews.author }</a>
									&nbsp;&nbsp;|&nbsp;&nbsp;${topNews.time }&nbsp;&nbsp;|&nbsp;&nbsp;
									<a href="single_post.html">${topNews.noteCount } comments</a>
								</div>
							</div>
						</div>
						<!-- One NewsClass 头条新闻 end-->
						
						<!-- Two NewsClass 最新新闻 start-->
						<div class="FImg span6 post ">
							<figure class="FImg">
								<!---最新新闻图片 --->
								<img src="images/${recentNews[0].pictures }" alt="最新新闻" "/>
								<!---新闻分类 --->
								<div class="cat-name">
									<span class="base">最新新闻</span> <span class="arrow"></span>
								</div>
							</figure>
							<div class="text">
								<!---新闻标题--->
								<h4 style="text-align:center;font-weight:bold;">
									<a href="Front?op=displayNews&newsId=${recentNews[0].newsId }"
										title="最新新闻"> ${recentNews[0].headTitle } </a>
								</h4>
								<!---新闻正文--->
								<p>${recentNews[0].content }</p>
								<p>
									<a href="Front?op=displayNews&newsId=${recentNews[0].newsId }">查看全文</a>
								</p>
								<br /> <a href="Front?op=listAll">阅读最近新闻</a>
								<div class="meta">
									By <a href="author.jsp">${recentNews[0].author }</a>
									&nbsp;&nbsp;|&nbsp;&nbsp;${recentNews[0].time }&nbsp;&nbsp;|&nbsp;&nbsp;
									<a href="single_post.html">${recentNews[0].noteCount } comments</a>
								</div>
							</div>
						</div>
						<!-- Two NewsClass 最新新闻 end-->
						<div class="clearfix ie-sep"></div>
						<!-- Clearfix -->
						<!---**第一行  end--->


						<!-- Three NewsClass 第一个新闻分类start-->
						<div class="span6 post no-margin-left FImg">
							<figure>
								<img src="images/${newsList1[0].pictures }"
									alt="${classList[0].content }" />
								<div class="cat-name">
									<span class="base">${classList[0].content }</span> <span
										class="arrow"></span>
								</div>
							</figure>
							<div class="text">
								<!---新闻标题--->
								<h4 style="text-align:center;font-weight:bold;">
									<a href="Front?op=displayNews&newsId=${newsList1[0].newsId }"
										title="最新新闻""> ${newsList1[0].headTitle } </a>
								</h4>
								<!---新闻正文--->
								<p>${newsList1[0].content }</p>
								<p>
									<a href="Front?op=displayNews&newsId=${newsList1[0].newsId }">查看全文</a>
								</p>
								<br /> <a
									href="Front?op=listByClass&classId=${newsList1[0].classId }">阅读${classList[0].content }最近新闻</a>
								<div class="meta">
									By <a href="author.jsp">${newsList1[0].author }</a>
									&nbsp;&nbsp;|&nbsp;&nbsp;${newsList1[0].time }&nbsp;&nbsp;|&nbsp;&nbsp;
									<a href="single_post.html">${newsList1[0].noteCount } comments</a>
								</div>
							</div>
						</div>
						<!-- Three NewsClass 第一个新闻分类end-->

						<!-- 论坛    start -->
						<!-- Review Posts -->
						<div class="span6 home-reviews">

							<!-- Header -->
							<div class="header">
								<div class="base">
									<h4 style="text-align:center;">论坛forum</h4>
									<a href="Front?op=listAllForum" title="论坛">more forum....</a>
								</div>
								<div class="arrow arrow-left"></div>
								<div class="arrow arrow-right"></div>
							</div>

							<!-- One forum-->
							<div class="item">
								<!-- 论坛的图片 -->
								<a href="Front?op=forum&num=14">
									<figure class="figure-hover">
										<img src="images/content/300/7.jpg" alt="Thumbnail 1" />
										<div class="base-val">70%</div>
										<div class="figure-hover-masked">
											<p class="icon-plus-small"></p>
										</div>
									</figure>
								</a>
								<!-- 论坛 content-->
								<div class="content">
									<p>
										<!-- 论坛 标题-->
										<a href="Front?op=forum&num=14" title="forum1">
											${forumList[13].subject }
										</a> 
										<i>by ${forumList[13].name }</i>
									</p>
									<div class="base-rate">
										<div class="rate-val" style="width:70%;"></div>
									</div>
								</div>
							</div>


							<!-- Two forum-->
							<div class="item">
								<a href="Front?op=forum&num=2">
									<figure class="figure-hover">
										<img src="images/content/300/8.jpg" alt="Thumbnail 2" />
										<div class="base-val">5.5</div>
										<div class="figure-hover-masked">
											<p class="icon-plus-small"></p>
										</div>
									</figure>
								</a>
								<div class="content">
									<p>
										<a href="Front?op=forum&num=2"	title="forum2">
											${forumList[1].subject }
										</a> 
										<i>by ${forumList[1].name }</i>
									</p>
									<div class="base-rate">
										<div class="rate-val" style="width:55%;"></div>
									</div>
								</div>
							</div>

							<!-- Three forum-->
							<div class="item">
								<a href="Front?op=forum&num=3">								
									<figure class="figure-hover">
										<img src="images/content/300/9.jpg" alt="Thumbnail 3" />
										<div class="base-val">99</div>
										<div class="figure-hover-masked">
											<p class="icon-plus-small"></p>
										</div>
									</figure>
								</a>
								<div class="content">
									<p>
										<a href="Front?op=forum&num=3"	title="forum3">
											${forumList[2].subject }
										</a> <i>by ${forumList[2].name }</i>
									</p>
									<div class="base-rate">
										<div class="rate-val" style="width:99%;"></div>
									</div>
								</div>
							</div>

						</div>
						<!-- End Reviews -->
						<!-- 论坛    end -->

						<div class="clearfix ie-sep"></div>
						<!-- Clearfix 换行-->

						<!-- 云计算  NewsClass-->
						<!-- Four NewsClass newsList_cloud-->
						<div class="span6 post no-margin-left FImg">
							<figure>
								<img src="images/${newsList_cloud[0].pictures }"
									alt="${classList[8].content }" />
								<div class="cat-name">
									<span class="base">${classList[8].content }</span> <span
										class="arrow"></span>
								</div>
							</figure>
							<div class="text">
								<!---新闻标题--->
								<h4 style="text-align:center;font-weight:bold;">
									<a	href="Front?op=displayNews&newsId=${newsList_cloud[0].newsId }"
										title="最新新闻""> ${newsList_cloud[0].headTitle } </a>
								</h4>
								<!---新闻正文--->
								<p>${newsList_cloud[0].content }</p>
								<p>
									<a
										href="Front?op=displayNews&newsId=${newsList_cloud[0].newsId }">查看全文</a>
								</p>
								<br /> <a
									href="Front?op=listByClass&classId=${newsList_cloud[0].classId }">阅读${classList[8].content }最近新闻</a>
								<div class="meta">
									By <a href="author.jsp">${newsList_cloud[0].author }</a>
									&nbsp;&nbsp;|&nbsp;&nbsp;${newsList_cloud[0].time }&nbsp;&nbsp;|&nbsp;&nbsp;
									<a href="single_post.html">${newsList_cloud[0].noteCount } comments</a>
								</div>
							</div>
						</div>

						<!-- 大数据  NewsClass-->
						<!-- Five NewsClass-->
						<div class="span6 post FImg">
							<figure>
								<img src="images/${newsList_bigData[0].pictures }"
									alt="${classList[7].content }" />
								<div class="cat-name">
									<span class="base">${classList[7].content }</span> <span
										class="arrow"></span>
								</div>
							</figure>
							<div class="text">
								<!---新闻标题--->
								<h4 style="text-align:center;font-weight:bold;">
									<a	href="Front?op=displayNews&newsId=${newsList_bigData[0].newsId }"
										title="最新新闻""> ${newsList_bigData[0].headTitle } </a>
								</h4>
								<!---新闻正文--->
								<p>${newsList_bigData[0].content }</p>
								<p>
									<a
										href="Front?op=displayNews&newsId=${newsList_bigData[0].newsId }">查看全文</a>
								</p>
								<br /> <a
									href="Front?op=listByClass&classId=${newsList_bigData[0].classId }">阅读${classList[7].content }最近新闻</a>
								<div class="meta">
									By <a href="author.jsp">${newsList_bigData[0].author }</a>
									&nbsp;&nbsp;|&nbsp;&nbsp;${newsList_bigData[0].time }&nbsp;&nbsp;|&nbsp;&nbsp;
									<a href="single_post.html">${newsList_bigData[0].noteCount } comments</a>
								</div>
							</div>
						</div>

						<div class="clearfix ie-sep"></div>
						<!-- Clearfix 换行-->



						<script type="text/javascript">
							
							$(function(){
								//初始化时将第一个div显示
								$(".NewsTag>div:first-child").addClass("selected");
								//class加上active,div添加class selected
								$(".newstabs>li").mouseenter(function(){
									$(this).addClass("active").siblings("li").removeClass("active");
				
									$(".NewsTag>div").eq($(this).index()).addClass("selected").siblings("div").removeClass("selected");
								});
							});
						</script>
						
						<!---###其他新闻分类 导航start--->
						<nav class="nav-pagination">
							<!-- 导航列表 start -->
							<ul id="countrytabs" class="newstabs">
								<c:forEach items="${classList }" var="classList" begin="1" end="5"	varStatus="status">
									<li>
										<a>${classList.content } </a>
									</li>
								</c:forEach>
							</ul>
							<!-- 导航列表 end -->

						</nav>
						<!-- End Nav-Pagination -->
						<!---###其他新闻分类 导航end--->
						
						<div class="NewsTag">
							<c:forEach items="${newsList2 }" var="list2" varStatus="status"	begin="0" end="4">
								<div class="home-galleries no-margin-left mainNews" id="${list2[status.index].className }">
									<!-- Header -->
									<div class="header">
										<div class="base">
											<!-- 新闻分类名 -->										
											<h4>&nbsp;&nbsp;${list2[status.index].className }</h4>										
											<div class="nav-control">
												<span class="previous"></span> <span class="next"></span>
											</div>
										</div>
										<div class="arrow arrow-left"></div>
										<div class="arrow arrow-right"></div>
									</div>
	
	
									<!-- 该分类新闻的第一条部分详细显示 -->
									<div class="item" id="${list2[0].className }">
										<figure class="figure-overlay figure-overlay-icon">
											<!-- 新闻图片 -->
											<a	href="Front?op=displayNews&newsId=${list2[0].newsId }">
												<img src="images/${list2[0].pictures }"
												alt="${list2[0].className }" />
												<div>
													<p class="icon-plus"></p>
												</div>
											</a>
										</figure>
										<a	href="Front?op=displayNews&newsId=${list2[0].newsId }">
											<!-- 新闻标题 -->
											<h4 style="text-align:center;font-weight:bold;">
												${list2[0].headTitle }
											</h4>
										</a>
										<p>
											${list2[0].content } 
											<a	href="Front?op=displayNews&newsId=${list2[0].newsId }">查看全文</a>
										</p>
									</div>
	
									<!-- 该分类新闻的其他7条新闻标题 -->
									<div class="item otherNews"	style="width: 50%; margin: 36px 0 0 60px;">
										<div class="newslist">
											<ul>
												<c:forEach items="${list2}" var="news" begin="1">
													<li><a
														href="Front?op=displayNews&newsId=${news.newsId }">${news.headTitle }</a>
													</li>
												</c:forEach>
											</ul>
											<br /> <br /> 
											<a
												href="Front?op=listByClass&classId=${list2[0].classId }">查看最新新闻
											</a>
										</div>
									</div>
									<div class="clearfix"></div>
									<!-- Clearfix -->
								</div>
							</c:forEach>
						</div>
						
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