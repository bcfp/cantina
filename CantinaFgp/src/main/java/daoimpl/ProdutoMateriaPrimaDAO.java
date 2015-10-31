package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoservice.IDAO;
import daoservice.IProdutoMateriaPrimaDAO;
import enumeradores.TipoProduto;
import vo.EstoqueMateriaPrimaVO;
import vo.MateriaPrimaVO;
import vo.ProdutoCantinaVO;
import vo.ProdutoMateriaPrimaVO;
import vo.ProdutoVendaVO;
import vo.UnidadeProdutoVO;

public class ProdutoMateriaPrimaDAO implements IProdutoMateriaPrimaDAO {
	
	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;

	{
		
		fabrica = ConnectionFactory.getInstance();
		
	}
	
	@Override
	public boolean inserir(ProdutoMateriaPrimaVO objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(ProdutoMateriaPrimaVO objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProdutoMateriaPrimaVO> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdutoMateriaPrimaVO consultarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ProdutoMateriaPrimaVO> consultarPorIdProduto(Long id) {
		
		List<ProdutoMateriaPrimaVO> listaMateriasPrimaProduto = new ArrayList<ProdutoMateriaPrimaVO>();
		
		try {
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement("select mp.cod_materia_prima, mp.descricao, mp.id_materia_prima, mp.lote,"
					+"mp.ativo, mp.preco_custo, u.id_unidade, u.abreviatura, u.descricao as descricao_unidade,"
					+"mpc.id_estoque_mat_prima, mpc.qtde_maxima, mpc.qtde_minima, mpc.estoque, r.qtde " 
					+"from produto_venda pv inner join receita r on pv.id_produto_venda = r.id_produto "
					+"inner join materia_prima mp on r.id_materia_prima = mp.id_materia_prima "
					+"inner join materia_prima_cantina mpc on mpc.id_materia_prima = mp.id_materia_prima "
					+"left join unidade u on u.id_unidade = mp.id_unidade "
					+"where pv.id_produto_venda = ? and pv.ativo = 1 and mp.ativo = 1 and u.ativo = 1");
			
			pstm.setLong(1, id);
			
			rs = pstm.executeQuery();
			
			ProdutoMateriaPrimaVO produtoMateriaPrima = null;
			
			while(rs.next()){
				
				produtoMateriaPrima = new ProdutoMateriaPrimaVO();
				produtoMateriaPrima.setMateriaPrima(new MateriaPrimaVO());
				produtoMateriaPrima.getMateriaPrima().setAtivo(rs.getBoolean("ativo"));
				produtoMateriaPrima.getMateriaPrima().setCodProduto(rs.getString("cod_materia_prima"));
				produtoMateriaPrima.getMateriaPrima().setDescricao(rs.getString("descricao"));
				produtoMateriaPrima.getMateriaPrima().setIdProduto(rs.getLong("id_materia_prima"));
				produtoMateriaPrima.getMateriaPrima().setPrecoCusto(rs.getDouble("preco_custo"));
				produtoMateriaPrima.getMateriaPrima().setLote(rs.getBoolean("lote"));
				produtoMateriaPrima.setQtde(rs.getDouble("qtde"));
				produtoMateriaPrima.getMateriaPrima().setUnidade(new UnidadeProdutoVO());
				produtoMateriaPrima.getMateriaPrima().getUnidade().setIdUnidadeProduto(rs.getLong("id_unidade"));
				produtoMateriaPrima.getMateriaPrima().getUnidade().setDescricao(rs.getString("descricao_unidade"));
				produtoMateriaPrima.getMateriaPrima().getUnidade().setAbreviatura(rs.getString("abreviatura"));
				produtoMateriaPrima.getMateriaPrima().setEstoque(new ProdutoCantinaVO());
				produtoMateriaPrima.getMateriaPrima().getEstoque().setIdEstoque(rs.getLong("id_estoque_mat_prima"));
				produtoMateriaPrima.getMateriaPrima().getEstoque().setQtdeAtual(rs.getDouble("estoque"));
				produtoMateriaPrima.getMateriaPrima().getEstoque().setQtdeMaxima(rs.getDouble("qtde_maxima"));
				produtoMateriaPrima.getMateriaPrima().getEstoque().setQtdeMinima(rs.getDouble("qtde_minima"));
				
				listaMateriasPrimaProduto.add(produtoMateriaPrima);
				
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
		
		return listaMateriasPrimaProduto;
	}

}
