package bo;

import java.util.List;

import vo.FuncionarioCantinaVO;
import daoimpl.FuncionarioDAO;

public class FuncionarioBO {

	private FuncionarioDAO funcionarioDao;
	
	{
		
		funcionarioDao = new FuncionarioDAO();
	}
	
	public List<FuncionarioCantinaVO> filtarFuncionariosPorNomeECodigo(String codigo, String nome){
		
		return funcionarioDao.filtarFuncionariosPorNomeECodigo(codigo, nome);
	}
	
}
