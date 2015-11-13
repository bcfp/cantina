package bo;

import vo.ProdutoVendaVO;
import daoimpl.ProdutoVendaDAO;
import daoservice.IProdutoVendaDAO;

public class ProdutoVendaBO extends ProdutoBO<ProdutoVendaVO, IProdutoVendaDAO> {
	
	public static IProdutoVendaDAO produtoVendaDao;
	
	static{
		produtoVendaDao = new ProdutoVendaDAO();
	}

	public ProdutoVendaBO() {
		super(produtoVendaDao);
	}
	

}
