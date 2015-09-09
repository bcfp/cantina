package interfaces;

import vo.GenericVO;

public interface ITelaManter<T extends GenericVO> {

	void abrirJanela();
	boolean cadastrar(T item);
	
}
