package ui;

import interfaces.ITelaManter;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.CantinaVO;
import enumeradores.TipoSolicitacao;

public class ConsultarCantinaView extends ConsultarPanelView<CantinaVO>{
	
	public ConsultarCantinaView() {
		super(
				"Cantina",
				new String[]{ "Código", "Cantina"}, 
				BancoFake.listaCantinas, 
				50, 100, 400, 200);
	}

	@Override
	protected String[] carregarGridItens(CantinaVO cantina) {
		
		String[] registro = new String[2];

		registro[0] = cantina.getIdCantina().toString();
		registro[1] = cantina.getNome();
		
		return registro;
	}

	@Override
	protected ITelaManter<CantinaVO> getTelaIncluir() {
		return new ManterCantinaView(TipoSolicitacao.INCLUIR, "Cadastrar Cantina");
	}
	
	@Override
	protected ITelaManter<CantinaVO> getTelaAlterar() {
		return new ManterCantinaView(TipoSolicitacao.DETALHAR, "Detalhar Cantina");
	}	

	@Override
	public void deletar(CantinaVO cantina) {

		JOptionPane.showMessageDialog(null, "Deletar Cantina");
		
	}
	
}