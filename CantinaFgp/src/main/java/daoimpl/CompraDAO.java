package daoimpl;

import java.util.List;

import utils.BancoFake;
import vo.CompraVO;
import daoservice.ICompraDAO;

public class CompraDAO implements ICompraDAO {

	@Override
	public CompraVO incluir(CompraVO compra) {
		
		// TODO - Fazer conexão com o banco
		
		CompraVO compraIncluida = new CompraVO();
		
		compraIncluida.setCodCompra("00001");
		
		return compraIncluida;
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
