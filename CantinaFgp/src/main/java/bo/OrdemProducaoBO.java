package bo;

import java.util.List;

import utils.UtilFuncoes;
import vo.OrdemProducaoVO;
import daoimpl.OrdemProducaoDAO;
import daoservice.IOrdemProducaoDAO;

public class OrdemProducaoBO {
	
	private IOrdemProducaoDAO ordemProdDao;
	
	{
		
		ordemProdDao = new OrdemProducaoDAO();
		
	}
	
	public List<OrdemProducaoVO> consultarListaEstatica(){
		
		return null;
	}
	
	public boolean isCampoFuncionarioVazio(String campo){
		
		return UtilFuncoes.isCampoVazio(campo);
	}
	
	public boolean isCampoCodigoFuncionarioVazio(String campo){
		
		return UtilFuncoes.isCampoVazio(campo);
	}
	
	public boolean isCampoProdutoVazio(String campo){
		
		return UtilFuncoes.isCampoVazio(campo);
	}
	
	public boolean isCampoCodigoProdutoVazio(String campo){
		return UtilFuncoes.isCampoVazio(campo);
	}
	
	public boolean isCampoQtdVazio(String campo){
		
		return UtilFuncoes.isCampoVazio(campo);
	}
	
	public boolean isCampoQtdNegativo(String campo){
		
		return Integer.parseInt(campo) <= 0;
	}

	public boolean incluirOrdemProducao(OrdemProducaoVO ordemProducao){
		
		return ordemProdDao.incluir(ordemProducao);
		
	}
		
	public boolean alterarOrdemProducao(OrdemProducaoVO ordemProducao){
		
		return ordemProdDao.alterar(ordemProducao);
		
	}
	
	public boolean isCampoQuantidadeNumerico(String campo){
		
		return UtilFuncoes.isCampoNumerico(campo);
	}
	

}
