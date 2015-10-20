package bo;

import vo.ProdutoMateriaPrimaVO;
import daoimpl.ReceitaDAO;

public class ReceitaBO {
	
	private ReceitaDAO receitaDao;

	
	{
		receitaDao = new ReceitaDAO();
	
	}
	
	public ProdutoMateriaPrimaVO buscaReceitaPorIdProduto(Long idProduto){
		
		return receitaDao.consultarPorIdProduto(idProduto);
	}
}
