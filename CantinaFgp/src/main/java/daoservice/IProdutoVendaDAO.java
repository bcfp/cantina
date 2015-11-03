package daoservice;

import vo.ProdutoVendaVO;

public interface IProdutoVendaDAO extends IProdutoDAO<ProdutoVendaVO> {

	boolean isQtdeMateriaPrima(ProdutoVendaVO produto);
	
}
