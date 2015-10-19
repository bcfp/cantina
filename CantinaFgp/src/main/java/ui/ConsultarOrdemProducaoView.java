package ui;

import java.util.List;

import interfaces.ITelaManter;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.OrdemProducaoVO;
import enumeradores.TipoSolicitacao;

public class ConsultarOrdemProducaoView extends ConsultarPanelView<OrdemProducaoVO> {

	public ConsultarOrdemProducaoView() {
		super("Ordem de Produção", new String[]{ "Código", "Data", "Produto", "Qtde", "Status"});
	}
	
	@Override
	protected String[] definirGridItens(OrdemProducaoVO ordemProducao) {
			
		String[] registro = new String[5];

		registro[0] = ordemProducao.getCodOrdemProducao();
		registro[1] = ordemProducao.getData().toString();
		registro[2] = ordemProducao.getProdutoVenda().getDescricao();
		registro[3] = ordemProducao.getQtde().toString();
		registro[4] = ordemProducao.getStatus().getDescricao();
		
		return registro;
		
	}	
	
	@Override
	protected ITelaManter<OrdemProducaoVO> getTelaIncluir() {
		return new ManterOrdemProducao(TipoSolicitacao.INCLUIR, "Cadastrar Ordem de Produção");
	}

	@Override
	protected ITelaManter<OrdemProducaoVO> getTelaAlterar() {
		return new ManterOrdemProducao(TipoSolicitacao.DETALHAR, "Detalhar Ordem de Produção");
	}

	@Override
	public void deletar(OrdemProducaoVO objeto) {

		JOptionPane.showMessageDialog(null, "Deletar OP");
		
	}

	@Override
	public List<OrdemProducaoVO> consultar() {
		return BancoFake.listaOrdensProducao;
	}
	
}
