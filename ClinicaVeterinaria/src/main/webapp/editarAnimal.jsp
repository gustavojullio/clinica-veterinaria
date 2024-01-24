<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, models.Veterinario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Editar - Animal</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel='stylesheet' href='css/style.css' />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<link rel='stylesheet' href='css/style.css' />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Prompt:ital,wght@0,200;0,300;0,400;1,100&display=swap"
	rel="stylesheet">
</head>

<body>
	<c:choose>
		<c:when test="${autenticado}">
			<header>
				<nav class="navbar navbar-expand-lg navbar-light bg-menu">
					<div class="container-fluid">
						<a class="navbar-brand nav-color" href="index.html"><img
							src="img/logo.png" class="img-fluid" alt="Logo" width="50"
							height="40"> Clínica Veterinária</a>
						<button class="navbar-toggler" type="button"
							data-bs-toggle="collapse"
							data-bs-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav me-auto mb-2 mb-lg-0">
								<li class="nav-item"><a class="nav-link nav-color"
									href="index.html"><i class="bi bi-house"></i> Home</a></li>
								<li class="nav-item"><a class="nav-link nav-color"
									href="/ClinicaVeterinaria/VeterinariosController?opcao=deslogar"><i
										class="bi bi-box-arrow-left"></i> Deslogar</a></li>
							</ul>
						</div>
					</div>
				</nav>
			</header>
			<div class="container">
				<h2>Editar Animal</h2>
				<form class="row g-3" method="post"
					action="/ClinicaVeterinaria/AnimaisController">
					<input type="hidden" name="id" value="${animal.id}" />

					<div class="col-12">
						<label for="nome" class="form-label">Nome</label> <input
							type="text" class="form-control" name="nome" size="60"
							value="${animal.nome}" required />
					</div>

					<div class="col-4">
						<label for="raca" class="form-label">Raça</label> <input
							type="text" class="form-control" name="raca"
							value="${animal.raca}" size="60" required />
					</div>


					<div class="col-4">
						<label for="peso" class="form-label">Peso</label> <input
							type="number" class="form-control" name="peso"
							value="${animal.peso}" size="60" required />
					</div>


					<div class="col-4">
						<label for="idade" class="form-label">Idade</label> <input
							type="number" class="form-control" name="idade"
							value="${animal.idade}" size="30" required />
					</div>

					<input type="hidden" name="id_veterinario"
						value="${animal.id_veterinario}" /> <input type="hidden"
						name="id_dono" value="${animal.id_dono}" />

					<div class="col-6">
						<label for="nomeVeterinario" class="form-label">Veterinário</label>
						<input type="text" class="form-control" name="raca"
							value="${animal.nomeVeterinario}" size="60" required readonly />
					</div>

					<div class="col-6">
						<label for="nomeDono" class="form-label">Dono</label> <input
							type="text" class="form-control" name="raca"
							value="${animal.nomeDono}" size="60" required readonly />
					</div>


					<div class="col-12">
						<a href="/ClinicaVeterinaria/AnimaisController?opcao=listAni"><button
								type="button" class="btn btn-danger">Voltar</button></a> </a> <input
							type="submit" name="opcao" value="Editar" class="btn btn-primary" />
					</div>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<header>
				<nav class="navbar navbar-expand-lg navbar-light bg-menu">
					<div class="container-fluid">
						<a class="navbar-brand nav-color" href="index.html"><img
							src="img/logo.png" class="img-fluid" alt="Logo" width="50"
							height="40"> Clínica Veterinária</a>
						<button class="navbar-toggler" type="button"
							data-bs-toggle="collapse"
							data-bs-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav me-auto mb-2 mb-lg-0">
								<li class="nav-item"><a class="nav-link nav-color"
									href="index.html"><i class="bi bi-house"></i> Home</a></li>
								<li class="nav-item"><a class="nav-link nav-color"
									href="/ClinicaVeterinaria/loginVeterinario.jsp"><i
										class="bi bi-person-circle"></i> Login</a></li>
							</ul>
						</div>
					</div>
				</nav>
			</header>
			<div class="container text-center mt-5">
				<h3 class="text-danger">Acesso não autorizado! Espero que não
					esteja tentando hackear a página!</h3>
				<p class="lead">Você não está autenticado. Por favor, faça login
					para continuar.</p>
				<a href="/ClinicaVeterinaria/loginVeterinario.jsp"
					class="btn btn-primary btn-lg">Fazer Login</a>
			</div>
		</c:otherwise>
	</c:choose>

</body>

</html>
