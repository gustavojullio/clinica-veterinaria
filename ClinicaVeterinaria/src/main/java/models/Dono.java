package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.DAO;

public class Dono {
	int id;
	String nome;
	int idade;
	long cpf;
	String endereco;
	String telefone;
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	DAO banco = new DAO();

	public Dono() {}
	
	public Dono(int id, String nome, int idade, long cpf, String endereco, String telefone) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public Dono(String nome, int idade, long cpf, String endereco, String telefone) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public Dono(int id) {
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

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public ArrayList<Dono> all() {
		String sql = "select * from dono";
		this.conexao = banco.conectar();
		ArrayList<Dono> donos = new ArrayList<>();
		
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				int idade = rs.getInt(3);
				long cpf = rs.getLong(4);
				String endereco = rs.getString(5);
				String telefone = rs.getString(6);
			
				Dono d = new Dono(id,nome,idade,cpf, endereco, telefone);
				donos.add(d);
			}
		} 
		catch (SQLException e) {
			System.out.println("Não foi possível retornar os donos: " + e);
		}
		finally {
			this.conexao = banco.desconectar(conexao);
			return donos;
		}
		
	}
	
	
	public boolean save() {
		String sql = "insert into dono(nome, idade, cpf, endereco, telefone) value(?,?,?,?,?)";
		this.conexao = banco.conectar();
		boolean salvo = false;
		
		try {
			pst = this.conexao.prepareStatement(sql);
			pst.setString(1, this.nome);
			pst.setInt(2, this.idade);
			pst.setLong(3, this.cpf);
			pst.setString(4, this.endereco);
			pst.setString(5, this.telefone);
			pst.executeUpdate();
			System.out.println("Dono cadastrado com sucesso!");
			salvo = true;
		}
		catch(SQLException e) {
			System.out.println("Não foi possível cadastrar o Dono: " + e);
		}
		finally {
			this.conexao = banco.desconectar(conexao);
			return salvo;
		}
	}
	
	public boolean update() {
		String sql = "update dono set nome=?, idade=?, cpf=?, endereco=?, telefone=? where id=?";
		this.conexao = banco.conectar();
		boolean editado = false;
		
		try {
			pst = this.conexao.prepareStatement(sql);
			pst.setString(1, this.nome);
			pst.setInt(2, this.idade);
			pst.setLong(3, this.cpf);
			pst.setString(4, this.endereco);
			pst.setString(5, this.telefone);
			pst.setInt(6, this.id);
			pst.executeUpdate();
			System.out.println("Dono editado com sucesso!");
			editado = true;
		}
		catch(SQLException e) {
			System.out.println("Não foi possível editar o dono: " + e);
		}
		finally {
			this.conexao = banco.desconectar(conexao);
			return editado;
		}
	}
	
	public boolean delete() {
		String sql = "delete from dono where id =?";
		this.conexao = banco.conectar();
		boolean deletado = false;
		
		try {
			pst = this.conexao.prepareStatement(sql);
			pst.setInt(1, this.id);
			pst.executeUpdate();
			System.out.println("Dono excluído com sucesso!");
			deletado = true;
		}
		catch(SQLException e) {
			System.out.println("Não foi possível excluir o dono: " + e);
		}
		finally {
			this.conexao = banco.desconectar(conexao);
			return deletado;
		}
	}
}