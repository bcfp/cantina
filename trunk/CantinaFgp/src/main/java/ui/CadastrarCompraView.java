package ui;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vo.CompraVO;
import vo.ItemCompraVO;

public class CadastrarCompraView extends CadastrarDialogView<CompraVO> {
	
	private JPanel pnlCampos;
	private JTable tabItemCompra;
	private DefaultTableModel modeloTabItemCompra;
	private JScrollPane barraTabItemCompra;

	
	{
		
		int widthCampos = this.getWidth();
		int yPnlCampos = 110;
		
		pnlCampos = new JPanel();
		pnlCampos.setBounds(yPnlCampos,10,widthCampos-yPnlCampos-20,480);
		pnlCampos.setLayout(null);
		pnlCampos.setBackground(Color.LIGHT_GRAY);
		
		tabItemCompra = new JTable();
		modeloTabItemCompra = new DefaultTableModel(){
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		
		modeloTabItemCompra.setColumnIdentifiers(new String[]{
				
				"Código",
				"Produto",
				"Quantidade",
				"Valor",
				"Total"
				
		});
		
		tabItemCompra.setModel(modeloTabItemCompra);
		
		barraTabItemCompra = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		barraTabItemCompra.setViewportView(tabItemCompra);
		
		barraTabItemCompra.setBounds(10, 240, pnlCampos.getWidth()-20, 230);
		
		pnlCampos.add(barraTabItemCompra);
		
		incluirComponenteCentro(pnlCampos);	
		
	}
	
	protected CadastrarCompraView() {
		super("Nova Compra");
		abrirJanela();
	}
	
	protected CadastrarCompraView(List<ItemCompraVO> itensCompra) {
		super("Nova Compra");
		carregarGridItens(itensCompra);
		abrirJanela();
	}
	
	
	@Override
	public void abrirJanela() {

		this.setVisible(true);
		
	}

	@Override
	public boolean cadastrar(CompraVO compra) {
			
		return false;
		
	}

	@Override
	protected boolean botaoGravar() {
		JOptionPane.showMessageDialog(null, "Compra Salva");
		return false;
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
	
	

}
