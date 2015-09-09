package interfaces;

import vo.GenericVO;

public interface ITelaConsultar<T extends GenericVO> {
	
	void deletar(T objeto);
	
}
