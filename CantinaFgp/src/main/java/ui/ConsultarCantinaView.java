package ui;

import interfaces.ITelaManter;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.CantinaVO;
import vo.GenericVO;
import enumeradores.TipoSolicitacao;

public class ConsultarCantinaView extends ConsultarPanelView<CantinaVO>{
	
	public ConsultarCantinaView() {
		super("Cantina",new String[]{
				"Código",
				"Nome"
		}, BancoFake.listaCantinas, 50, 100, 400, 200);
	}

	@Override
	public void deletar(CantinaVO cantina) {

		JOptionPane.showMessageDialog(null, "Deletar Cantina");
		
	}
	
	@Override
	protected GenericVO getItemDetalhar() {
		return BancoFake.listaCantinas.get(getTabGeneric().getSelectedRow());
	}
	
	@Override
	protected ITelaManter<CantinaVO> getTelaDetalhar() {
		return new ManterCantinaView(TipoSolicitacao.DETALHAR, "Detalhar Cantina");
	}

	@Override
	protected void carregarGridItens(List<CantinaVO> listaCantinas) {

		getModeloTabGeneric().setNumRows(0); // funciona para zerar o q tinha antes
		
		Iterator<CantinaVO> iCantina = listaCantinas.iterator();
		
		while(iCantina.hasNext()){
			
			CantinaVO cantina = (CantinaVO) iCantina.next();
			
			String[] registro = new String[2];

			registro[0] = cantina.getIdCantina().toString();
			registro[1] = cantina.getNome();
			
			getModeloTabGeneric().addRow(registro);	
			
		}
		
	}

	@Override
	protected void getTelaNovo() {

		new ManterCantinaView(TipoSolicitacao.INCLUIR, "Cadastrar Cantina").abrirJanela();
		
	}
	
}