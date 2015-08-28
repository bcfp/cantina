package interfaces;

import vo.GenericVO;

public interface ITelaAlterar<T extends GenericVO> {
	
	void abrirJanela(T objeto, ITelaConsultar<T> tela);

}