package ui;

import interfaces.ITelaManter;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.CompraVO;
import vo.GenericVO;
import enumeradores.TipoSolicitacao;

public class ConsultarCompraView extends ConsultarPanelView<CompraVO> {

	public ConsultarCompraView() {
		super("Compra",
			new String[]{
				"Código",
				"Data"
			}, 
			BancoFake.listaCompras, 50, 100, 400, 200);
	}

	@Override
	public void deletar(CompraVO compra) {

		JOptionPane.showMessageDialog(null, "Deletar Compra");
		
	}

	@Override
	protected GenericVO getItemDetalhar() {
		return BancoFake.listaCompras.get(getTabGeneric().getSelectedRow());
	}

	@Override
	protected ITelaManter<CompraVO> getTelaDetalhar() {
		return new ManterCompraView(TipoSolicitacao.DETALHAR, "Detalhar Compra");
	}

	@Override
	protected void carregarGridItens(List<CompraVO> listaCompras) {

		getModeloTabGeneric().setNumRows(0); // funciona para zerar o q tinha antes
		
		Iterator<CompraVO> iCompra = listaCompras.iterator();
		
		while(iCompra.hasNext()){
			
			CompraVO compra = (CompraVO) iCompra.next();
			
			String[] registro = new String[2];

			registro[0] = compra.getCodCompra();
			registro[1] = compra.getData().toString();
			
			getModeloTabGeneric().addRow(registro);	
			
		}
		
	}

	@Override
	protected void getTelaNovo() {

		new ManterCompraView(TipoSolicitacao.INCLUIR, "Cadastrar Compra").abrirJanela();
		
	}	
}