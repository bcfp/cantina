package daoservice;

import java.util.List;

import vo.GenericVO;

public interface IBuscarDAO<T extends GenericVO> {

	List<T> consultarPorNomeECodigo(String nome, String cod);
	
}