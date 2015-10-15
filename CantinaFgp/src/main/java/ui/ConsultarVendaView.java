package ui;

import interfaces.ITelaManter;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.VendaVO;
import enumeradores.TipoSolicitacao;

public class ConsultarVendaView extends ConsultarPanelView<VendaVO> {

	public ConsultarVendaView() {
		
		super("Venda",new String[] {

			"Código",
			"Data"

		}, BancoFake.listaVendas, 50, 100, 400, 200);

	}
	
	@Override
	protected ITelaManter<VendaVO> getTelaDetalhar() {
		return new ManterVendaView(TipoSolicitacao.DETALHAR, "Detalhar Venda");
	}

	@Override
	protected String[] carregarGridItens(VendaVO venda) {
		
		String[] registro = new String[2];

		registro[0] = venda.getCodVenda();
		registro[1] = venda.getData().toString();
		
		return registro;
	}

	@Override
	protected void getTelaNovo() {
		
		new ManterVendaView(TipoSolicitacao.INCLUIR, "Gerar Venda").abrirJanela();
		
	}

	@Override
	public void deletar(VendaVO venda) {

		JOptionPane.showMessageDialog(null, "Deletar Venda");
		
	}

}
