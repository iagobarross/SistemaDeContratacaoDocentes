package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CursoController;
import controller.DisciplinaController;
import controller.ProfessorController;

import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfDisciplinaNome;
	private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaHora;
	private JTextField tfDisciplinaTotalHoras;
	private JTextField tfDisciplinaCodigoCurso;
	private JTextField tfCursoNome;
	private JTextField tfCursoCodigo;
	private JTextField tfProfessorNome;
	private JTextField tfProfessorCPF;
	private JTextField tfProfessorPontos;
	private JTextField tfInscricoesNomeDisciplina;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela() {
		setTitle("Processo Seletivo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 480, 640));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 624, 441);
		contentPane.add(tabbedPane);
		
		JPanel disciplinas = new JPanel();
		disciplinas.setToolTipText("Controle de Disciplinas");
		tabbedPane.addTab("Disciplinas", null, disciplinas, null);
		disciplinas.setLayout(null);
		
		JLabel lblDisciplinaNome = new JLabel("Disciplina");
		lblDisciplinaNome.setBounds(10, 17, 59, 17);
		lblDisciplinaNome.setToolTipText("Nome da Disciplina");
		lblDisciplinaNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		disciplinas.add(lblDisciplinaNome);
		
		tfDisciplinaNome = new JTextField();
		tfDisciplinaNome.setBounds(79, 17, 200, 20);
		disciplinas.add(tfDisciplinaNome);
		tfDisciplinaNome.setColumns(10);
		
		JLabel lblDisciplinaCodigo = new JLabel("Código");
		lblDisciplinaCodigo.setBounds(10, 48, 59, 17);
		lblDisciplinaCodigo.setToolTipText("Código da Disciplina");
		lblDisciplinaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		disciplinas.add(lblDisciplinaCodigo);
		
		tfDisciplinaCodigo = new JTextField();
		tfDisciplinaCodigo.setBounds(79, 48, 101, 20);
		tfDisciplinaCodigo.setColumns(10);
		disciplinas.add(tfDisciplinaCodigo);
		
		JLabel lblDisciplinaDiaSemana = new JLabel("Dia da Semana");
		lblDisciplinaDiaSemana.setBounds(10, 79, 91, 14);
		lblDisciplinaDiaSemana.setToolTipText("Dia da Semana à lecionar a Disciplina");
		lblDisciplinaDiaSemana.setFont(new Font("Tahoma", Font.PLAIN, 14));
		disciplinas.add(lblDisciplinaDiaSemana);
		
		JComboBox<String> cbDisciplinaDiaSemana = new JComboBox<>();
		cbDisciplinaDiaSemana.setBounds(109, 77, 138, 22);
		disciplinas.add(cbDisciplinaDiaSemana);
		
		JLabel lblDisciplinaHora = new JLabel("Hora Inicial");
		lblDisciplinaHora.setBounds(315, 49, 70, 14);
		lblDisciplinaHora.setToolTipText("Horário que inicia a aula");
		lblDisciplinaHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		disciplinas.add(lblDisciplinaHora);
		
		tfDisciplinaHora = new JTextField();
		tfDisciplinaHora.setBounds(399, 48, 86, 20);
		disciplinas.add(tfDisciplinaHora);
		tfDisciplinaHora.setColumns(10);
		
		JLabel lblDisciplinaTotalHoras = new JLabel("Total de Horas Diárias");
		lblDisciplinaTotalHoras.setBounds(315, 77, 133, 14);
		lblDisciplinaTotalHoras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaTotalHoras.setToolTipText("Total de Horas por dia");
		disciplinas.add(lblDisciplinaTotalHoras);
		
		tfDisciplinaTotalHoras = new JTextField();
		tfDisciplinaTotalHoras.setBounds(453, 75, 51, 20);
		disciplinas.add(tfDisciplinaTotalHoras);
		tfDisciplinaTotalHoras.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 174, 584, 226);
		disciplinas.add(scrollPane);
		
		JTextArea taDisciplina = new JTextArea();
		taDisciplina.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPane.setViewportView(taDisciplina);
		
		JLabel lblDisciplinaCodigoCurso = new JLabel("Código do Curso");
		lblDisciplinaCodigoCurso.setBounds(315, 14, 105, 22);
		lblDisciplinaCodigoCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		disciplinas.add(lblDisciplinaCodigoCurso);
		
		tfDisciplinaCodigoCurso = new JTextField();
		tfDisciplinaCodigoCurso.setBounds(430, 16, 86, 20);
		disciplinas.add(tfDisciplinaCodigoCurso);
		tfDisciplinaCodigoCurso.setColumns(10);
		
		JButton btnDisciplinaLimpar = new JButton("Limpar");
		btnDisciplinaLimpar.setBounds(511, 146, 89, 23);
		disciplinas.add(btnDisciplinaLimpar);
		
		JButton btnDisciplinaBuscar = new JButton("Buscar");
		btnDisciplinaBuscar.setBounds(16, 128, 106, 35);
		disciplinas.add(btnDisciplinaBuscar);
		btnDisciplinaBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnDisciplinaCadastrar = new JButton("Cadastrar");
		btnDisciplinaCadastrar.setBounds(151, 128, 106, 35);
		disciplinas.add(btnDisciplinaCadastrar);
		btnDisciplinaCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnDisciplinaDeletar = new JButton("Deletar");
		btnDisciplinaDeletar.setBounds(290, 128, 106, 35);
		disciplinas.add(btnDisciplinaDeletar);
		btnDisciplinaDeletar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		cbDisciplinaDiaSemana.addItem("");
		cbDisciplinaDiaSemana.addItem("Segunda-Feira");
		cbDisciplinaDiaSemana.addItem("Terça-Feira");
		cbDisciplinaDiaSemana.addItem("Quarta-Feira");
		cbDisciplinaDiaSemana.addItem("Quinta-Feira");
		cbDisciplinaDiaSemana.addItem("Sexta-Feira");
		cbDisciplinaDiaSemana.addItem("Sábado");
		
		JPanel cursos = new JPanel();
		cursos.setToolTipText("Controle de Cursos");
		tabbedPane.addTab("Cursos", null, cursos, null);
		cursos.setLayout(null);
		
		JLabel lblCursoNome = new JLabel("Curso");
		lblCursoNome.setToolTipText("Nome do Curso");
		lblCursoNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCursoNome.setBounds(15, 16, 46, 18);
		cursos.add(lblCursoNome);
		
		tfCursoNome = new JTextField();
		tfCursoNome.setBounds(71, 14, 141, 20);
		cursos.add(tfCursoNome);
		tfCursoNome.setColumns(10);
		
		JLabel lblCursoCodigo = new JLabel("Código");
		lblCursoCodigo.setToolTipText("Código do Curso");
		lblCursoCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCursoCodigo.setBounds(15, 46, 46, 23);
		cursos.add(lblCursoCodigo);
		
		tfCursoCodigo = new JTextField();
		tfCursoCodigo.setBounds(72, 48, 86, 20);
		cursos.add(tfCursoCodigo);
		tfCursoCodigo.setColumns(10);
		
		JLabel lblCursoAreaConhecimento = new JLabel("Área do conhecimento");
		lblCursoAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCursoAreaConhecimento.setBounds(15, 79, 141, 20);
		cursos.add(lblCursoAreaConhecimento);
		
		JComboBox<String> cbCursoAreaConhecimento = new JComboBox<>();
		cbCursoAreaConhecimento.setBounds(160, 79, 186, 23);
		cursos.add(cbCursoAreaConhecimento);
		
		cbCursoAreaConhecimento.addItem("");
		cbCursoAreaConhecimento.addItem("Ciências Exatas e da Terra");
		cbCursoAreaConhecimento.addItem("Ciências Biológicas");
		cbCursoAreaConhecimento.addItem("Engenharias");
		cbCursoAreaConhecimento.addItem("Ciências da Saúde");
		cbCursoAreaConhecimento.addItem("Ciências Agrárias");
		cbCursoAreaConhecimento.addItem("Linguística, Letras e Artes");
		cbCursoAreaConhecimento.addItem("Ciências Sociais Aplicadas");
		cbCursoAreaConhecimento.addItem("Ciências Humanas");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(16, 174, 584, 226);
		cursos.add(scrollPane_1);
		
		JTextArea taCurso = new JTextArea();
		scrollPane_1.setViewportView(taCurso);
		
		JButton btnCursoCadastrar = new JButton("Cadastrar");
		btnCursoCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoCadastrar.setBounds(151, 128, 106, 35);
		cursos.add(btnCursoCadastrar);
		
		JButton btnCursoBuscar = new JButton("Buscar");
		btnCursoBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoBuscar.setBounds(16, 128, 106, 35);
		cursos.add(btnCursoBuscar);
		
		JButton btnCursoLimpar = new JButton("Limpar");
		btnCursoLimpar.setBounds(511, 146, 89, 23);
		cursos.add(btnCursoLimpar);
		
		JButton btnCursoDeletar = new JButton("Deletar");
		btnCursoDeletar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoDeletar.setBounds(290, 128, 106, 35);
		cursos.add(btnCursoDeletar);
		
		JPanel professores = new JPanel();
		professores.setToolTipText("Controle de Professores");
		tabbedPane.addTab("Professores", null, professores, null);
		professores.setLayout(null);
		
		JLabel lblProfessorNome = new JLabel("Nome");
		lblProfessorNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorNome.setToolTipText("Nome do Professor");
		lblProfessorNome.setBounds(10, 11, 54, 27);
		professores.add(lblProfessorNome);
		
		tfProfessorNome = new JTextField();
		tfProfessorNome.setBounds(59, 16, 165, 20);
		professores.add(tfProfessorNome);
		tfProfessorNome.setColumns(10);
		
		JLabel lblProfessorCPF = new JLabel("CPF");
		lblProfessorCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorCPF.setToolTipText("CPF do Professor");
		lblProfessorCPF.setBounds(10, 49, 36, 14);
		professores.add(lblProfessorCPF);
		
		tfProfessorCPF = new JTextField();
		tfProfessorCPF.setBounds(59, 49, 165, 20);
		professores.add(tfProfessorCPF);
		tfProfessorCPF.setColumns(10);
		
		JLabel lblProfessorAreaConhecimento = new JLabel("Área do Conhecimento");
		lblProfessorAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorAreaConhecimento.setBounds(10, 83, 145, 14);
		professores.add(lblProfessorAreaConhecimento);
		
		JComboBox<String> cbProfessorAreaConhecimento = new JComboBox<>();
		cbProfessorAreaConhecimento.setBounds(165, 80, 158, 20);
		professores.add(cbProfessorAreaConhecimento);
		
		JLabel lblProfessorPontos = new JLabel("Pontos");
		lblProfessorPontos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorPontos.setBounds(372, 19, 54, 14);
		professores.add(lblProfessorPontos);
		
		tfProfessorPontos = new JTextField();
		tfProfessorPontos.setBounds(436, 16, 54, 20);
		professores.add(tfProfessorPontos);
		tfProfessorPontos.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(16, 174, 584, 226);
		professores.add(scrollPane_2);
		
		JTextArea taProfessor = new JTextArea();
		scrollPane_2.setViewportView(taProfessor);
		
		JButton btnProfessorCadastrar = new JButton("Cadastrar");
		btnProfessorCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorCadastrar.setBounds(151, 128, 106, 35);
		professores.add(btnProfessorCadastrar);
		
		JButton btnProfessorBuscar = new JButton("Buscar");
		btnProfessorBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorBuscar.setBounds(16, 128, 106, 35);
		professores.add(btnProfessorBuscar);
		
		JButton btnProfessorDeletar = new JButton("Deletar");
		btnProfessorDeletar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorDeletar.setBounds(290, 128, 106, 35);
		professores.add(btnProfessorDeletar);
		
		JButton btnProfessorLimpar = new JButton("Limpar");
		btnProfessorLimpar.setBounds(511, 146, 89, 23);
		professores.add(btnProfessorLimpar);
		
		JPanel inscricoes = new JPanel();
		inscricoes.setToolTipText("Controle de Inscrições");
		tabbedPane.addTab("Inscrições", null, inscricoes, null);
		inscricoes.setLayout(null);
		
		JLabel lblInscricoesNomeDisciplina = new JLabel("Nome da Disciplina");
		lblInscricoesNomeDisciplina.setToolTipText("Nome da Disciplina");
		lblInscricoesNomeDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInscricoesNomeDisciplina.setBounds(12, 12, 118, 16);
		inscricoes.add(lblInscricoesNomeDisciplina);
		
		tfInscricoesNomeDisciplina = new JTextField();
		tfInscricoesNomeDisciplina.setBounds(134, 11, 165, 20);
		inscricoes.add(tfInscricoesNomeDisciplina);
		tfInscricoesNomeDisciplina.setColumns(10);
		
		JLabel lblInscricoesInscritos = new JLabel("Inscritos:");
		lblInscricoesInscritos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInscricoesInscritos.setBounds(12, 68, 56, 16);
		inscricoes.add(lblInscricoesInscritos);
		
		JButton btnInscricoesBuscar = new JButton("Buscar");
		btnInscricoesBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricoesBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInscricoesBuscar.setBounds(374, 12, 106, 35);
		inscricoes.add(btnInscricoesBuscar);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 95, 597, 307);
		inscricoes.add(scrollPane_3);
		
		JTextArea taInscricoesInscritos = new JTextArea();
		scrollPane_3.setViewportView(taInscricoesInscritos);
		
		DisciplinaController discCont = new DisciplinaController(tfDisciplinaCodigo, tfDisciplinaNome, cbDisciplinaDiaSemana, tfDisciplinaHora, tfDisciplinaTotalHoras, tfDisciplinaCodigoCurso, taDisciplina);
		btnDisciplinaCadastrar.addActionListener(discCont);
		btnDisciplinaBuscar.addActionListener(discCont);
		btnDisciplinaDeletar.addActionListener(discCont);
		btnDisciplinaLimpar.addActionListener(discCont);
		
		CursoController cursoCont = new CursoController(tfCursoCodigo, tfCursoNome, cbCursoAreaConhecimento, taCurso);
		btnCursoCadastrar.addActionListener(cursoCont);
		btnCursoBuscar.addActionListener(cursoCont);
		btnCursoDeletar.addActionListener(cursoCont);
		btnCursoLimpar.addActionListener(cursoCont);
		
		
		ProfessorController profCont = new ProfessorController(tfProfessorCPF, tfProfessorNome, cbProfessorAreaConhecimento, tfProfessorPontos, taProfessor);
		btnProfessorCadastrar.addActionListener(profCont);
		btnProfessorBuscar.addActionListener(profCont);
		btnProfessorDeletar.addActionListener(profCont);
		btnProfessorLimpar.addActionListener(profCont);
		
		
		
	}
}
