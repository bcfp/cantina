package daoservice;

import java.util.List;

import vo.ProdutoVO;

public interface IProdutoDAO<T extends ProdutoVO> extends IDAO<T> {

	List<T> consultarPorDescricao(String descricao);
	
}
