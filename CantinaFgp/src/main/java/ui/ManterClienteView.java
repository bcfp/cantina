package ui;

import javax.swing.JOptionPane;

import enumeradores.TipoManter;
import interfaces.ITelaConsultar;
import vo.ClienteVO;

public class ManterClienteView extends ManterDialogView<ClienteVO> {


	protected ManterClienteView(TipoManter solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}

	@Override
	public void abrirJanela(ClienteVO objeto) {
	
		this.setVisible(true);
		
	}	
	
	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}


	@Override
	public boolean incluir(ClienteVO cliente) {
		
		return false;
	}
	
	@Override
	public boolean alterar(ClienteVO cliente) {
		
		
		return false;
	}

	@Override
	protected boolean btnIncluir() {
		ClienteVO c = new ClienteVO();
		
		return alterar(c);
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

		JOptionPane.showMessageDialog(null, "Cliente Alterado");
		return false;
	}
	
}
