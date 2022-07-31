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
			<div class="col-4">
				<div class="mb-3">
					<label for="email" class="form-label">Email</label>
					<input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" required value="${profissional.email}" ${flagReadonly}>
					<!-- <div id="emailHelp" class="form-text text-danger">Erro! Email já cadastrado</div>  -->
				</div>
			</div>
			<div class="col-4">
				<div class="mb-3">
					<label for="inputPassword1" class="form-label">Senha</label>
					<input type="password" class="form-control" id="inputPassword1" name="senha" ${flagReadonly} value="${profissional.senha}">
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
					<!-- <div id="cpfHelp" class="form-text text-danger">CPF inválido, redigite</div> -->
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
					<select class="form-select" aria-label="Selecione" required id="areaConhecimento" name="areaConhecimento" onclick='mudaEspecialidade(this.value)'>
						<option  selected disabled >Selecione</option>
						<option value="Medicina" ${profissional.areaConhecimento == "Medicina" ? 'selected':''}>Medicina</option>
						<option value="Advocacia" ${profissional.areaConhecimento == "Advocacia" ? 'selected':''}>Advocacia</option>
						<option value="Psicologia" ${profissional.areaConhecimento == "Psicologia" ? 'selected':''}>Psicologia</option>
					</select>
			</div>
			<div class="col-3 mb-3">
					<label for="especialidade" class="form-label">Especialidade</label>
					<input type="text" class="form-control" id="especialidade" name="especialidade" required value="${profissional.especialidade}" readonly>
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
<script>
	function mudaEspecialidade(val){
		if ( val == 'Medicina'){
			document.getElementById('especialidade').value = 'Cardiologista';
		}else if (val == 'Advocacia'){
			document.getElementById('especialidade').value = 'Advogado Criminal';
		}else if ( val == 'Psicologia'){
			document.getElementById('especialidade').value = 'Psicologo Infantil';
		}
	}

</script>
