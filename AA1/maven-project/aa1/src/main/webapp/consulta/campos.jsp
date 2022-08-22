<%@page import="br.ufscar.dc.dsw.domain.Cliente"%>
<%@page import="br.ufscar.dc.dsw.domain.Login"%>
<%@page import="br.ufscar.dc.dsw.dao.ClienteDAO"%>
<%@page import="br.ufscar.dc.dsw.dao.ProfissionalDAO"%>
<%@page import="br.ufscar.dc.dsw.domain.Profissional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	ProfissionalDAO prf_dao = new ProfissionalDAO();
	Profissional prf = prf_dao.get( Long.parseLong( request.getParameter("cpf_profissional") ) );
	
	ClienteDAO clt_dao = new ClienteDAO();
	Login lgn = (Login) session.getAttribute("usuarioLogado");
	Cliente clt = clt_dao.get(clt_dao.getCpfUsuarioPorEmail( lgn.getEmail() ) );
%>

<div class="container">	<div class="row">
	<div class="row">
		<div class="col">
			<h1>Agendar consulta</h1>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col">
			<h3>Informações do cliente</h3>
		</div>
	</div>

	<div class="row mb-3">
		<div class="col-3">
			<label for="cliente_email" class="form-label">Email</label> 
			<input type="text" class="form-control" id="cliente_email" name="cliente_email" value="<%=clt.getEmail()%>" readonly>
		</div>
		<div class="col-5">
			<label for="cliente_nome" class="form-label">Nome</label> 
			<input class="form-control" id="cliente_nome" name="cliente_nome" value="<%=clt.getNome()%>" readonly>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col">
			<h3>Informações do Profissional</h3>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-3">
			<label for="profissional_email" class="form-label">Email</label>
			<input type="email" class="form-control" id="profissional_email" name="profissional_email" value="<%=prf.getEmail()%>" readonly> 
		</div>
			<div class="col-5">
			<label for="profissional_nome" class="form-label">Nome</label> 
			<input class="form-control" id="profissional_nome" name="profissional_nome" value="<%=prf.getNome()%>" readonly>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<h3>Informações da Consulta</h3>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-2">
			<label for="data_consulta" class="form-label">Data</label>
			<input type="date" class="form-control" id="data_consulta" name="data_consulta">
		</div>
		<div class="col-2">
				<label for="hora_cosulta" class="form-label">Horário</label> 
				<select class="form-select" id="hora_consulta" name="hora_consulta">
<c:forTokens items="08 09 10 11 14 15 16 17" delims=" " var="hora">
					<option value="${hora}:00:00">${hora}:00 ~ ${hora+1}:00</option>
</c:forTokens>
				</select>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<button type="submit" class="btn btn-primary">Agendar</button>
		</div>
	</div>
	
	<input type="hidden" class="form-control" id="num_consulta" name="num_consulta">
	<input type="hidden" class="form-control" id="cpf_profissional" name="cpf_profissional" value=<%=prf.getCpf()%>>
	<input type="hidden" class="form-control" id="cpf_cliente" name="cpf_cliente"  value=<%=clt.getCpf()%>>
	<input type="hidden" class="form-control" id="cancelada" name="cancelada">
</div>