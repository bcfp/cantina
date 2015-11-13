package bo;

import java.util.List;

import vo.UnidadeProdutoVO;
import daoimpl.UnidadeProdutoDAO;
import daoservice.IUnidadeProdutoDAO;

public class UnidadeProdutoBO {

	private IUnidadeProdutoDAO unidadeDao;
	
	{
		unidadeDao = new UnidadeProdutoDAO();
	}
	
	public List<UnidadeProdutoVO> consultar(){
		
		return unidadeDao.consultar();
		
	}
	
}
