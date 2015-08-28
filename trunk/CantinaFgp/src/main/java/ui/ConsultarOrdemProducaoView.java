package ui;

import interfaces.ITelaConsultar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.OrdemProducaoVO;

public class ConsultarOrdemProducaoView extends ConsultarPanelView<OrdemProducaoVO> implements ITelaConsultar<OrdemProducaoVO> {

	private JLabel lblTest;

	public ConsultarOrdemProducaoView() {
		super(new String[]{
				"Código",
				"Data",
				"Produto",
				"Qtde",
				"Status"
		}, BancoFake.listaOrdensProducao, 50, 100, 400, 200);
		abrirJanela();
	}

	public void abrirJanela() {
		
		setLayout(null);

		lblTest = new JLabel("Ordem de Produção");
		lblTest.setBounds(10, 10, 140, 140);

		add(lblTest);

	}

	@Override
	protected void getTelaNovo() {

		new CriarOrdemProducao();
		
	}

	@Override
	public void alterar() {

		JOptionPane.showMessageDialog(null, "Alterar OP");
		
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
																	  new DetalharOrdemProducaoView() );

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
