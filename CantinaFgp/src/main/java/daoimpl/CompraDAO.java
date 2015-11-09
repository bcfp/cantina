package daoimpl;

import java.util.List;

import utils.BancoFake;
import vo.CompraVO;
import daoservice.ICompraDAO;

public class CompraDAO implements ICompraDAO {

	@Override
	public boolean incluir(CompraVO compra) {
		
		return true;
	}

	@Override
	public boolean alterar(CompraVO compra) {
		
		return true;
	}

	@Override
	public boolean deletar(Long id) {
		
		return true;
	}

	@Override
	public List<CompraVO> consultar() {
		// TODO - Fazer conexão com o banco
		return BancoFake.listaCompras;
	}

	@Override
	public CompraVO consultarPorId(Long id) {
		
		return null;
	}

}
