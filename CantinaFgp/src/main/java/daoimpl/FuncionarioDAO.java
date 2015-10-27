package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.FuncionarioCantinaVO;
import vo.FuncionarioVO;
import vo.UsuarioVO;
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
			
			pstm = conexao.prepareStatement("select pf.id_pessoa_funcionario, pf.cod_funcionario, p.nome, p.cpf, p.data_nascimento, u.id_usuario "
					+ "from pessoa_funcionario pf "
					+ "inner join pessoa p on p.id_pessoa = pf.id_pessoa_funcionario "
					+ "inner join usuario u on u.id_usuario = pf.id_usuario "
					+ "inner join funcionario_cantina fc on pf.id_pessoa_funcionario = fc.id_pessoa_funcionario "
					+ "where fc.ativo = 1 and p.nome like ? and pf.cod_funcionario like ?");
			
			pstm.setString(1, "%" + nome + "%");
			pstm.setString(2, "%" + cod + "%");
			
			rs = pstm.executeQuery();
			
			FuncionarioVO funcionario = null;
			
			while(rs.next()){
				
				funcionario = new FuncionarioVO();
				funcionario.setCodPessoa(rs.getString("cod_funcionario"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setDataNascimento(rs.getDate("data_nascimento"));
				funcionario.setIdPessoa(rs.getLong("id_pessoa_funcionario"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(new UsuarioVO());
				funcionario.getUsuario().setIdUsuario(rs.getLong("id_usuario"));
				
				listaFuncionarios.add(funcionario);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
		
		return listaFuncionarios;
	}

}
