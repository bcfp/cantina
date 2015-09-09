package ui;

import interfaces.ITelaConsultar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.CompraVO;

public class ConsultarCompraView extends ConsultarPanelView<CompraVO> implements ITelaConsultar<CompraVO>{

	public ConsultarCompraView() {
		super("Compra",
			new String[]{
				"Código",
				"Data"
			}, 
			BancoFake.listaCompras, 50, 100, 400, 200);
	}

	@Override
	public void deletar(CompraVO compra) {

		JOptionPane.showMessageDialog(null, "Deletar Compra");
		
	}

	@Override
	protected void mouseClickedTab() {
		
		getTabGeneric().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(getTabGeneric().getSelectedRow() != -1){ // acerto ref. clique com botão direito
				
					CompraVO compra = BancoFake.listaCompras.get(getTabGeneric().getSelectedRow());
									
					new DialogConfirmacaoView<CompraVO>().abrirJanela( compra, 
																	  ConsultarCompraView.this,
																	  new ManterCompraView("Detalhar Compra"));

				}
				
			}
			
		});
						
	}

	@Override
	protected void carregarGridItens(List<CompraVO> listaCompras) {

		getModeloTabGeneric().setNumRows(0); // funciona para zerar o q tinha antes
		
		Iterator<CompraVO> iCompra = listaCompras.iterator();
		
		while(iCompra.hasNext()){
			
			CompraVO compra = (CompraVO) iCompra.next();
			
			String[] registro = new String[2];

			registro[0] = compra.getCodCompra();
			registro[1] = compra.getData().toString();
			
			getModeloTabGeneric().addRow(registro);	
			
		}
		
	}

	@Override
	protected void getTelaNovo() {

		new ManterCompraView("Cadastrar Compra").abrirJanela();
		
	}


	
}