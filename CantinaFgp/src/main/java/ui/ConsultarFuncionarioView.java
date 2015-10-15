package ui;

import interfaces.ITelaManter;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.FuncionarioVO;
import vo.GenericVO;
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
	protected GenericVO getItemDetalhar() {
		return BancoFake.listaFuncionarios.get(getTabGeneric().getSelectedRow());
	}

	@Override
	protected ITelaManter<FuncionarioVO> getTelaDetalhar() {
		return new ManterFuncionarioView(TipoSolicitacao.DETALHAR, "Detalhar Funcionário");
	}

	@Override
	protected void carregarGridItens(List<FuncionarioVO> listaFuncionarios) {

		getModeloTabGeneric().setNumRows(0); // funciona para zerar o q tinha antes
		
		Iterator<FuncionarioVO> iFuncionario = listaFuncionarios.iterator();
		
		while(iFuncionario.hasNext()){
			
			FuncionarioVO funcionario = (FuncionarioVO) iFuncionario.next();
			
			String[] registro = new String[2];

			registro[0] = funcionario.getCodPessoa();
			registro[1] = funcionario.getNome();
			
			getModeloTabGeneric().addRow(registro);	
			
		}
		
	}

	@Override
	protected void getTelaNovo() {

		new ManterFuncionarioView(TipoSolicitacao.INCLUIR, "Cadastrar Funcionário");
		
	}
	
}