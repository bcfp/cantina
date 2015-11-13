package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MateriaPrimaVO;
import vo.ProdutoVendaVO;
import vo.UnidadeProdutoVO;
import daoservice.IMateriaPrimaDAO;
import enumeradores.TipoProduto;

public class MateriaPrimaDAO implements IMateriaPrimaDAO {
	
	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		
		fabrica = ConnectionFactory.getInstance();
		
	}

	@Override
	public MateriaPrimaVO incluir(MateriaPrimaVO materiaPrima) {
		System.out.println("incluiu matéria prima");
		return null;
	}

	@Override
	public boolean alterar(MateriaPrimaVO materiaPrima) {
		
		return false;
	}

	@Override
	public boolean deletar(Long id) {
		
		return false;
	}

	@Override
	public List<MateriaPrimaVO> consultar() {
		
		List<MateriaPrimaVO> listaMateriasPrimas = new ArrayList<MateriaPrimaVO>();
		
		try {
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"select mp.id_materia_prima, mp.cod_materia_prima, mp.descricao, mp.qtd_estoque, mp.ativo, mp.preco_custo, mp.lote, mp.id_unidade, "
					+ "u.descricao, u.ativo "
					+ "from materia_prima mp inner join unidade u on u.id_unidade = mp.id_unidade");
			
			rs = pstm.executeQuery();
			
			MateriaPrimaVO materiaPrima = null;
			
			while(rs.next()){
				
				materiaPrima = new MateriaPrimaVO();
				materiaPrima.setIdProduto(rs.getLong("id_materia_prima"));
				materiaPrima.setCodProduto(rs.getString("id_materia_prima"));
				materiaPrima.setDescricao(rs.getString("descricao"));
				materiaPrima.setLote(rs.getBoolean("lote"));
				materiaPrima.setPrecoCusto(rs.getDouble("preco_custo"));
				materiaPrima.setAtivo(rs.getBoolean("ativo"));
				materiaPrima.setUnidade(new UnidadeProdutoVO());
				materiaPrima.getUnidade().setIdUnidadeProduto(rs.getLong("id_unidade"));
				materiaPrima.getUnidade().setDescricao(rs.getString("descricao"));
				materiaPrima.getUnidade().setStatus(rs.getBoolean("ativo"));
				
				listaMateriasPrimas.add(materiaPrima);
				
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
		
		return listaMateriasPrimas;
	}

	@Override
	public MateriaPrimaVO consultarPorId(Long id) {
		
		return null;
	}

	@Override
	public List<MateriaPrimaVO> consultarPorCodigoENome(String cod, String nome) {
		
		List<MateriaPrimaVO> listaMateriasPrimas = new ArrayList<MateriaPrimaVO>();
		
		try {
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"select mp.id_materia_prima, mp.cod_materia_prima, mp.descricao, "
					+ "mp.qtd_estoque, mp.ativo, mp.lote, mp.id_unidade, mp.preco_custo, "
					+ "u.descricao, u.ativo "
					+ "from materia_prima mp "
					+ "left join unidade u on u.id_unidade = mp.id_unidade "
					+ "where mp.cod_materia_prima like ? and mp.descricao like ?");
			
			pstm.setString(1, "%" + cod + "%");
			pstm.setString(2, "%" + nome + "%");
		
			rs = pstm.executeQuery();
			
			MateriaPrimaVO materiaPrima = null;
			
			while(rs.next()){
				
				materiaPrima = new MateriaPrimaVO();
				materiaPrima.setIdProduto(rs.getLong("id_materia_prima"));
				materiaPrima.setCodProduto(rs.getString("cod_materia_prima"));
				materiaPrima.setDescricao(rs.getString("descricao"));
				materiaPrima.setQtdeEstoque(rs.getDouble("qtd_estoque"));
				materiaPrima.setAtivo(rs.getBoolean("ativo"));
				materiaPrima.setLote(rs.getBoolean("lote"));
				materiaPrima.setPrecoCusto(rs.getDouble("preco_custo"));
				materiaPrima.setUnidade(new UnidadeProdutoVO());
				materiaPrima.getUnidade().setIdUnidadeProduto(rs.getLong("id_unidade"));
				materiaPrima.getUnidade().setDescricao(rs.getString("descricao"));
				materiaPrima.getUnidade().setStatus(rs.getBoolean("ativo"));
				
				listaMateriasPrimas.add(materiaPrima);
				
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
		
		return listaMateriasPrimas;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
