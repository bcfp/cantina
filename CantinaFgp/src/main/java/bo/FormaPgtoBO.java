package bo;

import java.util.List;

import vo.FormaPgtoVO;
import daoimpl.FormaPgtoDAO;
import daoservice.IFormaPgtoDAO;

public class FormaPgtoBO {
	
	private IFormaPgtoDAO formaPgtoDao;
	
	{
		
		formaPgtoDao = new FormaPgtoDAO();
	
	}
	
	public List<FormaPgtoVO> consultarTodasFormaPgto(){
		
		return formaPgtoDao.consultar();
		
	}
	
}
