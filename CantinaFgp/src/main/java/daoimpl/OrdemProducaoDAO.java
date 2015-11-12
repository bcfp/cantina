package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.BancoFake;
import vo.FuncionarioCantinaVO;
import vo.FuncionarioVO;
import vo.OrdemProducaoVO;
import vo.ProdutoVendaVO;
import vo.StatusVO;
import vo.UnidadeProdutoVO;
import daoservice.IOrdemProducaoDAO;
import enumeradores.TipoStatus;

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
		
		return ordemProd;
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
		
		List<OrdemProducaoVO> listaOrdensProducao = new ArrayList<OrdemProducaoVO>();
		
		try {
			conexao = fabrica.getConexao();
			
			String sql = "select op.qtde, op.data_ordem_producao, op.id_ordem_producao, p.id_produto_venda, p.cod_produto, p.descricao, p.preco_venda, "
					+ "u.id_unidade,  u.descricao, u.abreviatura, f.id_funcionario_cantina, pf.cod_funcionario, "
					+ "pe.nome, s.id_status, s.descricao, s.tipo "
					+ "from ordem_producao op "
					+ "inner join produto_venda p on op.id_produto = p.id_produto_venda "
					+ "inner join funcionario_cantina f on op.id_funcionario_cantina = f.id_funcionario_cantina "
					+ "inner join pessoa_funcionario pf on pf.id_pessoa_funcionario = f.id_pessoa_funcionario "
					+ "inner join pessoa pe on pe.id_pessoa = pf.id_pessoa_funcionario "
					+ "inner join status s on op.id_status = s.id_status "
					+ "inner join unidade u on u.id_unidade = p.id_unidade "
					+ "where p.fabricado = 1 and p.ativo = 1 and u.ativo = 1";
			
			pstm = conexao.prepareStatement(sql);
			
			OrdemProducaoVO ordemProducao = null;
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				
				ordemProducao = new OrdemProducaoVO();
				ordemProducao.setCodOrdemProducao(rs.getString("id_ordem_producao"));
				ordemProducao.setData(rs.getDate("data_ordem_producao"));
				ordemProducao.setQtde(rs.getInt("qtde"));
				ordemProducao.setFuncionarioCantina(new FuncionarioCantinaVO());
				ordemProducao.getFuncionarioCantina().setIdFuncionarioCantina(rs.getInt("id_funcionario_cantina"));
				ordemProducao.getFuncionarioCantina().setFuncionario(new FuncionarioVO());
				ordemProducao.getFuncionarioCantina().getFuncionario().setCodPessoa(rs.getString("cod_funcionario"));
				ordemProducao.getFuncionarioCantina().getFuncionario().setNome(rs.getString("nome"));
				ordemProducao.setIdOrdemProducao(rs.getLong("id_ordem_producao"));
				ordemProducao.setProdutoVenda(new ProdutoVendaVO());
				ordemProducao.getProdutoVenda().setIdProduto(rs.getLong("id_produto_venda"));
				ordemProducao.getProdutoVenda().setCodProduto(rs.getString("cod_produto"));
				ordemProducao.getProdutoVenda().setDescricao(rs.getString("descricao"));
				ordemProducao.getProdutoVenda().setPrecoVenda(rs.getDouble("preco_venda"));
				ordemProducao.getProdutoVenda().setUnidade(new UnidadeProdutoVO());
				ordemProducao.getProdutoVenda().getUnidade().setIdUnidadeProduto(rs.getLong("id_unidade"));
				ordemProducao.getProdutoVenda().getUnidade().setAbreviatura(rs.getString("abreviatura"));
				ordemProducao.getProdutoVenda().getUnidade().setDescricao(rs.getString("descricao"));
				ordemProducao.setStatus(new StatusVO());
				ordemProducao.getStatus().setIdStatus(rs.getLong("id_status"));
				String  tipoStatus = rs.getString("tipo");
				if(tipoStatus.equals(TipoStatus.GENERICO)){
					ordemProducao.getStatus().setTipoStatus(TipoStatus.GENERICO);
				}
				
				if(tipoStatus.equals(TipoStatus.ORDEM_COMPRA)){
					ordemProducao.getStatus().setTipoStatus(TipoStatus.ORDEM_COMPRA);
				}
				
				if(tipoStatus.equals(TipoStatus.ORDEM_PRODUCAO)){
					ordemProducao.getStatus().setTipoStatus(TipoStatus.ORDEM_PRODUCAO);
				}
				
				ordemProducao.getStatus().setDescricao(rs.getString("descricao"));
				
				listaOrdensProducao.add(ordemProducao);
				
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}finally{
		
			try {
				
				if(rs != null){
					
					rs.close();
				}
				 pstm.close();
				conexao.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		
		}
		
		return listaOrdensProducao;
		
	}

	@Override
	public OrdemProducaoVO consultarPorId(Long id) {
		
		return null;
	}
	
