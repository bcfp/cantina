package interfaces;

import vo.GenericVO;

public interface ITelaCadastrar<T extends GenericVO> {

	void abrirJanela();
	boolean cadastrar(T item);
	
}
