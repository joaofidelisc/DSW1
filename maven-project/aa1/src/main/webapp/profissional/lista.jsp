<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@include file="../navbar.jsp"%>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
<div class="container">
	<div class="row">
		<div class="col">
			<h1>Registros de Profissionais</h1>
		</div>
	</div>
	<br>
	<div>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">CPF</th>
					<th scope="col">Nome</th>
					<th scope="col">email</th>
					<th scope="col">Área</th>
					<th scope="col">Especialidade</th>
					<th scope="col">Currículo</th>
					<th scope="col">Operações</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="profissional" items="${requestScope.listaProfissionais}">
				<tr>
					<th scope="row">${profissional.cpf}</th>
					<td>${profissional.nome}</td>
					<td>${profissional.email}</td>
					<td>${profissional.areaConhecimento}</td>
					<td>${profissional.especialidade}</td>
					<td><a href="${profissional.local_pdf}" target="_blank">Currículo</a></td>
					<td>
						<a href="/<%=contextPath%>/profissionais/edicao?cpf=${profissional.cpf}"><button type="button" class="btn btn-primary">Editar<i class="fa-solid fa-trash-can"></i></button></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/<%=contextPath%>/profissionais/remocao?cpf=${profissional.cpf}"onclick="return confirm('Tem certeza de que deseja apagar este profissional?');"><button type="button" class="btn btn-danger">Apagar <i class="fa-solid fa-trash-can"></i></button></a>
					</td>
				</tr>
			</c:forEach>
				
			</tbody>
		</table>
	</div>
</div>
</body>

</html>
<%@include file="../footer.jsp"%>