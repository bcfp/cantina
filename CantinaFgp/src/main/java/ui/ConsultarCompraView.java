package ui;

import java.util.List;

import interfaces.ITelaManter;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.CompraVO;
import enumeradores.TipoSolicitacao;

public class ConsultarCompraView extends ConsultarPanelView<CompraVO> {

	public ConsultarCompraView() {
		super("Compra", new String[]{ "Código", "Data"});
	}	

	@Override
	protected String[] definirGridItens(CompraVO compra) {
		
		String[] registro = new String[2];

		registro[0] = compra.getCodCompra();
		registro[1] = compra.getData().toString();
		
		return registro;
		
	}

	@Override
	protected ITelaManter<CompraVO> getTelaIncluir() {
		return new ManterCompraView(TipoSolicitacao.INCLUIR, "Cadastrar Compra");
	}

	@Override
	protected ITelaManter<CompraVO> getTelaAlterar() {
		return new ManterCompraView(TipoSolicitacao.DETALHAR, "Detalhar Compra");
	}

	@Override
	public void deletar(CompraVO compra) {

		JOptionPane.showMessageDialog(null, "Deletar Compra");
		
	}

	@Override
	public List<CompraVO> consultar() {
		return BancoFake.listaCompras;
	}
	

}