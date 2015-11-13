package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import utils.BancoFake;
import vo.CantinaVO;
import vo.CompraVO;
import vo.FuncionarioCantinaVO;
import vo.OrdemProducaoVO;
import daoservice.ICompraDAO;
import enumeradores.TipoGeradorCompra;

public class CompraDAO implements ICompraDAO {

	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		
		fabrica = ConnectionFactory.getInstance();
		
	}
	
	@Override
	public CompraVO incluir(CompraVO compra) {
		
		java.sql.Date dataSql = new java.sql.Date(compra.getData().getTime());
		
		try {
			conexao = fabrica.getConexao();
			
			String sql = "insert into compra (data_compra, tipo_origem, id_coringa_origem, id_forma_pgto, id_fornecedor, id_status_compra) "
					+ "values (?,?,?,?,?,?)";
			
			pstm = conexao.prepareStatement(sql);
			pstm.setDate(1, dataSql);
			
			if(compra.getGeradorCompra() instanceof OrdemProducaoVO){
				
				OrdemProducaoVO ordemProducao = (OrdemProducaoVO) compra.getGeradorCompra();
				
				pstm.setString(2, TipoGeradorCompra.ORDEM_PRODUCAO.getTipo());
				pstm.setLong(3, ordemProducao.getIdOrdemProducao());
			}
			
			else{
				
				FuncionarioCantinaVO funcionarioCantina = (FuncionarioCantinaVO) compra.getGeradorCompra();
				pstm.setString(2, TipoGeradorCompra.FUNCIONARIO_CANTINA.getTipo());
				pstm.setLong(3, funcionarioCantina.getIdFuncionarioCantina());
				
			}
			
			//TODO ainda não deu certo mas foi comitado para alterações
			pstm.setLong(4, compra.getFormaPgto().getIdFormaPgtoVenda());
			pstm.setLong(5, compra.getFornecedor().getIdFornecedor());
			pstm.setLong(6, compra.getStatus().getIdStatus());
			
			
			pstm.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			
			try {
				conexao.close();
				pstm.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
				return null;
			}
			
			
		}
		
		return compra;
	}

	@Override
	public boolean alterar(CompraVO compra) {
		
		// TODO - Fazer conexão com o banco
		
		return true;
	}

	@Override
	public boolean deletar(Long id) {
		
		// TODO - Fazer conexão com o banco
		
		return true;
	}

	@Override
	public List<CompraVO> consultar() {
		// TODO - Fazer conexão com o banco
		return BancoFake.listaCompras;
	}

	@Override
	public CompraVO consultarPorId(Long id) {
		
		return null;
	}

}
