package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.BancoFake;
import vo.CompraVO;
import vo.FormaPgtoVO;
import vo.FornecedorVO;
import vo.FuncionarioCantinaVO;
import vo.OrdemProducaoVO;
import vo.StatusVO;
import daoservice.ICompraDAO;
import enumeradores.TipoGeradorCompra;
import enumeradores.TipoStatus;

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
		
		List<CompraVO> listaCompras = new ArrayList<CompraVO>();
		
		try {
			conexao = fabrica.getConexao();
			
			String sql = "select c.id_compra, c.data_compra, c.tipo_origem, c.id_coringa_origem, "
					+ "fp.id_forma_pgto, fp.descricao, f.id_fornecedor, f.nome, f.contato, s.id_status, s.descricao as descricao_status, s.tipo "
					+ "from compra c "
					+ "inner join forma_pgto fp on c.id_forma_pgto = fp.id_forma_pgto "
					+ "inner join fornecedor f on c.id_fornecedor = f.id_fornecedor "
					+ "inner join status s on c.id_status_compra = s.id_status "
					+ "where f.ativo = 1";
			
			pstm = conexao.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			CompraVO compra = null;
			
			while(rs.next()){
				
				compra = new CompraVO();
				compra.setCodCompra(rs.getString("id_compra"));
				compra.setData(rs.getDate("data_compra"));
				compra.setIdCompra(rs.getLong("id_compra"));
				
				compra.setFormaPgto(new FormaPgtoVO());
				compra.getFormaPgto().setIdFormaPgtoVenda(rs.getLong("id_forma_pgto"));
				compra.getFormaPgto().setDescricao(rs.getString("descricao"));
				
				compra.setFornecedor(new FornecedorVO());
				compra.getFornecedor().setIdFornecedor(rs.getLong("id_fornecedor"));
				compra.getFornecedor().setNome(rs.getString("nome"));
				compra.getFornecedor().setContato(rs.getString("contato"));
				
				compra.setStatus(new StatusVO());
				compra.getStatus().setDescricao("descricao_status");
				String  tipoStatus = rs.getString("tipo");
				
				if(tipoStatus.equals(TipoStatus.ORDEM_COMPRA)){
					compra.getStatus().setTipoStatus(TipoStatus.ORDEM_COMPRA);
				}
				
				if(tipoStatus.equals(TipoStatus.ORDEM_PRODUCAO)){
					compra.getStatus().setTipoStatus(TipoStatus.ORDEM_PRODUCAO);
				}
				compra.getStatus().setIdStatus(rs.getLong("id_status"));				
				
				listaCompras.add(compra);
				
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return null;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
		return listaCompras;
	}

	@Override
	public CompraVO consultarPorId(Long id) {
		
		return null;
	}

}
