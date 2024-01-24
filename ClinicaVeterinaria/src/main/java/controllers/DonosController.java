
package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.ControleLogado;
import models.Dono;

@WebServlet("/DonosController")
public class DonosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControleLogado log = new ControleLogado();
	boolean autenticado = false;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String opcao = request.getParameter("opcao");

		if (opcao.equals("cadastrar")) {
			if (log.isLogado()) {
				RequestDispatcher rd = request.getRequestDispatcher("cadastrarDono.jsp");
				request.setAttribute("autenticado", log.isLogado());
				rd.forward(request, response);

			} else
				response.sendRedirect("/ClinicaVeterinaria/DonoErro?erro=cadastrar");
		}
		
		if (opcao.equals("Cadastrar")) {
			String nome = request.getParameter("nome");
			int idade = Integer.parseInt(request.getParameter("idade"));
			long cpf = Long.parseLong(request.getParameter("cpf"));
			String endereco = request.getParameter("endereco");
			String telefone = request.getParameter("telefone");


			if (this.create(nome, idade, cpf, endereco, telefone))
				response.sendRedirect("/ClinicaVeterinaria/DonosController?opcao=listDon");
			else
				response.sendRedirect("/ClinicaVeterinaria/DonoErro?erro=cadastrar");
		}
		
		if (opcao.equals("listDon")) {
			RequestDispatcher rd = request.getRequestDispatcher("listaDonos.jsp");
			request.setAttribute("autenticado", log.isLogado());
			request.setAttribute("donos", this.index());
			rd.forward(request, response);
		}
		
		if (opcao.equals("consultar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Dono> donos = index();

			for (Dono dono : donos) {
				if (dono.getId() == (id)) {
					RequestDispatcher rd = request.getRequestDispatcher("dadosDono.jsp");
					request.setAttribute("autenticado", true);
					request.setAttribute("dono", dono);
					rd.forward(request, response);
					break;
				}
			}
		}
		
		if (opcao.equals("editar")) {

			int id = Integer.parseInt(request.getParameter("id"));

			ArrayList<Dono> donos = index();

			boolean encontrado = false;

			for (Dono dono : donos) {
				if (dono.getId() == (id)) {
					request.setAttribute("autenticado", true);
					request.setAttribute("dono", dono);
					encontrado = true;
					break;
				}
			}

			if (encontrado) {
				RequestDispatcher rd = request.getRequestDispatcher("editarDono.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("/ClinicaVeterinaria/DonoErro?erro=editar");
			}

		}
		
		if (opcao.equals("Editar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			String nome = request.getParameter("nome");
			int idade = Integer.parseInt(request.getParameter("idade"));
			long cpf = Long.parseLong(request.getParameter("cpf"));
			String endereco = request.getParameter("endereco");
			String telefone = request.getParameter("telefone");
			
			boolean editado = this.edit(id, nome, idade, cpf, endereco, telefone);
			if (editado)
				response.sendRedirect("/ClinicaVeterinaria/DonosController?opcao=listDon");
			else
				response.sendRedirect("/ClinicaVeterinaria/DonoErro?erro=editar");
		}
		
		if (opcao.equals("deletar")) {
			int id = Integer.parseInt(request.getParameter("id"));

			boolean deletado = this.remove(id);
			if (deletado)
				response.sendRedirect("/ClinicaVeterinaria/DonosController?opcao=listDon");
			else
				response.sendRedirect("/ClinicaVeterinaria/DonoErro?erro=deletar");
		}

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

	}

	public boolean create(String nome, int idade, long cpf, String endereco, String telefone) {
		Dono dono = new Dono(nome, idade, cpf, endereco, telefone);
		return dono.save();
	}

	public boolean edit(int id, String nome, int idade, long cpf, String endereco, String telefone) {
		Dono dono = new Dono(id, nome, idade, cpf, endereco, telefone);
		return dono.update();
	}

	public boolean remove(int id) {
		Dono dono = new Dono(id);
		return dono.delete();
	}

	public ArrayList<Dono> index() {
		Dono dono = new Dono();
		return dono.all();
	}

}
