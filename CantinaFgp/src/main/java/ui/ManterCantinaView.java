package ui;

import javax.swing.JOptionPane;

import vo.CantinaVO;
import vo.GenericVO;
import enumeradores.TipoSolicitacao;

public class ManterCantinaView extends ManterPanelView<CantinaVO> {
	
	protected ManterCantinaView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}

	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}
	
	@Override
	public void abrirJanela(CantinaVO cantina) {

		this.setVisible(true);
		
	}

	@Override
	public boolean incluir() {
		JOptionPane.showMessageDialog(null, "Cantina incluída");
		return false;
	}

	@Override
	public boolean alterar() {
		JOptionPane.showMessageDialog(null, "Cantina alterada");		
		return true;
	}

	@Override
	protected void limparCampos() {
		
		
	}

	@Override
	protected boolean habilitarCampos() {
		return false;
	}


}
