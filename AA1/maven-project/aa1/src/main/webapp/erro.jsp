<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../navbar.jsp"%>
<div class="container">
	<div class="row mb-3">
		<div class="col">
			<h1>Algo de errado aconteceu...</h1>		
		</div>
	</div>
<c:if test="${mensagens != null}">
	<div class="row mb-3">		
			<h3>Erros:</h3>
	</div>
	
	<div class="row mb-3 pb-0">
		<div class="col">
			<div class="alert alert-danger align-middle" role="alert">
				<ul>
					<c:forEach var="erro" items="${mensagens.erros}">
		  				<li>${erro}</li>
		  				</c:forEach>
				</ul>
						
			</div>
		</div>
	</div>		
</c:if>

</div>


<%@include file="../footer.jsp"%>