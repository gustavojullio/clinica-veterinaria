
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.DAO;

public class Animal {
	int id;
	String nome;
	String raca;
	float peso;
	int idade;
	int id_veterinario;
	int id_dono;
	String nomeVeterinario;
	String nomeDono;


	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	DAO banco = new DAO();

	public Animal() {}

	public Animal(int id, String nome, String raca, float peso, int idade, int id_veterinario, int id_dono, String nomeVeterinario,String nomeDono) {
		this.id = id;
		this.nome = nome;
		this.raca = raca;
		this.peso = peso;
		this.idade = idade;
		this.id_veterinario = id_veterinario;
		this.id_dono = id_dono;
		this.nomeVeterinario = nomeVeterinario;
		this.nomeDono = nomeDono;
	}

	public Animal(String nome, String raca, float peso, int idade, int id_veterinario, int id_dono, String nomeVeterinario,String nomeDono) {
		this.nome = nome;
		this.raca = raca;
		this.peso = peso;
		this.idade = idade;
		this.id_veterinario = id_veterinario;
		this.id_dono = id_dono;
		this.nomeVeterinario = nomeVeterinario;
		this.nomeDono = nomeDono;
	}
	
	public Animal(String nome, String raca, float peso, int idade, int id_veterinario, int id_dono) {
		this.id = id;
		this.nome = nome;
		this.raca = raca;
		this.peso = peso;
		this.idade = idade;
		this.id_veterinario = id_veterinario;
		this.id_dono = id_dono;
	}
	
