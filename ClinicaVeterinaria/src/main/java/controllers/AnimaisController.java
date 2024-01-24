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
import controllers.DonosController;
import controllers.VeterinariosController;
import models.Animal;
import models.Dono;
import models.Veterinario;

@WebServlet("/AnimaisController")
public class AnimaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControleLogado log = new ControleLogado();
	boolean autenticado = false;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String opcao = request.getParameter("opcao");

		if (opcao.equals("cadastrar")) {
			if (log.isLogado()) {
				DonosController donosController = new DonosController();
				VeterinariosController veterinariosController = new VeterinariosController();

				ArrayList<Veterinario> veterinarios = veterinariosController.index();
				ArrayList<Dono> donos = donosController.index();
				request.setAttribute("autenticado", log.isLogado());
				request.setAttribute("veterinarios", veterinarios);
				request.setAttribute("donos", donos);

				RequestDispatcher rd = request.getRequestDispatcher("cadastrarAnimal.jsp");

				rd.forward(request, response);

			} else
				response.sendRedirect("/ClinicaVeterinaria/AnimalErro?erro=cadastrar");
		}

		if (opcao.equals("Cadastrar")) {
			String nome = request.getParameter("nome");
			String raca = request.getParameter("raca");
			float peso = Float.parseFloat(request.getParameter("peso"));
			int idade = Integer.parseInt(request.getParameter("idade"));
			int id_veterinario = Integer.parseInt(request.getParameter("id_veterinario"));
			int id_dono = Integer.parseInt(request.getParameter("id_dono"));

			if (this.create(nome, raca, peso, idade, id_veterinario, id_dono))
				response.sendRedirect("/ClinicaVeterinaria/AnimaisController?opcao=listAni");
			else
				response.sendRedirect("/ClinicaVeterinaria/AnimalErro?erro=cadastrar");
		}

		if (opcao.equals("listAni")) {
			RequestDispatcher rd = request.getRequestDispatcher("listaAnimais.jsp");
			request.setAttribute("autenticado", log.isLogado());
			request.setAttribute("animais", this.index());
			rd.forward(request, response);
		}

		if (opcao.equals("consultar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Animal> animais = index();

			for (Animal animal : animais) {
				if (animal.getId() == (id)) {
					RequestDispatcher rd = request.getRequestDispatcher("dadosAnimal.jsp");
					request.setAttribute("autenticado", true);
					request.setAttribute("animal", animal);
					rd.forward(request, response);
					break;
				}
			}
		}

		if (opcao.equals("editar")) {

			int id = Integer.parseInt(request.getParameter("id"));

			ArrayList<Animal> animais = index();

			boolean encontrado = false;

			for (Animal animal : animais) {
				if (animal.getId() == (id)) {
					if (animal.getId_veterinario() == log.getIdLogado()) {
						request.setAttribute("autenticado", true);
						request.setAttribute("animal", animal);
						encontrado = true;
					} else {
						response.sendRedirect("/ClinicaVeterinaria/AnimalErro?erro=bloqueado");
					}
					break;
				}
			}

			if (encontrado) {
				RequestDispatcher rd = request.getRequestDispatcher("editarAnimal.jsp");
				rd.forward(request, response);
			}
		}

		if (opcao.equals("Editar")) {
			int id = Integer.parseInt(request.getParameter("id"));

			String nome = request.getParameter("nome");
			String raca = request.getParameter("raca");
			float peso = Float.parseFloat(request.getParameter("peso"));
			int idade = Integer.parseInt(request.getParameter("idade"));
			int id_veterinario = Integer.parseInt(request.getParameter("id_veterinario"));
			int id_dono = Integer.parseInt(request.getParameter("id_dono"));
			String nomeVeterinario = request.getParameter("nomeVeterinario");
			String nomeDono = request.getParameter("nomeDono");

			boolean editado = this.edit(id, nome, raca, peso, idade, id_veterinario, id_dono, nomeVeterinario,
					nomeDono);
			if (editado)
				response.sendRedirect("/ClinicaVeterinaria/AnimaisController?opcao=listAni");
			else
				response.sendRedirect("/ClinicaVeterinaria/AnimalErro?erro=editar");
		}

		if (opcao.equals("deletar")) {
			int id = Integer.parseInt(request.getParameter("id"));

			ArrayList<Animal> animais = index();

			for (Animal animal : animais) {
				if (animal.getId() == (id)) {
					if (animal.getId_veterinario() == log.getIdLogado()) {
						boolean deletado = this.remove(id);
						if (deletado)
							response.sendRedirect("/ClinicaVeterinaria/AnimaisController?opcao=listAni");
						else
							response.sendRedirect("/ClinicaVeterinaria/AnimalErro?erro=deletar");
					} else {
						response.sendRedirect("/ClinicaVeterinaria/AnimalErro?erro=bloqueado");
					}
					break;
				}
			}
		}
		
		if (opcao.equals("filtro")) {
			int idVet = Integer.parseInt(request.getParameter("id"));

			RequestDispatcher rd = request.getRequestDispatcher("listaAnimais.jsp");
			request.setAttribute("autenticado", log.isLogado());
			request.setAttribute("animais", this.indexFiltro(idVet));
			rd.forward(request, response);
		}
		
		if (opcao.equals("filtroDono")) {
			int idDono = Integer.parseInt(request.getParameter("id"));

			RequestDispatcher rd = request.getRequestDispatcher("listaAnimais.jsp");
			request.setAttribute("autenticado", log.isLogado());
			request.setAttribute("animais", this.indexFiltroDono(idDono));
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String opcao = request.getParameter("opcao");

	}

	public boolean create(String nome, String raca, float peso, int idade, int id_veterinario, int id_dono) {
		Animal animal = new Animal(nome, raca, peso, idade, id_veterinario, id_dono);
		return animal.save();
	}

	public boolean edit(int id, String nome, String raca, float peso, int idade, int id_veterinario, int id_dono,
			String nomeVeterinario, String nomeDono) {
		Animal animal = new Animal(id, nome, raca, peso, idade, id_veterinario, id_dono, nomeVeterinario, nomeDono);
		return animal.update();
	}

	public boolean remove(int id) {
		Animal animal = new Animal(id);
		return animal.delete();
	}

	public ArrayList<Animal> index() {
		Animal animal = new Animal();
		return animal.all();
	}
	
	public ArrayList<Animal> indexFiltro(int idVet) {
		Animal animal = new Animal();
		return animal.allFiltro(idVet);
	}
	
	public ArrayList<Animal> indexFiltroDono(int idDono) {
		Animal animal = new Animal();
		return animal.allFiltroDono(idDono);
	}

}
