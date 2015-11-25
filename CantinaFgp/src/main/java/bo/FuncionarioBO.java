package bo;

import java.util.List;

import vo.FuncionarioCantinaVO;
import daoimpl.FuncionarioCantinaDAO;
import daoservice.IFuncionarioCantinaDAO;

public class FuncionarioBO {

	private IFuncionarioCantinaDAO funcionarioCantinaDao;
	
	{
		
		funcionarioCantinaDao = new FuncionarioCantinaDAO();
		
	}
	
	public List<FuncionarioCantinaVO> buscarFuncionariosPorCodigoENome(String codigo, String nome){
		
		return funcionarioCantinaDao.buscarPorCodigoENome(codigo, nome);
		
	}
	
	public FuncionarioCantinaVO consultarPorId(Long id){
		
		return funcionarioCantinaDao.consultarPorId(id);
		
	}
	
}
