<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%-- 引用jstl标签库 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="nav-top">
	<span id="logo">ForFuture News后台管理系统</span>
	<!--top之right start-->
	<div class="nav-topright">
		<!---当前时间 start--->
		<span id="currentdate"> <script type="text/javascript">
			var m_names = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
					"八月", "September", "October", "November", "December");
			var d_names = new Array("周日", "周一", "周二", "周三", "周四", "周五", "周六");
			//获取当前系统的时间 年月日
			var currentTime = new Date();
			var day = currentTime.getDay();
			var month = currentTime.getMonth() + 1;
			var date = currentTime.getDate();
			var year = currentTime.getFullYear();

			document.write(d_names[day] + ", " + date + " "
					+ m_names[month - 1] + " " + year + ", ");
			//获取当前时间 时 分 秒
			var currentTime = new Date();
			var hours = currentTime.getHours();
			var minutes = currentTime.getMinutes();
			if (minutes < 10) {
				minutes = "0" + minutes;
			}
			document.write(hours + ":" + minutes + " ");
			if (hours > 11) {
				document.write("PM");
			} else {
				document.write("AM");
			}
		</script>
		</span>
		<!---当前时间 end--->
		<div class="top-info-wrap">
			<ul class="top-info-list clearfix">
				<a href="editAdmin.jsp">
					<li class="top_user">${sessionScope.adminUsername }</li>
				</a>
				<a href="changePassword.jsp">
					<li class="top_user">修改密码</li>
				</a>
				<a href="AdminQuit">
					<li class="top_user">注销</li>
				</a>
			</ul>
		</div>
	</div>
	<!--top之right end-->
</div>

