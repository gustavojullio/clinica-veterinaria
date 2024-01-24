package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.DAO;

public class Veterinario {
	int id;
	String nome;
	int idade;
	String telefone;
	String usuario;
	String senha;
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	DAO banco = new DAO();

	public Veterinario() {}
	
	public Veterinario(int id, String nome, int idade, String telefone, String usuario, String senha) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.telefone = telefone;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public Veterinario(String nome, int idade, String telefone, String usuario, String senha) {
		this.nome = nome;
		this.idade = idade;
		this.telefone = telefone;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public Veterinario(int id) {
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
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public ArrayList<Veterinario> all() {
		String sql = "select * from veterinario";
		this.conexao = banco.conectar();
		ArrayList<Veterinario> veterinarios = new ArrayList<>();
		
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				int idade = rs.getInt(3);
				String telefone = rs.getString(4);
				String usuario = rs.getString(5);
				String senha = rs.getString(6);
			
				Veterinario p = new Veterinario(id,nome,idade,telefone, usuario, senha);
				veterinarios.add(p);
			}
		} 
		catch (SQLException e) {
			System.out.println("Não foi possível retornar os veterinários: " + e);
		}
		finally {
			this.conexao = banco.desconectar(conexao);
			return veterinarios;
		}
		
	}
	
	public boolean save() {
		String sql = "insert into veterinario(nome, idade, telefone, usuario, senha) value(?,?,?,?,?)";
		this.conexao = banco.conectar();
		boolean salvo = false;
		
		try {
			pst = this.conexao.prepareStatement(sql);
			pst.setString(1, this.nome);
			pst.setInt(2, this.idade);
			pst.setString(3, this.telefone);
			pst.setString(4, this.usuario);
			pst.setString(5, this.senha);
			pst.executeUpdate();
			System.out.println("Veterinário cadastrado com sucesso!");
			salvo = true;
		}
		catch(SQLException e) {
			System.out.println("Não foi possível cadastrar o Veterinário: " + e);
		}
		finally {
			this.conexao = banco.desconectar(conexao);
			return salvo;
		}
	}
	
	public boolean update() {
		String sql = "update veterinario set nome=?, idade=?, telefone=?, usuario=?, senha=? where id=?";
		this.conexao = banco.conectar();
		boolean editado = false;
		
		try {
			pst = this.conexao.prepareStatement(sql);
			pst.setString(1, this.nome);
			pst.setInt(2, this.idade);
			pst.setString(3, this.telefone);
			pst.setString(4, this.usuario);
			pst.setString(5, this.senha);
			pst.setInt(6, this.id);
			pst.executeUpdate();
			System.out.println("Veterinário editado com sucesso!");
			editado = true;
		}
		catch(SQLException e) {
			System.out.println("Não foi possível editar o veterinário: " + e);
		}
		finally {
			this.conexao = banco.desconectar(conexao);
			return editado;
		}
	}
	
	public boolean delete() {
		String sql = "delete from veterinario where id =?";
		this.conexao = banco.conectar();
		boolean deletado = false;
		
		try {
			pst = this.conexao.prepareStatement(sql);
			pst.setInt(1, this.id);
			pst.executeUpdate();
			System.out.println("Veterinário excluído com sucesso!");
			deletado = true;
		}
		catch(SQLException e) {
			System.out.println("Não foi possível excluir o veterinário: " + e);
		}
		finally {
			this.conexao = banco.desconectar(conexao);
			return deletado;
		}
	}
	
	
}

