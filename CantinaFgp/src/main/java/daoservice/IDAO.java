package daoservice;

import java.util.List;

import vo.GenericVO;

public interface IDAO<T extends GenericVO> {
	
	boolean incluir(T objeto);
	boolean alterar(T objeto);
	boolean excluir(Long id);
	List<T> consultarTodos();
	T consultarPorId(Long id);
	
	
}
