package view;

import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class TelaProcessos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	JPanel pnlLogo = new JPanel();
	JLabel lblLogo = new JLabel();


	public TelaProcessos() {
		setTitle("Processos Abertos");
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 150, 824, 440);
		contentPane.add(scrollPane);
		
		JTextArea taProcessos = new JTextArea();
		taProcessos.setEditable(false);
		scrollPane.setViewportView(taProcessos);
		
	}
}
