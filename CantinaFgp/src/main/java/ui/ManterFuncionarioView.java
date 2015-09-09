package ui;

import javax.swing.JOptionPane;

import interfaces.ITelaConsultar;
import vo.FuncionarioVO;

public class ManterFuncionarioView extends ManterDialogView<FuncionarioVO> {

	protected ManterFuncionarioView(String tituloCabecalho) {
		super(tituloCabecalho);
	}

	@Override
	public void abrirJanela(FuncionarioVO objeto, ITelaConsultar<FuncionarioVO> tela) {

		this.setVisible(true);
		
	}

	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}

	@Override
	public boolean cadastrar(FuncionarioVO item) {
		
		return false;
	}

	@Override
	protected boolean gravar() {
		
		return false;
	}

	@Override
	protected boolean alterar() {

		JOptionPane.showMessageDialog(null, "Funcionario Alterado");
		return false;
	
	}

	@Override
	protected void limparCampos() {
		
		
	}	

}
