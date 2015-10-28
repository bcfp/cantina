package ui;

import interfaces.ITelaBuscar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ui.templates.BuscarDialogView;
import ui.templates.ManterPanelView;
import vo.FuncionarioVO;
import vo.GenericVO;
import vo.ItemCompraVO;
import vo.OrdemProducaoVO;
import vo.ProdutoMateriaPrimaVO;
import vo.ProdutoVendaVO;
import vo.StatusVO;
import bo.FuncionarioBO;
import bo.ProdutoMateriaPrimaBO;
import bo.ProdutoVendaBO;
import bo.StatusBO;
import enumeradores.TipoSolicitacao;

public class ManterOrdemProducaoView extends ManterPanelView<OrdemProducaoVO> implements ITelaBuscar {

	private JComboBox<String> cbxStatus;
	
	private JTextField txtCodOp;
	private JTextField txtCodProd;
	private JTextField txtDescProd;
	private JTextField txtQtdeProd;
	private JTextField txtCodFunc;
	private JTextField txtNomeFunc;

	private JLabel lblStatus;
	private JLabel lblCodOp;
	private JLabel lblCodProdVenda;
	private JLabel lblProdVenda;
	private JLabel lblQtdeProdVenda;
	private JLabel lblProduto;
	private JLabel lblMatPrima;	
	private JLabel lblFuncionario;
	private JLabel lblCodFunc;
	private JLabel lblNomeFunc;
	
	private JButton btnGerarOC;
	private JButton btnConsultarProd;
	private JButton btnConsultarFunc;

	private JTable tabMatPrimas;
	private DefaultTableModel modeloTabMatPrimas;
	private JScrollPane barraTabMatPrimas;
	
	private JPanel pnlMenuLateral;
	private JPanel pnlCampos;	
	
	private ProdutoVendaBO produtoVendaBO;
	private ProdutoMateriaPrimaBO receitaBO;
	private StatusBO statusBO;
	private FuncionarioBO funcionarioBO;
	
	private List<ProdutoMateriaPrimaVO> listaProdutosMateriaPrima;
 	private OrdemProducaoVO ordemProducao;
	private List<ItemCompraVO> listaItensCompra;
	
	private String acaoPesquisar;
	private static final String PESQ_FUNC = "funcionario";
	private static final String PESQ_PRODUTO = "produto";
	
	// Bloco de Inicialização

	{
		
		pnlCampos = new JPanel();
		cbxStatus = new JComboBox<String>();
		lblStatus = new JLabel("Status");
		lblCodOp = new JLabel("Número");
		lblCodProdVenda = new JLabel("Código");
		lblProdVenda = new JLabel("Produto");
		lblQtdeProdVenda = new JLabel("Quantidade");
		lblProduto = new JLabel("PRODUTO");
		lblFuncionario = new JLabel("FUNCIONARIO");
		lblMatPrima = new JLabel("RECEITA");
		lblCodFunc = new JLabel("Código");
		lblNomeFunc = new JLabel("Nome");

		txtCodOp = new JTextField();
		txtCodOp.setEnabled(false);
		txtCodProd = new JTextField();
		txtDescProd = new JTextField();
		txtQtdeProd = new JTextField();
		txtCodFunc = new JTextField();
		txtNomeFunc = new JTextField();
		
		btnGerarOC = new JButton("Gerar OC");
		
		btnConsultarProd = new JButton("Consultar");
		btnConsultarFunc = new JButton("Consultar");
		
		tabMatPrimas = new JTable();
		modeloTabMatPrimas = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		modeloTabMatPrimas.setColumnIdentifiers(new String[] {

				"Código", "Matéria-Prima", "Quantidade", "Estoque"

		});
		barraTabMatPrimas = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		produtoVendaBO = new ProdutoVendaBO();
		receitaBO = new ProdutoMateriaPrimaBO();
		statusBO = new StatusBO();
		funcionarioBO = new FuncionarioBO();
		
		pnlMenuLateral = new JPanel();
		
		listaItensCompra = new ArrayList<>();
		
	}
	
