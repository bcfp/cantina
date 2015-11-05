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
	
	public List<FuncionarioCantinaVO> filtarFuncionariosPorNomeECodigo(String codigo, String nome){
		
		return funcionarioCantinaDao.consultarPorNomeECodigo(codigo, nome);
		
	}
	
}
