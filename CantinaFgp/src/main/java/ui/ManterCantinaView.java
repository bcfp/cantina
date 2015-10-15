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
	public boolean incluir(CantinaVO cantina) {
		
		JOptionPane.showMessageDialog(null, "Cantina incluida");
		
		return false;
	}
	
	@Override
	public boolean alterar(CantinaVO cantina) {
		
		JOptionPane.showMessageDialog(null, "Cantina alterada");
		
		return false;
	}

	@Override
	protected boolean btnIncluir() {
		
		return false;
	}

	@Override
	protected boolean btnAlterar() {
		
		return false;
	}

	@Override
	protected void limparCampos() {
		
		
	}

	@Override
	protected boolean habilitarCampos() {
		
		JOptionPane.showMessageDialog(null, "Cantina Alterada");
		
		return false;
	}



}
