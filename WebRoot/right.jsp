<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="tabcontent.css" />
<style type="text/css">
	#login_success {
		padding-left: 40px;
		color: red;
		font-size: 16px;
		font-weight: bold;
	}
	
	#exit {
		font-weight: bold;
		float: right;
		padding-right: 10px;
	}
	
	#exit:hover {
		color: red;
	}
	
	.form_row input{
		width:259px;
		height:36px;
	}
	
	.login input .button{
		padding-left:20px;
	}
</style>


<script type="text/javascript">
		//新闻用户登录
		function login() {
			//1.获取表单中username和password中的值
			var username = $("#username").val();
			var password = $("#password").val();
			//alert("用户名为"+username+"密码为"+password);
			//2.发送Ajax请求
			$.post("Front?op=login",//ajax的请求URL地址
			{
				"username":username,
				"password":password
			},
			//3.服务器请求成功，在页面显示登录的结果
			function(data) {
				//alert(username+"  "+password+"  "+data+"  "+eval(data));
				if (eval(data) == true) {
					/* $("#templatemo_newsletter_section").empty();
					var tempDiv=$("<div><span id='login_success'>登录成功!，欢迎你  "+username+"</span></div>");
					$("#templatemo_newsletter_section").append(tempDiv); */
					alert("登录成功，欢迎您！！"+username);
					window.location.reload(true);
				} else if (eval(data) == false) {
					alert("登录失败，你输入的用户名和密码有误，请重新登录！");
					//登录失败，将username、password输入框清空
					$("#username").val("");
					$("#password").val("");
				}
			});
		}
	
	
		//退出登录
		function exit() {
			$.post("Front?op=exit",//ajax的请求URL地址					
			//3.服务器请求成功，在页面显示登录的结果
			function(data) {
				alert(data);
				window.location.reload(true);
			});
		}
	</script>


