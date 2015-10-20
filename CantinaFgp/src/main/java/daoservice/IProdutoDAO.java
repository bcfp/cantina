package daoservice;

import java.util.List;

import vo.ProdutoVO;
import vo.ProdutoVendaVO;

public interface IProdutoDAO<T extends ProdutoVO> extends IDAO<T> {

	List<ProdutoVendaVO> consultarPorNomeECodigo(String nome, String cod);

	
}
