package bo;

import java.util.List;

import utils.UtilFuncoes;
import vo.ProdutoVendaVO;
import daoimpl.ProdutoVendaDAO;

public class ProdutoVendaBO {
	
	public ProdutoVendaDAO produtoDao;
	
	public Boolean verificaDescricaoVazio(String descricao){
		
		return UtilFuncoes.isVazio(descricao);
		
	}
	
	public List<ProdutoVendaVO> filtarPorDescricao(String descricao){
		
		return produtoDao.consultarPorDescricao(descricao);
		
	}
	

}