<!---侧边栏Sidebar start--->
<div id="sidebar" class="span4">

	<!--widget 1 Start---->
	<!--登录注册 ---->
	<div class="widget clearfix">
		<div class="subscribe-form">

			<div class="header">
				<h4>登录</h4>
			</div>
			
			<div class="clear"></div>
			<div class="content">
				<p>您可以通过登录，发表属于您自己的评论。</p>
				
				<div class="login">
				<c:choose>
					<c:when test="${sessionScope.newsUsername==null }">
						<form method="get" action="#" id="form">
							<div class="form_row">
								<label>用户名</label> 
								<input class="inputfield" name="username" type="text" 
										id="username" placeholder="用户名" /><br /> <br /> 
								<label>密码</label>
								<input class="inputfield" name="password" type="password"
										id="password" placeholder="密码" /><br />
							</div>
							<br/>
							<input class="button pull-left" type="button" name="Submit" value="登录" onclick="javascript:login()" />
							
							<input class="button pull-right" type="reset" name="reset" value="重置" /> <br /> <br />
							<p>
								还未注册？请点击<a href="Front?op=beforeReg">注册</a>！
							</p>
							<p>
								忘记密码？请点击<a href="#">密码提示</a>！
							</p>
						</form>				
					</c:when>
					
					<c:otherwise>					
						<div id="login_success">
							ForFuture News 欢迎您！！ ${sessionScope.newsUsername } 
							<a id="exit" href="javascript:exit();">退出</a>
						</div>	
					</c:otherwise>
					</c:choose>
				</div>				
			</div>
		</div>
	</div>
	<!-- End Widget 1-->

	<!--widget 1 Start---->
	<div class="widget clearfix" style="display:none;">
		<ul class="social-subscribers">
			<li><a href="#" data-original-title="Like us on Facebook"> <img
					src="images/social/somacro/facebook.png" alt="Facebook" />
			</a>
				<p>
					25,645<i>likes</i>
				</p></li>
			<li><a href="#" data-original-title="Follow us on Twitter">
					<img src="images/social/somacro/twitter.png" alt="Twitter" />
			</a>
				<p>
					8,480<i>followers</i>
				</p></li>
			<li><a href="#" data-original-title="Subscribe our RSS Feed">
					<img src="images/social/somacro/rss.png" alt="RSS" />
			</a>
				<p>
					17,289<i>subscribers</i>
				</p></li>
		</ul>
	</div>
	<!-- End Widget 1-->

	<div class="widget clearfix">
		<div class="subscribe-form">
			<div class="header">
				<a href="https:ganquanzhong.top/" target="_blank">
					<h4>Subscribe Us</h4>
				</a>
			</div>

			<div class="content">
				<p>你可以随时更新我们的最新消息，订阅我们的时事通讯。</p>
					<form name="subscribe-form" id="" method="post" action="#">
						<input type="text" name="email" placeholder="your_email@qq.com" />
						<input type="submit" name="submit" value="Send" />
					</form>
		    </div>
		</div>
	</div>

	<!--widget 2 Start---->
	<!--新闻热度之comment start---->
	<div class="widget clearfix">
		<div class="enews-tab">

			<!-- Tab Menu -->
			<ul class="nav nav-tabs" id="enewsTabs">
				<li class="active"><a href="#tab-populars" data-toggle="tab">热度</a></li>
				<li><a href="#tab-recents" data-toggle="tab">最近</a></li>
				<li><a href="#tab-comments" data-toggle="tab">评论</a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane active" id="tab-populars">

					<!-- One -->
					<div class="item">
						<figure class="pull-left">
							<img src="images/content/300/5.jpg" alt="Thumbnail 1" />
						</figure>
						<div class="pull-right content">
							<h4>
								<a href="single_post.html" title=" Sebastian's Square in Opole">
									Small Market and St. Sebastian's Square in Opole </a>
							</h4>
							<p class="meta">7,849 views&nbsp;&nbsp;|&nbsp;&nbsp;49
								comments</p>
						</div>
					</div>

					<!-- Two -->
					<div class="item">
						<figure class="pull-left">
							<img src="images/content/300/12.jpg" alt="" />
						</figure>
						<div class="pull-right content">
							<h4>
								<a href="single_post.html" title="om in Italy"> Living Room
									in Italy </a>
							</h4>
							<p class="meta">6,988 views&nbsp;&nbsp;|&nbsp;&nbsp;42
								comments</p>
						</div>
					</div>

					<!-- Three -->
					<div class="item">
						<figure class="pull-left">
							<img src="images/content/300/7.jpg" alt="Thumbnail 3" />
						</figure>
						<div class="pull-right content">
							<h4>
								<a href="single_post.html" title="View  Minimal Design">
									Platform House With Minimal Design </a>
							</h4>
							<p class="meta">5,724 views&nbsp;&nbsp;|&nbsp;&nbsp;35
								comments</p>
						</div>
					</div>

					<!-- Four -->
					<div class="item">
						<figure class="pull-left">
							<img src="images/content/300/4.jpg" alt="Thumbnail 4" />
						</figure>
						<div class="pull-right content">
							<h4>
								<a href="single_post.html" title="Vie Silver Panorama">
									Winter Kitchen With Silver Panorama </a>
							</h4>
							<p class="meta">5,198 views&nbsp;&nbsp;|&nbsp;&nbsp;39
								comments</p>
						</div>
					</div>

				</div>
				<!-- End Populars -->

				<div class="tab-pane" id="tab-recents">

					<!-- One -->
					<div class="item">
						<figure class="pull-left">
							<img src="images/content/300/2.jpg" alt="Thumbnail 1" />
						</figure>
						<div class="pull-right content">
							<h4>
								<a href="single_post.html"
									title="View permalink Alvear Art Black and White Theme">Alvear
									Art Black and White Theme</a>
							</h4>
							<p class="meta">
								In <a href="blog_posts.html">Technology</a> on Jan. 14, 2018
							</p>
						</div>
					</div>

					<!-- Two -->
					<div class="item">
						<figure class="pull-left">
							<img src="images/content/300/5.jpg" alt="Thumbnail 2" />
						</figure>
						<div class="pull-right content">
							<h4>
								<a href="single_post.html"
									title="View permalink Camerette - Your Time to Explore">Camerette
									- Your Time to Explore</a>
							</h4>
							<p class="meta">
								In <a href="blog_posts.html">Education</a> on Jan. 13, 2017
							</p>
						</div>
					</div>

					<!-- Three -->
					<div class="item">
						<figure class="pull-left">
							<img src="images/content/300/4.jpg" alt="Thumbnail 3" />
						</figure>
						<div class="pull-right content">
							<h4>
								<a href="single_post.html"
									title="View permalink Living Room in Italy">Living Room in
									Italy</a>
							</h4>
							<p class="meta">
								In <a href="blog_posts.html">Entertainment</a> on Jan. 12, 2017
							</p>
						</div>
					</div>

					<!-- Four -->
					<div class="item">
						<figure class="pull-left">
							<img src="images/content/300/11.jpg" alt="Thumbnail 4" />
						</figure>
						<div class="pull-right content">
							<h4>
								<a href="single_post.html"
									title="View permalink Mosaic Pool is Amazing And Beautiful Place">Mosaic
									Pool is Amazing And Beautiful Place</a>
							</h4>
							<p class="meta">
								In <a href="blog_posts.html">Education</a> on Jan. 11, 2017
							</p>
						</div>
					</div>

					<!-- Five -->
					<div class="item">
						<figure class="pull-left">
							<img src="images/content/300/8.jpg" alt="Thumbnail 5" />
						</figure>
						<div class="pull-right content">
							<h4>
								<a href="single_post.html"
									title="View permalink Platform House with Minimal Design">Platform
									House with Minimal Design</a>
							</h4>
							<p class="meta">
								In <a href="blog_posts.html">Business</a> on Jan. 10, 2013
							</p>
						</div>
					</div>

				</div>
				<!-- End Recents -->

				<div class="tab-pane" id="tab-comments">

					<!-- One -->
					<div class="item">
						<figure class="pull-left">
							<img src="images/content/avatar/1.jpg" alt="Avatar 1" />
						</figure>
						<div class="pull-right content">
							<p>
								<a href="#">mdkiwol</a> on <a href="single_post.html"
									title="View comment on Glass House Below The Dark of Moon Light">Glass
									House Below The Dark of Moon Light</a>
							</p>
						</div>
					</div>

					<!-- Two -->
					<div class="item">
						<figure class="pull-left">
							<img src="images/content/avatar/2.jpg" alt="Avatar 2" />
						</figure>
						<div class="pull-right content">
							<p>
								<a href="#">holland</a> on <a href="single_post.html"
									title="View comment on Winter Kitchen with Silver Panorama">Winter
									Kitchen with Silver Panorama</a>
							</p>
						</div>
					</div>

					<!-- Three -->
					<div class="item">
						<figure class="pull-left">
							<img src="images/content/avatar/3.jpg" alt="Avatar 3" />
						</figure>
						<div class="pull-right content">
							<p>
								<a href="#">jagerjack</a> on <a href="single_post.html"
									title="View comment on Mosaic Pool is Very Amazing And Beautiful">Mosaic
									Pool is Very Amazing And Beautiful</a>
							</p>
						</div>
					</div>

					<!-- Four -->
					<div class="item">
						<figure class="pull-left">
							<img src="images/content/avatar/4.jpg" alt="Avatar 4" />
						</figure>
						<div class="pull-right content">
							<p>
								<a href="#">john</a> on <a href="single_post.html"
									title="View comment on House in The Woods">House in The
									Woods</a>
							</p>
						</div>
					</div>

					<!-- Five -->
					<div class="item">
						<figure class="pull-left">
							<img src="images/content/avatar/3.jpg" alt="Avatar 5" />
						</figure>
						<div class="pull-right content">
							<p>
								<a href="#">jagerjack</a> on <a href="single_post.html"
									title="View comment on Camerette - Your Time to Explore">Camerette
									- Your Time to Explore</a>
							</p>
						</div>
					</div>

				</div>
				<!-- End Comments -->
			</div>
			<!-- End Tab-Content -->

		</div>
		<!-- End Enews-Tab -->
	</div>
	<!--新闻热度之comment start---->
	<!-- End Widget 2-->
	


	<!--widget 3 Start---->
	<!--新闻图片显示 ---->
	<div class="widget clearfix">
		<div class="sponsors">

			<div class="header">
				<h4>今日图片</h4>
			</div>

			<div class="content">
				<a href="http://ganquanzhong.top">
				<img src="images/today/today-1.jpg" alt="图片 1" /> </a>
				<img src="images/today/today-2.jpg" alt="图片 2" />
				<img src="images/today/today-3.jpg" alt="图片 3"/> 
				<img src="images/today/today-4.jpg" alt="图片 4" />
			</div>

		</div>
	</div>
	<!-- End Widget 3-->

	<!--widget 4 Start---->
	<!--最佳图片显示 ---->
	<div class="widget clearfix">
		<div class="best-picture">

			<div class="header">
				<h4>最佳图片</h4>
			</div>

			<div class="content">
				<p style="font-size: 14px;text-align: center;text-decoration: underline;">
					<a href="single_photo.html" title="ForFuture News" >
						ForFuture News成功上线！！
						<a href="http:ganquanzhong.top" title="ForFuture Share"> ForFuture Share</a>
					</a>
				</p>

				<!-- Photo Galleries start-->
				<figure class="flexslider loading">
					<ul class="slides">
						<li><img src="images/content/full/4.jpg" alt="Thumbnail 1" /></li>
						<li><img src="images/content/full/9.jpg" alt="Thumbnail 2" /></li>
						<li><img src="images/content/full/6.jpg" alt="Thumbnail 3" /></li>
					</ul>
				</figure>
				<!-- Photo Galleries end-->

				<div class="meta">
					By <a href="author.html">ganquanzhong</a>
					&nbsp;&nbsp;|&nbsp;&nbsp;4. 1, 2018&nbsp;&nbsp;|&nbsp;&nbsp; <a
						href="single_video.html">66 comments</a>
				</div>
			</div>
		</div>
	</div>
	<!-- End Widget 4-->

	<!--widget 6 Start---->
	<div class="widget clearfix" style="display: none;">
		<div class="best-video">
			<div class="header">
				<h4>本周视频</h4>
			</div>

			<div class="content">
				<p>
					<a href="http://blog.csdn.net/" title="本周视频"> 全球最大中文IT社区 </a>
				</p>
				<figure>
					<iframe width="357px" height="316px" src="index.html"></iframe>
				</figure>
				<div class="meta">
					By <a href="author.html">ganquanzhong</a>
					&nbsp;&nbsp;|&nbsp;&nbsp;Jan. 12, 2013&nbsp;&nbsp;|&nbsp;&nbsp; <a
						href="single_video.html">15 comments</a>
				</div>
			</div>

		</div>
	</div>
	<!-- End Widget 6-->

</div>
<!-- End Sidebar -->
<!---侧边栏Sidebar end--->