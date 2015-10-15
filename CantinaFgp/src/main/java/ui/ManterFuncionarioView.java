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
	public boolean incluir() {
		JOptionPane.showMessageDialog(null, "Funcionario Incluído");
		return false;
	}

	@Override
	public boolean alterar() {
		JOptionPane.showMessageDialog(null, "Funcionario Alterado");	
		return false;
	}

	@Override
	protected boolean habilitarCampos() {

		return false;
	
	}

	@Override
	protected void limparCampos() {
		
		
	}	

}
