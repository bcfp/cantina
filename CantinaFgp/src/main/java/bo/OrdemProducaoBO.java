package bo;

import java.util.List;

import vo.OrdemProducaoVO;
import daoimpl.OrdemProducaoDAO;
import daoimpl.ProdutoVendaDAO;

public class OrdemProducaoBO {
	
	private OrdemProducaoDAO ordemProd;
	private ProdutoVendaDAO prodVenda;
	
	{
		
		ordemProd = new OrdemProducaoDAO();
	}
	
	public List<OrdemProducaoVO> consultarListaEstatica(){
		
		return null;
	}

}
