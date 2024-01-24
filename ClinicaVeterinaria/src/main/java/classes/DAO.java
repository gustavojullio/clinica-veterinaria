package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	Connection conexao = null;
	String url = "jdbc:mysql://localhost:3306/clinica_veterinaria";
	String user = "root";
	String password = "3030";
	
	public Connection conectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(url,user, password);
			System.out.println("Conexão efetuada!");
			return conexao;
		} catch (SQLException e) {
			System.out.println("Erro de conexão: " + e);
			conexao = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conexao=null;
		}
		finally {
			return conexao;
		}
	}
	
	public Connection desconectar(Connection conexao) {
		try {
			conexao.close();
			conexao = null;
		}
		catch(SQLException e) {
			System.out.println("Não foi possível encerrar a conexão! Erro: " + e);
			conexao = null;
		}
		finally {
			return conexao;
		}
	}
}
