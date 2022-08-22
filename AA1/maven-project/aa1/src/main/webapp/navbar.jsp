<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Clínica</title>
<link
	href="<%=application.getContextPath() %>/front-end-src/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<%
		String contexto1 = request.getContextPath().replace("/", "");
	%>
	<nav class="navbar navbar-expand-xl navbar-dark bg-dark fixed-top"
		aria-label="Sixth navbar example">
		<div class="container-fluid"> 
		<a class="navbar-brand" href="/<%=contexto1%>/home.jsp">Clínica</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarsExample06" aria-controls="navbarsExample06"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			
	
			<div class="collapse navbar-collapse" id="navbarsExample06">
				<ul class="navbar-nav me-auto mb-2 mb-xl-0">

<c:if test="${usuarioLogado.email == null}">
					<li class="nav-item"><a class="nav-link active" href="/<%=contexto1%>" aria-current="page">Login</a></li>
					
</c:if>
	
					<!--APENAS PARA PROFISSIONAIS E CLIENTES-->
<c:if test="${usuarioLogado.tipoLogin == 2 || usuarioLogado.tipoLogin == 3}">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/<%=contexto1%>/consultas">Consultas</a></li>
</c:if>
					<!--APENAS PARA CLIENTE-->
<c:if test="${usuarioLogado.tipoLogin != 1 || usuarioLogado.tipoLogin == null}">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/<%=contexto1%>/profissionais">Profissionais</a>
					</li>
</c:if>

<c:if test="${usuarioLogado.tipoLogin == 1}">
					<!--APENAS PARA ADMIN-->
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Registros </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="/<%=contexto1%>/clientes">Cliente</a></li>
							<li><a class="dropdown-item" href="/<%=contexto1%>/profissionais">Profissional</a></li>
						</ul>
					</li>

					
					<li class="nav-item dropdown">
					
					<a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Cadastro 
					</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="/<%=contexto1%>/clientes/cadastro">Cliente</a></li>
							<li><a class="dropdown-item" href="/<%=contexto1%>/profissionais/cadastro">Profissional</a></li>
						</ul>
					</li>
</c:if>
<c:if test="${usuarioLogado.email != null}">
					<li class="nav-item">
						<a class="nav-link active" href="/<%=contexto1%>/logout.jsp" aria-current="page">Sair</a>
					</li>
</c:if>
	
				</ul>
			</div>
		</div>
	</nav>
	<br><br><br><br>