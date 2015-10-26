package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.FuncionarioVO;
import daoservice.IDAO;

public class FuncionarioDAO implements IDAO<FuncionarioVO>{

	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		fabrica = ConnectionFactory.getInstance();
	}
	
	@Override
	public boolean inserir(FuncionarioVO objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(FuncionarioVO objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<FuncionarioVO> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FuncionarioVO consultarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<FuncionarioVO> filtarFuncionariosPorNomeECodigo(String cod, String nome){
		
		List<FuncionarioVO> listaFuncionarios = new ArrayList<FuncionarioVO>();
		
		try {
			conexao = fabrica.getConexao();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				conexao.close();
				pstm.close();
				if(rs != null){
					
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return null;
	}

}
