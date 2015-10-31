package daoservice;

import vo.ProdutoVO;

public interface IProdutoDAO<T extends ProdutoVO> extends IDAO<T>, IBuscarDAO<T>{


	
}
