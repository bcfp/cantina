package ui;

import interfaces.ITelaManter;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.ClienteVO;
import vo.GenericVO;
import enumeradores.TipoSolicitacao;

public class ConsultarClienteView extends ConsultarPanelView<ClienteVO>{

	public ConsultarClienteView() {
		super("Cliente", 
			new String[]{
				"Código",
				"Nome"
			}, 
			BancoFake.listaClientes, 50, 100, 400, 200);
	}

	@Override
	public void deletar(ClienteVO cliente) {
		
		JOptionPane.showMessageDialog(null, "Deletar Cliente");
		
	}
	
	@Override
	protected GenericVO getItemDetalhar() {
		return BancoFake.listaClientes.get(getTabGeneric().getSelectedRow());
	}

	@Override
	protected ITelaManter<ClienteVO> getTelaDetalhar() {
		return new ManterClienteView(TipoSolicitacao.DETALHAR, "Detalhar Cliente");
	}

	@Override
	protected void carregarGridItens(List<ClienteVO> listaClientes) {

		getModeloTabGeneric().setNumRows(0); // funciona para zerar o q tinha antes
		
		Iterator<ClienteVO> iCliente = listaClientes.iterator();
		
		while(iCliente.hasNext()){
			
			ClienteVO cliente = (ClienteVO) iCliente.next();
			
			String[] registro = new String[2];

			registro[0] = cliente.getCodPessoa();
			registro[1] = cliente.getNome();
			
			getModeloTabGeneric().addRow(registro);	
			
		}
		
	}

	@Override
	protected void getTelaNovo() {
		
		new ManterClienteView(TipoSolicitacao.INCLUIR, "Cadastrar Cliente").abrirJanela();
		
	}
	
}