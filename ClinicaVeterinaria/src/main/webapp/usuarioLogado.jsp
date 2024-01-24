<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List, models.Veterinario"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Boas-Vindas Veterinário</title>
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
				<h3>Bem-vindo!</h3>
				<p>O que deseja fazer?</p>
				<br>
				<div class="row">
					<div class="col-md-6">
						<div class="welcome-box">
							<a
								href="/ClinicaVeterinaria/VeterinariosController?opcao=cadastrar"><i
								class="bi bi-file-earmark-plus"></i> Cadastrar Veterinário</a>
						</div>
					</div>
					<div class="col-md-6">
						<div class="welcome-box">
							<a
								href="/ClinicaVeterinaria/VeterinariosController?opcao=listVet"><i
								class="bi bi-list"></i>Listar Veterinários</a>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="welcome-box">
							<a href="/ClinicaVeterinaria/AnimaisController?opcao=cadastrar"><i
								class="bi bi-file-earmark-plus"></i> Cadastrar Animais</a>
						</div>
					</div>
					<div class="col-md-6">
						<div class="welcome-box">
							<a href="/ClinicaVeterinaria/AnimaisController?opcao=listAni"><i
								class="bi bi-list"></i> Listar Animais</a>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="welcome-box">
							<a href="/ClinicaVeterinaria/DonosController?opcao=cadastrar"><i
								class="bi bi-file-earmark-plus"></i> Cadastrar Donos</a>
						</div>
					</div>
					<div class="col-md-6">
						<div class="welcome-box">
							<a href="/ClinicaVeterinaria/DonosController?opcao=listDon"><i
								class="bi bi-list"></i> Listar Donos</a>
						</div>
					</div>
				</div>
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
