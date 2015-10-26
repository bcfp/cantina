package bo;

import java.util.List;

import vo.FuncionarioVO;
import daoimpl.FuncionarioDAO;

public class FuncionarioBO {

	private FuncionarioDAO funcionarioDao;
	
	{
		
		funcionarioDao = new FuncionarioDAO();
	}
	
	public List<FuncionarioVO> filtarFuncionariosPorNomeECodigo(String codigo, String nome){
		
		return funcionarioDao.filtarFuncionariosPorNomeECodigo(codigo, nome);
	}
	
}
