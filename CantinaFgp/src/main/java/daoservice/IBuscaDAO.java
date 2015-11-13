package daoservice;

import java.util.List;

import vo.GenericVO;

public interface IBuscaDAO<T extends GenericVO> {

	List<T> consultarPorCodigoENome(String cod, String nome);
	
}
