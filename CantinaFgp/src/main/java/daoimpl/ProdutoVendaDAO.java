package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.ProdutoVendaVO;
import vo.UnidadeProdutoVO;
import daoservice.IProdutoDAO;
import enumeradores.TipoProduto;

public class ProdutoVendaDAO implements IProdutoDAO<ProdutoVendaVO>{
	
	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		
		fabrica = ConnectionFactory.getInstance();
		
	}
	
	@Override
	public List<ProdutoVendaVO> consultarPorNomeECodigo(String nome, String cod){
		
		List<ProdutoVendaVO> listaProdutosVenda = new ArrayList<ProdutoVendaVO>();
		
		try {
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement("select pv.id_produto_venda, pv.cod_produto, pv.descricao, pv.ativo, pv.preco_custo, pv.preco_venda, pv.fabricado,"
					+ "pv.lote, pv.dias_vencimento, pv.id_unidade, u.descricao, u.ativo from produto_venda pv left join unidade u on u.id_unidade = pv.id_unidade where pv.cod_produto like ?  and pv.descricao like ?");
			
			pstm.setString(1, "%" + cod + "%");
			pstm.setString(2, "%" + nome + "%");
		
			rs = pstm.executeQuery();
			
			ProdutoVendaVO produtoVenda = null;
			
			while(rs.next()){
				
				produtoVenda = new ProdutoVendaVO();
				produtoVenda.setCodProduto(rs.getString("cod_produto"));
				produtoVenda.setDescricao(rs.getString("descricao"));
				produtoVenda.setDiasVencimento(rs.getInt("dias_vencimento"));
				if(rs.getBoolean("fabricado")){
					produtoVenda.setTipo(TipoProduto.PRODUCAO);
				}
				else{
					produtoVenda.setTipo(TipoProduto.REVENDA);
				}
				produtoVenda.setIdProduto(rs.getLong("id_produto_venda"));
				produtoVenda.setLote(rs.getBoolean("lote"));
				produtoVenda.setPrecoCusto(rs.getDouble("preco_custo"));
				produtoVenda.setPrecoVenda(rs.getDouble("preco_venda"));
				produtoVenda.setAtivo(rs.getBoolean("ativo"));
				produtoVenda.setUnidade(new UnidadeProdutoVO());
				produtoVenda.getUnidade().setIdUnidadeProduto(rs.getLong("id_unidade"));
				produtoVenda.getUnidade().setDescricao(rs.getString("descricao"));
				produtoVenda.getUnidade().setStatus(rs.getBoolean("ativo"));
				
				listaProdutosVenda.add(produtoVenda);
				
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
		
		return listaProdutosVenda;
		/*boolean flagSucesso = false;
		
		try {
			
			String sql = "{call pr(?),(?)}";
			conexao = fabrica.getConexao();
			
			cstm = conexao.prepareCall(sql);
			cstm.setString(1, nome);
			//cstm.registerOutParameter(2, OracleTypes.CURSOR);
			
			if(!cstm.execute()){
				 
				flagSucesso = true;
				rs = (ResultSet)cstm.getObject(2);
				
			}
			
			ProdutoVendaVO produto = null;
			
			while(rs.next()){
				
				produto = new ProdutoVendaVO();
				produto.setIdProduto(rs.getLong(""));
				produto.setCodProduto(rs.getString(""));
				produto.setDescricao(rs.getString(""));
				produto.setLote(rs.getBoolean(""));
				produto.setPrecoCusto(rs.getDouble(""));
				produto.setStatus(rs.getBoolean(""));
				produto.setDiasVencimento(rs.getInt(""));
				produto.setPrecoVenda(rs.getDouble(""));
				
				listaProdutos.add(produto);
			}
			
			return listaProdutos;
			
		} catch (ClassNotFoundException e) {
			
			return null;
			
		} catch (SQLException e) {
			
			return null;
			
		}*/
		
//		ProdutoVendaVO produto = new ProdutoVendaVO();
//		produto.setDescricao("Coxinha");
//		produto.setCodProduto("1");
//		produto.setIdProduto(1l);
//		
//		listaProdutos.add(produto);
//		
		
	}

	@Override
	public boolean inserir(ProdutoVendaVO produtoVenda) {
		
		return false;
	}

	@Override
	public boolean alterar(ProdutoVendaVO produtoVenda) {
		
		return false;
	}

	@Override
	public boolean excluir(Long id) {
		
		return false;
	}
 
	@Override
	public List<ProdutoVendaVO> consultarTodos() {
		
		List<ProdutoVendaVO> listaProdutosVenda = new ArrayList<ProdutoVendaVO>();
		
		try {
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement("select pv.id_produto_venda, pv.cod_produto, pv.descricao, pv.ativo, pv.preco_custo, pv.preco_venda, pv.fabricado,"
					+ "pv.lote, pv.dias_vencimento, pv.id_unidade, u.descricao, u.ativo from produto_venda pv inner join unidade u on u.id_unidade = pv.id_unidade");
			
			rs = pstm.executeQuery();
			
			ProdutoVendaVO produtoVenda = null;
			
			if(rs.next()){
				
				produtoVenda = new ProdutoVendaVO();
				produtoVenda.setCodProduto(rs.getString("cod_produto"));
				produtoVenda.setDescricao(rs.getString("descricao"));
				produtoVenda.setDiasVencimento(rs.getInt("dias_vencimento"));
				if(rs.getBoolean("fabricado")){
					produtoVenda.setTipo(TipoProduto.PRODUCAO);
				}
				else{
					if(rs.getBoolean("revenda")){
						produtoVenda.setTipo(TipoProduto.REVENDA);
					}
					else{
						produtoVenda.setTipo(TipoProduto.MATERIA_PRIMA);
					}
				}
				produtoVenda.setIdProduto(rs.getLong("id_produto_venda"));
				produtoVenda.setLote(rs.getBoolean("lote"));
				produtoVenda.setPrecoCusto(rs.getDouble("preco_custo"));
				produtoVenda.setPrecoVenda(rs.getDouble("preco_venda"));
				produtoVenda.setAtivo(rs.getBoolean("ativo"));
				produtoVenda.setUnidade(new UnidadeProdutoVO());
				produtoVenda.getUnidade().setIdUnidadeProduto(rs.getLong("id_unidade"));
				produtoVenda.getUnidade().setDescricao(rs.getString("descricao"));
				produtoVenda.getUnidade().setStatus(rs.getBoolean("ativo"));
				
				listaProdutosVenda.add(produtoVenda);
				
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
		
		return listaProdutosVenda;
	}

	@Override
	public ProdutoVendaVO consultarPorId(Long id) {
		
		ProdutoVendaVO produtoVenda = new ProdutoVendaVO();
		
		try {
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement("select pv.id_produto_venda, pv.cod_produto, pv.descricao, pv.ativo, pv.preco_custo, pv.preco_venda, pv.fabricado,"
					+ "pv.lote, pv.dias_vencimento, pv.id_unidade, u.descricao, u.ativo from produto_venda pv where id_produto_venda = ? inner join unidade u on u.id_unidade = pv.id_unidade");
			
			
			pstm.setLong(1, id);
			
			rs = pstm.executeQuery();
		
			
			if(rs.next()){
				
				produtoVenda = new ProdutoVendaVO();
				produtoVenda.setCodProduto(rs.getString("cod_produto"));
				produtoVenda.setDescricao(rs.getString("descricao"));
				produtoVenda.setDiasVencimento(rs.getInt("dias_vencimento"));
				if(rs.getBoolean("fabricado")){
					produtoVenda.setTipo(TipoProduto.PRODUCAO);
				}
				else{
					if(rs.getBoolean("revenda")){
						produtoVenda.setTipo(TipoProduto.REVENDA);
					}
					else{
						produtoVenda.setTipo(TipoProduto.MATERIA_PRIMA);
					}
				}
				produtoVenda.setIdProduto(rs.getLong("id_produto_venda"));
				produtoVenda.setLote(rs.getBoolean("lote"));
				produtoVenda.setPrecoCusto(rs.getDouble("preco_custo"));
				produtoVenda.setPrecoVenda(rs.getDouble("preco_venda"));
				produtoVenda.setAtivo(rs.getBoolean("ativo"));
				produtoVenda.setUnidade(new UnidadeProdutoVO());
				produtoVenda.getUnidade().setIdUnidadeProduto(rs.getLong("id_unidade"));
				produtoVenda.getUnidade().setDescricao(rs.getString("descricao"));
				produtoVenda.getUnidade().setStatus(rs.getBoolean("ativo"));
				
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
		
		return produtoVenda;
	}

}
