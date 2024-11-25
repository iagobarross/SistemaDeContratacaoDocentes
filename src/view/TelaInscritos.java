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

public class TelaInscritos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfInscritosNomeDisciplina;
	
	JPanel pnlLogo = new JPanel();
	JLabel lblLogo = new JLabel();

	public TelaInscritos() {
		setTitle("Classificação de Inscritos");
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
		
		JLabel lblDisciplinaNome = new JLabel("Disciplina");
		lblDisciplinaNome.setToolTipText("Nome da Disciplina");
		lblDisciplinaNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaNome.setBounds(10, 22, 59, 17);
		contentPane.add(lblDisciplinaNome);
		
		tfInscritosNomeDisciplina = new JTextField();
		tfInscritosNomeDisciplina.setColumns(10);
		tfInscritosNomeDisciplina.setBounds(109, 22, 200, 20);
		contentPane.add(tfInscritosNomeDisciplina);
		
		JButton btnInscritosListar = new JButton("Listar Inscritos");
		btnInscritosListar.setBounds(10, 85, 157, 53);
		contentPane.add(btnInscritosListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 150, 824, 440);
		contentPane.add(scrollPane);
		
		JTextArea taInscritos = new JTextArea();
		taInscritos.setEditable(false);
		taInscritos.setFont(new Font("Monospaced", Font.PLAIN, 14));
		scrollPane.setViewportView(taInscritos);
		
		JButton btnProcessosLimpar = new JButton("Limpar");
		btnProcessosLimpar.setBounds(745, 124, 89, 23);
		contentPane.add(btnProcessosLimpar);
		
		InscritosController inscCont =new InscritosController(tfInscritosNomeDisciplina, taInscritos);
		
		btnInscritosListar.addActionListener(inscCont);
		btnProcessosLimpar.addActionListener(inscCont);
	}
}
