package bo;

import vo.ProdutoMateriaPrimaVO;
import daoimpl.ProdutoMateriaPrimaDAO;

public class ProdutoMateriaPrimaBO {
	
	private ProdutoMateriaPrimaDAO receitaDao;

	
	{
		receitaDao = new ProdutoMateriaPrimaDAO();
	
	}
	
	public ProdutoMateriaPrimaVO buscaReceitaPorIdProduto(Long idProduto){
		
		return receitaDao.consultarPorIdProduto(idProduto);
	}
}
