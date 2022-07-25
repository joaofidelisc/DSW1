<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row">
		<div class="col">
			<h1>Cadastro de Profissionais</h1>
		</div>
	</div>
	<br>
	<!--INFORMAÇÕES DE ACESSO-->
	<div class="row">
		<div class="col">
			<h3>Informações de acesso</h3>
		</div>
	</div>

		<div class="row">
			<div class="col">
				<div class="mb-3">
					<label for="email" class="form-label">Email</label>
					<input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" required value="${profissional.email}" ${flagReadonly}>
					<div id="emailHelp" class="form-text text-danger">Erro! Email
						já cadastrado</div>
				</div>
			</div>
			<div class="col">
				<div class="mb-3">
					<label for="inputPassword1" class="form-label">Senha</label>
					<input type="password" class="form-control" id="inputPassword1" name="senha" ${flagReadonly} value="${profissional.senha}">
				</div>
			</div>
			<div class="col">
				<div class="mb-3">
					<label for="inputPassword2" class="form-label">Confirmação de senha</label>
					<input type="password" class="form-control" id="inputPassword2" aria-describedby="passHelp2" ${flagReadonly} value="${profissional.senha}">
					<div id="passHelp2" class="form-text text-danger">As senhas não coincidem</div>
				</div>
			</div>
			<div class="col"></div>
		</div>
		<div class="row">
			<div class="col"></div>
		</div>

		<br>
		<!--INFORMAÇÕES PESSOAIS-->
		<div class="row">
			<div class="col">
				<h3>Informações pessoais</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
				<div class="mb-3">
					<label for="cpf" class="form-label">CPF</label> 
					<input type="text" class="form-control" id="cpf" name="cpf" aria-describedby="cpfHelp" required value="${profissional.cpf}" ${flagReadonly}>
					<div id="cpfHelp" class="form-text text-danger">CPF inválido,
						redigite</div>
				</div>
			</div>
			<div class="col-5">
				<div class="mb-3">
					<label for="nome" class="form-label">Nome</label>
					<input type="text" class="form-control" id="nome" name="nome" aria-describedby="nomeHelp" required value="${profissional.nome}">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
					<label for="areaConhecimento" class="form-label">Área de conhecimento</label> 
					<select class="form-select" aria-label="Selecione" id="areaConhecimento" name="areaConhecimento">
						<option value="Medicina" ${profissional.areaConhecimento == "Medicina" ? 'selected':''}>Medicina</option>
						<option value="Advocacia" ${profissional.areaConhecimento == "Advocacia" ? 'selected':''}>Advocacia</option>
						<option value="Psicologia" ${profissional.areaConhecimento == "Psicologia" ? 'selected':''}>Psicologia</option>
					</select>
			</div>
			<div class="col-3 mb-3">
					<label for="especialidade" class="form-label">Especialidade</label>
					<select class="form-select" aria-label="Selecione" id="especialidade" name="especialidade">
						<option value="Cardiologista" ${profissional.especialidade == "Cardiologista" ? 'selected':''}>Cardiologista</option>
						<option value="Advogado Criminal" ${profissional.especialidade == "Advogado Criminal" ? 'selected':''}>Advogado Criminal</option>
						<option value="Psicologo Infantil" ${profissional.especialidade == "Psicologo Infantil" ? 'selected':''}>Psicólogo Infantil</option>
					</select>
			</div>

		</div>
		<div class="row mb-3">
			<div class="col-5">
				  <label for="formFile" class="form-label">Qualificações profissionais</label>
				  <input type="text" class="form-control" type="file" id="local_pdf" name="local_pdf" value="${profissional.local_pdf}">
			</div>
		
		</div>
		
		
		
		<div class="row">
			<div class="col">
				<button type="submit" class="btn btn-primary">Salvar</button>
				<button type="reset" class="btn btn-secondary">Limpar</button>
			</div>
		</div>
</div>