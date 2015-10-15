package ui;

import javax.swing.JOptionPane;

import vo.FuncionarioVO;
import enumeradores.TipoManter;

public class ManterFuncionarioView extends ManterDialogView<FuncionarioVO> {

	protected ManterFuncionarioView(TipoManter solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}

	@Override
	public void abrirJanela(FuncionarioVO objeto) {

		this.setVisible(true);
		
	}

	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}

	@Override
	public boolean incluir(FuncionarioVO item) {
		
		return false;
	}

	@Override
	public boolean alterar(FuncionarioVO item) {
		
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
	protected boolean habilitarCampos() {

		JOptionPane.showMessageDialog(null, "Funcionario Alterado");
		return false;
	
	}

	@Override
	protected void limparCampos() {
		
		
	}	

}
