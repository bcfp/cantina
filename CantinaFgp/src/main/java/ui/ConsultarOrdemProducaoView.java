package ui;

import interfaces.ITelaManter;

import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.GenericVO;
import vo.OrdemProducaoVO;
import enumeradores.TipoSolicitacao;

public class ConsultarOrdemProducaoView extends ConsultarPanelView<OrdemProducaoVO> {

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

		new ManterOrdemProducao(TipoSolicitacao.INCLUIR, "Cadastrar Ordem de Produção").abrirJanela();
		
	}

	@Override
	public void deletar(OrdemProducaoVO objeto) {

		JOptionPane.showMessageDialog(null, "Deletar OP");
		
	}

	@Override
	protected GenericVO getItemDetalhar() {
		return BancoFake.listaOrdensProducao.get(getTabGeneric().getSelectedRow());
	}

	@Override
	protected ITelaManter<OrdemProducaoVO> getTelaDetalhar() {
		return new ManterOrdemProducao(TipoSolicitacao.DETALHAR, "Detalhar Ordem de Produção");
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
