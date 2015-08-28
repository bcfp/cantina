package ui;

import interfaces.ITelaConsultar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.FuncionarioVO;

public class ConsultarFuncionarioView extends ConsultarPanelView<FuncionarioVO> implements ITelaConsultar<FuncionarioVO>{

	private JLabel lblTest;
	
	public ConsultarFuncionarioView() {
		super(new String[]{
				"Codigo",
				"Nome"
		}, BancoFake.listaFuncionarios, 50, 100, 400, 200);
		abrirJanela();
	}
	
	public void abrirJanela(){
		
		setLayout(null);
		
		lblTest = new JLabel("Funcionario");
		lblTest.setBounds(140, 70, 140, 140);
		
		add(lblTest);
		
	}

	@Override
	public void alterar() {

		JOptionPane.showMessageDialog(null, "Alterar Funcionario");
		
	}

	@Override
	public void deletar(FuncionarioVO funcionario) {

		JOptionPane.showMessageDialog(null, "Deletar Funcionario");
		
	}

	@Override
	protected void mouseClickedTab() {
		
		getTabGeneric().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(getTabGeneric().getSelectedRow() != -1){ // acerto ref. clique com botão direito
				
					FuncionarioVO funcionario = BancoFake.listaFuncionarios.get(getTabGeneric().getSelectedRow());
									
					new DialogConfirmacaoView<FuncionarioVO>().abrirJanela( funcionario, 
																	  ConsultarFuncionarioView.this,
																	  new DetalharFuncionarioView() );

				}
				
			}
			
		});
						
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

		JOptionPane.showMessageDialog(null, "Novo Funcionario");
		
	}
	
}