	// Construtores
	
	public ManterOrdemProducaoView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}
	
	
	// Métodos
	
	@Override
	public void abrirJanela() {
		
		
		
		btnGerarOC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				getListaItensCompra().clear();
								
				ItemCompraVO itemCompra;
				
				for (ProdutoMateriaPrimaVO produtoMateriaPrima : getListaProdutosMateriaPrima()) {
					
					if (produtoMateriaPrima.getQtde() > produtoMateriaPrima.getMateriaPrima().getEstoques().getQtdeAtual()) {
						itemCompra = new ItemCompraVO();
						itemCompra.setProduto(produtoMateriaPrima.getMateriaPrima());
						itemCompra.setQtde(produtoMateriaPrima.getQtde() - produtoMateriaPrima.getMateriaPrima().getEstoques().getQtdeAtual() );
						itemCompra.setValor(produtoMateriaPrima.getMateriaPrima().getPrecoCusto());
						getListaItensCompra().add(itemCompra);
					}
					
				}
				if(getListaItensCompra().size() > 0){
					new GeradorView(null, getListaItensCompra()).abrirTela();
				}
				else{
					JOptionPane.showMessageDialog(null, "Não há produtos abaixo do estoque");
				}
				
			}

		});
		
		
		btnConsultarProd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
				acaoPesquisar = PESQ_PRODUTO;
				
				new BuscarDialogView(ManterOrdemProducaoView.this, new String[] {"Código", "Nome", "Valor de venda"}).abrirJanela();
												
			}
			
		});
		
		
		btnConsultarFunc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				acaoPesquisar = PESQ_FUNC;
				
				new BuscarDialogView(ManterOrdemProducaoView.this, new String[] {"Código", "Nome"}).abrirJanela();
												
			}
		});
		
		List<StatusVO> listaStatus = statusBO.consultarTodosStatus();
		
		for (StatusVO statusVO : listaStatus) {
			
			cbxStatus.addItem(statusVO.getDescricao());
		}
		cbxStatus.setSelectedIndex(4);
		cbxStatus.setEnabled(false);
		
		definicoesPagina();
		
	}
	
	
	// Métodos Tela Busca
	
	/**
	 * Carrega na tela ManterOrdemProducao o item selecionado na tela de consulta
	 */
	@Override
	public void carregarItemSelecionado(GenericVO item) {
		
		if(item instanceof ProdutoVendaVO){
			
			modeloTabMatPrimas.setNumRows(0);
			
			ProdutoVendaVO produtoVenda = (ProdutoVendaVO) item; 
			
			txtCodProd.setText(produtoVenda.getCodProduto());
			txtDescProd.setText(produtoVenda.getDescricao());
			
			List<ProdutoMateriaPrimaVO> receita = receitaBO.buscaReceitaPorIdProduto(produtoVenda.getIdProduto());
			
			setListaProdutosMateriaPrima(receita);
			carregarGridReceita(receita);
			
			btnGerarOC.setEnabled(true);

		}
		else{
			if(item instanceof FuncionarioVO){
				
				FuncionarioVO funcionario = (FuncionarioVO) item;
				
				txtCodFunc.setText(funcionario.getCodPessoa());
				txtNomeFunc.setText(funcionario.getNome());
				
			}
		}
		
		
	}
	

	@Override
	public List<GenericVO> pesquisarItem(Map<String, String> parametros) {
		
		List<GenericVO> listaGenericos = new ArrayList<GenericVO>();
		switch (acaoPesquisar) {
		
			case PESQ_PRODUTO:
				List<ProdutoVendaVO> listaProdutos	= produtoVendaBO.filtarProdutoVendaPorNomeECodigo(parametros.get("Nome"), parametros.get("Código"));	
				
				for (ProdutoVendaVO produtoVendaVO : listaProdutos) {
					
					listaGenericos.add(produtoVendaVO);
				}
				
				return listaGenericos;
				
			case PESQ_FUNC:
				
				List<FuncionarioVO> listaFuncionarios = funcionarioBO.filtarFuncionariosPorNomeECodigo(parametros.get("Código"), parametros.get("Nome"));
				for (FuncionarioVO funcionarioVO : listaFuncionarios) {
					
					listaGenericos.add(funcionarioVO);
				}
				
				return listaGenericos;
				
			default:
				
				return null;
				
			}
		
	}


	@Override
	public String[] carregarGridTelaBusca(GenericVO item) {
			
		if(item instanceof ProdutoVendaVO){
			
			ProdutoVendaVO produtoVenda = (ProdutoVendaVO) item; 
			
			String[] registro = new String[3];

			registro[0] = produtoVenda.getCodProduto().toString();
			registro[1] = produtoVenda.getDescricao();
			registro[2] = produtoVenda.getPrecoVenda().toString();
			
			return registro;
			
		}
		else if(item instanceof FuncionarioVO){
				
			FuncionarioVO funcionarioCantina = (FuncionarioVO) item;

			String[] registro = new String[2];

			registro[0] = funcionarioCantina.getCodPessoa();
			registro[1] = funcionarioCantina.getNome();

			return registro;
			
		}
				
		return null;
		
	}

	// Fim Métodos Tela Busca
	
	@Override
	public void abrirJanela(OrdemProducaoVO ordemProducao) {
		
		setOrdemProducao(ordemProducao);
						
		txtCodProd.setText(ordemProducao.getProdutoVenda().getCodProduto());
		txtDescProd.setText(ordemProducao.getProdutoVenda().getDescricao());
		txtQtdeProd.setText(ordemProducao.getQtde().toString());
		
		setListaProdutosMateriaPrima(getOrdemProducao().getProdutoVenda().getReceita());
		carregarGridReceita(getOrdemProducao().getProdutoVenda().getReceita());
		
		abrirJanela();
		
	}
	
	private void carregarGridReceita(List<ProdutoMateriaPrimaVO> receita) {

		modeloTabMatPrimas.setNumRows(0);
						
		if(receita != null){
			
			Iterator<ProdutoMateriaPrimaVO> iReceita = receita.iterator();
			
			while(iReceita.hasNext()){
				
				ProdutoMateriaPrimaVO produtoMateriaPrima = iReceita.next();
				
				String[] registro = new String[4];

				registro[0] = produtoMateriaPrima.getMateriaPrima().getCodProduto();
				registro[1] = produtoMateriaPrima.getMateriaPrima().getDescricao();
				registro[2] = produtoMateriaPrima.getQtde().toString();
				registro[3] = produtoMateriaPrima.getMateriaPrima().getEstoques().getQtdeAtual().toString();
				
				modeloTabMatPrimas.addRow(registro);	
				
			}
		}
		
	}

	@Override
	public boolean incluir() {
		
		JOptionPane.showMessageDialog(null, "Ordem Produção Incluída");
		return false;
	}

	@Override
	public boolean alterar() {
		JOptionPane.showMessageDialog(null, "Ordem Produção Alterada");	
		return true;
	}


	@Override
	protected boolean habilitarCampos() {

		return false;
	
	}

	@Override
	protected void limparCampos() {
		
	}
	
	private void definicoesPagina(){

		txtDescProd.setEditable(false);
		txtNomeFunc.setEditable(false);
		
		if(getOrdemProducao()!=null){
			btnConsultarProd.setVisible(false);
			txtCodProd.setEditable(false);
		}
		else{
			btnGerarOC.setEnabled(false);
		}
		
		int widthCampos = this.getWidth() - 110;

		int espXLbl = 20;
		int espXLbl2 = 350;
		int espXTxt = espXLbl + 90;
		int espXTxt2 = espXTxt + 300;
		int espY = 20;
		int espEntre = 35;	
		int altura = 30;

		pnlCampos.setBounds(10, 10, widthCampos, 480);
		pnlCampos.setLayout(null);
		pnlCampos.setBackground(Color.LIGHT_GRAY);
		

		lblCodOp.setBounds(espXLbl, espY, 50, altura);
		txtCodOp.setBounds(espXTxt, espY, 70, altura);
		
		lblStatus.setBounds(espXLbl + 200, espY, 50, altura);
		cbxStatus.setBounds(espXLbl + 250, espY, 120, altura);
		
		lblProduto.setBounds(espXLbl, espY + espEntre * 2, 80, altura);
		lblCodProdVenda.setBounds(espXLbl, espY + espEntre * 3, 50, altura);
		lblProdVenda.setBounds(espXLbl, espY + espEntre * 4, 50, altura);
		lblQtdeProdVenda.setBounds(espXLbl, espY + espEntre * 5, 80, altura);
		
		txtCodProd.setBounds(espXTxt, espY + espEntre * 3, 50, altura);
		btnConsultarProd.setBounds(espXTxt + 60, espY + espEntre * 3, 90, altura);
		txtDescProd.setBounds(espXTxt, espY + espEntre * 4, 220, altura);
		txtQtdeProd.setBounds(espXTxt, espY + espEntre * 5, 70, altura);

		lblFuncionario.setBounds(espXLbl2, espY + espEntre * 2, 80, altura);
		lblCodFunc.setBounds(espXLbl2, espY + espEntre * 3, 80, altura);
		lblNomeFunc.setBounds(espXLbl2, espY + espEntre * 4, 80, altura);

		txtCodFunc.setBounds(espXTxt2, espY + espEntre * 3, 50, altura);
		btnConsultarFunc.setBounds(espXTxt2 + 60, espY + espEntre * 3, 90, altura);
		txtNomeFunc.setBounds(espXTxt2, espY + espEntre * 4, 150, altura);
		
		tabMatPrimas.setModel(modeloTabMatPrimas);

		barraTabMatPrimas.setViewportView(tabMatPrimas);

		barraTabMatPrimas.setBounds(10, 240, pnlCampos.getWidth() - 20, 230);

		lblMatPrima.setBounds(espXLbl, 240, 100, altura);

		pnlCampos.add(cbxStatus);
		pnlCampos.add(barraTabMatPrimas);
		pnlCampos.add(lblStatus);
		pnlCampos.add(lblCodOp);
		pnlCampos.add(lblProduto);
		pnlCampos.add(lblCodProdVenda);
		pnlCampos.add(lblProdVenda);
		pnlCampos.add(lblQtdeProdVenda);
		pnlCampos.add(lblMatPrima);
		pnlCampos.add(txtCodOp);
		pnlCampos.add(txtCodProd);
		pnlCampos.add(txtDescProd);
		pnlCampos.add(txtQtdeProd);
		pnlCampos.add(btnConsultarProd);
		pnlCampos.add(btnConsultarFunc);
		pnlCampos.add(lblFuncionario);
		pnlCampos.add(lblCodFunc);
		pnlCampos.add(lblNomeFunc);
		pnlCampos.add(txtCodFunc);
		pnlCampos.add(txtNomeFunc);

		adicionarComponentesCentro(pnlCampos);
		
		pnlMenuLateral.add(btnGerarOC);
		pnlMenuLateral.setBackground(Color.WHITE);
		pnlMenuLateral.setLayout(new GridLayout(10,1));
		
		this.add(pnlMenuLateral, BorderLayout.WEST);
		
		this.setVisible(true);
		
	}
	


	public OrdemProducaoVO getOrdemProducao() {
		return ordemProducao;
	}


	public void setOrdemProducao(OrdemProducaoVO ordemProducao) {
		this.ordemProducao = ordemProducao;
	}


	public List<ProdutoMateriaPrimaVO> getListaProdutosMateriaPrima() {
		return listaProdutosMateriaPrima;
	}


	public void setListaProdutosMateriaPrima(
			List<ProdutoMateriaPrimaVO> listaProdutosMateriaPrima) {
		this.listaProdutosMateriaPrima = listaProdutosMateriaPrima;
	}


	public List<ItemCompraVO> getListaItensCompra() {
		return listaItensCompra;
	}


	public void setListaItensCompra(List<ItemCompraVO> listaItensCompra) {
		this.listaItensCompra = listaItensCompra;
	}







}
