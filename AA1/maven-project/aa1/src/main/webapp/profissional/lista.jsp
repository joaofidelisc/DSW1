<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@include file="../navbar.jsp"%>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
<div class="container">
<form action="/<%=contexto1%>/profissionais" method="post">
	<div class="row mb-3">
		<div class="col">
			<h1>Filtros</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-3">
			<h5>Área de Conhecimento</h5>	
			<div class="form-check mb-2">
				<input class="form-check-input" type="checkbox" name="checkArea" value="Medicina">
			  	<label class="form-check-label" for="checkMedicina">
			    	Medicina
			  	</label>
			</div>
			<div class="form-check mb-2">
				<input class="form-check-input" type="checkbox" name="checkArea" value="Advocacia">
			  	<label class="form-check-label" for="checkAdvocacia">
			    	Advocacia
			  	</label>
			</div>
			<div class="form-check mb-2">
				<input class="form-check-input" type="checkbox" name="checkArea" value="Psicologia">
			  	<label class="form-check-label" for="checkPsicologia">
			    	Psicologia
			  	</label>
			</div>	
		</div>
		<div class="col-3">
			<h5>Especialidade</h5>	
			<div class="form-check mb-2">
				<input class="form-check-input" type="checkbox" name="checkEspecialidade" value="Cardiologista">
			  	<label class="form-check-label" for="checkCardiologista">
			    	Cardiologista
			  	</label>
			</div>
			<div class="form-check mb-2">
				<input class="form-check-input" type="checkbox" name="checkEspecialidade" value = "Advogado Criminal">
			  	<label class="form-check-label" for="checkAdvogadoCriminal">
			    	Advogado Criminal
			  	</label>
			</div>
			<div class="form-check mb-2">
				<input class="form-check-input" type="checkbox" name="checkEspecialidade" value="Psicologo Infantil">
			  	<label class="form-check-label" for="checkPsicologoInfantil">
			    	Psicologo Infantil
			  	</label>
			</div>	
		</div>
	</div>
	<div class="row mb-3">
		<div class="col">
			<button type="submit" class="btn btn-primary">Filtrar</button>
			<button type="reset" class="btn btn-secondary">Limpar</button>
		</div>
	</div>
</form>
	<div class="row mb-3">
		<div class="col">
			<h1>Registros de Profissionais</h1>
		</div>
	</div>

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
<c:if test="${usuarioLogado.tipoLogin == 1 ||  usuarioLogado.tipoLogin == 3}"><!-- Admins podem alterar/deletar e clientes podem marcar consultas -->
					<th scope="col">Operações</th>
</c:if>					

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
<c:if test="${usuarioLogado.tipoLogin == 1 }">					
					<td>
						<a href="/<%=contextPath%>/profissionais/edicao?cpf=${profissional.cpf}"><button type="button" class="btn btn-primary">Editar<i class="fa-solid fa-trash-can"></i></button></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/<%=contextPath%>/profissionais/remocao?cpf=${profissional.cpf}"onclick="return confirm('Tem certeza de que deseja apagar este profissional?');"><button type="button" class="btn btn-danger">Apagar <i class="fa-solid fa-trash-can"></i></button></a>
					</td>
</c:if>
<c:if test="${usuarioLogado.tipoLogin == 3}"> <!--  se for cliente, ele pode agendar uma consulta -->
					<td>
						<a href="/<%=contextPath%>/consultas/cadastro?cpf_profissional=${profissional.cpf}"><button type="button" class="btn btn-primary">Agendar Consulta<i class="fa-solid fa-trash-can"></i></button></a>
					</td>
</c:if>
				</tr>
			</c:forEach>
				
			</tbody>
		</table>
	</div>
</div>
</body>

</html>
<%@include file="../footer.jsp"%>