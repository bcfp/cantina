package interfaces;

import java.util.List;
import java.util.Map;

import vo.GenericVO;

public interface ITelaBuscar {

	List<GenericVO> pesquisar(Map<String, String> parametros);
	void carregarItemSelecionado(GenericVO objeto);
	String[] definirGridBusca(GenericVO item);
	
}
