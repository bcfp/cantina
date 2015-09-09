package ui;

import interfaces.ITelaConsultar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.CantinaVO;

public class ConsultarCantinaView extends ConsultarPanelView<CantinaVO> implements ITelaConsultar<CantinaVO>{
	
	public ConsultarCantinaView() {
		super("Cantina",new String[]{
				"Código",
				"Nome"
		}, BancoFake.listaCantinas, 50, 100, 400, 200);
	}

	@Override
	public void alterar() {

		JOptionPane.showMessageDialog(null, "Alterar Cantina");
		
	}

	@Override
	public void deletar(CantinaVO cantina) {

		JOptionPane.showMessageDialog(null, "Deletar Cantina");
		
	}

	@Override
	protected void mouseClickedTab() {
		
		getTabGeneric().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(getTabGeneric().getSelectedRow() != -1){ // acerto ref. clique com botão direito
				
					CantinaVO cantina = BancoFake.listaCantinas.get(getTabGeneric().getSelectedRow());
									
					new DialogConfirmacaoView<CantinaVO>().abrirJanela( cantina, 
																	  ConsultarCantinaView.this,
																	  null );

				}
				
			}
			
		});
						
	}

	@Override
	protected void carregarGridItens(List<CantinaVO> listaCantinas) {

		getModeloTabGeneric().setNumRows(0); // funciona para zerar o q tinha antes
		
		Iterator<CantinaVO> iCantina = listaCantinas.iterator();
		
		while(iCantina.hasNext()){
			
			CantinaVO cantina = (CantinaVO) iCantina.next();
			
			String[] registro = new String[2];

			registro[0] = cantina.getIdCantina().toString();
			registro[1] = cantina.getNome();
			
			getModeloTabGeneric().addRow(registro);	
			
		}
		
	}

	@Override
	protected void getTelaNovo() {

		JOptionPane.showMessageDialog(null, "Nova Cantina");
		
	}
	
}