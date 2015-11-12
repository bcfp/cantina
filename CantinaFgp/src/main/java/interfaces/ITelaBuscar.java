package interfaces;

import java.util.List;
import java.util.Map;

import vo.GenericVO;

public interface ITelaBuscar {

	List<GenericVO> buscarItem(Map<String, String> parametrosBusca);
	void carregarItemSelecionado(GenericVO item);
	String[] carregarGridTelaBusca(GenericVO item);
	
}
