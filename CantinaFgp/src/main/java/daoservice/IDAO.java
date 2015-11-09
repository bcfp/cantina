package daoservice;

import java.util.List;

import vo.GenericVO;

public interface IDAO<T extends GenericVO> {
	
	boolean incluir(T objeto);
	boolean alterar(T objeto);
	boolean deletar(Long id);
	List<T> consultar();
	T consultarPorId(Long id);
	
	
}
