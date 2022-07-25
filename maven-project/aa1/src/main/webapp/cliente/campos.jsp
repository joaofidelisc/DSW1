<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row">
		<div class="col">
			<c:choose>
				<c:when test="${cliente != null}"><!-- Se existir cliente, vamos alterá-lo -->
					<h1>Edição de Clientes</h1>
				</c:when>
				<c:otherwise>
					<h1>Cadastro de Clientes</h1>
				</c:otherwise>
			</c:choose>
			
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col">
			<h3>Informações de acesso</h3>
		</div>
	</div>


	<div class="row mb-3">
		<div class="col">
			<label for="email" class="form-label">Email</label>
			<input type="email" class="form-control" id="email" name="email"	aria-describedby="emailHelp" required value="${cliente.email}" ${flagReadonly}>
			<div id="emailHelp" class="form-text text-danger">Erro! Email já cadastrado</div>
		</div>
		<div class="col">
			<label for="inputPassword1" class="form-label">Senha</label> 
			<input type="password" class="form-control" name="senha" id="inputPassword1" ${flagReadonly} value="${cliente.senha}">
		</div>
		<div class="col">
			<label for="inputPassword2" class="form-label">Confirmação de senha</label>
			<input type="password" class="form-control" id="inputPassword2" aria-describedby="passHelp2" ${flagReadonly} value="${cliente.senha}">
			<div id="passHelp2" class="form-text text-danger">As senhas não coincidem</div>
		</div>
	</div>

	<br>
	<!--INFORMAÃÃES PESSOAIS-->
	<div class="row mb-3">
		<div class="col">
			<h3>Informações pessoais</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-2">
			<label for="cpf" class="form-label">CPF</label>
			<input type="text" class="form-control" id="cpf" name="cpf" aria-describedby="cpfHelp" required value="${cliente.cpf}" ${flagReadonly}>
			<div id="cpfHelp" class="form-text text-danger">CPF inválido, redigite</div>
		</div>
		<div class="col-5">
			<label for="nome" class="form-label">Nome</label> 
			<input type="text" class="form-control" id="nome" name="nome" aria-describedby="nomeHelp" required value="${cliente.nome}">
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-2">
			<label for="telefone" class="form-label">Telefone</label>
			<input type="text" class="form-control" id="telefone" name="telefone" aria-describedby="telefoneHelp" required value="${cliente.telefone}">
			<div id="telefoneHelp" class="form-text text-danger">Formato de telefone incorreto</div>
		</div>
		<div class="col-3">
			<label for="sexo" class="form-label">Sexo</label> 
			<select class="form-select" aria-label="Selecione" name="sexo">
				<option value="F" ${cliente.sexo == "F" ? 'selected':''}>Feminino</option>
				<option value="M" ${cliente.sexo == "M" ? 'selected':''}>Masculino</option>
				<option value="N" ${cliente.sexo == "N" ? 'selected':''}>Prefiro não responder</option>
			</select>
		</div>
		<div class="col-2">
			<label for="data_nasc" class="form-label">Data de Nascimento</label>
			<input type="date" class="form-control" id="data_nascimento" name="data_nascimento" aria-describedby="data_nascHelp" required value="${cliente.data_nascimento}">
		</div>
	</div>
	<div class="row">
		<div class="col">
			<button type="submit" class="btn btn-primary">Salvar</button>
			<button type="reset" class="btn btn-secondary">Limpar</button>
		</div>
	</div>
</div>