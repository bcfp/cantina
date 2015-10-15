package ui;

import interfaces.ITelaConsultar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import enumeradores.TipoManter;
import utils.BancoFake;
import vo.ProdutoVO;

public class ConsultarProdutoView extends ConsultarPanelView<ProdutoVO> implements ITelaConsultar<ProdutoVO>{

	public ConsultarProdutoView() {
		
		super("Produto", new String[] {

			"Código",
			"Descricao"

		}, BancoFake.listaProdutos, 10, 100, 400, 200);
	
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
																		new ManterProdutoView(TipoManter.DETALHAR, "Detalhar Produto"));

				}
				
			}
			
		});
						
	}

	@Override
	public void deletar(ProdutoVO produto) {

		JOptionPane.showMessageDialog(null, "Produto Excluido");
				
	}

	@Override
	protected void getTelaNovo() {
		
		new ManterProdutoView(TipoManter.INCLUIR, "Cadastrar Produto").abrirJanela();;
		
	}
}
