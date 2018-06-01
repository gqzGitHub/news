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
	<title>注册</title>
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
	<style type="text/css">
		#registForm{
			background-color: #f3c7b8;
		}
		#templatemo_newsletter_section_regist label{
			margin-top:-30px;		
		}
		
		#templatemo_newsletter_section_regist h2{
			font-size:27px;
			text-align:center;
			color:#f15620;
			text-shodw:1px 1px #f15620;
		}
	
		hr{
			width:90%; height:3px;
		}
		
		.form_row1{
			font-size:16px;
			margin-left:300px;
		}
		
		.form_row1 input{
			width:210px;
			height:36px;
		}
		
		.form_row1 .button{
			width:60px;
			text-align:center;
		}
		
		#checkResult{
			color:red;
			font-size:12px;
		}
	</style>
	
	
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
		//检查用户名是否合法
		function checkName(){
			var username=$("#username").val();
			//使用Ajax请求
			$.post(
				"Front?op=checkName",
				{"username":username},
				function(data){
					$("#checkResult").html(data);
				}
			);
		}
	
		//刷新验证码：
		function refresh(){
			var src=$("#VerifyCodeImg").attr("src");
			//添加一个时间参数，保存每一次的src不一样，防止浏览器的缓存			
			$("#VerifyCodeImg").attr("src",src+"?"+ new Date());			
		}
		
		 //表单验证 validate
		$().ready(function (){
			$("#registForm").validate({
				rules:{
						username:{required:true,rangelenght:[2,15]},
						password:{required:true,rangelenght:[6,16]},
						repassword:{required:true,rangelenght:[6,16],equalTo:"#password"},
						question:{required:true},
						answer:{required:true},
						emailAddr:{required:true,email:true},
						qq:{required:true,digits:true},
						VerifyCode:{required:true}
				},
				message:{
						username:{required:"请输入用户名！",rangelenght:$().validator.foamt("请输入一个在{0}和{1}之间的字符串")},
						password:{required:"请输入密码！"},
						repassword:{required:"请确认密码！"},
						question:{required:"请输入密码问题！"},
						answer:{required:"请输入问题答案！"},
						emailAddr:{required:"请输入电子邮件地址！",email:"请输入正确的电子邮件地址！"},
						qq:{required:"请输入QQ号码！",digits:"请输入正确格式的QQ号码！"},
						VerifyCode:{required:"请输入验证码！"}
				},
				submitHandler:function(form){//验证成功后提交
					form.submit();
				}
			})
		});
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
						<!--注册表单-->
						<div id="templatemo_newsletter_section_regist">
						<h2>注册</h2>
						<br />
						<hr><br/>
						<form method="post" action="Front?op=regist" style="font-size: 15px" id="registForm">
							<div class="form_row1">
								<label>用户名:</label> 
								<input class="inputfield" name="username" type="text" 
											id="username" placeholder="请输入用户名" onblur="javascript:checkName()"/>
								<br />
								<div id="checkResult"></div>	
								<br /> 
								<label>密码:</label> 
								<input class="inputfield" name="password" type="password" id="password" placeholder="请输入密码" />
								<br /><br /> 
								<label>确认密码:</label> 
								<input class="inputfield" name="repassword" type="password" id="repassword" placeholder="请输入确认密码" />
								<br /><br /> 
								<label>性别:</label> 
								<select class="inputfield" name="sex" style="font-size: 13px">
									<option value="男">男</option>
									<option value="女">女</option>
								</select> <br /><br /> 
								<label>密码提示问题:</label> 
								<input class="inputfield" name="question" type="text" id="question" placeholder="密码提示问题"/>
								<br /><br /> 
								<label>密码提示答案:</label> 
								<input class="inputfield" name="answer" type="text" id="answer" placeholder="密码提示答案" />
								<br /><br />
								<label>电子邮件地址:</label> 
								<input class="inputfield" name="emailAddr" type="text" id="emailAddr" placeholder="电子邮件地址"/>
								<br /><br />
								<label>QQ号码:</label> 
								<input class="inputfield" name="qq"	type="text" id="qq" placeholder="QQ号码"/>
								<br />
								<br />
								<label> 验证码:
									<a onclick="javascript:refresh();">
										<img alt="验证码的图片" src="VerifyCode" id="VerifyCodeImg"  />
									</a>
								</label>
								<input class="inputfield" name="verifyCode" type="text" id="verifyCode" placeholder="验证码"/>
								<br />
								<input class="button" type="submit" name="Submit" value="注册" />&nbsp;&nbsp;&nbsp;&nbsp;
								<input class="button" type="reset" name="reset" value="重置" /> 
							</div>
							<br />
							<br />
						</form>
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