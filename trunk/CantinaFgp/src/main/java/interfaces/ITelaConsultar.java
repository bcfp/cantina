package interfaces;

import vo.GenericVO;

public interface ITelaConsultar<T extends GenericVO> {

	void alterar();
	void deletar(T objeto);
	
}
