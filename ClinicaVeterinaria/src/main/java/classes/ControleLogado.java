package classes;

public class ControleLogado {
	private static boolean logado = false;

	private static int idLogado = 0;

	public static boolean isLogado() {
		return logado;
	}

	public static void setLogado(boolean logado) {
		ControleLogado.logado = logado;
	}
	
	public static int getIdLogado() {
		return idLogado;
	}

	public static void setIdLogado(int idLogado) {
		ControleLogado.idLogado = idLogado;
	}
	
	
	
}
