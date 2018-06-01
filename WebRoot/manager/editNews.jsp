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
		<!-- 在线编辑器 -->
		<script type="text/javascript" src="../fckeditor/fckeditor.js"></script>
		<script src="https://cdn.ckeditor.com/ckeditor5/1.0.0-alpha.2/inline/ckeditor.js"></script>
		<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
		<script type="text/javascript">
			$(function() {
				//1.建立FckEditor的对象
				var ofckEditor = new FCKeditor("content");
				//参数是页面的TextArea的name属性
				//2.设定路径
				ofckEditor.BasePath = "../fckeditor/";
				//绝对路径，news的工程名url
				//3.FckEditor的对象属性（宽、高）
				ofckEditor.Width = 1000;
				ofckEditor.Height = 400;
				//4.利用该FckEditor的对象替换掉页面的TextArea
				ofckEditor.ReplaceTextarea();

				//实时更新，图案片选择时，img的src属性跟着改变
				$("#pictures").change(function() {
					var file = $("#pictures").get(0).files[0]; //获取选择文件的图片文件内容
					if(file) {
						//读取文件内容
						var reader = new FileReader();
						reader.readAsDataURL(file);
						//读取完毕之后，修改img标签的src属性
						reader.onload = function(e) {
							$("#newPic").attr("src", e.target.result);
						}
					}
				});
			});
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
					<div class="crumb-list"><i class="icon-font"></i>
						<a href="index.jsp">首页</a><span class="crumb-step">&gt;</span>
						<a class="crumb-name" href="NewsList?op=newsList">新闻管理</a><span class="crumb-step">&gt;</span><span>修改新闻</span></div>
				</div>
				<div class="result-wrap">
					<div class="result-content">
						<form action="NewsList?op=edit" method="post" id="myform" name="myform" enctype="multipart/form-data">
							<input type="hidden" name="newsId" value="${news.newsId }" />
							<table class="insert-tab" width="100%">
								<tbody>
									<tr>
										<th width="120"><i class="require-red">*</i>新闻分类：</th>
										<td>
											<!-- 动态生成新闻分类 -->
											<select name="classId" id="classId" class="required">
												<c:forEach items="${classList}" var="c">
													<option value="${c.classId}" <c:if test="${c.classId==news.classId}">selected="selected"</c:if>>${c.content}
													</option>
												</c:forEach>
											</select>
										</td>

										<tr>
											<th><i class="require-red">*</i>新闻标题：</th>
											<td>
												<input class="common-text required" id="headTitle" name="headTitle" size="50" type="text" value="${news.headTitle }">
											</td>
										</tr>
										<tr>
											<th>作者：</th>
											<td><input class="common-text" name="author" size="50" type="text" value="${news.author }"></td>
										</tr>
										<tr>
											<th>编辑：</th>
											<td><input class="common-text" name="editor" size="50" type="text" value="${news.editor }"></td>
										</tr>
										<tr>
											<th>新闻来源：</th>
											<td><input class="common-text" name="newsFrom" size="50" type="text" value="${news.newsFrom }"></td>
										</tr>

										<!-- 新闻图片的操作   将图片地址与图片显示效果联动-->
										<tr>
											<th>新闻图片：</th>
											<td>
												<input id="pictures" name="pictures" type="file" value="${news.pictures }" accept="image/*"><br/>
												<img id="newPic" src="../images/${news.pictures }" width="425px" height="250px" />
											</td>
										</tr>

										<tr>
											<th>是否置顶：</th>
											<td>
												<input name="top" type="radio" value="1" <c:if test="${news.top==1 }">checked="checked"</c:if>
												>是
												<input name="top" type="radio" value="0" <c:if test="${news.top==0 }">checked="checked"</c:if>
												>否
											</td>
										</tr>

										<tr>
											<th>内容：</th>
											<td>
												<textarea name="content" class="common-textarea" id="content" cols="30" style="width: 98%;" rows="10">
                                					${news.content }
                                				</textarea>
											</td>
										</tr>
										<tr>
											<th></th>
											<td>
												<input class="btn btn-primary btn6 mr10" value="提交" type="submit">
												<input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
											</td>
										</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>

			</div>
			<!--/main-->

			<!----main end------>

		</div>

		<div class="clear"></div>
		<!--底部版权信息 start-->
		<div class="footer_location">
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