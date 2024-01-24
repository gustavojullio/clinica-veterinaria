<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Login - Veterinário</title>
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

<body class="login-body">
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-menu">
			<div class="container-fluid">
				<a class="navbar-brand nav-color" href="index.html"><img
					src="img/logo.png" class="img-fluid" alt="Logo" width="50"
					height="40"> Clínica Veterinária</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link nav-color"
							href="index.html"><i class="bi bi-house"></i> Home</a></li>
						<li class="nav-item"><a class="nav-link nav-color"
							href="loginVeterinario.jsp"><i class="bi bi-person-circle"></i>
								Login</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<div class="container">
		<div class="login-container">
			<i class="bi bi-person-circle login-icon"></i>
			<h2>Bora fazer login veterinário?</h2>
			<form method="post"
				action="/ClinicaVeterinaria/VeterinariosController">
				<div class="mb-3">
					<label for="usuario" class="form-label">Usuário</label> <input
						type="text" class="form-control" name="usuario" required>
				</div>
				<div class="mb-3">
					<label for="senha" class="form-label">Senha</label> <input
						type="password" class="form-control" name="senha" required>
				</div>
				<input type="submit" name="opcao" value="Logar"
					class="btn btn-primary btn-login" /> <a href="index.html"
					class="btn btn-secondary btn-login">Cancelar</a>
			</form>
		</div>
	</div>
</body>

</html>
