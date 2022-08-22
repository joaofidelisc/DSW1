<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@include file="../navbar.jsp"%>
			<c:choose>
				<c:when test = "${cliente != null}">
					<form action="atualizacao" method ="post">
						<%@include file="campos.jsp" %>
					</form>
				</c:when>
				<c:otherwise>
					<form action="insercao" method="post">
						<%@include file="campos.jsp"%>
					</form>
				</c:otherwise>
			</c:choose>
	
<%@include file="../footer.jsp"%>