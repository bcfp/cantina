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
		
		return UtilFuncoes.isVazio(descricao);
		
	}
	
	public List<ProdutoVendaVO> filtarPorNomeECodigo(String nome, String cod){
		
		return produtoDao.consultarPorNomeECodigo(nome, cod);
		
	}
	

}
