package ui;

import javax.swing.JOptionPane;

import ui.templates.ManterFrameView;
import vo.FuncionarioVO;
import vo.GenericVO;
import enumeradores.TipoSolicitacao;

public class ManterFuncionarioView extends ManterFrameView<FuncionarioVO> {

	protected ManterFuncionarioView(TipoSolicitacao solicitacao, String tituloCabecalho) {
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
		return true;
	}

	@Override
	protected boolean habilitarCampos() {

		return false;
	
	}

	@Override
	protected void limparCampos() {
		
		
	}

}
