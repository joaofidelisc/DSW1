<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/navbar.jsp"%>

<div class="container">
	<div class="row mb-3">
		<div class="col">
			<c:choose>
				<c:when test="${usuarioLogado.email != null}">
					<h1>Olá, ${usuarioLogado.email} !</h1>
				</c:when>

			</c:choose>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col">
			<h1>SISTEMA PARA AGENDAMENTO DE CONSULTAS ONLINE</h1>
		</div>
	</div>
	
	<div class="row mb-3">
		<div class="col">
			<h3>Integrantes</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col">
			<h5>Italo Carvalho Vianelli Ribeiro</h5>
			<h5>João Vitor Fidelis Cardozo</h5>
			<h5>Luiz Filipe de Almeida Mattos</h5>
			<h5>Matteus Guilherme De Souza</h5>
			<h5>Sara Ferreira</h5>
			
		</div>
	</div>

</div>

<%@include file="/footer.jsp"%>