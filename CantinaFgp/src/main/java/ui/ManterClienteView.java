package ui;

import javax.swing.JOptionPane;

import interfaces.ITelaConsultar;
import vo.ClienteVO;

public class ManterClienteView extends ManterDialogView<ClienteVO> {


	protected ManterClienteView(String tituloCabecalho) {
		super(tituloCabecalho);
	}

	@Override
	public void abrirJanela(ClienteVO objeto, ITelaConsultar<ClienteVO> tela) {
	
		this.setVisible(true);
		
	}	
	
	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}


	@Override
	public boolean cadastrar(ClienteVO item) {
		
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

		JOptionPane.showMessageDialog(null, "Cliente Alterado");
		return false;
	}
	
}
