package ui;

import interfaces.ITelaManter;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.CompraVO;
import enumeradores.TipoSolicitacao;

public class ConsultarCompraView extends ConsultarPanelView<CompraVO> {

	public ConsultarCompraView() {
		super("Compra",
			new String[]{
				"Código",
				"Data"
			}, 
			BancoFake.listaCompras, 50, 100, 400, 200);
	}

	@Override
	public void deletar(CompraVO compra) {

		JOptionPane.showMessageDialog(null, "Deletar Compra");
		
	}

	@Override
	protected ITelaManter<CompraVO> getTelaDetalhar() {
		return new ManterCompraView(TipoSolicitacao.DETALHAR, "Detalhar Compra");
	}
	

	@Override
	protected String[] carregarGridItens(CompraVO compra) {
		
		String[] registro = new String[2];

		registro[0] = compra.getCodCompra();
		registro[1] = compra.getData().toString();
		
		return registro;
		
	}


	@Override
	protected void getTelaNovo() {

		new ManterCompraView(TipoSolicitacao.INCLUIR, "Cadastrar Compra").abrirJanela();
		
	}	
}