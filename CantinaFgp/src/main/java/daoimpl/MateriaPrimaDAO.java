package daoimpl;

import java.util.List;

import vo.MateriaPrimaVO;
import daoservice.IMateriaPrimaDAO;

public class MateriaPrimaDAO implements IMateriaPrimaDAO {

	@Override
	public MateriaPrimaVO incluir(MateriaPrimaVO materiaPrima) {
		System.out.println("incluiu matéria prima");
		return null;
	}

	@Override
	public boolean alterar(MateriaPrimaVO materiaPrima) {
		
		return false;
	}

	@Override
	public boolean deletar(Long id) {
		
		return false;
	}

	@Override
	public List<MateriaPrimaVO> consultar() {
		
		return null;
	}

	@Override
	public MateriaPrimaVO consultarPorId(Long id) {
		
		return null;
	}

	@Override
	public List<MateriaPrimaVO> consultarPorCodigoENome(String nome, String cod) {
		return null;
	}

}
