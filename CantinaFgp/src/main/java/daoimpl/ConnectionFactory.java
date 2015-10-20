package daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static ConnectionFactory fabrica;
	private Connection conexao;

	private ConnectionFactory() {

	}

	public static ConnectionFactory getInstance() {

		if (fabrica == null) {

			fabrica = new ConnectionFactory();

		}

		return fabrica;

	}

	public Connection getConexao() throws ClassNotFoundException, SQLException{
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		//CONEXAO CAINÃ NOTE conexao = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/CANTINA", "cantina", "123");
		//CONEXAO CAINÃ FABRICA
		conexao = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/CANTINA", "sa", "caina123");
		
		if(conexao != null){
		
			System.out.println("CONECTOO");
			
		}
		
		
		return conexao;
		
	}

	public void fecharConexao() throws SQLException {

		if (conexao != null) {

			conexao.close();
		}

	}

}
