package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import vo.CompraVO;
import vo.ItemCompraVO;
import vo.MateriaPrimaVO;
import vo.OrdemProducaoVO;
import vo.ProdutoMateriaPrimaVO;
import vo.StatusVO;
import bo.ProdutoVendaBO;
import bo.ProdutoMateriaPrimaBO;
import enumeradores.TipoSolicitacao;

public class ManterOrdemProducao extends ManterDialogView<OrdemProducaoVO> {

	private JComboBox<StatusVO> cbxStatus;
	
	private JTextField txtCodOp;
	private JTextField txtCodProdVenda;
	private JTextField txtProdVenda;
	private JTextField txtQtdeProdVenda;
	private JTextField txtNomeFunc;

	private JLabel lblStatus;
	private JLabel lblCodOp;
	private JLabel lblCodProdVenda;
	private JLabel lblProdVenda;
	private JLabel lblQtdeProdVenda;
	private JLabel lblProduto;
	private JLabel lblMatPrima;	
	private JLabel lblFuncionario;
	private JLabel lblNomeFuncionario;
	
	private JButton btnGerarOC;
	private JButton btnConsultarProd;

	private JTable tabMatPrimas;
	private DefaultTableModel modeloTabMatPrimas;
	private JScrollPane barraTabMatPrimas;
	
	private JPanel pnlMenuLateral;
	private JPanel pnlCampos;;
	
	private ProdutoVendaBO produtoVendaBO;
	private ProdutoMateriaPrimaBO produtoMateriaPrimaBO;
	
	private OrdemProducaoVO ordemProducao;
	
	
	// Bloco de Inicialização
	
	{
		
		pnlCampos = new JPanel();
		cbxStatus = new JComboBox<StatusVO>();
		lblStatus = new JLabel("Status");
		lblCodOp = new JLabel("Número");
		lblCodProdVenda = new JLabel("Código");
		lblProdVenda = new JLabel("Produto");
		lblQtdeProdVenda = new JLabel("Quantidade");
		lblProduto = new JLabel("PRODUTO");
		lblFuncionario = new JLabel("FUNCIONARIO");
		lblMatPrima = new JLabel("RECEITA");
		lblNomeFuncionario = new JLabel("Nome:");

		txtCodOp = new JTextField();
		txtCodOp.setEnabled(false);
		txtCodProdVenda = new JTextField();
		txtProdVenda = new JTextField();
		txtQtdeProdVenda = new JTextField();
		txtNomeFunc = new JTextField();
		
		btnGerarOC = new JButton("Gerar OC");
		
		btnConsultarProd = new JButton("Consultar");
		tabMatPrimas = new JTable();
		modeloTabMatPrimas = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		barraTabMatPrimas = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		produtoVendaBO = new ProdutoVendaBO();
		produtoMateriaPrimaBO = new ProdutoMateriaPrimaBO();
		pnlMenuLateral = new JPanel();
		
	}
	
	// Construtores
	
	public ManterOrdemProducao(TipoSolicitacao solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}
	
	
	// Métodos
	
