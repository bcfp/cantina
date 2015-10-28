package bo;

import java.util.List;

import utils.UtilFuncoes;
import vo.ProdutoVendaVO;
import daoimpl.ProdutoVendaDAO;

public class ProdutoVendaBO {
	
	public ProdutoVendaDAO produtoDao;
	
	{
		
		produtoDao = new ProdutoVendaDAO();
	}
	
	public Boolean verificaDescricaoVazio(String descricao){
		
		return UtilFuncoes.isCampoVazio(descricao);
		
	}
	
	public List<ProdutoVendaVO> filtarProdutoVendaPorNomeECodigo(String nome, String cod){
		
		return produtoDao.consultarPorNomeECodigo(nome, cod);
		
	}
	
	public List<ProdutoVendaVO> consultarTodosProdutos(){
		
		return produtoDao.consultarTodos();
	}
	
	public ProdutoVendaVO consultarProdutoPorId(Long id){
		
		return produtoDao.consultarPorId(id);
	}
	

}
