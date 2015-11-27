package ui.templates;

import interfaces.ITelaBuscar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BtnBuscar extends JButton {

	private ITelaBuscar tela;
		
	public BtnBuscar(ITelaBuscar tela) {
		super("Buscar");
		this.tela = tela;
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BuscarDialogView(BtnBuscar.this.tela).abrirJanela();
			}
		});
	}
}  
	
