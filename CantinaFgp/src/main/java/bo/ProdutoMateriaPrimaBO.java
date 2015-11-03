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
	
	public List<ProdutoMateriaPrimaVO> buscaReceitaPorIdProduto(Long idProduto){
		
		return receitaDao.consultarReceitaPorIdProduto(idProduto);
	}
}
