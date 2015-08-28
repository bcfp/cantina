package interfaces;

import vo.GenericVO;

public interface ITelaDetalhar<T extends GenericVO> {
	
	void abrirJanela(T objeto);

}
