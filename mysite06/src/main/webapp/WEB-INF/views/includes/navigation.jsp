<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="emptyString" value="" />
<div id="navigation">
	<ul>
		<li><a href="${pageContext.request.contextPath }">전예준</a></li>
		<li><a href="${pageContext.request.contextPath }/guestbook">방명록</a></li>
		<li><a href="${pageContext.request.contextPath }/board">게시판</a></li>
		<li><a href="${pageContext.request.contextPath }/gallery">갤러리</a></li>
	</ul>
</div>