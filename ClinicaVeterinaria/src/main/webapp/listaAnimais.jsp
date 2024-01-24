<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List, models.Animal" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Lista - Animais</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">	<link rel='stylesheet' href='css/style.css'/>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>    <link rel='stylesheet' href='css/style.css'/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Prompt:ital,wght@0,200;0,300;0,400;1,100&display=swap" rel="stylesheet">
</head>

<body>
	 <c:choose>
        <c:when test="${autenticado}">
            <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-menu">
            <div class="container-fluid">
                <a class="navbar-brand nav-color" href="index.html"><img src="img/logo.png" class="img-fluid" alt="Logo" width="50"
                        height="40"> Clínica Veterinária</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link nav-color" href="index.html"><i class="bi bi-house"></i> Home</a>
                        </li>
                                                <li class="nav-item">
                            <a class="nav-link nav-color" href="/ClinicaVeterinaria/VeterinariosController?opcao=deslogar"><i class="bi bi-box-arrow-left"></i> Deslogar</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <div class="container">
       <h2>Animais Cadastrados:</h2>
        <div class="container mt-3">
		    <div class="row thead">
		        <div class="col-7 col-lg-6">
		            <p class="text-center">Nome</p>
		        </div>
		        <div class="col-5 col-lg-3">
		            <p class="text-center">Operações</p>
		        </div>
		    </div>
    
	    <c:forEach items="${animais}" var="animal">
	        <div class="row border rounded-1 my-2">
	            <div class="col-7 col-lg-6">
	                <p>${animal.nome}</p>
	            </div>
	            <div class="col-5 col-lg-3">
	                <p class="text-center">
	                    <a href="/ClinicaVeterinaria/AnimaisController?opcao=consultar&id=${animal.id}"><i class="bi bi-eye-fill operation"></i></a>
	                    <a href="/ClinicaVeterinaria/AnimaisController?opcao=editar&id=${animal.id}"><i class="bi bi-pencil-square operation"></i></a>
	                    <a href="/ClinicaVeterinaria/AnimaisController?opcao=deletar&id=${animal.id}"><i class="bi bi-trash3-fill operation"></i></a>
	                </p>
	            </div>
	        </div>
	    </c:forEach>
	    <br>
	    <p style="text-align: center"><button type="button" class="btn btn-menu"><a href="/ClinicaVeterinaria/VeterinariosController?opcao=retornar">Menu Inicial</i></a></button></p>
</div>
            
        </c:when>
        <c:otherwise>
        	<header>
		      <nav class="navbar navbar-expand-lg navbar-light bg-menu">
		        <div class="container-fluid">
		          <a class="navbar-brand nav-color" href="index.html"><img src="img/logo.png" class="img-fluid" alt="Logo" width="50"
		              height="40"> Clínica Veterinária</a>
		          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
		            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		            <span class="navbar-toggler-icon"></span>
		          </button>
		          <div class="collapse navbar-collapse" id="navbarSupportedContent">
		            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		              <li class="nav-item">
		                <a class="nav-link nav-color" href="index.html"><i class="bi bi-house"></i> Home</a>
		              </li>
		              <li class="nav-item">
		                <a class="nav-link nav-color" href="/ClinicaVeterinaria/loginVeterinario.jsp"><i class="bi bi-person-circle"></i> Login</a>
		              </li>
		            </ul>
		          </div>
		        </div>
		      </nav>
		    </header>
            <div class="container text-center mt-5">
			    <h3 class="text-danger">Acesso não autorizado! Espero que não esteja tentando hackear a página!</h3>
			    <p class="lead">Você não está autenticado. Por favor, faça login para continuar.</p>
			    <a href="/ClinicaVeterinaria/loginVeterinario.jsp" class="btn btn-primary btn-lg">Fazer Login</a>
			</div>
        </c:otherwise>
    </c:choose>
    
</body>

</html>
