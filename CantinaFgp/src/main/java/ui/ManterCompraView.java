package ui;

import interfaces.ITelaBuscar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.jdesktop.swingx.JXDatePicker;

import ui.templates.BuscarDialogView;
import ui.templates.ManterPanelView;
import utils.BancoFake;
import vo.CompraVO;
import vo.FornecedorProdutoVO;
import vo.FornecedorVO;
import vo.GenericVO;
import vo.ItemCompraVO;
import vo.ProdutoMateriaPrimaVO;
import vo.ProdutoVO;
import vo.ProdutoVendaVO;
import vo.StatusVO;
import enumeradores.TipoSolicitacao;

public class ManterCompraView extends ManterPanelView<CompraVO> implements ITelaBuscar {
	
	// Atributos Tela
	
	private JComboBox<String> cbxStatusCompra;
	private JComboBox<String> cbxFormaPgto;
	
	private JXDatePicker dtpDataCompra;
	
	private JTextField txtCodOc;
	private JTextField txtCodProdCompra;
	private JTextField txtProdCompra;
	private JTextField txtQtdeProdCompra;
	private JTextField txtValorProdCompra;
	private JTextField txtCodFornCompra;
	private JTextField txtFornCompra;
	
	private JLabel lblFormaPgto;
	private JLabel lblStatusCompra;
	private JLabel lblDataCompra;
	private JLabel lblCodOc;
	private JLabel lblCodProdCompra;
	private JLabel lblProdCompra;
	private JLabel lblQtdeProdCompra;
	private JLabel lblTitProduto;
	private JLabel lblValorProdCompra;
	private JLabel lblTitFornecedor;
	private JLabel lblCodFornCompra;
	private JLabel lblFornCompra;
	private JLabel lblValorTotal;
	private JLabel lblTotal;
	private Font fonteTotal;
	
	private JButton btnBuscarProd;
	private JButton btnBuscarForn;
	private JButton btnAddProd;
	
	private JPanel pnlCampos;
	private JTable tabItemCompra;
	private DefaultTableModel modeloTabItemCompra;
	private JScrollPane barraTabItemCompra;
	
	private String acaoPesquisar;
	private static final String PESQ_FORNECEDOR = "fornecedor";
	private static final String PESQ_PRODUTO = "produto";
	
	private List<ItemCompraVO> listaItensCompra;

	
	// Bloco de inicialização
	
