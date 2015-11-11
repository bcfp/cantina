package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import utils.BancoFake;
import vo.OrdemProducaoVO;
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
	public OrdemProducaoVO incluir(OrdemProducaoVO ordemProd) {
		/*
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
		*/
		return BancoFake.ordemProd;
	}

	@Override
	public boolean alterar(OrdemProducaoVO ordemProducao) {
		
		// TODO - Fazer conexão com o banco
		
		return true;
		
	}

	@Override
	public boolean deletar(Long id) {
		
		return true;
		
	}

	@Override
	public List<OrdemProducaoVO> consultar() {
		
		// TODO - Fazer conexão com o banco
		
		return BancoFake.listaOrdensProducao;
		
	}

	@Override
	public OrdemProducaoVO consultarPorId(Long id) {
		
		return null;
	}

}
