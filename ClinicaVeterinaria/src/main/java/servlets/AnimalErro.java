package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.ControleLogado;


@WebServlet("/AnimalErro")
public class AnimalErro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String erro = request.getParameter("erro");
		
		if(erro.equals("cadastrar")) {
			  response.sendRedirect("/ClinicaVeterinaria/erroCadastrarAnimal.jsp");
		}
		
		if(erro.equals("deletar")) {
			  response.sendRedirect("/ClinicaVeterinaria/erroDeletarAnimal.jsp");
		}
		
		if(erro.equals("editar")) {
			  response.sendRedirect("/ClinicaVeterinaria/erroEditarAnimal.jsp");
		}
		if(erro.equals("bloqueado")) {
			  response.sendRedirect("/ClinicaVeterinaria/erroBloqueadoAnimal.jsp");
		}
		
	}

}
