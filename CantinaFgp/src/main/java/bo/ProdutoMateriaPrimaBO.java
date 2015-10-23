package bo;

import java.util.List;

import vo.ProdutoMateriaPrimaVO;
import daoimpl.ProdutoMateriaPrimaDAO;

public class ProdutoMateriaPrimaBO {
	
	private ProdutoMateriaPrimaDAO receitaDao;

	
	{
		receitaDao = new ProdutoMateriaPrimaDAO();
	
	}
	
	public List<ProdutoMateriaPrimaVO> buscaReceitaPorIdProduto(Long idProduto){
		
		return receitaDao.consultarReceitaPorIdProduto(idProduto);
	}
}
