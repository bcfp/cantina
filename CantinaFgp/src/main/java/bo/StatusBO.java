package bo;

import java.util.List;

import vo.StatusVO;
import daoimpl.StatusDAO;
import daoservice.IStatusDAO;
import enumeradores.TipoStatus;

public class StatusBO {
	
	private IStatusDAO statusDao;
	
	{
		
		statusDao = new StatusDAO();
	
	}
	
	public List<StatusVO> consultarTodosStatus(TipoStatus tipo){
		
		return statusDao.consultar(tipo);
		
	}

}
