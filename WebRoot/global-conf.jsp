<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 整个ForFuture网站  主题设置-->

<!---返回顶部 start--Scroll滚回头部  可添加二维码-->
<a href="#" class="scrollup" title="返回头部!">滚动</a>
<a href="#" class="ecode" title="二维码">二维码</a>

<!---返回顶部 start---->

<!---定制customized主题设置 start---->
<div id="customize">
	<h5>主题</h5>
	<div class="wrapper">
		<!---全局颜色 start---->
		<div class="colors">
			<h6>颜色</h6>
			<a href="#" class="orange" title="Orange" style="background-color:#f15620;"></a> 
			<a href="#" class="blue" title="Blue" style="background-color:#1d96e2;"></a> 
			<a href="#"	class="green" title="Green" style="background-color:#45b519;"></a>
		</div>
		<!---全局颜色 end---->

		<!---全局背景 start---->
		<div class="background">
			<h6>背景</h6>
			<a href="#" class="1" title="Background 1"><img	src="images/pattern/1.jpg" alt="1" /></a> 
			<a href="#" class="2" title="Background 2"><img src="images/pattern/2.jpg" alt="2" /></a>
			<a href="#" class="3" title="Background 3"><img	src="images/pattern/3.jpg" alt="3" /></a> 
			<a href="#" class="4" title="Background 4"><img src="images/pattern/4.jpg" alt="4" /></a>
			<a href="#" class="5" title="Background 5"><img	src="images/pattern/5.jpg" alt="5" /></a> 
			<a href="#" class="6" title="Background 6"><img src="images/pattern/6.jpg" alt="6" /></a>
			<a href="#" class="7" title="Background 7"><img	src="images/pattern/7.jpg" alt="7" /></a> 
			<a href="#" class="8" title="Background 8"><img src="images/pattern/8.jpg" alt="8" /></a>
			<a href="#" class="9" title="Background 9"><img	src="images/pattern/9.jpg" alt="9" /></a> 
			<a href="#" class="10" title="Background 10"><img src="images/pattern/10.jpg" alt="10" /></a>
			<a href="#" class="11" title="Background 11"><img src="images/pattern/11.jpg" alt="11" /></a> 
			<a href="#" class="12"	title="Background 12"><img src="images/pattern/12.jpg" alt="12" /></a>
			<a href="#" class="13" title="Background 13"><img src="images/pattern/13.jpg" alt="13" /></a> 
			<a href="#" class="14"	title="Background 14"><img src="images/pattern/14.jpg" alt="14" /></a>
			<a href="#" class="15"	title="Background 15"><img src="images/pattern/15.jpg" alt="15" /></a>
		</div>
		<!---全局背景 end---->
	</div>
</div>
<!---定制customized主题设置 end---->