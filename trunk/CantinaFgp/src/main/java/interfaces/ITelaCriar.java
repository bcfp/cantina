package interfaces;

import vo.GenericVO;

public interface ITelaCriar<T extends GenericVO> {

	void abrirJanela();
	boolean criarItem(T item);
	
}
