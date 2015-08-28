package ui;

import interfaces.ITelaConsultar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.ProdutoVO;

public class ConsultarProdutoView extends ConsultarPanelView<ProdutoVO> implements ITelaConsultar<ProdutoVO>{

	private JLabel lblTest;
	
	public ConsultarProdutoView() {
		
		super(new String[] {

			"Código",
			"Descricao"

		}, BancoFake.listaProdutos, 50, 100, 400, 200);

		abrirJanela();
	
	}
	
	public void abrirJanela(){
		
		setLayout(null);
		
		lblTest = new JLabel("Produto");
		lblTest.setBounds(10, 10, 140, 140);
		
		add(lblTest);
			
	}

	@Override
	protected void carregarGridItens(List<ProdutoVO> listaProtudos) {

		getModeloTabGeneric().setNumRows(0); // funciona para zerar o q tinha antes
		
		Iterator<ProdutoVO> iProduto = listaProtudos.iterator();
		
		while(iProduto.hasNext()){
			
			ProdutoVO prod = (ProdutoVO) iProduto.next();
			
			String[] registro = new String[2];

			registro[0] = prod.getCodProduto();
			registro[1] = prod.getDescricao();
			
			getModeloTabGeneric().addRow(registro);	
			
		}
		
	}

	@Override
	protected void mouseClickedTab() {
		
		getTabGeneric().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(getTabGeneric().getSelectedRow() != -1){ // acerto ref. clique com botão direito
				
					ProdutoVO prod = BancoFake.listaProdutos.get(getTabGeneric().getSelectedRow());
									
					new DialogConfirmacaoView<ProdutoVO>().abrirJanela( prod, 
																		ConsultarProdutoView.this,
																		new DetalharProdutoView() );

				}
				
			}
			
		});
						
	}
	
	@Override
	public void alterar() {

		JOptionPane.showMessageDialog(null, "Produto Alterado");
		
	}

	@Override
	public void deletar(ProdutoVO produto) {

		JOptionPane.showMessageDialog(null, "Produto Excluido");
				
	}

	@Override
	protected void getTelaNovo() {
		
		new CriarProdutoView().abrirJanela();
		
	}
}
