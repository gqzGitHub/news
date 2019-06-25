<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 整个ForFuture网站  top navigation-->
		<!---顶部导航 start---->
		<div id="top-navigation">
			<div class="container">
				<!-- Navigation start-->
				<ul class="nav-menu pull-left">
					<li class="active">
						<a href="Front?op=main"	style="color: #fffcfc;font-size: 29px;font-family: '楷体';">FroFuture News</a>
						<div class="nav-sub-menu">
							<ul class="container">								
								<li>
									<a href="Front?op=beforeReg" target="_blank">Register</a>
								</li>
								<li>
									<a href="/index/time.html" target="_blank">Time</a>
								</li>
								<li>
									<a href="/index" target="_blank">More</a>
								</li>
								<li>
									<a href="https:ganquanzhong.top" target="_blank">Share</a>
								</li>	
								<li>
									<a href="/translate/" target="_blank">Translate</a>
								</li>							
								<li>
									<a href="https://github.com/gqzGitHub/" target="_blank">gqzGitHub</a>
								</li>
								<li>
									<a href="/news/manager/Mlogin.jsp" target="_blank">News Manager</a>
								</li>							
							</ul>
						</div>
					</li>
				</ul>
				<!-- Navigation end-->

				<!-- 整个网络Search Form start-->
				<form name="form-search" method="post"
					action="Front?op=query&search=theweb"
					class="form-search pull-right">
					<input type="text" name="q" placeholder="整个网络...."
						class="input-icon input-icon-search" /> <input type="submit"
						name="Search" value="搜索" alt="Search" id="searchbutton" title="搜索" />
				</form>
				<!-- Search Form end-->

			</div>
			<!-- End Container -->
		</div>
		<!-- End Top-Navigation -->
		<!---顶部导航 end---->