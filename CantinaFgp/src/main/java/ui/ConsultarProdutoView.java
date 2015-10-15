package ui;

import interfaces.ITelaManter;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.ProdutoVO;
import enumeradores.TipoSolicitacao;

public class ConsultarProdutoView extends ConsultarPanelView<ProdutoVO> {

	public ConsultarProdutoView() {
		
		super("Produto", new String[] {

			"Código",
			"Descricao"

		}, BancoFake.listaProdutos, 10, 100, 400, 200);
	
	}

	@Override
	protected String[] carregarGridItens(ProdutoVO produto) {
		
		String[] registro = new String[2];

		registro[0] = produto.getCodProduto();
		registro[1] = produto.getDescricao();
		
		return registro;
	}
	
	@Override
	protected ITelaManter<ProdutoVO> getTelaDetalhar() {
		return new ManterProdutoView(TipoSolicitacao.DETALHAR, "Detalhar Produto");
	}	

	@Override
	public void deletar(ProdutoVO produto) {

		JOptionPane.showMessageDialog(null, "Produto Excluido");
				
	}

	@Override
	protected void getTelaNovo() {
		
		new ManterProdutoView(TipoSolicitacao.INCLUIR, "Cadastrar Produto").abrirJanela();;
		
	}


}
