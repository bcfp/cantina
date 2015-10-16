package ui;

import interfaces.ITelaManter;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.VendaVO;
import enumeradores.TipoSolicitacao;

public class ConsultarVendaView extends ConsultarPanelView<VendaVO> {

	public ConsultarVendaView() {
		
		super(	
				"Venda", 
				new String[] {"Código",	"Data"}, 
				BancoFake.listaVendas, 
				50, 100, 400, 200
			 );

	}

	@Override
	protected String[] carregarGridItens(VendaVO venda) {
		
		String[] registro = new String[2];

		registro[0] = venda.getCodVenda();
		registro[1] = venda.getData().toString();
		
		return registro;
	}

	@Override
	protected ITelaManter<VendaVO> getTelaIncluir() {
		return new ManterVendaView(TipoSolicitacao.INCLUIR, "Cadastrar Venda");
	}
	
	@Override
	protected ITelaManter<VendaVO> getTelaAlterar() {
		return new ManterVendaView(TipoSolicitacao.DETALHAR, "Detalhar Venda");
	}

	@Override
	public void deletar(VendaVO venda) {

		JOptionPane.showMessageDialog(null, "Deletar Venda");
		
	}

}
