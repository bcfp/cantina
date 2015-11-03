package bo;

import java.util.List;

import utils.UtilFuncoes;
import vo.ProdutoVendaVO;
import daoimpl.ProdutoVendaDAO;
import daoservice.IProdutoVendaDAO;

public class ProdutoVendaBO {
	
	public IProdutoVendaDAO produtoVendaDao;
	
	{
		produtoVendaDao = new ProdutoVendaDAO();
	}
	
	public Boolean verificaDescricaoVazio(String descricao){
		
		return UtilFuncoes.isCampoVazio(descricao);
		
	}
	
	public List<ProdutoVendaVO> filtarProdutoVendaPorNomeECodigo(String nome, String cod){
		
		return produtoVendaDao.consultarPorNomeECodigo(nome, cod);
		
	}
	
	public List<ProdutoVendaVO> consultarTodosProdutos(){
		
		return produtoVendaDao.consultarTodos();
	}
	
	public ProdutoVendaVO consultarProdutoPorId(Long id){
		
		return produtoVendaDao.consultarPorId(id);
	}
	
	public boolean isQtdeMateriaPrima(ProdutoVendaVO produto){
		
		return produtoVendaDao.isQtdeMateriaPrima(produto);
		
	}
	
}