//	public List<OrdemProducaoVO> filtrarOrdemProducao(){
//		
//		List<OrdemProducaoVO> listaOrdensProducao = new ArrayList<OrdemProducaoVO>();
//			
//		try {
//			conexao = fabrica.getConexao();
//			
//			String sql = "select op.qtde, op.data_ordem_producao, op.id_ordem_producao, p.id_produto_venda, p.cod_produto, p.descricao, p.preco_venda, "
//					+ "p.dias_vencimento, u.id_unidade,  u.descricao, u.abreviatura, f.id_funcionario_cantina, pf.cod_funcionario, "
//					+ "pe.nome, s.id_status, s.descricao, s.tipo "
//					+ "from ordem_producao op "
//					+ "inner join produto_venda p on op.id_produto = p.id_produto_venda "
//					+ "inner join funcionario_cantina f on op.id_funcionario_cantina = f.id_funcionario_cantina "
//					+ "inner join pessoa_funcionario pf on pf.id_pessoa_funcionario = f.id_pessoa_funcionario "
//					+ "inner join pessoa pe on pe.id_pessoa = pf.id_pessoa_funcionario "
//					+ "inner join status s on op.id_status = s.id_status "
//					+ "inner join unidade u on u.id_unidade = p.id_unidade "
//					+ "where p.fabricado = 1 and p.ativo = 1 and u.ativo = 1";
//			
//			pstm = conexao.prepareStatement(sql);
//			
//			OrdemProducaoVO ordemProducao = null;
//			
//			rs = pstm.executeQuery();
//			
//			if(rs.next()){
//				
//				
//				ordemProducao = new OrdemProducaoVO();
//				ordemProducao.setCodOrdemProducao(rs.getString("id_ordem_producao"));
//				ordemProducao.setData(rs.getDate("data_ordem_producao"));
//				ordemProducao.setQtde(rs.getInt("qtde"));
//				ordemProducao.setFuncionarioCantina(new FuncionarioCantinaVO());
//				ordemProducao.getFuncionarioCantina().setIdFuncionarioCantina(rs.getInt("id_funcionario_cantina"));
//				ordemProducao.getFuncionarioCantina().setFuncionario(new FuncionarioVO());
//				ordemProducao.getFuncionarioCantina().getFuncionario().setCodPessoa(rs.getString("cod_funcionario"));
//				ordemProducao.getFuncionarioCantina().getFuncionario().setNome(rs.getString("nome"));
//				ordemProducao.setIdOrdemProducao(rs.getLong("id_ordem_producao"));
//				ordemProducao.setProdutoVenda(new ProdutoVendaVO());
//				ordemProducao.getProdutoVenda().setIdProduto(rs.getLong("id_produto_venda"));
//				ordemProducao.getProdutoVenda().setCodProduto(rs.getString("cod_produto"));
//				ordemProducao.getProdutoVenda().setDescricao(rs.getString("descricao"));
//				ordemProducao.getProdutoVenda().setPrecoVenda(rs.getDouble("preco_venda"));
//				ordemProducao.getProdutoVenda().setDiasVencimento(rs.getInt("dias_vencimento"));
//				ordemProducao.getProdutoVenda().setUnidade(new UnidadeProdutoVO());
//				ordemProducao.getProdutoVenda().getUnidade().setIdUnidadeProduto(rs.getLong("id_unidade"));
//				ordemProducao.getProdutoVenda().getUnidade().setAbreviatura(rs.getString("abreviatura"));
//				ordemProducao.getProdutoVenda().getUnidade().setDescricao(rs.getString("descricao"));
//				ordemProducao.setStatus(new StatusVO());
//				ordemProducao.getStatus().setIdStatus(rs.getLong("id_status"));
//				String  tipoStatus = rs.getString("tipo");
//				if(tipoStatus.equals(TipoStatus.GENERICO)){
//					ordemProducao.getStatus().setTipoStatus(TipoStatus.GENERICO);
//				}
//				
//				if(tipoStatus.equals(TipoStatus.ORDEM_COMPRA)){
//					ordemProducao.getStatus().setTipoStatus(TipoStatus.ORDEM_COMPRA);
//				}
//				
//				if(tipoStatus.equals(TipoStatus.ORDEM_PRODUCAO)){
//					ordemProducao.getStatus().setTipoStatus(TipoStatus.ORDEM_PRODUCAO);
//				}
//				
//				ordemProducao.getStatus().setDescricao(rs.getString("descricao"));
//				
//				listaOrdensProducao.add(ordemProducao);
//				
//			}
//			
//		} catch (ClassNotFoundException e) {
//			
//			e.printStackTrace();
//			return null;
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//			return null;
//		}finally{
//			
//			try {
//				
//				if(rs != null){
//					
//					rs.close();
//				}
//				 pstm.close();
//				conexao.close();
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//				return null;
//			}
//		
//		}
//		
//		return listaOrdensProducao;
//				
//	}

}
