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

		<link rel="icon" href="images/icon.jpg" type="x-images/icon"/>
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
			<div class="main-wrap">
		        <div class="crumb-wrap">
		            <div class="crumb-list">
		            	<!-- 消息提示，走马灯 start-->
		            	<marquee>
		            		<span style="background-color: #a3f7e3;color: #ff0000;font-family: initial;font-weight: bolder;
    									font-size: 26px;">
		            			ForFuture News
		            		</span>
		            		<span style="margin-left: 90px;color: #e20404;font-size: 22px;font-family: cursive;text-shadow: -1px 1px #e00d3d;">
		            			${sessionScope.adminUsername }
		            		</span>
		            		<span style="margin-left:60px;color:#33a185; font-size: 22px;">
		            			ForFuture News Manager System
		            		</span>
		            		<span style="margin-left:90px;background-color: #a3f7e3;color: #ff0000;font-family: initial;font-weight: bolder;
    									font-size: 26px;">
		            			ShareFiles
		            		</span>		            		
		            	</marquee>
		            	<!-- 消息提示，走马灯 start-->
		            </div>
		        </div>
		        <div class="result-wrap">
		        	<br style="line-height: 10px;"/>
		            <div class="result-title">
		                <h1 style="font-size: 18px;">快捷操作</h1>
		            </div>		            
		            <div class="result-content">
		                <div class="short-wrap">
		                    <a href="Back?op=preAdd"><i class="icon-font" style="padding-left:25px ;">&#xe005;</i>新增新闻</a>
		                    <a href="addNewsclass.jsp"><i class="icon-font" style="padding-left:25px ;">&#xe048;</i>新增新闻分类</a>
		                    <a href="Back?op=newsList"><i class="icon-font" style="padding-left:25px ;">&#xe041;</i>管理新闻</a>
		                    <a href="addAdmin.jsp"><i class="icon-font" style="padding-left:25px ;">&#xe041;</i>新增管理员</a>
		                    <a href="changePassword.jsp"><i class="icon-font" style="padding-left:25px ;">&#xe041;</i>修改密码</a>
		                </div>
		            </div>		           
		            <br />
		            <div class="result-title">
		                <h1 style="font-size: 18px;">新闻操作</h1>
		            </div>		            
		            <div class="result-content">
		                <div class="short-wrap">
		                	<a href="Back?op=preAdd"><i class="icon-font" style="padding-left:25px ;">&#xe005;</i>添加新闻</a>
							<a href="Back?op=preEdit&newsId=${news.newsId }"><i class="icon-font" style="padding-left:25px ;">&#xe005;</i>修改新闻</a>
							<a href="Back?op=newsClassList"><i class="icon-font" style="padding-left:25px ;">&#xe005;</i>新闻分类管理</a>
							<a href="Back?op=newsList"><i class="icon-font" style="padding-left:25px ;">&#xe005;</i>新闻管理</a>						
		                </div>
		            </div>
		            <br />
		            <div class="result-title">
		                <h1 style="font-size: 18px;">论坛操作</h1>
		            </div>		            
		            <div class="result-content">
		                <div class="short-wrap">
		                	<a href="addForum.jsp"><i class="icon-font" style="padding-left:25px ;">&#xe005;</i>添加论坛</a>											
		                	<a href="Back?op=forumList"><i class="icon-font" style="padding-left:25px ;">&#xe005;</i>论坛管理</a>	
		                </div>
		            </div>
		            <br/>
		            <div class="result-title">
		                <h1 style="font-size: 18px;">权限操作</h1>
		            </div>		            
		            <div class="result-content">
		                <div class="short-wrap">
		                	<a href="Back?op=adminList"><i class="icon-font" style="padding-left:25px ;">&#xe005;</i>管理员列表</a>											
		                	<a href="Back?op=userList"><i class="icon-font" style="padding-left:25px ;">&#xe005;</i>成员管理</a>	
		                </div>
		            </div>
		             <br/>
		            <div class="result-title">
		                <h1 style="font-size: 18px;">ForFuture Design</h1>
		            </div>		            
		            <div class="result-content">
		                <div class="short-wrap">
		                	<a href="https://ganquanzhong.top/ShareFiles/" target="_Blank"><i class="icon-font" style="padding-left:25px ;">&#xe005;</i>ShareFiles</a>											
		                	<a href="https://ganquanzhong.top/index/" target="_Blank"><i class="icon-font" style="padding-left:25px ;">&#xe005;</i>More</a>	
		                </div>
		            </div>
		        </div>
		        
		        
	        </div>
			<!----main end------>

		</div>

		<div class="clear"></div>
		<!--底部版权信息 start-->
		<div class="footer" style="bottom:auto;">
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