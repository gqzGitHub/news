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
	<link href='http://fonts.googleapis.com/css?family=Oswald'
		rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Bitter'
		rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Open+Sans'
		rel='stylesheet' type='text/css'>
	
	<noscript>
		<link rel="stylesheet" href="css/no-js.css">
	</noscript>
	
	<!-- Favicons -->
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="apple-touch-icon" href="images/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72"
		href="images/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114"
		href="images/apple-touch-icon-114x114.png">
	<style type="text/css">
			.newsDetailupdatedtime, .updatedhits{
				float:right;
				padding-right:10px;
				color:#f15620;
				font-weight:500;
			}
			
			#login_username{
				color:red;
				font-weight:bold;
				padding-left:120px;
			}
			#commentator{
				float:left;
				font-size:14px;
				text-indent:1em;
				color:red;
			}
			#comment_time{
				float:left;
				display:block;
				color:bule;
				padding-left:20px;
				
			}
			#comment_content{
				margin-top:9px;
				text-indent:2em;
				font-weight:bold;
			}
			#login_success{
				padding-left: 40px;
				color:red;
				font-size:16px;
				font-weight:bold;
			}
		</style>
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
	<script type="text/javascript">
			$().ready(function() {
				//alert("隐藏发表评论区！");
				$("#addComment").hide();
			});
			
			function showComment() {
				$("#addComment").show();
			}
				
			$().ready(function(){				
				//页面定时执行一个方法				
				setInterval(auto(),1000);//每隔一秒钟自动执行auto方法
			});
			
			function auto(){
				//从服务器获取该新闻的最新评论在页面中显示<div id="notes"> </div>
				//1.获取当前新闻的newsId
				var newsId=${newsDetail.newsId };
				var notes=$("#notes");//获取评论区的对象元素
				notes.empty();//将原有的评论信息清空
				//2.访问服务器，取该新闻的最新评论，使用Ajax的异步请求方式
				$.post(
					"Front?op=getNotes",//访问的地址 url
					{"newsId": newsId}, //传递的参数，以键值对的形式
					//成功获取服务器传递的值时，执行该函数
					function(data){						
						//3.更新评论列表的内容
						//data是服务器返回的数据库，所以需要遍历
						//每一个新闻评论，构建一个Div，加入到notes中区
						//each函数的参数 第一个参数是要遍历的数组  第二个参数是对数组每个元素要进行的操作（i表示索引）
						$.each(eval(data),function(i,n){
							var tempDiv=$("<div></div>");
							tempDiv.html(
								"<span id='commentator'>评论人："+n.username+
								"</span><span id='comment_time'>评论时间："+n.time+
								"</span></br><span id='comment_content'>评论内容："+n.content+
								"</span></br></br>");
								notes.append(tempDiv);
						});
					},"json"
				);
			}
			
			//发表新闻评论
			function addNote(){
				//1.获取当前页面的newsId值
				var newsId=${newsDetail.newsId };
				//获取评论区中的内容
				var content =$("#comment").val();							
				//2.发送Ajax请求
				$.post(
					"Front?op=addNote",//ajax的请求URL地址
					{"newsId":newsId,"content":content},
					//3.服务器请求成功，在页面显示登录的结果
					function(data){
						alert(data);//弹出服务器返回的结果
						auto();//加载评论列表，保证最新的评论实时显示
						$("#comment").val("");//清空评论去的内容，方便下次在评论
					}
				);
			}
	</script>
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

			<!---######新闻Content内容  start---->
			<div class="row-fluid">
				<!---左侧Main start--->
				<div id="main" class="span8 image-preloader">
					<!-- Start Row-Fluid -->
					<div class="row-fluid">
						
						<!-- 新闻详情页显示 -->
						<div id="templatemo_main_leftcol">
				             <img src="images/${newsDetail.pictures }" alt="image" width="100%" height="300px"/>
				             <br/>
				             <br/>
				             <h3 style="color:#f15620;text-align:center;">${newsDetail.headTitle }</h3>
				             <p> 编辑： 
				             	<a href="#">
				             		<span style="color:#f15620;">${newsDetail.editor }</span>
				             	</a>
				             &nbsp;&nbsp;&nbsp;&nbsp;
				                                        分类：<a href="#"><span style="color:#f15620;">${newsDetail.className }</span></a>
				             	 
				             	 <span class="newsDetailupdatedtime" style="font-weight:bold;">${newsDetail.time }</span>
				             	 &nbsp;&nbsp;&nbsp;&nbsp;
				                 <span class="updatedhits"><span style="color:#000;">点击率：</span>${newsDetail.hits }</span><br />
				             </p> 
				             <p>${newsDetail.content } </p>
				             <br /><br /><br />
				             
				             <div class="clearfix ie-sep"></div>
				             <jsp:include page="dashang.jsp"></jsp:include>
				             <h4>评论：</h4> 
				             <div id="notes">
				             
				             </div>
				             
				             <h4> <a href="javascript:showComment()">发表评论</a></h4>                               
				             <hr/>  
				             <div id="addComment" >
				                                       发表评论：<br/>
				                  <textarea name="content" id="comment" rows="5" cols="100" ></textarea>
				                  <br/>
				                  <input class="pull-left" type="button" value="发评论" onclick="javascript:addNote()"/>
				                  <br/><br/>还未登录？请先登录！<a href="#templatemo_newsletter_section">登录</a><br/>
				             </div>
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