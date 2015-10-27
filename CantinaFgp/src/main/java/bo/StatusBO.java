package bo;

import java.util.List;

import vo.StatusVO;
import daoimpl.StatusDAO;

public class StatusBO {
	
	private StatusDAO statusDao;
	
	{
		
		statusDao = new StatusDAO();
	
	}
	
	public List<StatusVO> consultarTodosStatus(){
		
		return statusDao.consultarTodos();
		
	}

}
