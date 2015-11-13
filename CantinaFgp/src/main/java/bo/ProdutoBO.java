package bo;

import java.util.List;

import utils.UtilFuncoes;
import vo.ProdutoVO;
import daoservice.IProdutoDAO;

public abstract class ProdutoBO<T extends ProdutoVO, D extends IProdutoDAO<T>> {
	
	private D produtoDao;
	
	public ProdutoBO(D produtoDao) {
		this.produtoDao = produtoDao;
	}
	
	public T incluir(T produto){
		
		return produtoDao.incluir(produto);
		
	}

	public Boolean verificaDescricaoVazio(String descricao){
		
		return UtilFuncoes.isCampoVazio(descricao);
		
	}
	
	public List<T> filtrarProdutoVendaPorCodigoENome(String nome, String cod){
		
		return produtoDao.consultarPorCodigoENome(nome, cod);
		
	}
	
	public List<T> consultarTodosProdutos(){
		
		return produtoDao.consultar();
	}
	
	public T consultarProdutoPorId(Long id){
		
		return (T) produtoDao.consultarPorId(id);
	}
	
}
