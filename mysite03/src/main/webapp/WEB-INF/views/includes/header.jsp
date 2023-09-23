<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="
             org.springframework.web.context.*,
             org.springframework.web.context.support.*"%>
<%@ page import="com.poscodx.mysite.listener.Site" %>
<%@ page import="com.poscodx.mysite.vo.SiteVo" %>
<%
   ServletContext ctx = pageContext.getServletContext();
   WebApplicationContext wac =
         WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
   
	Site siteBean = (Site)wac.getBean("site");
	SiteVo tempVo = (SiteVo)siteBean.getSiteVo();
	request.setAttribute("tempVo", tempVo);
	out.println("temp:----->" + tempVo);
%>

<div id="header">
	<h1>${tempVo.title }</h1>
	<ul>
		<c:choose>
			<c:when test="${empty authUser }">
				<li><a href="${pageContext.request.contextPath }/user/login">로그인</a><li>
				<li><a href="${pageContext.request.contextPath }/user/join">회원가입</a><li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath }/user/update">회원정보수정</a><li>
				<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a><li>
				<li>${authUser.name }님 안녕하세요 ^^;</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>