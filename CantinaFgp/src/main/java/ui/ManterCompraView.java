package ui;

import interfaces.ITelaBuscar;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.jdesktop.swingx.JXDatePicker;

import ui.templates.BuscarDialogView;
import ui.templates.ManterFrameView;
import utils.BancoFake;
import vo.CompraVO;
import vo.FormaPgtoVO;
import vo.FornecedorVO;
import vo.GenericVO;
import vo.ItemCompraVO;
import vo.ProdutoVO;
import vo.StatusVO;
import bo.CompraBO;
import bo.FormaPgtoBO;
import bo.StatusBO;
import enumeradores.TipoSolicitacao;
import enumeradores.TipoStatus;

public class ManterCompraView extends ManterFrameView<CompraVO> implements ITelaBuscar {
	
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

	private CompraBO compraBo;
	private StatusBO statusBo;
	private FormaPgtoBO formaPgtoBo;

	private CompraVO compra;
	private List<ItemCompraVO> listaItensCompra;
	private ProdutoVO produto;
	private FornecedorVO fornecedor;
	
	private List<StatusVO> listaStatus;
	private List<FormaPgtoVO> listaFormasPgto;
	
	private Double totalCompra;

	
	// Bloco de inicialização
	
	{
		
		int widthCampos = this.getWidth();

		pnlCampos = new JPanel();
		pnlCampos.setBounds(10, 10, widthCampos-25, 480);
		pnlCampos.setLayout(null);
		pnlCampos.setBackground(Color.LIGHT_GRAY);

		dtpDataCompra = new JXDatePicker(new Date());
		
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
		
		//
		
		btnAddProd.setEnabled(false);
		
		totalCompra = 0d;
				
		statusBo = new StatusBO();
		compraBo = new CompraBO();
		
		compra = new CompraVO();
		
		listaStatus = new ArrayList<StatusVO>();
		
		listaStatus = statusBo.consultarTodosStatus(TipoStatus.ORDEM_COMPRA);
		
		for (StatusVO statusVo : listaStatus) {
			
			cbxStatusCompra.addItem(statusVo.getDescricao());
			
		}
		cbxStatusCompra.setSelectedItem("Em Aberto");
		
		formaPgtoBo = new FormaPgtoBO();
		
		listaFormasPgto = formaPgtoBo.consultarTodasFormaPgto();
		
		for (FormaPgtoVO formaPgto : listaFormasPgto) {
			
			cbxFormaPgto.addItem(formaPgto.getDescricao());
			
		}
		
		listaItensCompra = new ArrayList<ItemCompraVO>();
				
		btnAddProd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(produto!=null){
					if(adicionarProduto(produto)){
						btnAddProd.setEnabled(false);
						txtCodProdCompra.setText("");
						txtProdCompra.setText("");
						txtQtdeProdCompra.setText("");
						txtValorProdCompra.setText("");
						
					}
				}
				
			}
			
		});
		
	}
	
	// Construtor

	protected ManterCompraView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}
	
	// Métodos
	
	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}

	@Override
	public void abrirJanela(CompraVO compra) {
		
		desabilitarCampos();
				
		txtCodOc.setText(compra.getCodCompra());
		dtpDataCompra.setDate(compra.getData());
		cbxStatusCompra.setSelectedItem(compra.getStatus().getDescricao());
		txtCodFornCompra.setText(compra.getFornecedor().getCodFornecedor());
		txtFornCompra.setText(compra.getFornecedor().getNome());
		cbxFormaPgto.setSelectedItem(compra.getFormaPgto().getDescricao());
		
		listaItensCompra = compra.getItensCompra();
		fornecedor = compra.getFornecedor();
				
		carregarGridItens(listaItensCompra);
		
		this.setVisible(true);
		
	}
	
	private Boolean adicionarProduto(ProdutoVO produto) {

		String qtdeTxt = txtQtdeProdCompra.getText();
		String valorTxt = txtValorProdCompra.getText();
		String prod = txtProdCompra.getText();

		if (compraBo.isCampoVazio(prod)) {
			JOptionPane.showMessageDialog(null, "Favor informar um produto", "Campo Vazio", JOptionPane.YES_OPTION);
		} 
		else {
			if (compraBo.isCampoVazio(qtdeTxt) || !compraBo.isQtdeValida(qtdeTxt)) {
				JOptionPane.showMessageDialog(null, "Favor informar uma quantidade válida", "Campo Vazio", JOptionPane.YES_OPTION);
			} 
			else {
				if (compraBo.isCampoVazio(valorTxt) || !compraBo.isValorValido(valorTxt)) {
					JOptionPane.showMessageDialog(null, "Favor informar um valor válido", "Campo Vazio", JOptionPane.YES_OPTION);
				} 
				else {
						Double qtde = Double.parseDouble(qtdeTxt);
						Double valor = Double.parseDouble(valorTxt);

						if (qtde <= 0) {

							JOptionPane.showMessageDialog(
											null,
											"Favor informar uma quantidade maior que zero",
											"Quantidade Inválida",
											JOptionPane.YES_OPTION);

						} 
						else {

							Double qtdeInserida = qtde;
							Double qtdeLista = 0d;
							int sizeLista = listaItensCompra.size();
							boolean produtoNaLista = false;

							for (int l = 0; l < sizeLista; l++) {

								if (produto.getCodProduto() == listaItensCompra.get(l).getProduto().getCodProduto()
										&& valor.toString().equals(listaItensCompra.get(l).getValor().toString())) {

									produtoNaLista = true;

									qtdeLista = listaItensCompra.get(l).getQtde();

									listaItensCompra.get(l).setQtde(qtdeLista + qtdeInserida);

								}

							}

							if (!produtoNaLista) {
								ItemCompraVO itemCompra = new ItemCompraVO();

								itemCompra.setProduto(produto);
								itemCompra.setQtde(qtde);
								itemCompra.setValor(valor);

								listaItensCompra.add(itemCompra);
							}

							carregarGridItens(listaItensCompra);

							return true;

						}
				}
			}
		}
		
		return false;
		
	}
		
	@Override
	public boolean incluir() {

		compra.setData(dtpDataCompra.getDate());
		compra.setStatus(listaStatus.get(cbxStatusCompra.getSelectedIndex()));
		compra.setFornecedor(fornecedor);
		compra.setItensCompra(listaItensCompra);

		CompraVO compraIncluida = compraBo.incluir(compra);

		if (compraIncluida != null) {

			txtCodOc.setText(compraIncluida.getCodCompra());

			JOptionPane.showMessageDialog(null, "Compra incluída");

			return true;

		}
		else{
			JOptionPane.showMessageDialog(null, "Falha ao incluir a compra", "Erro", JOptionPane.YES_OPTION);
		}
		
		return false;
		
	}
	
	@Override
	public boolean alterar() {
		
		if(compraBo.alterar(compra)){
			
			JOptionPane.showMessageDialog(null, "Compra alterada");		
			
			return true;
			
		}
		
		return false;
		
	}
	
	@Override
	public boolean isCamposValidos(StringBuilder msgErro){
		
		boolean isCamposValidos = true;
				
		if(!compraBo.isDataValida(dtpDataCompra.getDate())){
			msgErro.append("A data deve ser menor ou igual à data de hoje\n");
			isCamposValidos = false;
		}
		
		if(listaItensCompra == null || listaItensCompra.size() == 0){
			msgErro.append("Favor incluir ao menos um produto na compra\n");
			isCamposValidos = false;
		}
		
		if(fornecedor == null || fornecedor.getCodFornecedor() == null || fornecedor.getCodFornecedor().equals("")){
			msgErro.append("Favor informar o fornecedor\n");
			isCamposValidos = false;			
		}
		
		return isCamposValidos;
		
	}
	
	private void carregarGridItens(List<ItemCompraVO> itensCompra) {
		
		modeloTabItemCompra.setNumRows(0);
		
		Iterator<ItemCompraVO> iIc = itensCompra.iterator();
		
		Double total = 0d;
		totalCompra = 0d;
		
		while(iIc.hasNext()){
			
			ItemCompraVO ic = (ItemCompraVO) iIc.next();
			
			String[] registro = new String[5];

			registro[0] = ic.getProduto().getCodProduto();
			registro[1] = ic.getProduto().getDescricao();
			registro[2] = ic.getQtde().toString();
			registro[3] = ic.getValor().toString();
			total = ic.getQtde() * ic.getValor();
			registro[4] = total.toString();
			
			modeloTabItemCompra.addRow(registro);	
			
			totalCompra += total;
			
		}
		
		String rsTotal = "R$ " + totalCompra.toString();
		
		lblValorTotal.setText(rsTotal);
				
	}

	@Override
	protected void limparCampos() {
		
	}

	@Override
	protected boolean habilitarCampos() {
		
		dtpDataCompra.setEditable(true);
		cbxStatusCompra.setEnabled(true);
		btnBuscarProd.setEnabled(true);
		txtCodOc.setEditable(true);	
		txtCodProdCompra.setEditable(true);
		txtQtdeProdCompra.setEditable(true);
		txtValorProdCompra.setEditable(true);
		btnBuscarForn.setEnabled(true);
		txtCodFornCompra.setEditable(true);
		cbxFormaPgto.setEnabled(true);
		
		return true;
	}
	
	@Override
	protected boolean desabilitarCampos(){
		
		dtpDataCompra.setEditable(false);
		cbxStatusCompra.setEnabled(false);
		btnBuscarProd.setEnabled(false);
		txtCodOc.setEditable(false);
		txtCodProdCompra.setEditable(false);
		txtQtdeProdCompra.setEditable(false);
		txtValorProdCompra.setEditable(false);
		btnAddProd.setEnabled(false);
		btnBuscarForn.setEnabled(false);
		txtCodFornCompra.setEditable(false);
		cbxFormaPgto.setEnabled(false);
		
		return true;
	}

	// Métodos ITelaBuscar

	@Override
	public List<GenericVO> buscarItem(Map<String, String> parametros) {
				
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
				
		if(item instanceof ProdutoVO){
						
			produto = (ProdutoVO) item; 
			
			txtCodProdCompra.setText(produto.getCodProduto());
			txtProdCompra.setText(produto.getDescricao());
			
			btnAddProd.setEnabled(true);
			
		}
		else{
			if(item instanceof FornecedorVO){
			
				fornecedor = (FornecedorVO) item;
				
				txtCodFornCompra.setText(fornecedor.getCodFornecedor());
				txtFornCompra.setText(fornecedor.getNome());
				
			}
			
		}
		
	}

	@Override
	public String[] carregarGridTelaBusca(GenericVO item) {
				
		if(item instanceof ProdutoVO){
			
			ProdutoVO produto = (ProdutoVO) item; 
			
			String[] registro = new String[3];

			registro[0] = produto.getCodProduto();
			registro[1] = produto.getDescricao();
			registro[2] = produto.getPrecoCusto().toString();
			
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
