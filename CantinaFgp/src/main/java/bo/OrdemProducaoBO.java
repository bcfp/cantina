package bo;

import java.util.List;

import utils.UtilFuncoes;
import vo.OrdemProducaoVO;
import daoimpl.OrdemProducaoDAO;
import daoservice.IOrdemProducaoDAO;
import daoservice.IProdutoDAO;

public class OrdemProducaoBO {
	
	private IOrdemProducaoDAO ordemProd;
	private IProdutoDAO prodVenda;
	
	{
		
		ordemProd = new OrdemProducaoDAO();
		
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

	public boolean inserirOrdemProducao(OrdemProducaoVO ordemProducao){
		
		return ordemProd.inserir(ordemProducao);
		
	}
	
	public boolean alterarOrdemProducao(OrdemProducaoVO ordemProducao){
		
		return ordemProd.alterar(ordemProducao);
		
	}
	
	public boolean isCampoQuantidadeNumerico(String campo){
		
		return UtilFuncoes.isCampoNumerico(campo);
	}
	

}
