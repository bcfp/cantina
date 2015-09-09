package interfaces;

import vo.GenericVO;

public interface ITelaManter<T extends GenericVO> {

	void abrirJanela();
	void abrirJanela(T objeto, ITelaConsultar<T> tela);
	boolean cadastrar(T objeto);
	
}
