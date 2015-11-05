package bo;

import java.util.List;

import vo.StatusVO;
import daoimpl.StatusDAO;
import daoservice.IStatusDAO;

public class StatusBO {
	
	private IStatusDAO statusDao;
	
	{
		
		statusDao = new StatusDAO();
	
	}
	
	public List<StatusVO> consultarTodosStatus(){
		
		return statusDao.consultarTodos();
		
	}

}
