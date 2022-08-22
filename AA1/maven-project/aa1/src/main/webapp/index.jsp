<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:choose >
	<c:when test="${usuarioLogado.email != null}">
	<%@include file="/home.jsp"%>
	</c:when>
	<c:otherwise>
		<%@include file="/navbar.jsp"%>
		<form method="post" action="login.jsp">
		<section class="vh-100">
			<div class="container-fluid h-custom">
				<div class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-md-9 col-lg-6 col-xl-5">
						<img src="./front-end-src/assets/trabalhadores.svg"
							class="img-fluid" alt="workers"
							style="-webkit-mask-image: linear-gradient(to top, transparent 1%, black 100%);">
					</div>
					
					<div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
						<h2 class="display-5">Bem-vinde a Clínica</h2>
						<p><strong>Todos serviços em um só lugar!</strong></p>
		
						<h4>Login</h4>
		
						<!-- Email input -->
						<div class="form-outline mb-4">
							<label class="form-label" for="form3Example3">Email</label> <input
								type="email" id="email" name="email"
								class="form-control form-control-lg"
								placeholder="Digite seu endereço de email" />
						</div>
		
						<!-- Password input -->
						<div class="form-outline mb-3">
							<label class="form-label" for="form3Example4">Senha</label> <input
								type="password" id="senha" name="senha"
								class="form-control form-control-lg"
								placeholder="Digite sua senha" />
						</div>
		
		
						<div class="text-center text-lg-start mt-4 pt-2 mb-3">
							<button type="submit" class="btn btn-primary btn-lg"
								value="btnLogin" name="btnLogin"
								style="padding-left: 2.5rem; padding-right: 2.5rem;">Entrar</button>
						</div>
					</div>
				</div>
			</div>
		</section>
		</form>
	</c:otherwise>
</c:choose>
	
<%@include file="/footer.jsp"%>