package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.ProdutoMateriaPrimaBO;
import utils.BancoFake;
import vo.CompraVO;
import vo.FormaPgtoVO;
import vo.FornecedorVO;
import vo.FuncionarioCantinaVO;
import vo.ItemCompraVO;
import vo.MateriaPrimaVO;
import vo.OrdemProducaoVO;
import vo.ProdutoMateriaPrimaVO;
import vo.StatusVO;
import daoservice.ICompraDAO;
import enumeradores.TipoGeradorCompra;
import enumeradores.TipoProduto;
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
		
		java.sql.Date dataSql = new java.sql.Date(compra.getData().getTime());
		
		try {
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"update compra "
					+ "set data_compra = ?, tipo_origem = ?, id_coringa_origem = ?, id_forma_pgto = ?, "
					+ "id_fonecedor = ?, id_status_compra = ?");
			
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
			
			if(pstm.executeUpdate() > 0){
				
				List<ItemCompraVO> itensCompraBanco = consultarItensCompraPorIdCompra(compra.getIdCompra());
				
				if(itensCompraBanco == null){
					conexao.rollback();
					return false;
				}
				
				List<ItemCompraVO> itensCompraAtual = compra.getItensCompra();
				
				List<ItemCompraVO> itensCompraIncluir = new ArrayList<ItemCompraVO>();
				List<ItemCompraVO> itensCompraAlterar = new ArrayList<ItemCompraVO>();
				List<ItemCompraVO> itensCompraExcluir = new ArrayList<ItemCompraVO>();
				
				for (ItemCompraVO itemCompra : itensCompraAtual) {
					
					Boolean itemCompraNovo = true;
					
					ItemCompraVO itemComAtual = itemCompra;
					
					for (ItemCompraVO itemCompraBanco : itensCompraBanco) {
						
						ItemCompraVO itemComBanco = itemCompraBanco;
						
						if(itemComAtual.getCompra().getIdCompra() == itemComBanco.getCompra().getIdCompra()){
							
							itensCompraAlterar.add(itemCompra);
							itemCompraNovo = false;
															
						}
						
					}
					
					if(itemCompraNovo){
						
						itensCompraIncluir.add(itemCompra);
					}
					
				}
				
				for (ItemCompraVO itemCompraBanco : itensCompraBanco) {
					
					Boolean itemCompraExcluir = true;
					
					ItemCompraVO itemComBanco = itemCompraBanco;
					
					for (ItemCompraVO itemCompra : itensCompraAtual) {
						
						ItemCompraVO itemCom = itemCompra;
						
						if(itemComBanco.getCompra().getIdCompra() == itemCom.getCompra().getIdCompra()){
							itemCompraExcluir = false;
						}
						
					}
					
					if(itemCompraExcluir){
						
						itensCompraExcluir.add(itemCompraBanco);
						
					}
					
				}
				
				if(itensCompraIncluir.size() > 0){
					
					pstm = conexao.prepareStatement("insert into item_compra (qtde, valor, id_coriga_produto, tipo, id_compra, id_produto_venda)"
							+ " values (?,?,?,?,?,?");
					
					for (ItemCompraVO itemCompra : itensCompraIncluir){
						pstm.setDouble(1, itemCompra.getQtde());
						pstm.setDouble(2, itemCompra.getValor());
						pstm.setLong(3, itemCompra.getProduto().getIdProduto());
						pstm.setString(4, itemCompra.getProduto().getTipo().getTipoProduto());
						pstm.setLong(5, itemCompra.getCompra().getIdCompra());
						pstm.setLong(6, itemCompra.getProduto().getIdProduto());
						
						if(pstm.executeUpdate() == 0){
							conexao.rollback();
							return false;
						}
						
						
					}
				}
				
				if(itensCompraAlterar.size() > 0){
					
					pstm = conexao.prepareStatement("update item_compra set qtde = ?, valor = ?, id_coringa_produto = ?, tipo = ?, id_compra = ?");
					
					for(ItemCompraVO itemCompra : itensCompraAlterar){
						
						pstm.setDouble(1, itemCompra.getQtde());
						pstm.setDouble(2, itemCompra.getValor());
						pstm.setLong(3, itemCompra.getProduto().getIdProduto());
						pstm.setString(4, itemCompra.getProduto().getTipo().getTipoProduto());
						pstm.setLong(5, itemCompra.getCompra().getIdCompra());
						
						if(pstm.executeUpdate() == 0){
							conexao.rollback();
							return false;
						}
					}
				}
				
				if(itensCompraExcluir.size() > 0){
					
					pstm = conexao.prepareStatement("delete from item_compra where id_item_compra = ?");
					
					for(ItemCompraVO itemCompra : itensCompraExcluir){
						
						pstm.setLong(1, itemCompra.getIdItemCompra());
						
						if(pstm.executeUpdate() == 0){
							conexao.rollback();
							return false;
						}
					}
				} 
				
				
			}
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}finally{
			
			try {
				conexao.close();
				pstm.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean deletar(Long id) {
		
		
		
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
	
	private List<ItemCompraVO>	consultarItensCompraPorIdCompra(Long idCompra){
		
		List<ItemCompraVO> listaItensCompra =  new ArrayList<ItemCompraVO>();
		
		try {
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"select id_item_compra, qtde, valor, id_coringa_produto, tipo, id_compra "
					+ "where id_compra = ?");
			
			pstm.setLong(1, idCompra);
			
			rs = pstm.executeQuery();
			
			ItemCompraVO itemCompra = null;
			
			while(rs.next()){
				
				itemCompra = new ItemCompraVO();
				itemCompra.setIdItemCompra(rs.getLong("id_item_compra"));
				itemCompra.setQtde(rs.getDouble("qtde"));
				itemCompra.setValor(rs.getDouble("valor"));
				if(rs.getString("tipo").equals(TipoProduto.REVENDA)){
					
					itemCompra.getProduto().setTipo(TipoProduto.REVENDA);
					itemCompra.getProduto().setIdProduto(rs.getLong("id_coringa_produto"));
				}
				if(rs.getString("tipo").equals(TipoProduto.PRODUCAO)){
					
					itemCompra.getProduto().setTipo(TipoProduto.PRODUCAO);
					itemCompra.getProduto().setIdProduto(rs.getLong("id_coringa_produto"));
				}
				if(rs.getString("tipo").equals(TipoProduto.MATERIA_PRIMA)){
					
					itemCompra.getProduto().setTipo(TipoProduto.MATERIA_PRIMA);
					itemCompra.getProduto().setIdProduto(rs.getLong("id_coringa_produto"));
				}
				itemCompra.setCompra(new CompraVO());
				itemCompra.getCompra().setIdCompra(rs.getLong("id_compra"));
				
				listaItensCompra.add(itemCompra);
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conexao.close();
				pstm.close();
				if(rs != null){
					rs.close();
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaItensCompra;
	}
	
	

}
