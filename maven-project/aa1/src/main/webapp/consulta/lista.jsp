<%@page import="br.ufscar.dc.dsw.dao.ProfissionalDAO"%>
<%@page import="br.ufscar.dc.dsw.dao.ClienteDAO"%>
<%@page import="br.ufscar.dc.dsw.domain.Profissional"%>
<%@page import="br.ufscar.dc.dsw.domain.Cliente"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@include file="../navbar.jsp"%>
	<%
		String contextPath = request.getContextPath().replace("/", "");
		ClienteDAO cltDAO = new ClienteDAO();
		ProfissionalDAO prfDAO = new ProfissionalDAO();
		Cliente clt = null;
		Profissional prf= null;
	%>
<div class="container">
	<div class="row">
		<div class="col">
			<h1>Olá, ${usuarioLogado.email}. Aqui estão as suas consultas.</h1>
		</div>
	</div>
	<br>
	<div>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Data</th>
					<th scope="col">Hora</th>
<c:choose>
	<c:when test="${usuarioLogado.tipoLogin == 2 }"><!-- se ele for prof nao precisa ver ele mesmo -->
					<th scope="col">Cliente</th>
	</c:when>										
	<c:otherwise>									<!-- se ele for cliente nao precisa ver ele mesmo -->
						<th scope="col">Profissional</th>
	</c:otherwise>
</c:choose>	

					<th scope="col">Operações</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="consulta" items="${requestScope.listaConsulta}">
<%
				prf = prfDAO.get( Long.parseLong( request.getAttribute("consulta.cpf_profissional").toString() ) );
				clt = cltDAO.get( Long.parseLong( request.getAttribute("consulta.cpf_cliente").toString() ) );
%>
				<tr class="${consulta.cancelada == TRUE ? 'table-danger' : ''}">
					<th scope="row">${consulta.data_consulta}</th>
					<td>${consulta.hora_consulta}</td>
<c:choose>
	<c:when test="${usuarioLogado.tipoLogin == 2 }"><!-- se ele for prof nao precisa ver ele mesmo -->
					<td><%=clt.getNome()%></td>
	</c:when>										
	<c:otherwise>									<!-- se ele for cliente nao precisa ver ele mesmo -->
					<td><%=prf.getNome()%></td>
	</c:otherwise>
</c:choose>	
					
					<td>
						<a inline href="/<%=contextPath%>/consultas/edicao?num_consulta=${consulta.num_consulta}"><button type="button" class="btn btn-primary">Editar<i class="fa-solid fa-trash-can"></i></button></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/<%=contextPath%>/clientes/remocao?num_consulta=${consulta.num_consulta}"onclick="return confirm('Tem certeza de que deseja cancelar esta consulta?');"><button type="button" class="btn btn-danger">Cancelar <i class="fa-solid fa-trash-can"></i></button></a>
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