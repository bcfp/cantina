package daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.ProdutoVendaVO;
import daoservice.IProdutoDAO;

public class ProdutoVendaDAO implements IProdutoDAO<ProdutoVendaVO>{
	
	private Connection conexao;
	private ConnectionFactory fabrica;
	private CallableStatement cstm;
	private ResultSet rs;
	
	{
		
		fabrica = ConnectionFactory.getInstance();
		
	}
	
	@Override
	public List<ProdutoVendaVO> consultarPorDescricao(String descricao){
		
		List<ProdutoVendaVO> listaProdutos = new ArrayList<ProdutoVendaVO>();
		boolean flagSucesso = false;
		
		try {
			
			String sql = "{call pr(?),(?)}";
			conexao = fabrica.getConexao();
			
			cstm = conexao.prepareCall(sql);
			cstm.setString(1, descricao);
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
			
		}
		
	}

	@Override
	public boolean inserir(ProdutoVendaVO produtoVenda) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(ProdutoVendaVO produtoVenda) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProdutoVendaVO> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdutoVendaVO consultarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
