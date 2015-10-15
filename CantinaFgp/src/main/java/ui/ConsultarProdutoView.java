package ui;

import interfaces.ITelaManter;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.GenericVO;
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
	protected GenericVO getItemDetalhar() {
		return BancoFake.listaProdutos.get(getTabGeneric().getSelectedRow());
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
