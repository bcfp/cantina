package daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bo.OrdemProducaoBO;
import vo.OrdemProducaoVO;
import vo.ProdutoVendaVO;
import daoservice.IOrdemProducaoDAO;

public class OrdemProducaoDAO implements IOrdemProducaoDAO{
	
	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		
		fabrica = ConnectionFactory.getInstance();
		
	}

	@Override
	public boolean inserir(OrdemProducaoVO ordemProd) {
		
		java.sql.Date dataSql = new java.sql.Date(ordemProd.getData().getTime());
		
		try {
			conexao = fabrica.getConexao();
			
			
			
			pstm = conexao.prepareStatement("insert into ordem_producao(qtde, data_ordem_producao, id_produto, id_funcionario_cantina, id_status) "
					+ "values (?,?,?,?,?)");
			
			pstm.setInt(1, ordemProd.getQtde());
			pstm.setDate(2, dataSql);
			pstm.setLong(3, ordemProd.getProdutoVenda().getIdProduto());
			pstm.setLong(4, ordemProd.getFuncionarioCantina().getIdFuncionarioCantina());
			pstm.setLong(5, ordemProd.getStatus().getIdStatus());
			
			pstm.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean alterar(OrdemProducaoVO objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<OrdemProducaoVO> consultarTodos() {
		
		return null;
	}

	@Override
	public OrdemProducaoVO consultarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
