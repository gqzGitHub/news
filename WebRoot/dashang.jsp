<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>

<head>
<link rel="shortcut icon" href="images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>打赏</title>

<script src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/dashang.js"></script>
<link href='css/dashang.css'	rel='stylesheet' type='text/css'>
<script type="text/javascript">
	$(function() {
		$(".pay_item")
				.click(
						function() {
							$(this).addClass('checked').siblings('.pay_item')
									.removeClass('checked');
							var dataid = $(this).attr('data-id');
							$(".shang_payimg img").attr("src",
									"img/" + dataid + ".png");
							$("#shang_pay_txt").text(
									dataid == "alipay" ? "支付宝" : "微信");
						});
	});

	function dashangToggle() {
		$(".hide_box").fadeToggle();
		$(".shang_box").fadeToggle();
	}
</script>
</head>

<body>
	<!-- 打赏 start -->
	<!---打赏 start-->
	<div class="dashang_content">
		<p>
			<a href="javascript:void(0)" onclick="dashangToggle()"
				class="dashang" title="打赏，支持一下">打赏</a>
		</p>
		<div class="hide_box"></div>

		<div class="shang_box">
			<a class="shang_close" href="javascript:void(0)"
				onclick="dashangToggle()" title="关闭"> <img src="img/close.jpg"
				alt="取消" /></a>
			<div class="shang_tit">
				<p>感谢您的支持，我会继续努力的!</p>
			</div>
			<div class="shang_payimg">
				<img src="img/alipay.png" alt="扫码支持" title="扫一扫" />
			</div>
			<div class="pay_explain">扫码打赏，你说多少就多少</div>
			<div class="shang_payselect">
				<div class="pay_item checked" data-id="alipay">
					<span class="radiobox"></span> <span class="pay_logo"><img
						src="img/alipay.jpg" alt="支付宝" /></span>
				</div>
				<div class="pay_item" data-id="weipay">
					<span class="radiobox"></span> <span class="pay_logo"><img
						src="img/wechat.jpg" alt="微信" /></span>
				</div>
			</div>
			<div class="shang_info">
				<p>
					打开<span id="shang_pay_txt">支付宝</span>扫一扫，即可进行扫码打赏哦
				</p>
				<p>
					Powered by <a href="#" target="_blank" title="ForFuture Design">ForFuture</a>，Design
					ForFuture
				</p>
			</div>
		</div>
	</div>
	<!---打赏 end-->
	<!-- 打赏 end -->

</body>
</html>