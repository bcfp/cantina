package bo;

import java.util.List;

import vo.ProdutoMateriaPrimaVO;
import daoimpl.ProdutoMateriaPrimaDAO;
import daoservice.IProdutoMateriaPrimaDAO;

public class ProdutoMateriaPrimaBO {
	
	private IProdutoMateriaPrimaDAO receitaDao;

	
	{
		receitaDao = new ProdutoMateriaPrimaDAO();
	
	}
	
	
	public List<ProdutoMateriaPrimaVO> consultarReceitaPorIdProduto(Long idProduto){
		return receitaDao.consultarReceitaPorIdProduto(idProduto);
		
	}
	
	
}
