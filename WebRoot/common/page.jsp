<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

第${page.pageNumber}页/共${page.totalPageNumber}页
&nbsp;&nbsp;
<a href="${pageContext.request.contextPath }${page.url}
	&num=${page.prePageNumber}">上一页 </a>
&nbsp;&nbsp;
<a href="${pageContext.request.contextPath }${page.url}
	&num=${page.nextPageNumber}">下一页 </a>

