package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import vo.FuncionarioVO;
import daoservice.IFuncionarioDAO;

public class FuncionarioDAO implements IFuncionarioDAO {

	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		fabrica = ConnectionFactory.getInstance();
	}
	
	@Override
	public boolean inserir(FuncionarioVO funcionario) {
		return false;
	}

	@Override
	public boolean alterar(FuncionarioVO funcionario) {
		return false;
	}

	@Override
	public boolean excluir(Long id) {
		return false;
	}

	@Override
	public List<FuncionarioVO> consultarTodos() {
		return null;
	}

	@Override
	public FuncionarioVO consultarPorId(Long id) {
		return null;
	}


}
