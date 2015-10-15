package ui;

import javax.swing.JOptionPane;

import interfaces.ITelaConsultar;
import vo.CantinaVO;

public class ManterCantinaView extends ManterDialogView<CantinaVO> {

	protected ManterCantinaView(String tituloCabecalho) {
		super(tituloCabecalho);
	}

	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}
	
	@Override
	public void abrirJanela(CantinaVO objeto) {
		
		this.setVisible(true);
		
	}
	
	@Override
	public boolean cadastrar(CantinaVO item) {
		
		return false;
	}

	@Override
	protected boolean gravar() {
		
		return false;
	}

	@Override
	protected void limparCampos() {
		
		
	}

	@Override
	protected boolean alterar() {
		
		JOptionPane.showMessageDialog(null, "Cantina Alterada");
		
		return false;
	}



}
