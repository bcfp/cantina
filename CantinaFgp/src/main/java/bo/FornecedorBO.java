package bo;

import java.util.List;

import vo.FornecedorVO;
import daoimpl.FornecedorDAO;

public class FornecedorBO {
	
	private FornecedorDAO fornDao;
	
	{
		fornDao = new FornecedorDAO();
	}
	
	public List<FornecedorVO>consultar(){
		
		
		return fornDao.consultar();
		
	}

}
