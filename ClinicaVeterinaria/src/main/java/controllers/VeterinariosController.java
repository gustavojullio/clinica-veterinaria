package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes.ControleLogado;
import models.Veterinario;

@WebServlet("/VeterinariosController")
public class VeterinariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControleLogado log = new ControleLogado();
	boolean autenticado = false;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String opcao = request.getParameter("opcao");

		if (opcao.equals("Logar")) {
			ArrayList<Veterinario> veterinarios = index();
			String usuarioLogin = request.getParameter("usuario");
			String senhaLogin = request.getParameter("senha");
			String nome = "";
			for (Veterinario veterinario : veterinarios) {
				if (veterinario.getUsuario().equals(usuarioLogin) && veterinario.getSenha().equals(senhaLogin)) {
					log.setLogado(true);
					log.setIdLogado(veterinario.getId());
					nome = veterinario.getNome();
					break;
				} else {
					log.setLogado(false);
					log.setIdLogado(0);
				}
			}

			if (log.isLogado()) {
				RequestDispatcher rd = request.getRequestDispatcher("usuarioLogado.jsp");
				request.setAttribute("veterinarios", this.index());
				request.setAttribute("nome", nome);
				request.setAttribute("autenticado", log.isLogado());
				rd.forward(request, response);

			} else {
				response.sendRedirect("/ClinicaVeterinaria/usuarioNaoLogado.jsp");
			}

		}
		
		if (opcao.equals("deslogar")) {
			  log.setLogado(false); 
			  log.setIdLogado(0);
			  response.sendRedirect("index.html"); 
		 }
		
		
		if (opcao.equals("cadastrar")) {
			if (log.isLogado()) {
				RequestDispatcher rd = request.getRequestDispatcher("cadastrarVeterinario.jsp");
				request.setAttribute("autenticado", log.isLogado());
				rd.forward(request, response);
				
			}else
				response.sendRedirect("/ClinicaVeterinaria/VeterinarioErro?erro=cadastrar");
		}
		

		if (opcao.equals("Cadastrar")) {
			String nome = request.getParameter("nome");
			int idade = Integer.parseInt(request.getParameter("idade"));
			String telefone = request.getParameter("telefone");
			String usuario = request.getParameter("usuario");
			String senha = request.getParameter("senha");

			System.out.println(nome);

			if (this.create(nome, idade, telefone, usuario, senha))
				response.sendRedirect("/ClinicaVeterinaria/VeterinariosController?opcao=listVet");
			else
				response.sendRedirect("/ClinicaVeterinaria/VeterinarioErro?erro=cadastrar");
		}

		if (opcao.equals("listVet")) {
			RequestDispatcher rd = request.getRequestDispatcher("listaVeterinarios.jsp");
			request.setAttribute("autenticado", log.isLogado());
			request.setAttribute("veterinarios", this.index());
			rd.forward(request, response);
		}

		if (opcao.equals("consultar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Veterinario> veterinarios = index();

			for (Veterinario veterinario : veterinarios) {
				if (veterinario.getId() == (id)) {
					RequestDispatcher rd = request.getRequestDispatcher("dadosVeterinario.jsp");
					request.setAttribute("autenticado", true);
					request.setAttribute("veterinario", veterinario);
					rd.forward(request, response);
					break;
				}
			}
		}

		if (opcao.equals("editar")) {

			int id = Integer.parseInt(request.getParameter("id"));

			ArrayList<Veterinario> veterinarios = index();

			boolean encontrado = false;

			for (Veterinario veterinario : veterinarios) {
				if (veterinario.getId() == (id)) {
					request.setAttribute("autenticado", true);
					request.setAttribute("veterinario", veterinario);
					encontrado = true;
					break;
				}
			}

			if (encontrado) {
				RequestDispatcher rd = request.getRequestDispatcher("editarVeterinario.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("/ClinicaVeterinaria/VeterinarioErro?erro=editar");
			}

		}
		
		if (opcao.equals("Editar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String nome = request.getParameter("nome");
			int idade = Integer.parseInt(request.getParameter("idade"));
			String telefone = request.getParameter("telefone");
			String usuario = request.getParameter("usuario");
			String senha = request.getParameter("senha");
			
			boolean editado = this.edit(id, nome, idade, telefone, usuario, senha);
			if (editado)
				response.sendRedirect("/ClinicaVeterinaria/VeterinariosController?opcao=listVet");
			else
				response.sendRedirect("/ClinicaVeterinaria/VeterinarioErro?erro=editar");
		}


		if (opcao.equals("deletar")) {
			int id = Integer.parseInt(request.getParameter("id"));

			boolean deletado = this.remove(id);
			if (deletado)
				response.sendRedirect("/ClinicaVeterinaria/VeterinariosController?opcao=listVet");
			else
				response.sendRedirect("/ClinicaVeterinaria/VeterinarioErro?erro=deletar");
		}
		
		if (opcao.equals("retornar")) {
			if (log.isLogado()) {
				RequestDispatcher rd = request.getRequestDispatcher("usuarioLogado.jsp");
				request.setAttribute("autenticado", log.isLogado());
				rd.forward(request, response);
				
			}else
				response.sendRedirect("/ClinicaVeterinaria/usuarioNaoLogado.jsp");
		}
		
		 
	}


	public boolean create(String nome, int idade, String telefone, String usuario, String senha) {
		Veterinario veterinario = new Veterinario(nome, idade, telefone, usuario, senha);
		return veterinario.save();
	}

	public boolean edit(int id, String nome, int idade, String telefone, String usuario, String senha) {
		Veterinario veterinario = new Veterinario(id, nome, idade, telefone, usuario, senha);
		return veterinario.update();
	}

	public boolean remove(int id) {
		Veterinario veterinario = new Veterinario(id);
		return veterinario.delete();
	}

	public ArrayList<Veterinario> index() {
		Veterinario veterinario = new Veterinario();
		return veterinario.all();
	}

}
