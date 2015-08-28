package daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static ConnectionFactory fabrica;
	private Connection conexao;
	
	private ConnectionFactory(){
		
		
	}
	
	public static ConnectionFactory getInstance() {
		
		if(fabrica == null){
			
			fabrica = new ConnectionFactory();
			
		}
		
		return fabrica;
		
	}
	
	public Connection getConexao() throws ClassNotFoundException, SQLException{
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		conexao = DriverManager.getConnection("jdbc:jdts:sqlserver://localhost:1433","sa","sql");
		
		return conexao;
		
	}
	
	public void fecharConexao() throws SQLException{
		
		if(conexao != null){
			
			conexao.close();
		}
		
	}
	
}
