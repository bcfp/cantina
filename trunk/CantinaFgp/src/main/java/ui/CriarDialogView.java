package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import vo.GenericVO;

public abstract class CriarDialogView<T extends GenericVO> extends JDialog {
	
	private JButton btnGravar;
	
	public CriarDialogView() {

		botaoGravar();

	}
	
	protected abstract boolean gravar();

	private void botaoGravar(){
		
		btnGravar = new JButton("Gravar");
		
		btnGravar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				CriarDialogView.this.gravar();
				
			}
			
		});

		btnGravar.setBounds(10, 10, 120, 40);
		btnGravar.setVisible(true);
		this.add(btnGravar);
		
	}
	
}
