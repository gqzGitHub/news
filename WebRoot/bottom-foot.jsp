<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 整个ForFuture网站  bottom & foot -->
		<!---bottom底部  start---->
		<div id="bottom">
			<div class="container">
				<div class="row-fluid">

					<div class="span3 widget clearfix">
						<div class="header">
							<h4>关于我们</h4>
						</div>

						<div class="content">
							<span> ForFuture Group
								OpenStack <br /> <br /> VPN Spark ERP IE10 Eclipse CRM
								JavaScript 数据库 Ubuntu NFC WAP <br /> <br /> jQuery BI HTML5
								Spring Apache .NET API HTML SDK IIS Fedora XML <br /> <br />
								LBS Unity Splashtop UML components Windows Mobile Rails QEMU KDE
							</span>
						</div>
					</div>
					<!--1 End Widget -->

					<div class="span3 widget clearfix">
						<div class="tags-cloud">

							<div class="header">
								<h4>标签云</h4>
							</div>

							<ul>
								<li><a href="http://www.microsoft.com">windows</a></li>
								<li><a href="http://www.apple.com">apple</a></li>
								<li><a href="http://www.microsoft.com">software</a></li>								
								<li><a href="http://www.apache.org/">smartphone</a></li>
								<li><a href="http://www.apache.org/">Java Web</a></li>
								<li><a href="http://www.runoob.com/">Java EE</a></li>
								<li><a href="http://www.runoob.com/">JavaScript</a></li>
								<li><a href="http://www.runoob.com/">jQuery</a></li>
								<li><a href="http://www.runoob.com/">菜鸟教程</a></li>
								<li><a href="https://ganquanzhong.top/Web">ganquanzhong</a></li>
								<li><a href="https://ganquanzhong.top/ShareFiles">ForFuture ShareFiles</a></li>
								<li><a href="https://github.com/gqzGitHub/" target="_blank">gqzGitHub</a></li>	
							</ul>

						</div>
					</div>
					<!--2 End Widget -->

					<div class="span3 widget clearfix">
						<div class="tweets">

							<div class="header">
								<h4>Latest</h4>
							</div>

							<ul>
								<li>
									<span class="picons"> <!-- --></span>
									<p>
										<a href="https://github.com/gqzGitHub/">@gqzGitHub</a> CSDN (Chinese
										Software Developer Network) 创立于1999年， 是中国最大的IT社区和服务平台，
										为中国的软件开发者和IT从业者提供知识传播、职业发展、软件开发等全生命周期服务
									</p> <span class="date">- 10 hours ago</span>
								</li>

								<li>
									<span class="picons"> <!-- --></span>
									<p>
										<a href="https://gitee.com/">@GQZ ForFuture</a>
										码云(gitee.com)是开源中国社区团队推出的基于Git的快速的、
										免费的、稳定的在线代码托管平台,不限制私有库和公有库数量.
									</p> <span class="date">- 2 days ago</span>
								</li>								
							</ul>

						</div>
					</div>
					<!--3 End Widget -->

					<div class="span3 widget clearfix">
						<div class="flickr-photos">

							<div class="header">
								<h4>技术推送</h4>
							</div>

							<ul id="flickr-gallery">
								<a href="http://www.runoob.com" target="_blank">
								学的不仅是技术,更是梦想!</a>
								菜鸟教程(www.runoob.com)提供了最全的编程技术基础教程,
								介绍了HTML、CSS、Javascript、Python,Java,Ruby,C,PHP ,
								MySQL等各种编程语言的基础知识。
							</ul>

						</div>
					</div>
					<!--4 End Widget -->

				</div>
				<!-- End Row-Fluid -->
			</div>
			<!-- End Container -->
		</div>		
		<!---bottom底部  end---->

		<!---footer页脚  start---->
		<div id="footer">
			<div class="container">
				<!--版权所有 start--->
				<p class="pull-left">
					Copyright&copy;2017~2020 Mike Zhong&nbsp;&nbsp;
					|&nbsp;&nbsp;版权所有&nbsp;&nbsp; |&nbsp;&nbsp;违法必究&nbsp;&nbsp;
					|&nbsp;&nbsp; <a href="http://ganquanzhong.top/" target="_blank"
						title="ganquanzhong">FuFuture Share</a>&nbsp;ForFuture News <a href="#"
						target="_blank" title="ganquanzhong">mike zhong</a>
			      <img alt="公网安备" src="images/icp.png" width="20px" height="20px">	
				  <a href="http://www.beian.gov.cn/portal/registerSystemInfo" target="_blank">鄂ICP备18005830号</a>
				</p>
				
				<!--版权所有 end--->
				<!--社会 推送 start--->
				<ul class="social pull-right">
					<li><a href="#" title="Youtube"><img
							src="images/social/f0101/youtube.png" alt="Youtube" /></a></li>
					<li><a href="#" title="LinkedIn"><img
							src="images/social/f0101/linkedin.png" alt="LinkedIn" /></a></li>
					<li><a href="#" title="Vimeo"><img
							src="images/social/f0101/vimeo.png" alt="Vimeo" /></a></li>
					<li><a href="#" title="Tumblr"><img
							src="images/social/f0101/tumblr.png" alt="Tumblr" /></a></li>
					<li><a href="#" title="Flickr"><img
							src="images/social/f0101/flickr.png" alt="Flickr" /></a></li>
					<li><a href="#" title="DeviantArt"><img
							src="images/social/f0101/deviantart.png" alt="DeviantArt" /></a></li>
					<li><a href="#" title="Delicious"><img
							src="images/social/f0101/delicious.png" alt="Delicious" /></a></li>
					<li><a href="#" title="Facebook"><img
							src="images/social/f0101/facebook.png" alt="Facebook" /></a></li>
					<li><a href="#" title="Twitter"><img
							src="images/social/f0101/twitter.png" alt="Twitter" /></a></li>
				</ul>
				<!--社会 推送 end--->
			</div>
			<!-- End Container -->
		</div>		
		<!---footer页脚  end---->