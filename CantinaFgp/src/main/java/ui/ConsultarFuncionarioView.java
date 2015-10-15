package ui;

import interfaces.ITelaManter;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.FuncionarioVO;
import enumeradores.TipoSolicitacao;

public class ConsultarFuncionarioView extends ConsultarPanelView<FuncionarioVO> {
	
	public ConsultarFuncionarioView() {
		super("Funcionário",
				new String[]{
				"Codigo",
				"Nome"
		}, 
		BancoFake.listaFuncionarios, 50, 100, 400, 200);
	}

	@Override
	public void deletar(FuncionarioVO funcionario) {

		JOptionPane.showMessageDialog(null, "Deletar Funcionario");
		
	}

	@Override
	protected ITelaManter<FuncionarioVO> getTelaDetalhar() {
		return new ManterFuncionarioView(TipoSolicitacao.DETALHAR, "Detalhar Funcionário");
	}

	@Override
	protected String[] carregarGridItens(FuncionarioVO funcionario) {
			
			String[] registro = new String[2];

			registro[0] = funcionario.getCodPessoa();
			registro[1] = funcionario.getNome();
			
			return registro;
		
	}

	@Override
	protected void getTelaNovo() {

		new ManterFuncionarioView(TipoSolicitacao.INCLUIR, "Cadastrar Funcionário");
		
	}
	
}