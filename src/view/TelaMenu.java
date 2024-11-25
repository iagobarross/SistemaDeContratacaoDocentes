package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class TelaMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	JPanel pnlLogo = new JPanel();
	JLabel lblLogo = new JLabel();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenu frame = new TelaMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaMenu() {
		setTitle("Sistema de Contratação de Docentes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 631, 359);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 480, 640));
		setContentPane(contentPane);

		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/LogoCps.png"));
		Image scaledImage = originalIcon.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
		contentPane.setLayout(null);
		pnlLogo.setBounds(10, 11, 100, 80);
		pnlLogo.setLayout(null);
		contentPane.add(pnlLogo);
		lblLogo.setBounds(0, 0, 100, 80);
		pnlLogo.add(lblLogo);
		lblLogo.setIcon(new ImageIcon(scaledImage));

		JButton btnTela = new JButton("Controle de Dados");
		btnTela.setBounds(10, 197, 173, 40);
		btnTela.setToolTipText("Ir para Tela de Processo Seletivo");
		btnTela.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnTela);

		JButton btnInscritos = new JButton("Inscritos");
		btnInscritos.setToolTipText("Consulta de Inscritos por Disciplina");
		btnInscritos.setBounds(222, 197, 173, 40);
		btnInscritos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnInscritos);
		
		JLabel lblTelaMenu = new JLabel("Menu Principal");
		lblTelaMenu.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTelaMenu.setBounds(244, 58, 136, 21);
		contentPane.add(lblTelaMenu);
		
		JButton btnProcessos = new JButton("Processos");
		btnProcessos.setToolTipText("Cursos com processos abertos");
		btnProcessos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProcessos.setBounds(432, 197, 173, 40);
		contentPane.add(btnProcessos);

		btnTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela tela = new Tela();
				tela.setVisible(true);
				
			}
		});

		btnInscritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInscritos TelaInscritos = new TelaInscritos();
				TelaInscritos.setVisible(true);
			}
		});
		
		btnProcessos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaProcessos TelaProcessos = new TelaProcessos();
				TelaProcessos.setVisible(true);
			}
		});
	}
}
