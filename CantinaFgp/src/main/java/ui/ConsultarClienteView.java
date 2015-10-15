package ui;

import interfaces.ITelaConsultar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import enumeradores.TipoSolicitacao;
import utils.BancoFake;
import vo.ClienteVO;

public class ConsultarClienteView extends ConsultarPanelView<ClienteVO> implements ITelaConsultar<ClienteVO>{

	public ConsultarClienteView() {
		super("Cliente", 
			new String[]{
				"Código",
				"Nome"
			}, 
			BancoFake.listaClientes, 50, 100, 400, 200);
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
																	  new ManterClienteView(TipoSolicitacao.DETALHAR, "Detalhar Cliente"));

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
		
		new ManterClienteView(TipoSolicitacao.INCLUIR, "Cadastrar Cliente").abrirJanela();
		
	}
	
}