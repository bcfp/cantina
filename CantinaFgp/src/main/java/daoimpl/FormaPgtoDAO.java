package daoimpl;

import java.util.List;

import utils.BancoFake;
import vo.FormaPgtoVO;
import daoservice.IFormaPgtoDAO;

public class FormaPgtoDAO implements IFormaPgtoDAO {

	@Override
	public boolean incluir(FormaPgtoVO formaPgto) {
		
		return true;
	}

	@Override
	public boolean alterar(FormaPgtoVO formaPgto) {
		
		return true;
	}

	@Override
	public boolean deletar(Long id) {
		
		return true;
	}

	@Override
	public List<FormaPgtoVO> consultar() {
		// TODO - Fazer conexão com o banco
		return BancoFake.listaFormasPgto;
		
	}

	@Override
	public FormaPgtoVO consultarPorId(Long id) {
		
		return null;
	}

}
