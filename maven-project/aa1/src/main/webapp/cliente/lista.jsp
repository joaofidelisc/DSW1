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
			<h1>Registros de clientes</h1>
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
					<th scope="col">Telefone</th>
					<th scope="col">Sexo</th>
					<th scope="col">Nascimento</th>
					<th scope="col">Operações</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="cliente" items="${requestScope.listaClientes}">
				<tr>
					<th scope="row">${cliente.cpf}</th>
					<td>${cliente.nome}</td>
					<td>${cliente.email}</td>
					<td>${cliente.telefone }</td>
					<td>${cliente.sexo}</td>
					<td>${cliente.data_nascimento}</td>
					<td>
						<a inline href="/<%=contextPath%>/clientes/edicao?cpf=${cliente.cpf}"><button type="button" class="btn btn-primary">Editar<i class="fa-solid fa-trash-can"></i></button></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/<%=contextPath%>/clientes/remocao?cpf=${cliente.cpf}"onclick="return confirm('Tem certeza de que deseja apagar este cliente?');"><button type="button" class="btn btn-danger">Apagar <i class="fa-solid fa-trash-can"></i></button>
						</a>
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