package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.InscritosController;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class TelaManual extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfInscritosNomeDisciplina;
	
	JPanel pnlLogo = new JPanel();
	JLabel lblLogo = new JLabel();

	public TelaManual() {
		setTitle("Manual do Usuário");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 860, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 480, 640));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/LogoCps.png"));
		Image scaledImage = originalIcon.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
		contentPane.setLayout(null);
		
		pnlLogo.setBounds(734, 11, 100, 80);
		pnlLogo.setLayout(null);
		contentPane.add(pnlLogo);
		lblLogo.setBounds(0, 0, 100, 80);
		pnlLogo.add(lblLogo);
		lblLogo.setIcon(new ImageIcon(scaledImage));
		
		JLabel lblNewLabel = new JLabel("Seja bem vindo(a) ao Sistema de Contratação de Docentes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 704, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Aqui está um breve passo a passo de como algumas coisas funcionam.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 41, 574, 37);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cadastros");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 88, 100, 26);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("* Para cadastrar uma disciplina:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_3.setBounds(10, 124, 666, 26);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("O campo \"Código Disciplina\" deverá conter 3 caracteres numéricos, ordenados por:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(10, 145, 666, 20);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("113");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_3_1_1.setBounds(53, 185, 57, 26);
		contentPane.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Primeiro dígito - Código do curso (versão numérica)");
		lblNewLabel_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1_1_1.setBounds(150, 175, 383, 20);
		contentPane.add(lblNewLabel_3_1_1_1);
		
		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("Segundo e terceiro dígito - Código da disciplina");
		lblNewLabel_3_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1_1_1_1.setBounds(150, 199, 526, 20);
		contentPane.add(lblNewLabel_3_1_1_1_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("* Para cadastrar um curso:");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_3_2.setBounds(10, 229, 666, 26);
		contentPane.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("O campo \"Código Curso\" deverá conter 3 dígitos de letras maiúsculas");
		lblNewLabel_3_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1_2.setBounds(10, 250, 666, 20);
		contentPane.add(lblNewLabel_3_1_2);
		
		JLabel lblNewLabel_3_1_1_2 = new JLabel("ADS");
		lblNewLabel_3_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_3_1_1_2.setBounds(53, 290, 57, 26);
		contentPane.add(lblNewLabel_3_1_1_2);
		
		JLabel lblNewLabel_3_1_1_1_2 = new JLabel("Análise e Desenvolvimento de Sistemas");
		lblNewLabel_3_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1_1_1_2.setBounds(150, 296, 383, 20);
		contentPane.add(lblNewLabel_3_1_1_1_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Atualizações");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(10, 353, 100, 26);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1_3 = new JLabel("Para atualizar um item, primeiro digite o campo do item que deseja localizar (ex.: Nome do professor = Pedro), ");
		lblNewLabel_3_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1_3.setBounds(10, 389, 836, 20);
		contentPane.add(lblNewLabel_3_1_3);
		
		JLabel lblNewLabel_3_1_3_1 = new JLabel("e clique em Atualizar. Depois, caso o item tenha sido encontrado pelo sistema, digite nos campos os dados de acordo com o que ");
		lblNewLabel_3_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1_3_1.setBounds(10, 409, 836, 20);
		contentPane.add(lblNewLabel_3_1_3_1);
		
		JLabel lblNewLabel_3_1_3_1_1 = new JLabel("deseja substituir. Digite apenas o que deseja substituir, pois os campos em branco serão preenchidos pelo valor antigo.");
		lblNewLabel_3_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1_3_1_1.setBounds(10, 431, 836, 20);
		contentPane.add(lblNewLabel_3_1_3_1_1);
		
		
	}
}