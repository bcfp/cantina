package ui;

import interfaces.ITelaManter;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ui.templates.ConsultarPanelView;
import utils.BancoFake;
import vo.ProdutoVO;
import enumeradores.TipoSolicitacao;

public class ConsultarProdutoView extends ConsultarPanelView<ProdutoVO> {

	// Atributos Tela
	
	private JLabel lblCodProduto;
	
	
	// Construtores
	
	public ConsultarProdutoView() {
		
		super("Produto", new String[] {"Código", "Descricao"});
	
	}
	
	// Métodos ConsultarPanelView

	@Override
	protected String[] definirGridItens(ProdutoVO produto) {
		
		String[] registro = new String[2];

		registro[0] = produto.getCodProduto();
		registro[1] = produto.getDescricao();
		
		return registro;
	}

	@Override
	protected ITelaManter<ProdutoVO> getTelaIncluir() {
		return new ManterProdutoView(TipoSolicitacao.INCLUIR, "Cadastrar Produto");
	}	
	
	@Override
	protected ITelaManter<ProdutoVO> getTelaAlterar() {
		return new ManterProdutoView(TipoSolicitacao.DETALHAR, "Detalhar Produto");
	}
	
	// Métodos ITelaConsultar

	@Override
	public void deletar(ProdutoVO produto) {

		JOptionPane.showMessageDialog(null, "Produto Excluido");
				
	}

	@Override
	public List<ProdutoVO> consultar() {
		return BancoFake.listaProdutos;
	}


}
