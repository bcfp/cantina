package ui;

import interfaces.ITelaBuscar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
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
import utils.UtilFuncoes;
import vo.EstoqueMateriaPrimaVO;
import vo.FuncionarioCantinaVO;
import vo.GenericVO;
import vo.ItemCompraVO;
import vo.OrdemProducaoVO;
import vo.ProdutoMateriaPrimaVO;
import vo.ProdutoVendaVO;
import vo.StatusVO;
import bo.FuncionarioBO;
import bo.OrdemProducaoBO;
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
	private OrdemProducaoBO ordemProducaoBO;
	
	private List<ProdutoMateriaPrimaVO> receita;
 	private OrdemProducaoVO ordemProducao;
	private List<ItemCompraVO> listaItensCompra;
	
	private List<FuncionarioCantinaVO> listaFuncionarios;
	private List<ProdutoVendaVO> listaProdutos;
	private List<StatusVO> listaStatus;
	
	private StringBuilder msgErroCampos;
	
	private ProdutoVendaVO produtoVenda;
	private EstoqueMateriaPrimaVO estoqueMatPrima;
	private FuncionarioCantinaVO funcionarioCantina;
	
	private String acaoPesquisar;
	private static final String PESQ_FUNC = "funcionario";
	private static final String PESQ_PRODUTO = "produto";
	
	
	// Bloco de Inicialização

	{
		
		pnlCampos = new JPanel();
		cbxStatus = new JComboBox<String>();
		cbxStatus.setEnabled(false);
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
		txtCodOp.setEditable(false);
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
		ordemProducaoBO = new OrdemProducaoBO();
		
		pnlMenuLateral = new JPanel();
		
		listaItensCompra = new ArrayList<>();
		listaProdutos = new ArrayList<ProdutoVendaVO>();
		listaFuncionarios = new ArrayList<FuncionarioCantinaVO>();
		listaStatus = new ArrayList<StatusVO>();
		
		produtoVenda = new ProdutoVendaVO();
		funcionarioCantina = new FuncionarioCantinaVO();
		
		listaStatus = statusBO.consultarTodosStatus();
		
		for (StatusVO statusVO : listaStatus) {
			
			cbxStatus.addItem(statusVO.getDescricao());
			
		}
				
	}
	
	// Construtores
	
	public ManterOrdemProducaoView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}
	
	
	// Métodos
	
	@Override
	public void abrirJanela() {
		
		if(ordemProducao == null){
			cbxStatus.setSelectedItem("Em Aberto");			
		}
		
		cbxStatus.setEnabled(false);
	
		btnGerarOC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				listaItensCompra.clear();
								
				ItemCompraVO itemCompra;
				
				Double qtdeTotal = 0d;
				Double qtdeAtual = 0d;
				
				for (ProdutoMateriaPrimaVO produtoMateriaPrima : receita) {
					
					qtdeAtual = produtoMateriaPrima.getMateriaPrima().getEstoque().getQtdeAtual();
					qtdeTotal = calcularQtdeTotal(produtoMateriaPrima.getQtde());
					
					if (qtdeTotal > qtdeAtual) {
						itemCompra = new ItemCompraVO();
						itemCompra.setProduto(produtoMateriaPrima.getMateriaPrima());
						itemCompra.setQtde(qtdeTotal - qtdeAtual);
						itemCompra.setValor(produtoMateriaPrima.getMateriaPrima().getPrecoCusto());
						listaItensCompra.add(itemCompra);
					}
					
				}
				
				if(listaItensCompra.size() > 0){
					new GeradorView(null, listaItensCompra).abrirTela();
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
		
		txtQtdeProd.addKeyListener(new KeyAdapter() { 
			
			@Override
			public void keyTyped(KeyEvent evento) {
								
				if(!UtilFuncoes.isCampoNumerico(String.valueOf(evento.getKeyChar()))){
					evento.consume();
				}
				
			}
					
			@Override
			public void keyReleased(KeyEvent evento) {
				if(receita.size() >= 0){
					carregarGridReceita(receita);
				}
			}
			
		});
				
		definicoesPagina();
		
	}
		
	@Override
	public void abrirJanela(OrdemProducaoVO ordemProducao) {
		
		this.ordemProducao = ordemProducao;
		
		String status = ordemProducao.getStatus().getDescricao();
		
		System.out.println(status);
		
		if(status.equals("Concluído")){
			btnAlterar.setEnabled(false);
		}
		
		txtCodOp.setText(ordemProducao.getCodOrdemProducao());
		cbxStatus.setSelectedItem(status);
		txtCodProd.setText(ordemProducao.getProdutoVenda().getCodProduto());
		txtDescProd.setText(ordemProducao.getProdutoVenda().getDescricao());
		txtQtdeProd.setText(ordemProducao.getQtde().toString());
		txtCodFunc.setText(ordemProducao.getFuncionarioCantina().getFuncionario().getCodPessoa());
		txtNomeFunc.setText(ordemProducao.getFuncionarioCantina().getFuncionario().getNome());
		
		receita = ordemProducao.getProdutoVenda().getReceita();
		carregarGridReceita(ordemProducao.getProdutoVenda().getReceita());

		txtCodProd.setEditable(false);
		txtCodFunc.setEditable(false);
		txtQtdeProd.setEditable(false);
		btnConsultarFunc.setEnabled(false);
		
		abrirJanela();
		
	}
	
	// Métodos Tela Busca
	
	/**
	 * Carrega na tela ManterOrdemProducao o item selecionado na tela de consulta
	 */
	@Override
	public void carregarItemSelecionado(GenericVO item) {
		
		if(item instanceof ProdutoVendaVO){
			
			modeloTabMatPrimas.setNumRows(0);
			
			produtoVenda = (ProdutoVendaVO) item; 
			
			txtCodProd.setText(produtoVenda.getCodProduto());
			txtDescProd.setText(produtoVenda.getDescricao());
						
			receita = receitaBO.buscaReceitaPorIdProduto(produtoVenda.getIdProduto());
			
			carregarGridReceita(receita);
			
			btnGerarOC.setEnabled(true);

		}
		else{
			if(item instanceof FuncionarioCantinaVO){
				
				funcionarioCantina = (FuncionarioCantinaVO) item;
				
				txtCodFunc.setText(funcionarioCantina.getFuncionario().getCodPessoa());
				txtNomeFunc.setText(funcionarioCantina.getFuncionario().getNome());
				
			}
		}
		
		
	}
	

	@Override
	public List<GenericVO> pesquisarItem(Map<String, String> parametros) {
		
		List<GenericVO> listaGenericos = new ArrayList<GenericVO>();
		switch (acaoPesquisar) {
		
			case PESQ_PRODUTO:
				
				listaProdutos = produtoVendaBO.filtarProdutoVendaPorNomeECodigo(parametros.get("Nome"), parametros.get("Código"));	
				
				for (ProdutoVendaVO produtoVendaVO : listaProdutos) {
					
					listaGenericos.add(produtoVendaVO);
				}
				
				return listaGenericos;
				
			case PESQ_FUNC:
				
				listaFuncionarios = funcionarioBO.filtarFuncionariosPorNomeECodigo(parametros.get("Nome"), parametros.get("Código"));
				
				for (FuncionarioCantinaVO funcionarioVO : listaFuncionarios) {
					
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

			registro[0] = produtoVenda.getCodProduto();
			registro[1] = produtoVenda.getDescricao();
			registro[2] = produtoVenda.getPrecoVenda().toString();
			
			return registro;
			
		}
		else if(item instanceof FuncionarioCantinaVO){
				
			FuncionarioCantinaVO funcionarioCantina = (FuncionarioCantinaVO) item;

			String[] registro = new String[2];

			registro[0] = funcionarioCantina.getFuncionario().getCodPessoa();
			registro[1] = funcionarioCantina.getFuncionario().getNome();

			return registro;
			
		}
				
		return null;
		
	}

	// Fim Métodos Tela Busca
	
	private Double calcularQtdeTotal(Double qtdeMp){
		
		Double qtdeProd = 0d;
		
		String qtdeTxt = txtQtdeProd.getText();
		
		if(!UtilFuncoes.isCampoVazio(qtdeTxt)){
			qtdeProd = Double.parseDouble(qtdeTxt);
		}
		
		return qtdeProd * qtdeMp;
		
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
				registro[2] = calcularQtdeTotal(produtoMateriaPrima.getQtde()).toString();
				registro[3] = produtoMateriaPrima.getMateriaPrima().getEstoque().getQtdeAtual().toString();
				
				modeloTabMatPrimas.addRow(registro);	
				
			}
		}
		
	}

	@Override
	public boolean incluir() {
			
		if(isCamposValidos()){
			
			OrdemProducaoVO ordemProducao = new OrdemProducaoVO();
			ordemProducao.setData(new Date());
			ordemProducao.setQtde(Integer.parseInt(txtQtdeProd.getText()));
			ordemProducao.setFuncionarioCantina(funcionarioCantina);
			ordemProducao.setProdutoVenda(produtoVenda);
			ordemProducao.setStatus(listaStatus.get(cbxStatus.getSelectedIndex()));
						
			if(ordemProducaoBO.incluirOrdemProducao(ordemProducao)){
								
				/*
				 * TODO - Na inclusão deve ser verificado se o status é diferente de 'em aberto',
				 * se for a qtde de cada matéria-prima deve ser retirada do estoque.
				 */
				
				JOptionPane.showMessageDialog(null, "Ordem Produção Incluída");
				
			}
			else{
				JOptionPane.showMessageDialog(null, "Erro ao gravar", "Erro", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			return true;
			
		}
		else{
			
			JOptionPane.showMessageDialog(null, msgErroCampos, "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
	}
	
	@Override
	public boolean alterar() {
		
		/*
		 * TODO - Uma OP 'em produção' não pode ser alterada para 'aguardando compra' ou para 'em aberto'
		 * 
		 * 
		 * 
		 */
			
		if(isCamposValidos()){
						
			OrdemProducaoVO ordemProducao = new OrdemProducaoVO();
			ordemProducao.setCodOrdemProducao(txtCodOp.getText());
			ordemProducao.setData(this.ordemProducao.getData());
			ordemProducao.setQtde(Integer.parseInt(txtQtdeProd.getText()));
			ordemProducao.setFuncionarioCantina(funcionarioCantina);
			ordemProducao.setProdutoVenda(produtoVenda);
			ordemProducao.setStatus(listaStatus.get(cbxStatus.getSelectedIndex()));
						
			if(ordemProducaoBO.alterarOrdemProducao(ordemProducao)){
				
				/*
				 *  TODO - Na alteração deve ser verificado se o status é concluído, 
				 *  se for o produto deve ser incluído no estoque de produto venda
				 *  a inclusão deve ser feita junto da alteração
				 */
				
				JOptionPane.showMessageDialog(null, "Ordem Produção Alterada");
				
			}
			else{
				JOptionPane.showMessageDialog(null, "Erro ao alterar", "Erro", JOptionPane.ERROR_MESSAGE);
				return false;
			}
						
			
			btnConsultarFunc.setEnabled(false);
			cbxStatus.setEnabled(false);
			txtCodProd.setEnabled(false);
			txtCodFunc.setEnabled(false);
			txtQtdeProd.setEnabled(false);
				
			return true;
			
		}
		else{
			JOptionPane.showMessageDialog(null, msgErroCampos, "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
	}
	
	private boolean isCamposValidos(){
	
		msgErroCampos = new StringBuilder();
		boolean isCamposValidos = true;
		
		if(ordemProducaoBO.isCampoFuncionarioVazio(txtNomeFunc.getText())){
			
			msgErroCampos.append("Favor preencher o campo nome do funcionário\n");
			isCamposValidos = false;
			
		}
		
		if(ordemProducaoBO.isCampoCodigoFuncionarioVazio(txtCodFunc.getText())){
			
			msgErroCampos.append("Favor preencher o campo código do funcionário\n");
			isCamposValidos = false;
		}
		
		if(ordemProducaoBO.isCampoProdutoVazio(txtDescProd.getText())){
			msgErroCampos.append("Favor preencher o campo nome do produto\n");
			isCamposValidos = false;
		}
		
		if(ordemProducaoBO.isCampoCodigoProdutoVazio(txtCodProd.getText())){
			msgErroCampos.append("Favor preencher o campo código do produto\n");
			isCamposValidos = false;
		}
		
		if(ordemProducaoBO.isCampoQtdVazio(txtQtdeProd.getText())){
			
			msgErroCampos.append("Favor preencher o campo quantidade do produto\n");
			isCamposValidos = false;
		}
		else if(!ordemProducaoBO.isCampoQuantidadeNumerico(txtQtdeProd.getText())){
			
			msgErroCampos.append("Favor preencher apenas numeros no campo quantidade do produto\n");
			isCamposValidos = false;
		}
		else{
			if(ordemProducaoBO.isCampoQtdNegativo(txtQtdeProd.getText())){
					
				msgErroCampos.append("Favor preencher o campo quantidade do produto com um valor maior que 0\n");
				isCamposValidos = false;
			
			}
			
			if(isCamposValidos){
				
				if(!cbxStatus.getSelectedItem().equals("Concluído")){
					
					int qtdProdutos = ordemProducaoBO.converterStringParaInt(txtQtdeProd.getText());
					
					for (ProdutoMateriaPrimaVO produtoMateriaPrimaVO : receita) {
						
						if(produtoMateriaPrimaVO.getQtde() * qtdProdutos > produtoMateriaPrimaVO.getMateriaPrima().getEstoque().getQtdeAtual()){
							
							msgErroCampos.append("A quantidade de " + produtoMateriaPrimaVO.getMateriaPrima().getDescricao() + " necessária para fabricar esse produto está abaixo do estoque\n");
							isCamposValidos = false;
							
						}
						
					}
					
				}
			}
		}
		
		return isCamposValidos;
		
	}

	@Override
	protected boolean habilitarCampos() {

		if(ordemProducao.getStatus().getDescricao().equals("Em Aberto")){
			txtCodProd.setEditable(true);
			txtQtdeProd.setEditable(true);
		}
		
		cbxStatus.setEnabled(true);
		txtCodFunc.setEditable(true);
		btnConsultarFunc.setEnabled(true);
		
		return true;
	
	}

	@Override
	protected void limparCampos() {
		
	}
	
	private void definicoesPagina(){

		txtDescProd.setEditable(false);
		txtNomeFunc.setEditable(false);
		
		if(ordemProducao!=null){
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


}
