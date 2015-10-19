package bo;

import vo.ReceitaVO;
import daoimpl.ReceitaDAO;

public class ReceitaBO {
	
	private ReceitaDAO receitaDao;

	
	{
		receitaDao = new ReceitaDAO();
	
	}
	
	public ReceitaVO buscaReceitaPorIdProduto(Long idProduto){
		
		return receitaDao.consultarPorIdProduto(idProduto);
	}
}
