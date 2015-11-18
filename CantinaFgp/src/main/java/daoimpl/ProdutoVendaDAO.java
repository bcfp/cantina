package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MateriaPrimaVO;
import vo.ProdutoCantinaVO;
import vo.ProdutoMateriaPrimaVO;
import vo.ProdutoVendaVO;
import vo.UnidadeProdutoVO;
import bo.ProdutoMateriaPrimaBO;
import daoservice.IProdutoVendaDAO;
import enumeradores.TipoProduto;

public class ProdutoVendaDAO implements IProdutoVendaDAO{
	
	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	private ProdutoMateriaPrimaBO prodMatPrimaBo;
	
	{
		
		fabrica = ConnectionFactory.getInstance();
		
	}
	
	@Override
	public List<ProdutoVendaVO> consultarPorCodigoENome(String cod, String nome){
		
		List<ProdutoVendaVO> listaProdutosVenda = new ArrayList<ProdutoVendaVO>();
		
		try {
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"select pv.id_produto_venda, pv.cod_produto, pv.descricao, pv.qtd_estoque, pv.ativo, pv.preco_custo, "
					+ "pv.preco_venda, pv.fabricado, pv.lote, pv.id_unidade, "
					+ "u.descricao, u.ativo "
					+ "from produto_venda pv "
					+ "inner join unidade u on u.id_unidade = pv.id_unidade "
					+ "where pv.cod_produto like ?  and pv.descricao like ?");
			
			pstm.setString(1, "%" + cod + "%");
			pstm.setString(2, "%" + nome + "%");
		
			rs = pstm.executeQuery();
			
			ProdutoVendaVO produtoVenda = null;
			
			while(rs.next()){
				
				produtoVenda = new ProdutoVendaVO();
				produtoVenda.setIdProduto(rs.getLong("id_produto_venda"));
				produtoVenda.setCodProduto(rs.getString("cod_produto"));
				produtoVenda.setDescricao(rs.getString("descricao"));
				if(rs.getBoolean("fabricado")){
					produtoVenda.setTipo(TipoProduto.PRODUCAO);
				}
				else{
					produtoVenda.setTipo(TipoProduto.REVENDA);
				}
				produtoVenda.setQtdeEstoque(rs.getDouble("qtd_estoque"));
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
	public Long getUltimoIdGerado(){
		
		Long id = null;
		
		try {
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement("select id_produto_venda from produto_venda where id_produto_venda = (select max(id_produto_venda) from produto_venda)");
		
			rs = pstm.executeQuery();
			
			if(rs.next()){
				id = rs.getLong("id_produto_venda");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public ProdutoVendaVO incluir(ProdutoVendaVO produtoVenda) {

		ProdutoVendaVO prodIncluido = null;
		
		try{
						
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"insert into produto_venda"
					+ "(cod_produto, descricao, qtd_estoque, ativo, preco_custo, preco_venda, fabricado, lote, id_unidade) "
					+ "values (?,?,?,?,?,?,?,?,?)");
			
			pstm.setString(1, produtoVenda.getCodProduto());
			pstm.setString(2, produtoVenda.getDescricao());
			pstm.setDouble(3, produtoVenda.getQtdeEstoque());
			pstm.setInt(4, 1);
			pstm.setDouble(5, produtoVenda.getPrecoCusto());
			pstm.setDouble(6, produtoVenda.getPrecoVenda());
			
			int fabricado = 0;
			if(produtoVenda.getTipo().equals(TipoProduto.PRODUCAO)){
				fabricado = 1;
			}
			pstm.setInt(7, fabricado);
			pstm.setBoolean(8, produtoVenda.getLote());
			pstm.setLong(9, produtoVenda.getUnidade().getIdUnidadeProduto());
			
			if(pstm.executeUpdate() > 0){
				
				if(produtoVenda.getTipo().equals(TipoProduto.PRODUCAO)){

					Long ultimoIdGerado = getUltimoIdGerado();
					produtoVenda.setIdProduto(ultimoIdGerado);
					
					// Cadastrando matérias-primas
					
					pstm = conexao.prepareStatement("insert into receita (qtde, id_materia_prima, id_produto, id_unidade) values (?,?,?,?)");

					List<ProdutoMateriaPrimaVO> receita = produtoVenda.getReceita();
					
					for (ProdutoMateriaPrimaVO itemReceita : receita) {
						
						pstm.setDouble(1, itemReceita.getQtde());
						pstm.setLong(2, itemReceita.getMateriaPrima().getIdProduto());
						pstm.setLong(3, itemReceita.getProdutoFabricado().getIdProduto());
						pstm.setLong(4, itemReceita.getUnidade().getIdUnidadeProduto());
						
						if(pstm.executeUpdate() > 0){
							
							ProdutoCantinaVO estoque = produtoVenda.getEstoque();
							
							pstm = conexao.prepareStatement("insert into produto_venda_cantina (qtde_maxima, qtde_minima, estoque, ativo, id_cantina, id_produto_venda) values (?,?,?,?,?,?)");

							pstm.setDouble(1, estoque.getQtdeMaxima());
							pstm.setDouble(2, estoque.getQtdeMinima());
							pstm.setDouble(3, 0);
							pstm.setInt(4, 1);
							pstm.setLong(5, 1);
							pstm.setLong(6, produtoVenda.getIdProduto());
							
							if(pstm.executeUpdate() == 0){
								conexao.rollback();
								return null;
							}
							
						}
						else{
							conexao.rollback();
							return null;							
						}
						
					}
				}
			}
			else{
				conexao.rollback();
				return null;
			}
						
			prodIncluido = produtoVenda;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			try {conexao.rollback();} catch (SQLException e1) {}
		} catch (SQLException e) {
			e.printStackTrace();
			try {conexao.rollback();} catch (SQLException e1) {}
		} finally {
			
			try {
				conexao.close();
				pstm.close();
			} catch (SQLException e) {
				return null;
			}
			
		}

		return prodIncluido;
		
	}

	@Override
	public boolean alterar(ProdutoVendaVO produtoVenda) {
		
		try{
						
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
						"update produto_venda "
					  + "set cod_produto = ?, descricao = ?, preco_custo = ?, preco_venda = ?, fabricado = ?, lote = ?, id_unidade = ? "
					  + "where id_produto_venda = ?"
			);
			
			pstm.setString(1, produtoVenda.getCodProduto());
			pstm.setString(2, produtoVenda.getDescricao());
			pstm.setDouble(3, produtoVenda.getPrecoCusto());
			pstm.setDouble(4, produtoVenda.getPrecoVenda());
			
			int fabricado = 0;
			if(produtoVenda.getTipo().equals(TipoProduto.PRODUCAO)){
				fabricado = 1;
			}
			pstm.setInt(5, fabricado);
			pstm.setBoolean(6, produtoVenda.getLote());
			pstm.setLong(7, produtoVenda.getUnidade().getIdUnidadeProduto());
			
			pstm.setDouble(8, produtoVenda.getIdProduto());
			
			if(pstm.executeUpdate() > 0){
				
				if(produtoVenda.getTipo().equals(TipoProduto.PRODUCAO)){
					
					prodMatPrimaBo = new ProdutoMateriaPrimaBO();
					
					List<ProdutoMateriaPrimaVO> receitaBanco = prodMatPrimaBo.buscaReceitaPorIdProduto(produtoVenda.getIdProduto());
					
					if(receitaBanco == null){
						conexao.rollback();
						return false;
					}
					
					List<ProdutoMateriaPrimaVO> receitaAtual = produtoVenda.getReceita();

					List<ProdutoMateriaPrimaVO> receitaProdIncluir = new ArrayList<ProdutoMateriaPrimaVO>();				
					List<ProdutoMateriaPrimaVO> receitaProdAlterar = new ArrayList<ProdutoMateriaPrimaVO>();
					List<ProdutoMateriaPrimaVO> receitaProdExcluir = new ArrayList<ProdutoMateriaPrimaVO>();
										
					for (ProdutoMateriaPrimaVO matPrimaProd : receitaAtual) {
						
						Boolean prodNovo = true;
						
						MateriaPrimaVO mpAtual = matPrimaProd.getMateriaPrima();
						
						for (ProdutoMateriaPrimaVO matPrimaBanco : receitaBanco) {
							
							MateriaPrimaVO mpBanco = matPrimaBanco.getMateriaPrima();
							
							if(mpAtual.getIdProduto() == mpBanco.getIdProduto()){
								
								receitaProdAlterar.add(matPrimaProd);
								prodNovo = false;
																
							}
							
						}
						
						if(prodNovo){
							
							receitaProdIncluir.add(matPrimaProd);
							
						}
						
					}
					
					
					for (ProdutoMateriaPrimaVO matPrimaBanco : receitaBanco) {
						
						Boolean mpExcluir = true;
						
						MateriaPrimaVO mpBanco = matPrimaBanco.getMateriaPrima();
						
						for (ProdutoMateriaPrimaVO matPrimaProd : receitaAtual) {
							
							MateriaPrimaVO mpProd = matPrimaProd.getMateriaPrima();
							
							if(mpBanco.getIdProduto() == mpProd.getIdProduto()){
								mpExcluir = false;
							}
							
						}
						
						// Matéria Prima Nova
						if(mpExcluir){
							
							receitaProdExcluir.add(matPrimaBanco);
							
						}
						
					}
					
					if (receitaProdIncluir.size() > 0) {
						
						pstm = conexao.prepareStatement("insert into receita (qtde, id_materia_prima, id_produto, id_unidade) values (?,?,?,?)");
						
						for (ProdutoMateriaPrimaVO itemReceita : receitaProdIncluir) {
							
							pstm.setDouble(1, itemReceita.getQtde());
							pstm.setLong(2, itemReceita.getMateriaPrima().getIdProduto());
							pstm.setLong(3, itemReceita.getProdutoFabricado().getIdProduto());
							pstm.setLong(4, itemReceita.getUnidade().getIdUnidadeProduto());
							
							if(pstm.executeUpdate() == 0){
								conexao.rollback();
								return false;
							}
							
						}
						
					}
					
					if (receitaProdAlterar.size() > 0) {
						
						pstm = conexao.prepareStatement("update receita set qtde = ?, id_unidade = ? where id_materia_prima = ? and id_produto = ?");
					
						for (ProdutoMateriaPrimaVO itemReceita : receitaAtual) {
														
							pstm.setDouble(1, itemReceita.getQtde());
							pstm.setLong(2, itemReceita.getUnidade().getIdUnidadeProduto());
							pstm.setLong(3, itemReceita.getMateriaPrima().getIdProduto());
							pstm.setLong(4, itemReceita.getProdutoFabricado().getIdProduto());
														
							if(pstm.executeUpdate() == 0){
								conexao.rollback();
								return false;
							}
							
						}
						
					}
					
					if (receitaProdExcluir.size() > 0) {
						
						pstm = conexao.prepareStatement("delete from receita where id_materia_prima = ? and id_produto = ?");
						
						for (ProdutoMateriaPrimaVO mpExcluir : receitaProdExcluir) {
							
							pstm.setLong(1, mpExcluir.getMateriaPrima().getIdProduto());
							pstm.setLong(2, mpExcluir.getProdutoFabricado().getIdProduto());
							
							if(pstm.executeUpdate() == 0){
								conexao.rollback();
								return false;
							}
							
						}
						
					}					
					
					ProdutoCantinaVO estoque = produtoVenda.getEstoque();
					
					pstm = conexao.prepareStatement("update produto_venda_cantina set qtde_maxima = ?, qtde_minima = ? where id_produto_venda = ?");

					pstm.setDouble(1, estoque.getQtdeMaxima());
					pstm.setDouble(2, estoque.getQtdeMinima());
					pstm.setLong(3, produtoVenda.getIdProduto());
													
					if(pstm.executeUpdate() == 0){
						conexao.rollback();
						return false;
					}
					
				}
			}
			else{
				conexao.rollback();
				return false;
			}
				
									
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			try {
				conexao.rollback();
				return false;
			} catch (SQLException e1) {}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexao.rollback();
				return false;
				} catch (SQLException e1) {}
		} finally {
			
			try {
				conexao.close();
				pstm.close();
			} catch (SQLException e) {
				return false;
			}
			
		}

		return true;
		
	}

	@Override
	public boolean deletar(Long id) {
		
		return false;
	}
 
	@Override
	public List<ProdutoVendaVO> consultar() {
		
		List<ProdutoVendaVO> listaProdutosVenda = new ArrayList<ProdutoVendaVO>();
		
		try {
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"select pv.id_produto_venda, pv.cod_produto, pv.descricao, pv.qtd_estoque, pv.ativo, pv.preco_custo, pv.preco_venda, pv.fabricado, pv.lote, pv.id_unidade, "
					+ "u.descricao, u.ativo, "
					+ "pc.estoque, pc.qtde_maxima, pc.qtde_minima "
					+ "from produto_venda pv "
					+ "inner join unidade u on u.id_unidade = pv.id_unidade "
					+ "inner join produto_venda_cantina pc on pc.id_produto_venda = pv.id_produto_venda ");
			
			rs = pstm.executeQuery();
			
			ProdutoVendaVO produtoVenda = null;
			
			while(rs.next()){
				
				produtoVenda = new ProdutoVendaVO();
				produtoVenda.setIdProduto(rs.getLong("id_produto_venda"));
				produtoVenda.setCodProduto(rs.getString("cod_produto"));
				produtoVenda.setDescricao(rs.getString("descricao"));
				if(rs.getBoolean("fabricado")){
					produtoVenda.setTipo(TipoProduto.PRODUCAO);
				}
				else{
					produtoVenda.setTipo(TipoProduto.REVENDA);
				}
				produtoVenda.setQtdeEstoque(rs.getDouble("qtd_estoque"));
				produtoVenda.setLote(rs.getBoolean("lote"));
				produtoVenda.setPrecoCusto(rs.getDouble("preco_custo"));
				produtoVenda.setPrecoVenda(rs.getDouble("preco_venda"));
				produtoVenda.setAtivo(rs.getBoolean("ativo"));
				
				produtoVenda.setEstoque(new ProdutoCantinaVO());
				produtoVenda.getEstoque().setQtdeAtual(rs.getDouble("estoque"));
				produtoVenda.getEstoque().setQtdeMaxima(rs.getDouble("qtde_maxima"));
				produtoVenda.getEstoque().setQtdeMinima(rs.getDouble("qtde_minima"));
				
				produtoVenda.setReceita(new ProdutoMateriaPrimaBO().buscaReceitaPorIdProduto(produtoVenda.getIdProduto()));
			
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
					+ "pv.lote, pv.id_unidade, u.descricao, u.ativo from produto_venda pv where id_produto_venda = ? inner join unidade u on u.id_unidade = pv.id_unidade");
			
			
			pstm.setLong(1, id);
			
			rs = pstm.executeQuery();
		
			
			if(rs.next()){
				
				produtoVenda = new ProdutoVendaVO();
				produtoVenda.setCodProduto(rs.getString("cod_produto"));
				produtoVenda.setDescricao(rs.getString("descricao"));
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
