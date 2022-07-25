<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container">
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
			<label for="email_cliente" class="form-label">Email</label> 
			<input type="text" class="form-control" id="email_cliente" readonly>
		</div>
		<div class="col-5">
			<label for="nome_cliente" class="form-label">Nome</label> 
			<input class="form-control" id="nome_cliente" name="nome_cliente" readonly>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col">
			<h3>Informações do Profissional</h3>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-3">
			<label for="email_profissional" class="form-label">Email</label>
			<input type="email" class="form-control" id="email_profissional" readonly> 
		</div>
			<div class="col-5">
			<label for="nome_profissional" class="form-label">Nome</label> 
			<input class="form-control" id="nome_profissional" name="nome_profissional" readonly>
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
				<input class="form-control" class="horario_fim" id="hora_cosulta" name="hora_consulta" placeholder="00:00">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<button type="submit" class="btn btn-primary">Agendar</button>
		</div>
	</div>
	
	<input type="hidden" class="form-control" id="num_consulta" name="num_consulta">
	<input type="hidden" class="form-control" id="cpf_profissional" name="cpf_profissional">
	<input type="hidden" class="form-control" id="cpf_cliente" name="cpf_cliente">
	<input type="hidden" class="form-control" id="cancelada" name="cancelada">
</div>