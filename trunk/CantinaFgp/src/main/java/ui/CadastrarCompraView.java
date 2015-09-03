package ui;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vo.CompraVO;
import vo.ItemCompraVO;

public class CadastrarCompraView extends CadastrarDialogView<CompraVO> {

	protected CadastrarCompraView() {
		super("Nova Compra");
		abrirJanela();
	}
	
	protected CadastrarCompraView(List<ItemCompraVO> itensCompra) {
		super("Nova Compra");
		abrirJanela();
	}
	
	// TODO CONTINUAR DAQUI, COLOCAR TABELA PARA LISTA DE ITEM-COMPRA
	
	private JTable tabItemCompra;
	private DefaultTableModel modeloTabItemCompra;
	private JScrollPane barraTabItemCompra;
	
	
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
	
	

}
