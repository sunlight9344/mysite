<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("newline", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(vo.contents, newline, "<br>") }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?curPage=${curPage }">글목록</a>
					
					<c:choose>
						<c:when test="${authUser.no eq vo.user_no }">
							<a href="${pageContext.request.contextPath }/board?a=modifyform&no=${vo.no }&curPage=${curPage }">수정</a>
						</c:when>
						<%--
							<c:when test="${empty authUser }">
								<a href="${pageContext.request.contextPath }/user?a=loginform" id="new-book">수정(로그인 필요)</a>
							</c:when>
							<c:otherwise>
								<a href="">수정불가</a>
							</c:otherwise>
						 --%>
					</c:choose>
					
					<c:choose>
						<c:when test="${empty authUser }">
							<a href="${pageContext.request.contextPath }/user?a=loginform" id="new-book">답글쓰기(로그인 필요)</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath }/board?a=writeform&no=${vo.no }" id="new-book">답글쓰기</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>