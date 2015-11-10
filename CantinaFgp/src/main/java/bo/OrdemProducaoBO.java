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
		
		return UtilFuncoes.stringToInteger(campo) <= 0;
	}
	
	public boolean isCampoQuantidadeNumerico(String campo){
		
		return UtilFuncoes.isCampoNumerico(campo);
	}
	
	public Integer stringToInteger(String campo){
		return UtilFuncoes.stringToInteger(campo);
	}

	// CRUD
	
	public OrdemProducaoVO incluir(OrdemProducaoVO ordemProducao){
		
		return ordemProdDao.incluir(ordemProducao);
		
	}
	
	public List<OrdemProducaoVO> consultar(){
		
		return ordemProdDao.consultar();
		
	}
		
	public boolean alterar(OrdemProducaoVO ordemProducao){
		
		return ordemProdDao.alterar(ordemProducao);
		
	}
	
	public boolean deletar(OrdemProducaoVO ordemProducao){
		
		OrdemProducaoVO op = ordemProducao;
		
		return ordemProdDao.deletar(op.getIdOrdemProducao());
		
	}

}
