package ui;

import interfaces.ITelaManter;

import javax.swing.JOptionPane;

import utils.BancoFake;
import vo.ClienteVO;
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
	protected ITelaManter<ClienteVO> getTelaDetalhar() {
		return new ManterClienteView(TipoSolicitacao.DETALHAR, "Detalhar Cliente");
	}
	
	@Override
	protected String[] carregarGridItens(ClienteVO cliente) {
		
		String[] registro = new String[2];

		registro[0] = cliente.getCodPessoa();
		registro[1] = cliente.getNome();
		
		return registro;
		
	}

	@Override
	protected void getTelaNovo() {
		
		new ManterClienteView(TipoSolicitacao.INCLUIR, "Cadastrar Cliente").abrirJanela();
		
	}
	
}