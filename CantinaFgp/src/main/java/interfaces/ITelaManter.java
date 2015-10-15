package interfaces;

import vo.GenericVO;

public interface ITelaManter<T extends GenericVO> {

	void abrirJanela();
	void abrirJanela(T objeto);
	boolean cadastrar(T objeto);
	
}
