<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>ForFuture News 后台管理</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="icon" href="images/icon.jpg" type="x-images/icon"/>
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/component.css" />
<!--[if IE]>
<script src="js/html5.js"></script>
<![endif]-->
	<style type="text/css">
		input:focus{ 
			border:2px #2a445c solid;			
		}	
		
		#buttonStyle{
			background:none;
  		 	border:none;
		}
	</style>

	
</head>
<body>
		<div class="container demo-1">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<!--logo_box start--->
					<div class="logo_box">
						<h3 style="text-align:center; color:#1bd7ff; font-weight:bold">ForFuture News后台管理系统</h3>
						<form action="AdminLogin" name="f" method="post">
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="username" id="username" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入用户名">
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input name="password" id="password"  class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
							</div>
							<li id="error">
	                    		<%
		                    		String msg=(String)request.getAttribute("msg");
		                    		if(msg!=null && !msg.equals("")){
		                    			out.print(msg);
		                    		}
	                    	 	%>
                    		</li>
							<div class="mb2">
								<a class="act-but submit" style="color: #FFFFFF">
									<button style="submit" class="login_btn" id="buttonStyle">登 录</button>
								</a>
								
								
							</div>
							<div style="text-align:center; color:red; font-size:18px">
								<p style="color:white;">Copyright &copy; 2017-2020
									<a href="http://www.ganquanzhong.top/mynews/" target="_blank" style="color:gray; font-size:16px;font-family:'楷体'">
										ForFuture News
									</a>
								</p>
							</div>
						</form>
					</div>
					<!--logo_box end--->
				</div>
			</div>			
		</div>
		<!-- /container -->
	<script src="js/TweenLite.min.js"></script>
	<script src="js/EasePack.min.js"></script>
	<script src="js/rAF.js"></script>
	<script src="js/demo-1.js"></script>
		
	</body>
</html>
