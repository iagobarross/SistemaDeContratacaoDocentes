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
import javax.swing.SwingConstants;

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
		setBounds(100, 100, 860, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 480, 640));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 844, 590);
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
		tfDisciplinaNome.setBounds(109, 17, 200, 20);
		disciplinas.add(tfDisciplinaNome);
		tfDisciplinaNome.setColumns(10);
		
		JLabel lblDisciplinaCodigo = new JLabel("Código");
		lblDisciplinaCodigo.setBounds(10, 48, 59, 17);
		lblDisciplinaCodigo.setToolTipText("Código da Disciplina");
		lblDisciplinaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		disciplinas.add(lblDisciplinaCodigo);
		
		tfDisciplinaCodigo = new JTextField();
		tfDisciplinaCodigo.setBounds(109, 48, 101, 20);
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
		lblDisciplinaHora.setBounds(363, 49, 70, 14);
		lblDisciplinaHora.setToolTipText("Horário que inicia a aula");
		lblDisciplinaHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		disciplinas.add(lblDisciplinaHora);
		
		tfDisciplinaHora = new JTextField();
		tfDisciplinaHora.setBounds(536, 48, 86, 20);
		disciplinas.add(tfDisciplinaHora);
		tfDisciplinaHora.setColumns(10);
		
		JLabel lblDisciplinaTotalHoras = new JLabel("Total de Horas Diárias");
		lblDisciplinaTotalHoras.setBounds(364, 79, 133, 14);
		lblDisciplinaTotalHoras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaTotalHoras.setToolTipText("Total de Horas por dia");
		disciplinas.add(lblDisciplinaTotalHoras);
		
		tfDisciplinaTotalHoras = new JTextField();
		tfDisciplinaTotalHoras.setBounds(536, 78, 51, 20);
		disciplinas.add(tfDisciplinaTotalHoras);
		tfDisciplinaTotalHoras.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 174, 813, 388);
		disciplinas.add(scrollPane);
		
		JTextArea taDisciplina = new JTextArea();
		taDisciplina.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPane.setViewportView(taDisciplina);
		
		JLabel lblDisciplinaCodigoCurso = new JLabel("Código do Curso");
		lblDisciplinaCodigoCurso.setBounds(363, 14, 105, 22);
		lblDisciplinaCodigoCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		disciplinas.add(lblDisciplinaCodigoCurso);
		
		tfDisciplinaCodigoCurso = new JTextField();
		tfDisciplinaCodigoCurso.setBounds(536, 17, 86, 20);
		disciplinas.add(tfDisciplinaCodigoCurso);
		tfDisciplinaCodigoCurso.setColumns(10);
		
		JButton btnDisciplinaLimpar = new JButton("Limpar");
		btnDisciplinaLimpar.setBounds(740, 147, 89, 23);
		disciplinas.add(btnDisciplinaLimpar);
		
		JButton btnDisciplinaBuscar = new JButton("Buscar");
		btnDisciplinaBuscar.setBounds(109, 128, 106, 35);
		disciplinas.add(btnDisciplinaBuscar);
		btnDisciplinaBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnDisciplinaCadastrar = new JButton("Cadastrar");
		btnDisciplinaCadastrar.setBounds(269, 128, 106, 35);
		disciplinas.add(btnDisciplinaCadastrar);
		btnDisciplinaCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnDisciplinaDeletar = new JButton("Deletar");
		btnDisciplinaDeletar.setBounds(433, 128, 106, 35);
		disciplinas.add(btnDisciplinaDeletar);
		btnDisciplinaDeletar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		cbDisciplinaDiaSemana.addItem("");
		cbDisciplinaDiaSemana.addItem("Segunda-Feira");
		cbDisciplinaDiaSemana.addItem("Terça-Feira");
		cbDisciplinaDiaSemana.addItem("Quarta-Feira");
		cbDisciplinaDiaSemana.addItem("Quinta-Feira");
		cbDisciplinaDiaSemana.addItem("Sexta-Feira");
		cbDisciplinaDiaSemana.addItem("Sábado");
		
		DisciplinaController discCont = new DisciplinaController(tfDisciplinaCodigo, tfDisciplinaNome, cbDisciplinaDiaSemana, tfDisciplinaHora, tfDisciplinaTotalHoras, tfDisciplinaCodigoCurso, taDisciplina);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtualizar.setBounds(598, 128, 106, 35);
		disciplinas.add(btnAtualizar);
		btnDisciplinaCadastrar.addActionListener(discCont);
		btnDisciplinaBuscar.addActionListener(discCont);
		btnDisciplinaDeletar.addActionListener(discCont);
		btnDisciplinaLimpar.addActionListener(discCont);
		
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
		tfCursoNome.setBounds(71, 14, 334, 20);
		cursos.add(tfCursoNome);
		tfCursoNome.setColumns(10);
		
		JLabel lblCursoCodigo = new JLabel("Código");
		lblCursoCodigo.setToolTipText("Código do Curso");
		lblCursoCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCursoCodigo.setBounds(15, 46, 46, 23);
		cursos.add(lblCursoCodigo);
		
		tfCursoCodigo = new JTextField();
		tfCursoCodigo.setBounds(72, 48, 170, 21);
		cursos.add(tfCursoCodigo);
		tfCursoCodigo.setColumns(10);
		
		JLabel lblCursoAreaConhecimento = new JLabel("Área do conhecimento");
		lblCursoAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCursoAreaConhecimento.setBounds(15, 79, 141, 20);
		cursos.add(lblCursoAreaConhecimento);
		
		JComboBox<String> cbCursoAreaConhecimento = new JComboBox<>();
		cbCursoAreaConhecimento.setBounds(160, 79, 245, 23);
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
		scrollPane_1.setBounds(16, 174, 813, 388);
		cursos.add(scrollPane_1);
		
		JTextArea taCurso = new JTextArea();
		scrollPane_1.setViewportView(taCurso);
		
		JButton btnCursoCadastrar = new JButton("Cadastrar");
		btnCursoCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoCadastrar.setBounds(299, 128, 106, 35);
		cursos.add(btnCursoCadastrar);
		
		JButton btnCursoBuscar = new JButton("Buscar");
		btnCursoBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoBuscar.setBounds(136, 128, 106, 35);
		cursos.add(btnCursoBuscar);
		
		JButton btnCursoLimpar = new JButton("Limpar");
		btnCursoLimpar.setBounds(740, 148, 89, 23);
		cursos.add(btnCursoLimpar);
		
		JButton btnCursoDeletar = new JButton("Deletar");
		btnCursoDeletar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoDeletar.setBounds(454, 128, 106, 35);
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
		tfProfessorNome.setBounds(59, 16, 274, 22);
		professores.add(tfProfessorNome);
		tfProfessorNome.setColumns(10);
		
		JLabel lblProfessorCPF = new JLabel("CPF");
		lblProfessorCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorCPF.setToolTipText("CPF do Professor");
		lblProfessorCPF.setBounds(10, 49, 36, 14);
		professores.add(lblProfessorCPF);
		
		tfProfessorCPF = new JTextField();
		tfProfessorCPF.setBounds(59, 49, 274, 23);
		professores.add(tfProfessorCPF);
		tfProfessorCPF.setColumns(10);
		
		JLabel lblProfessorAreaConhecimento = new JLabel("Área do Conhecimento");
		lblProfessorAreaConhecimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorAreaConhecimento.setBounds(10, 83, 145, 14);
		professores.add(lblProfessorAreaConhecimento);
		
		JComboBox<String> cbProfessorAreaConhecimento = new JComboBox<>();
		cbProfessorAreaConhecimento.setBounds(165, 80, 168, 17);
		professores.add(cbProfessorAreaConhecimento);
		
		JLabel lblProfessorPontos = new JLabel("Pontos");
		lblProfessorPontos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessorPontos.setBounds(372, 19, 54, 14);
		professores.add(lblProfessorPontos);
		
		tfProfessorPontos = new JTextField();
		tfProfessorPontos.setBounds(436, 16, 94, 22);
		professores.add(tfProfessorPontos);
		tfProfessorPontos.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(16, 174, 813, 388);
		professores.add(scrollPane_2);
		
		JTextArea taProfessor = new JTextArea();
		scrollPane_2.setViewportView(taProfessor);
		
		JButton btnProfessorCadastrar = new JButton("Cadastrar");
		btnProfessorCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorCadastrar.setBounds(283, 128, 106, 35);
		professores.add(btnProfessorCadastrar);
		
		JButton btnProfessorBuscar = new JButton("Buscar");
		btnProfessorBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorBuscar.setBounds(118, 128, 106, 35);
		professores.add(btnProfessorBuscar);
		
		JButton btnProfessorDeletar = new JButton("Deletar");
		btnProfessorDeletar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorDeletar.setBounds(451, 128, 106, 35);
		professores.add(btnProfessorDeletar);
		
		JButton btnProfessorLimpar = new JButton("Limpar");
		btnProfessorLimpar.setBounds(740, 148, 89, 23);
		professores.add(btnProfessorLimpar);
		
		JPanel inscricoes = new JPanel();
		inscricoes.setToolTipText("Controle de Inscrições");
		tabbedPane.addTab("Inscrições", null, inscricoes, null);
		inscricoes.setLayout(null);
		
		JLabel lblInscricoesNomeDisciplina = new JLabel("Nome da Disciplina");
		lblInscricoesNomeDisciplina.setToolTipText("Nome da Disciplina");
		lblInscricoesNomeDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInscricoesNomeDisciplina.setBounds(27, 13, 118, 16);
		inscricoes.add(lblInscricoesNomeDisciplina);
		
		tfInscricoesNomeDisciplina = new JTextField();
		tfInscricoesNomeDisciplina.setBounds(184, 12, 311, 23);
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
		btnInscricoesBuscar.setBounds(107, 59, 106, 35);
		inscricoes.add(btnInscricoesBuscar);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 95, 827, 467);
		inscricoes.add(scrollPane_3);
		
		JTextArea taInscricoesInscritos = new JTextArea();
		scrollPane_3.setViewportView(taInscricoesInscritos);
		
		JButton btnAtualizar_1_1_1 = new JButton("Atualizar");
		btnAtualizar_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtualizar_1_1_1.setBounds(582, 59, 106, 35);
		inscricoes.add(btnAtualizar_1_1_1);
		
		JButton btnProfessorCadastrar_1 = new JButton("Cadastrar");
		btnProfessorCadastrar_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorCadastrar_1.setBounds(277, 59, 106, 35);
		inscricoes.add(btnProfessorCadastrar_1);
		
		JButton btnProfessorDeletar_1 = new JButton("Deletar");
		btnProfessorDeletar_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorDeletar_1.setBounds(429, 59, 106, 35);
		inscricoes.add(btnProfessorDeletar_1);
		
		JButton btnProfessorLimpar_1 = new JButton("Limpar");
		btnProfessorLimpar_1.setBounds(740, 67, 89, 23);
		inscricoes.add(btnProfessorLimpar_1);
		
		CursoController cursoCont = new CursoController(tfCursoCodigo, tfCursoNome, cbCursoAreaConhecimento, taCurso);
		
		JButton btnAtualizar_1 = new JButton("Atualizar");
		btnAtualizar_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtualizar_1.setBounds(601, 128, 106, 35);
		cursos.add(btnAtualizar_1);
		
		JLabel lblModoAlteracao = new JLabel("Modo de Alteração");
		lblModoAlteracao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModoAlteracao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModoAlteracao.setBounds(678, 16, 151, 18);
		cursos.add(lblModoAlteracao);
		
		JButton btnSaveAlteracao = new JButton("Salvar Alterações");
		btnSaveAlteracao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSaveAlteracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSaveAlteracao.setBounds(688, 48, 141, 21);
		cursos.add(btnSaveAlteracao);
		btnCursoCadastrar.addActionListener(cursoCont);
		btnCursoBuscar.addActionListener(cursoCont);
		btnCursoDeletar.addActionListener(cursoCont);
		btnCursoLimpar.addActionListener(cursoCont);
		
		
		ProfessorController profCont = new ProfessorController(tfProfessorCPF, tfProfessorNome, cbProfessorAreaConhecimento, tfProfessorPontos, taProfessor);
		
		JButton btnAtualizar_1_1 = new JButton("Atualizar");
		btnAtualizar_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtualizar_1_1.setBounds(609, 128, 106, 35);
		professores.add(btnAtualizar_1_1);
		btnProfessorCadastrar.addActionListener(profCont);
		btnProfessorBuscar.addActionListener(profCont);
		btnProfessorDeletar.addActionListener(profCont);
		btnProfessorLimpar.addActionListener(profCont);
		
		
		
	}
}