	public Animal(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	public int getId_veterinario() {
		return id_veterinario;
	}

	public void setId_veterinario(int id_veterinario) {
		this.id_veterinario = id_veterinario;
	}

	public int getId_dono() {
		return id_dono;
	}

	public void setId_dono(int id_dono) {
		this.id_dono = id_dono;
	}
	
	public String getNomeVeterinario() {
		return nomeVeterinario;
	}

	public void setNomeVeterinario(String nomeVeterinario) {
		this.nomeVeterinario = nomeVeterinario;
	}

	public String getNomeDono() {
		return nomeDono;
	}

	public void setNomeDono(String nomeDono) {
		this.nomeDono = nomeDono;
	}
	
	public ArrayList<Animal> all() {
		String sql = "select ANIMAL.*, VETERINARIO.nome nome_veterinario , DONO.nome nome_dono\r\n"
				+ "from animal ANIMAL\r\n"
				+ "inner join dono DONO on ANIMAL.id_dono = DONO.id\r\n"
				+ "inner join veterinario VETERINARIO on ANIMAL.id_veterinario = VETERINARIO.id";
		this.conexao = banco.conectar();
		ArrayList<Animal> animais = new ArrayList<>();
		
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String raca = rs.getString("raca");
				float peso = rs.getFloat("peso");
				int idade = rs.getInt("idade");
				int id_veterinario = rs.getInt("id_veterinario");
				int id_dono = rs.getInt("id_dono");
				String nomeVeterinario = rs.getString("nome_veterinario");
	            String nomeDono = rs.getString("nome_dono");
			
				Animal a = new Animal(id,nome,raca,peso,idade,id_veterinario,id_dono, nomeVeterinario, nomeDono);
				animais.add(a);
			}
		} 
		catch (SQLException e) {
			System.out.println("Não foi possível retornar os animais: " + e);
		}
		finally {
			this.conexao = banco.desconectar(conexao);
			return animais;
		}
		
	}
	public boolean save() {
		String sql = "insert into animal(nome, raca, peso, idade, id_veterinario, id_dono) value(?,?,?,?,?,?)";
		this.conexao = banco.conectar();
		boolean salvo = false;
		
		try {
			pst = this.conexao.prepareStatement(sql);
			pst.setString(1, this.nome);
			pst.setString(2, this.raca);
			pst.setFloat(3, this.peso);
			pst.setInt(4, this.idade);
			pst.setInt(5, this.id_veterinario);
			pst.setInt(6, this.id_dono);
			pst.executeUpdate();
			System.out.println("Animal cadastrado com sucesso!");
			salvo = true;
		}
		catch(SQLException e) {
			System.out.println("Não foi possível cadastrar o Animal: " + e);
		}
		finally {
			this.conexao = banco.desconectar(conexao);
			return salvo;
		}
	}
	
	public boolean update() {
		String sql = "update animal set nome=?, raca=?, peso=?, idade=? where id=?";
		this.conexao = banco.conectar();
		boolean editado = false;
		
		try {
			pst = this.conexao.prepareStatement(sql);
			pst.setString(1, this.nome);
			pst.setString(2, this.raca);
			pst.setFloat(3, this.peso);
			pst.setInt(4, this.idade);
			pst.setInt(5, this.id);
			pst.executeUpdate();
			System.out.println("Animal editado com sucesso!");
			editado = true;
		}
		catch(SQLException e) {
			System.out.println("Não foi possível editar o animal: " + e);
		}
		finally {
			this.conexao = banco.desconectar(conexao);
			return editado;
		}
	}
	public boolean delete() {
		String sql = "delete from animal where id =?";
		this.conexao = banco.conectar();
		boolean deletado = false;
		
		try {
			pst = this.conexao.prepareStatement(sql);
			pst.setInt(1, this.id);
			pst.executeUpdate();
			System.out.println("Animal excluído com sucesso!");
			deletado = true;
		}
		catch(SQLException e) {
			System.out.println("Não foi possível excluir o animal: " + e);
		}
		finally {
			this.conexao = banco.desconectar(conexao);
			return deletado;
		}
	}
	
	public ArrayList<Animal> allFiltro(int idVet) {
		String sql = "select ANIMAL.*, VETERINARIO.nome nome_veterinario , DONO.nome nome_dono\r\n"
				+ "from animal ANIMAL\r\n"
				+ "inner join dono DONO on ANIMAL.id_dono = DONO.id\r\n"
				+ "inner join veterinario VETERINARIO on ANIMAL.id_veterinario = VETERINARIO.id\r\n"
				+ "where VETERINARIO.id = ?";
		this.conexao = banco.conectar();
		ArrayList<Animal> animais = new ArrayList<>();
		
		try {
			pst = conexao.prepareStatement(sql);
			pst.setInt(1, idVet);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String raca = rs.getString("raca");
				float peso = rs.getFloat("peso");
				int idade = rs.getInt("idade");
				int id_veterinario = rs.getInt("id_veterinario");
				int id_dono = rs.getInt("id_dono");
				String nomeVeterinario = rs.getString("nome_veterinario");
	            String nomeDono = rs.getString("nome_dono");
			
				Animal a = new Animal(id,nome,raca,peso,idade,id_veterinario,id_dono, nomeVeterinario, nomeDono);
				animais.add(a);
			}
		} 
		catch (SQLException e) {
			System.out.println("Não foi possível retornar os animais: " + e);
		}
		finally {
			this.conexao = banco.desconectar(conexao);
			return animais;
		}
		
	}
	
	public ArrayList<Animal> allFiltroDono(int idDono) {
		String sql = "select ANIMAL.*, VETERINARIO.nome nome_veterinario , DONO.nome nome_dono\r\n"
				+ "from animal ANIMAL\r\n"
				+ "inner join dono DONO on ANIMAL.id_dono = DONO.id\r\n"
				+ "inner join veterinario VETERINARIO on ANIMAL.id_veterinario = VETERINARIO.id\r\n"
				+ "where DONO.id = ?";
		this.conexao = banco.conectar();
		ArrayList<Animal> animais = new ArrayList<>();
		
		try {
			pst = conexao.prepareStatement(sql);
			pst.setInt(1, idDono);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String raca = rs.getString("raca");
				float peso = rs.getFloat("peso");
				int idade = rs.getInt("idade");
				int id_veterinario = rs.getInt("id_veterinario");
				int id_dono = rs.getInt("id_dono");
				String nomeVeterinario = rs.getString("nome_veterinario");
	            String nomeDono = rs.getString("nome_dono");
			
				Animal a = new Animal(id,nome,raca,peso,idade,id_veterinario,id_dono, nomeVeterinario, nomeDono);
				animais.add(a);
			}
		} 
		catch (SQLException e) {
			System.out.println("Não foi possível retornar os animais: " + e);
		}
		finally {
			this.conexao = banco.desconectar(conexao);
			return animais;
		}
		
	}
	

}