	@Override
	public void abrirJanela() {
		
		btnGerarOC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new GeradorView(new OrdemProducaoVO(), null).abrirTela();
				
			}

		});
		
		
		btnConsultarProd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new ConsultarProdutosDialogView().abrirJanela(txtCodProdVenda.getText(), txtNomeFunc.getText());
				
				ProdutoMateriaPrimaVO receita = produtoMateriaPrimaBO.buscaReceitaPorIdProduto(1l);
				
				String[] registro = new String[4];
				
				registro[0] = receita.getMateriaPrima().getCodProduto();
				registro[1] = receita.getMateriaPrima().getDescricao();
				registro[2] = String.valueOf(receita.getQtde());
				
				modeloTabMatPrimas.addRow(registro);
				
			}
		});
		
		definicoesPagina();
		
	}

	@Override
	public void abrirJanela(OrdemProducaoVO ordemProducao) {
		
		setOrdemProducao(ordemProducao);
		
		txtCodProdVenda.setText(ordemProducao.getProdutoVenda().getCodProduto());
		txtProdVenda.setText(ordemProducao.getProdutoVenda().getDescricao());
		txtQtdeProdVenda.setText(ordemProducao.getQtde().toString());
		
		carregarGridReceita(getOrdemProducao().getProdutoVenda().getReceita());
		
		abrirJanela();
		
	}
	
	private void carregarGridReceita(List<ProdutoMateriaPrimaVO> receita) {

		modeloTabMatPrimas.setNumRows(0);
		
		// TODO - CONTINUAR AQUI
		
		System.out.println("Receita Nula? " + receita != null);
		
		if(receita != null){
			
			Iterator<ProdutoMateriaPrimaVO> iReceita = receita.iterator();
			
			System.out.println(iReceita.hasNext());
			
			while(iReceita.hasNext()){
				
				ProdutoMateriaPrimaVO produtoMateriaPrima = iReceita.next();
				
				String[] registro = new String[4];

				registro[0] = produtoMateriaPrima.getMateriaPrima().getCodProduto();
				registro[1] = produtoMateriaPrima.getMateriaPrima().getDescricao();
				registro[2] = produtoMateriaPrima.getQtde().toString();
				registro[3] = produtoMateriaPrima.getMateriaPrima().getEstoques().get(0).getQtdeAtual().toString();
				
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
		
		if(getOrdemProducao()!=null){
			btnConsultarProd.setVisible(false);
			txtCodProdVenda.setEditable(false);
		}
		
		txtProdVenda.setEditable(false);
		btnGerarOC.setEnabled(false);
		
		int widthCampos = this.getWidth() - 110;

		int espXLbl = 20;
		int espXTxt = 110;
		int espY = 20;
		int espEntre = 35;
		int altura = 30;

		pnlCampos.setBounds(10, 10, widthCampos, 480);
		pnlCampos.setLayout(null);
		pnlCampos.setBackground(Color.LIGHT_GRAY);
		
		cbxStatus.setBounds(espXLbl + 250, espY, 120, altura);

		lblStatus.setBounds(espXLbl + 200, espY, 50, altura);
		lblCodOp.setBounds(espXLbl, espY, 50, altura);
		lblProduto.setBounds(espXLbl, espY + espEntre * 2, 80, altura);
		lblFuncionario.setBounds(espXLbl + 350, espY + espEntre * 2, 80, altura );
		lblNomeFuncionario.setBounds(espXLbl + 350, espY + espEntre * 3, 80, altura);
		lblCodProdVenda.setBounds(espXLbl, espY + espEntre * 3, 50, altura);
		lblProdVenda.setBounds(espXLbl, espY + espEntre * 4, 50, altura);
		lblQtdeProdVenda.setBounds(espXLbl, espY + espEntre * 5, 80, altura);

		txtCodOp.setBounds(espXTxt, espY, 70, altura);
		txtCodProdVenda.setBounds(espXTxt, espY + espEntre * 3, 70, altura);
		txtProdVenda.setBounds(espXTxt, espY + espEntre * 4, 220, altura);
		txtQtdeProdVenda.setBounds(espXTxt, espY + espEntre * 5, 70, altura);
		txtNomeFunc.setBounds(espXTxt + 300, espY + espEntre * 3, 150, altura);

		
		btnConsultarProd.setBounds(190, espY + espEntre * 3, 100, altura);
		

		modeloTabMatPrimas.setColumnIdentifiers(new String[] {

				"Código", "Matéria-Prima", "Quantidade", "Estoque"

		});

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
		pnlCampos.add(txtCodProdVenda);
		pnlCampos.add(txtProdVenda);
		pnlCampos.add(txtQtdeProdVenda);
		pnlCampos.add(btnConsultarProd);
		pnlCampos.add(lblFuncionario);
		pnlCampos.add(lblNomeFuncionario);
		pnlCampos.add(txtNomeFunc);

		incluirComponenteCentro(pnlCampos);
		
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


}
