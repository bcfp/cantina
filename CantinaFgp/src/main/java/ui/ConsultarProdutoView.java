﻿package ui;

import interfaces.ITelaBuscar;
import interfaces.ITelaManter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ui.templates.BuscarDialogView;
import ui.templates.ConsultarPanelView;
import utils.BancoFake;
import vo.FornecedorVO;
import vo.GenericVO;
import vo.ProdutoVO;
import enumeradores.TipoSolicitacao;

public class ConsultarProdutoView extends ConsultarPanelView<ProdutoVO> implements ITelaBuscar {

	// Atributos Tela

	private JLabel lblFiltrar;
	
	private JLabel lblCodProduto;
	private JLabel lblDescricao;
	private JLabel lblCodFornecedor;
	private JLabel lblFornecedor;
	private JLabel lblTipoProduto;
	
	private JTextField txtCodProduto;
	private JTextField txtDescricao;
	private JTextField txtCodFornecedor;
	private JTextField txtFornecedor;

	private JRadioButton rdoLote;
	private JComboBox<String> cbxTipoProduto;
	
	private JButton btnBuscarForn;
	
	// ITelaBusca

	private String acaoPesquisar;
	private static final String PESQ_FORNECEDOR = "fornecedor";
	
	private FornecedorVO fornecedor;

	// Bloco de Inicialização
	
	{
	
		lblFiltrar = new JLabel("FILTRAR");
		
		lblCodProduto = new JLabel("Código");
		lblDescricao = new JLabel("Produto");
		lblCodFornecedor = new JLabel("Código");
		lblFornecedor = new JLabel("Fornecedor");
		lblTipoProduto = new JLabel("Tipo");
		
		txtCodProduto = new JTextField();
		txtDescricao = new JTextField();
		txtCodFornecedor = new JTextField();
		txtFornecedor = new JTextField();

		rdoLote = new JRadioButton("Lote");
		
		cbxTipoProduto = new JComboBox<String>();
		
		btnBuscarForn = new JButton("Consultar");
				
	}
	
	
	// Construtores
	
	public ConsultarProdutoView() {
		
		super("Produto", new String[] {"Código", "Descricao", "Tipo", "Valor Venda", "Estoque"}, 10, 220, 665, 190);
		this.setSize(750, 480);
		definicoesPagina();
		
	}
	
	// Métodos
	
	private void definicoesPagina(){
				
		int espXLbl = 20;
		int espXTxt = 100;
		int espY = 20;
		int espEntre = 35;
		int altura = 30;
		
		lblFiltrar.setBounds(espXLbl, espY, 50, altura);

		lblCodProduto.setBounds(espXLbl, espY + espEntre, 50, altura);
		lblDescricao.setBounds(espXLbl, espY + espEntre * 2, 50, altura);
		lblCodFornecedor.setBounds(espXLbl, espY + espEntre * 3, 50, altura);
		lblFornecedor.setBounds(espXLbl, espY + espEntre * 4, 80, altura);

		txtCodProduto.setBounds(espXTxt, espY + espEntre, 50, altura);
		txtDescricao.setBounds(espXTxt, espY + espEntre * 2, 150, altura);
		txtCodFornecedor.setBounds(espXTxt, espY + espEntre * 3, 50, altura);
		btnBuscarForn.setBounds(espXTxt + 60, espY + espEntre * 3, 90, altura);
		txtFornecedor.setBounds(espXTxt, espY + espEntre * 4, 150, altura);

		int espXLbl2 = 330;
		int espXTxt2 = 380;

		lblTipoProduto.setBounds(espXLbl2, espY + espEntre, 80, altura);
		cbxTipoProduto.setBounds(espXTxt2, espY + espEntre, 125, altura);		

		rdoLote.setBounds(espXLbl2, espY + espEntre * 4, 50, altura);
		
		rdoLote.setBackground(Color.LIGHT_GRAY);
		
		btnBuscarForn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				acaoPesquisar = PESQ_FORNECEDOR;
				
				new BuscarDialogView(ConsultarProdutoView.this, new String[]{"Código", "Fornecedor"}).abrirJanela();;				
				
			}
			
		});
				
		adicionarComponenteCentro(lblFiltrar);
		adicionarComponenteCentro(lblCodProduto);
		adicionarComponenteCentro(lblDescricao);
		adicionarComponenteCentro(lblCodFornecedor);
		adicionarComponenteCentro(lblFornecedor);
		adicionarComponenteCentro(lblTipoProduto);
		
		adicionarComponenteCentro(txtCodProduto);
		adicionarComponenteCentro(txtDescricao);
		adicionarComponenteCentro(txtCodFornecedor);
		adicionarComponenteCentro(txtFornecedor);
		adicionarComponenteCentro(btnBuscarForn);

		adicionarComponenteCentro(rdoLote);
		adicionarComponenteCentro(cbxTipoProduto);
		
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

	// Métodos ITelaBuscar
	
	@Override
	public List<GenericVO> buscarItem(Map<String, String> parametros) {
		
		switch (acaoPesquisar) {
						
			case PESQ_FORNECEDOR:
			
				return BancoFake.listaFornecedorGeneric;

		}
	
		return null;
		
	}

	@Override
	public void carregarItemSelecionado(GenericVO item) {
		
		switch (acaoPesquisar) {
						
			case PESQ_FORNECEDOR:
			
			fornecedor = (FornecedorVO) item;
			
			txtCodFornecedor.setText(fornecedor.getCodFornecedor());
			txtFornecedor.setText(fornecedor.getNome());

		}
		
	}

	@Override
	public String[] carregarGridTelaBusca(GenericVO item) {
		
		String[] registro = null;
		
		switch (acaoPesquisar) {
						
			case PESQ_FORNECEDOR:
				
				FornecedorVO fornecedor = (FornecedorVO) item; 
				
				registro = new String[3];
	
				registro[0] = fornecedor.getCodFornecedor();
				registro[1] = fornecedor.getNome();
				
			return registro;

		}
		
		return registro;
		
	}


}
