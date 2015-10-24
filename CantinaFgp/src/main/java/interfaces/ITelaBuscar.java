package interfaces;

import java.util.List;
import java.util.Map;

import vo.GenericVO;

public interface ITelaBuscar {

	List<GenericVO> pesquisarItem(Map<String, String> parametros);
	void carregarItemSelecionado(GenericVO item);
	String[] carregarGridTelaBusca(GenericVO item);
	
}
