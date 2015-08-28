package ui;

import interfaces.ITelaConsultar;
import interfaces.ITelaDetalhar;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vo.GenericVO;

public class DialogConfirmacaoView<T extends GenericVO> extends JDialog {

	private JPanel panel;
	private JButton btnDetalhar;
	private JButton btnExcluir;
	private T objeto;
	private ITelaConsultar<T> telaConsultar;
	private ITelaDetalhar<T> telaDetalhar;

	// BLOCO DE INICIALIZAÇÃO

	{

		panel = new JPanel();
		btnDetalhar = new JButton();
		btnExcluir = new JButton();

	}

	// O GENERIC TYPE É O MESMO TIPO PQ O OBJETO SERÁ O MESMO DA SUA TELA

	/**
	 * Método que irá abrir a janela de opções para Detalhar, Alterar e Excluir
	 * um objeto
	 * 
	 * A responsabilidade de alteração e exclusão do objeto é de quem está
	 * chamando, utilização de CallBack
	 * 
	 * @param objeto
	 *            T objeto = Generic Type do objeto que será excluído
	 * @param telaConsultar
	 *            ITelaConsultar<T> tela = Janela que implementa a Interface
	 *            ITelaCrud
	 * @param telaAlterar
	 *            = Instância de alterar o livro
	 */

	public void abrirJanela(T objeto, ITelaConsultar<T> telaConsultar, ITelaDetalhar<T> telaDetalhar) {

		this.objeto = objeto;
		this.telaConsultar = telaConsultar;
		this.telaDetalhar = telaDetalhar;

		// botões

		btnDetalhar.setText("Detalhar");
		btnExcluir.setText("Excluir");

		// ***** EVENTOS

		// * btnDetalhar

		btnDetalhar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DialogConfirmacaoView.this.telaDetalhar.abrirJanela(DialogConfirmacaoView.this.objeto);

			}

		});

		// * btnExcluir

		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int opcao = JOptionPane.showOptionDialog(null,
						"Deseja realmente excluir?","Exclusão",
						JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, null,
						null, null);

				if (opcao == JOptionPane.YES_OPTION) {

					// CALL BACK - CHAMANDO O MÉTODO DELETAR DO ConsultarBiblioView(tela) de volta

					DialogConfirmacaoView.this.telaConsultar.deletar(DialogConfirmacaoView.this.objeto);

					DialogConfirmacaoView.this.dispose();

				}

			}

		});

		definicoesPagina();

	}

	private void definicoesPagina() {

		panel.setLayout(new GridLayout(1, 3, 5, 20));

		panel.add(btnDetalhar);
		panel.add(btnExcluir);

		panel.setVisible(true);
		panel.setBounds(20, 30, 260, 30);

		this.add(panel);

		this.setLayout(null);
		this.setTitle("Escolha uma ação");
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(300, 125);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

}
