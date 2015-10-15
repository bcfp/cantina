package ui;

import javax.swing.JOptionPane;

import vo.CantinaVO;
import enumeradores.TipoManter;

public class ManterCantinaView extends ManterDialogView<CantinaVO> {
	
	protected ManterCantinaView(TipoManter solicitacao, String tituloCabecalho) {
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
		return false;
	}

	@Override
	protected void limparCampos() {
		
		
	}

	@Override
	protected boolean habilitarCampos() {
		return false;
	}



}
