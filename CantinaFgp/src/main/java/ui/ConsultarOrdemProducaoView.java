package ui;

import interfaces.ITelaManter;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import utils.BancoFake;
import vo.OrdemProducaoVO;
import enumeradores.TipoSolicitacao;

public class ConsultarOrdemProducaoView extends ConsultarPanelView<OrdemProducaoVO> {

	// Atributos tela

	private JLabel lblFiltro;
	private JLabel lblCodOrdemProd;
	private JLabel lblCodProd;
	private JLabel lblStatus;
	private JLabel lblDataInicial;
	private JLabel lblDataFinal;
	
	private JTextField txtCodOrdemProd;
	private JTextField txtCodProd;
	private JTextField txtStatus;
	private JTextField txtDataInicial;
	private JTextField txtDataFinal;
	
	// Bloco de inicialização
	
	{

		lblFiltro = new JLabel();
		lblCodOrdemProd = new JLabel();
		lblCodProd = new JLabel();
		lblStatus = new JLabel();
		lblDataInicial = new JLabel();
		lblDataFinal = new JLabel();

		txtCodOrdemProd = new JTextField();
		txtCodOrdemProd = new JTextField();
		txtCodOrdemProd = new JTextField();
		txtCodOrdemProd = new JTextField();
		txtCodOrdemProd = new JTextField();
		
	}
	
	// Construtores
	
	public ConsultarOrdemProducaoView() {
		super("Ordem de Produção", new String[]{ "Código", "Data", "Produto", "Qtde", "Status"});
	}
	
	// Métodos
	
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