	{
		
		int widthCampos = this.getWidth();

		pnlCampos = new JPanel();
		pnlCampos.setBounds(10, 10, widthCampos-25, 480);
		pnlCampos.setLayout(null);
		pnlCampos.setBackground(Color.LIGHT_GRAY);

		dtpDataCompra = new JXDatePicker();
		
		cbxStatusCompra = new JComboBox<String>();
		cbxFormaPgto = new JComboBox<String>();

		lblFormaPgto = new JLabel("Pagamento");
		lblStatusCompra = new JLabel("Status");
		lblDataCompra = new JLabel("Data");
		lblCodOc = new JLabel("Número");
		lblCodProdCompra = new JLabel("Código");
		lblProdCompra = new JLabel("Produto");
		lblQtdeProdCompra = new JLabel("Quantidade");
		lblValorProdCompra = new JLabel("Valor");
		lblTitProduto = new JLabel("PRODUTO");
		lblTitFornecedor = new JLabel("FORNECEDOR");
		lblCodFornCompra = new JLabel("Código");
		lblFornCompra = new JLabel("Fornecedor");
		lblTotal = new JLabel("TOTAL");	
		fonteTotal = new Font("Verdana", Font.BOLD, 20);
		lblTotal.setFont(fonteTotal);
		lblValorTotal = new JLabel("R$ 0,00");
		lblValorTotal.setFont(fonteTotal);
		
		txtCodOc = new JTextField();
		txtCodProdCompra = new JTextField();
		txtProdCompra = new JTextField();
		txtQtdeProdCompra = new JTextField();
		txtValorProdCompra = new JTextField();
		txtCodFornCompra = new JTextField();
		txtFornCompra = new JTextField();
		
		txtCodOc.setEditable(false);
		txtProdCompra.setEditable(false);
		txtFornCompra.setEditable(false);

		btnBuscarForn = new JButton("Consultar");
		btnBuscarForn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				acaoPesquisar = PESQ_FORNECEDOR;
				
				new BuscarDialogView(ManterCompraView.this, new String[] {"Código", "Nome", "Contato"}).abrirJanela();
				
			}
			
		});
		
		btnBuscarProd = new JButton("Consultar");
		btnBuscarProd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				acaoPesquisar = PESQ_PRODUTO;
				
				new BuscarDialogView(ManterCompraView.this, new String[] {"Código", "Nome", "Valor de venda"}).abrirJanela();
												
			}
			
		});
		
		int espXLbl = 20;
		int espXTxt = espXLbl + 90;
		int	espXForn = 350;
		int espY = 20;
		int espEntre = 35;
		int altura = 30;
		
		lblCodOc.setBounds(espXLbl, espY, 50, altura);
		lblTitProduto.setBounds(espXLbl, espY + espEntre, 80, altura);
		lblCodProdCompra.setBounds(espXLbl, espY + espEntre * 2, 50, altura);
		lblProdCompra.setBounds(espXLbl, espY + espEntre * 3, 50, altura);
		lblQtdeProdCompra.setBounds(espXLbl, espY + espEntre * 4, 80, altura);
		lblValorProdCompra.setBounds(espXLbl, espY + espEntre * 5, 80, altura);

		txtCodOc.setBounds(espXTxt, espY, 70, altura);
		txtCodProdCompra.setBounds(espXTxt, espY + espEntre * 2, 70, altura);
		txtProdCompra.setBounds(espXTxt, espY + espEntre * 3, 220, altura);
		txtQtdeProdCompra.setBounds(espXTxt, espY + espEntre * 4, 70, altura);
		txtValorProdCompra.setBounds(espXTxt, espY + espEntre * 5, 70, altura);
		
		btnBuscarProd.setBounds(espXTxt + 80, espY + espEntre * 2, 100, altura);
		
		lblStatusCompra.setBounds(480, espY, 80, altura);
		cbxStatusCompra.setBounds(530, espY, 130, altura);
		
		lblDataCompra.setBounds(250, espY, 80, altura);
		dtpDataCompra.setBounds(290, espY, 140, altura);
		
		lblTitFornecedor.setBounds(espXLbl + espXForn, espY + espEntre, 80, altura);
		lblCodFornCompra.setBounds(espXLbl + espXForn, espY + espEntre * 2, 80, altura);
		lblFornCompra.setBounds(espXLbl + espXForn, espY + espEntre * 3, 80, altura);
		
		txtCodFornCompra.setBounds(espXTxt + espXForn, espY + espEntre * 2, 70, altura);
		txtFornCompra.setBounds(espXTxt + espXForn, espY + espEntre * 3, 200, altura);

		btnBuscarForn.setBounds(espXTxt + espXForn + 80, espY + espEntre * 2, 100, altura);
		
		lblFormaPgto.setBounds(espXLbl + espXForn, espY + espEntre * 4, 80, altura);
		cbxFormaPgto.setBounds(espXTxt + espXForn, espY + espEntre * 4, 120, altura);

		lblTotal.setBounds(espXLbl + espXForn, espY + espEntre * 5 + 5, 80, altura);
		lblValorTotal.setBounds(espXTxt + espXForn, espY + espEntre * 5 + 5, 120, altura);
		
		btnAddProd = new JButton("+");
		btnAddProd.setBounds(190, espY + espEntre * 5, 50, altura);

		tabItemCompra = new JTable();
		modeloTabItemCompra = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		modeloTabItemCompra.setColumnIdentifiers(new String[] {

				"Código", "Produto", "Quantidade", "Valor", "Total"

		});

		tabItemCompra.setModel(modeloTabItemCompra);

		barraTabItemCompra = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		barraTabItemCompra.setViewportView(tabItemCompra);

		barraTabItemCompra.setBounds(10, 240, pnlCampos.getWidth() - 20, 230);

		pnlCampos.add(barraTabItemCompra);
		pnlCampos.add(cbxStatusCompra);
		pnlCampos.add(dtpDataCompra);
		pnlCampos.add(cbxFormaPgto);
		pnlCampos.add(lblValorTotal);
		pnlCampos.add(lblTotal);
		pnlCampos.add(lblFormaPgto);
		pnlCampos.add(lblStatusCompra);
		pnlCampos.add(lblDataCompra);
		pnlCampos.add(lblCodOc);
		pnlCampos.add(lblTitProduto);
		pnlCampos.add(lblTitFornecedor);
		pnlCampos.add(lblCodFornCompra);
		pnlCampos.add(lblFornCompra);
		pnlCampos.add(txtFornCompra);
		pnlCampos.add(lblCodProdCompra);
		pnlCampos.add(lblProdCompra);
		pnlCampos.add(lblQtdeProdCompra);
		pnlCampos.add(lblValorProdCompra);
		pnlCampos.add(txtCodFornCompra);
		pnlCampos.add(txtCodOc);
		pnlCampos.add(txtCodProdCompra);
		pnlCampos.add(txtProdCompra);
		pnlCampos.add(txtQtdeProdCompra);
		pnlCampos.add(txtValorProdCompra);
		pnlCampos.add(btnBuscarProd);
		pnlCampos.add(btnBuscarForn);
		pnlCampos.add(btnAddProd);

		pnlCampos.setLayout(null);
		pnlCampos.setBackground(Color.LIGHT_GRAY);
		
		adicionarComponentesCentro(pnlCampos);

	}
	
	protected ManterCompraView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}
	
	
	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}

	@Override
	public void abrirJanela(CompraVO compra) {
		carregarGridItens(compra.getItensCompra());
		this.setVisible(true);
	}

	@Override
	public boolean incluir() {
		JOptionPane.showMessageDialog(null, "Compra incluída");
		return false;
	}

	@Override
	public boolean alterar() {
		JOptionPane.showMessageDialog(null, "Compra alterada");		
		return true;
	}

	@Override
	protected void limparCampos() {
		
	}
	
	private void carregarGridItens(List<ItemCompraVO> itensCompra) {
		
		modeloTabItemCompra.setNumRows(0);
		
		Iterator<ItemCompraVO> iIc = itensCompra.iterator();
		
		while(iIc.hasNext()){
			
			ItemCompraVO ic = (ItemCompraVO) iIc.next();
			
			String[] registro = new String[5];

			registro[0] = ic.getProduto().getCodProduto();
			registro[1] = ic.getProduto().getDescricao();
			registro[2] = ic.getQtde().toString();
			registro[3] = ic.getValor().toString();
			Double total = ic.getQtde() * ic.getValor();
			registro[4] = total.toString();
			
			modeloTabItemCompra.addRow(registro);	
			
		}
				
	}

	@Override
	protected boolean habilitarCampos() {
		return false;
	}

	// Métodos ITelaBuscar

	@Override
	public List<GenericVO> pesquisarItem(Map<String, String> parametros) {
				
		switch (acaoPesquisar) {
			
			case PESQ_PRODUTO:
				
				return BancoFake.listaProdutosGeneric;
				
			case PESQ_FORNECEDOR:
				
				return BancoFake.listaFornecedorGeneric;

		}
		
		return null;
	}


	@Override
	public void carregarItemSelecionado(GenericVO item) {
		
		
		if(item instanceof ProdutoVendaVO){
						
			ProdutoVendaVO produtoVenda = (ProdutoVendaVO) item; 
			
			txtCodProdCompra.setText(produtoVenda.getCodProduto());
			txtProdCompra.setText(produtoVenda.getDescricao());
			
		}
		else{
			if(item instanceof FornecedorVO){
			
				FornecedorVO fornecedor = (FornecedorVO) item;
				
				txtCodFornCompra.setText(fornecedor.getCodFornecedor());
				txtFornCompra.setText(fornecedor.getNome());
				
			}
			
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
		else{
			if(item instanceof FornecedorVO){
							
				FornecedorVO fornecedor = (FornecedorVO) item;
				
				String[] registro = new String[3];

				registro[0] = fornecedor.getCodFornecedor();
				registro[1] = fornecedor.getNome();
				registro[2] = fornecedor.getContato();
				
				return registro;
				
			}
			
		}
		
		return null;
	}

}
