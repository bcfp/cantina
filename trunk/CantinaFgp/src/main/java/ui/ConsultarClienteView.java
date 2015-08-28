package ui;

import interfaces.ITelaConsultar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.ClienteVO;

public class ConsultarClienteView extends ConsultarPanelView<ClienteVO> implements ITelaConsultar<ClienteVO>{

	private JLabel lblTest;
	
	public ConsultarClienteView() {
		super(new String[]{
			"Código",
			"Nome"
		}, BancoFake.listaClientes, 50, 100, 400, 200);
		abrirJanela();
	}
	
	public void abrirJanela(){
		
		setLayout(null);
		
		lblTest = new JLabel("Cliente");
		lblTest.setBounds(140, 70, 140, 140);
		
		add(lblTest);
		
	}

	@Override
	public void alterar() {
		
		JOptionPane.showMessageDialog(null, "Alterar Cliente");
		
	}

	@Override
	public void deletar(ClienteVO cliente) {
		
		JOptionPane.showMessageDialog(null, "Deletar Cliente");
		
	}

	@Override
	protected void mouseClickedTab() {
		
		getTabGeneric().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(getTabGeneric().getSelectedRow() != -1){ // acerto ref. clique com botão direito
				
					ClienteVO cliente = BancoFake.listaClientes.get(getTabGeneric().getSelectedRow());
									
					new DialogConfirmacaoView<ClienteVO>().abrirJanela( cliente, 
																	  ConsultarClienteView.this,
																	  new DetalharClienteView() );

				}
				
			}
			
		});
						
	}

	@Override
	protected void carregarGridItens(List<ClienteVO> listaClientes) {

		getModeloTabGeneric().setNumRows(0); // funciona para zerar o q tinha antes
		
		Iterator<ClienteVO> iCliente = listaClientes.iterator();
		
		while(iCliente.hasNext()){
			
			ClienteVO cliente = (ClienteVO) iCliente.next();
			
			String[] registro = new String[2];

			registro[0] = cliente.getCodPessoa();
			registro[1] = cliente.getNome();
			
			getModeloTabGeneric().addRow(registro);	
			
		}
		
	}

	@Override
	protected void getTelaNovo() {
		
		JOptionPane.showMessageDialog(null, "Novo Cliente");
		
	}
	
}