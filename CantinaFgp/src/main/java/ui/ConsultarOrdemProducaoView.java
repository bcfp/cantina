package ui;

import interfaces.ITelaConsultar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.OrdemProducaoVO;

public class ConsultarOrdemProducaoView extends ConsultarPanelView<OrdemProducaoVO> implements ITelaConsultar<OrdemProducaoVO> {

	public ConsultarOrdemProducaoView() {
		super("Ordem de Produção",
				new String[]{
				"Código",
				"Data",
				"Produto",
				"Qtde",
				"Status"
			}, BancoFake.listaOrdensProducao, 50, 100, 400, 200);
		
		abrirTela();

	}
	
	public void abrirTela(){
		
		JButton b = new JButton();
		b.setText("Teste");

		adicionarBotao(b);
		
		
		
	}


	@Override
	protected void getTelaNovo() {

		new ManterOrdemProducao();
		
	}
	
	@Override
	public void deletar(OrdemProducaoVO objeto) {

		JOptionPane.showMessageDialog(null, "Deletar OP");
		
	}

	@Override
	protected void mouseClickedTab() {
		
		getTabGeneric().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(getTabGeneric().getSelectedRow() != -1){ // acerto ref. clique com botão direito
				
					OrdemProducaoVO op = BancoFake.listaOrdensProducao.get(getTabGeneric().getSelectedRow());
									
					new DialogConfirmacaoView<OrdemProducaoVO>().abrirJanela( op, 
																	  ConsultarOrdemProducaoView.this,
																	  null );

				}
				
			}
			
		});
						
	}

	@Override
	protected void carregarGridItens(List<OrdemProducaoVO> listaOrdensProducao) {

		getModeloTabGeneric().setNumRows(0); // funciona para zerar o q tinha antes
		
		Iterator<OrdemProducaoVO> iOp = listaOrdensProducao.iterator();
		
		while(iOp.hasNext()){
			
			OrdemProducaoVO op = (OrdemProducaoVO) iOp.next();
			
			String[] registro = new String[5];

			registro[0] = op.getCodOrdemProducao();
			registro[1] = op.getData().toString();
			registro[2] = op.getProdutoVenda().getDescricao();
			registro[3] = op.getQtde().toString();
			registro[4] = op.getStatus().getDescricao();
			
			getModeloTabGeneric().addRow(registro);	
			
		}
		
	}
	
}
