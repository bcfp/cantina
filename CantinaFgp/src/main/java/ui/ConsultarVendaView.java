package ui;

import interfaces.ITelaManter;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.GenericVO;
import vo.VendaVO;
import enumeradores.TipoSolicitacao;

public class ConsultarVendaView extends ConsultarPanelView<VendaVO> {

	public ConsultarVendaView() {
		
		super("Venda",new String[] {

			"Código",
			"Data"

		}, BancoFake.listaVendas, 50, 100, 400, 200);

	}

	@Override
	protected GenericVO getItemDetalhar() {
		return BancoFake.listaVendas.get(getTabGeneric().getSelectedRow());
	}

	@Override
	protected ITelaManter<VendaVO> getTelaDetalhar() {
		return new ManterVendaView(TipoSolicitacao.DETALHAR, "Detalhar Venda");
	}

	@Override
	protected void carregarGridItens(List<VendaVO> listaVendas) {

		getModeloTabGeneric().setNumRows(0); // funciona para zerar o q tinha antes
		
		Iterator<VendaVO> iVenda = listaVendas.iterator();
		
		while(iVenda.hasNext()){
			
			VendaVO venda = (VendaVO) iVenda.next();
			
			String[] registro = new String[2];

			registro[0] = venda.getCodVenda();
			registro[1] = venda.getData().toString();
			
			getModeloTabGeneric().addRow(registro);	
			
		}
		
	}


	@Override
	protected void getTelaNovo() {
		
		new ManterVendaView(TipoSolicitacao.INCLUIR, "Gerar Venda").abrirJanela();
		
	}

	@Override
	public void deletar(VendaVO venda) {

		JOptionPane.showMessageDialog(null, "Deletar Venda");
		
	}


}
