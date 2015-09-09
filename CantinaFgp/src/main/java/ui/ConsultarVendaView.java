package ui;

import interfaces.ITelaConsultar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.VendaVO;

public class ConsultarVendaView extends ConsultarPanelView<VendaVO> implements ITelaConsultar<VendaVO>{


	public ConsultarVendaView() {
		
		super("Venda",new String[] {

			"Código",
			"Data"

		}, BancoFake.listaVendas, 50, 100, 400, 200);

	}

	@Override
	protected void mouseClickedTab() {
		
		getTabGeneric().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(getTabGeneric().getSelectedRow() != -1){ // acerto ref. clique com botão direito
				
					VendaVO venda = BancoFake.listaVendas.get(getTabGeneric().getSelectedRow());
									
					new DialogConfirmacaoView<VendaVO>().abrirJanela( venda, 
																	  ConsultarVendaView.this,
																	  null );

				}
				
			}
			
		});
						
	}

	@Override
	protected void carregarGridItens(List<VendaVO> listaVendas) {

		getModeloTabGeneric().setNumRows(0); // funciona para zerar o q tinha antes
		
		Iterator<VendaVO> iVenda = listaVendas.iterator();
		
		while(iVenda.hasNext()){
			
			VendaVO venda = (VendaVO) iVenda.next();
			
			String[] registro = new String[2];

			registro[0] = venda.getCodVenda();
			registro[1] = venda.getData().toString();
			
			getModeloTabGeneric().addRow(registro);	
			
		}
		
	}


	@Override
	protected void getTelaNovo() {
		
		JOptionPane.showMessageDialog(null, "Nova Venda");
		
	}

	@Override
	public void deletar(VendaVO objeto) {

		JOptionPane.showMessageDialog(null, "Deletar Venda");
		
	}

}
