<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.ufscar.dc.dsw.dao.ProfissionalDAO"%>
<%@page import="br.ufscar.dc.dsw.dao.ClienteDAO"%>
<%@page import="br.ufscar.dc.dsw.domain.Profissional"%>
<%@page import="br.ufscar.dc.dsw.domain.Cliente"%>
<%@page import="br.ufscar.dc.dsw.domain.Consulta"%>

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
		<table class="table align-middle">
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
			<c:forEach var="consulta" items="${requestScope.listaConsultas}">
<%
				Consulta auxConsulta = (Consulta) pageContext.getAttribute("consulta");
				prf = prfDAO.get( auxConsulta.getCpf_profissional() );
				clt = cltDAO.get( auxConsulta.getCpf_cliente() );
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String dataFormatada = df.format(auxConsulta.getData_consulta());
				
				
%>
				<tr class="${consulta.cancelada == true ? 'table-danger' : ''}">
					<th scope="row"><%=dataFormatada%></th>
<%					
					String strHoraConsulta = "";
					int hora = auxConsulta.getHora_consulta().getHours();
					int minuto = auxConsulta.getHora_consulta().getMinutes();
					
					if(  hora < 10) 
						strHoraConsulta = "0" + hora; 
					else 
						strHoraConsulta = "" + hora;
					strHoraConsulta += ":";
					
					if(  minuto < 10) 
						strHoraConsulta += "0" + minuto; 
					else 
						strHoraConsulta += "" + minuto;
%>
					<td><%=strHoraConsulta%></td>
<c:choose>
	<c:when test="${usuarioLogado.tipoLogin == 2 }"><!-- se ele for prof nao precisa ver ele mesmo -->
					<td><%=clt.getNome()%></td>
	</c:when>										
	<c:otherwise>									<!-- se ele for cliente nao precisa ver ele mesmo -->
					<td><%=prf.getNome()%></td>
	</c:otherwise>
</c:choose>	
					<td>
<c:choose >
<c:when test="${!consulta.cancelada}">
						<a href="/<%=contextPath%>/consultas/cancelar?num_consulta=${consulta.num_consulta}"onclick="return confirm('Tem certeza de que deseja cancelar esta consulta?');"><button type="button" class="btn btn-danger">Cancelar<i class="fa-solid fa-trash-can"></i></button></a>
</c:when>
<c:otherwise>
						Consulta cancelada
</c:otherwise>
		
</c:choose>
					</td>
				</tr>
			</c:forEach>
				
			</tbody>
		</table>
	</div>
</div>

<%@include file="../footer.jsp"%>