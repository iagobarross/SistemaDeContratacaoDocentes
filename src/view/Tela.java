package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CursoController;
import controller.DisciplinaController;
import controller.InscricoesController;
import controller.ProfessorController;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
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
	private JTextField tfInscricoesNomeProfessor;

	public Tela() {

		setTitle("Processo Seletivo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 868, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 480, 640));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 852, 590);
		contentPane.add(tabbedPane);

//===============================================================================================================================================
//DISCIPLINAS

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
		btnDisciplinaBuscar.setBounds(131, 128, 106, 35);
		disciplinas.add(btnDisciplinaBuscar);
		btnDisciplinaBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnDisciplinaListarTodos = new JButton("Listar");
		btnDisciplinaListarTodos.setBounds(15, 128, 106, 35);
		disciplinas.add(btnDisciplinaListarTodos);
		btnDisciplinaListarTodos.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnDisciplinaCadastrar = new JButton("Cadastrar");
		btnDisciplinaCadastrar.setBounds(247, 128, 106, 35);
		disciplinas.add(btnDisciplinaCadastrar);
		btnDisciplinaCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnDisciplinaDeletar = new JButton("Deletar");
		btnDisciplinaDeletar.setBounds(363, 128, 106, 35);
		disciplinas.add(btnDisciplinaDeletar);
		btnDisciplinaDeletar.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblDisciplinaModoAlteracao = new JLabel("Modo de Alteração");
		lblDisciplinaModoAlteracao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDisciplinaModoAlteracao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisciplinaModoAlteracao.setBounds(678, 16, 151, 18);
		lblDisciplinaModoAlteracao.setVisible(false);
		disciplinas.add(lblDisciplinaModoAlteracao);

		JButton btnDisciplinaSalvarAlteracao = new JButton("Salvar Alterações");
		btnDisciplinaSalvarAlteracao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisciplinaSalvarAlteracao.setBounds(688, 48, 141, 21);
		btnDisciplinaSalvarAlteracao.setVisible(false);
		btnDisciplinaSalvarAlteracao.setEnabled(false);
		disciplinas.add(btnDisciplinaSalvarAlteracao);

		JButton btnDisciplinaAtualizar = new JButton("Atualizar");
		btnDisciplinaAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDisciplinaAtualizar.setBounds(479, 128, 106, 35);
		disciplinas.add(btnDisciplinaAtualizar);

		DisciplinaController discCont = new DisciplinaController(tfDisciplinaCodigo, tfDisciplinaNome,
				cbDisciplinaDiaSemana, tfDisciplinaHora, tfDisciplinaTotalHoras, tfDisciplinaCodigoCurso, taDisciplina,
				lblDisciplinaModoAlteracao, btnDisciplinaSalvarAlteracao);

		btnDisciplinaSalvarAlteracao.addActionListener(discCont);
		btnDisciplinaBuscar.addActionListener(discCont);
		btnDisciplinaCadastrar.addActionListener(discCont);
		btnDisciplinaDeletar.addActionListener(discCont);
		btnDisciplinaLimpar.addActionListener(discCont);
		btnDisciplinaListarTodos.addActionListener(discCont);
		btnDisciplinaAtualizar.addActionListener(discCont);

		cbDisciplinaDiaSemana.addItem("");
		cbDisciplinaDiaSemana.addItem("Segunda-Feira");
		cbDisciplinaDiaSemana.addItem("Terça-Feira");
		cbDisciplinaDiaSemana.addItem("Quarta-Feira");
		cbDisciplinaDiaSemana.addItem("Quinta-Feira");
		cbDisciplinaDiaSemana.addItem("Sexta-Feira");
		cbDisciplinaDiaSemana.addItem("Sábado");

//===============================================================================================================================================
//CURSOS

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
		taCurso.setEditable(false);
		scrollPane_1.setViewportView(taCurso);

		JButton btnCursoCadastrar = new JButton("Cadastrar");
		btnCursoCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoCadastrar.setBounds(247, 128, 106, 35);
		cursos.add(btnCursoCadastrar);

		JButton btnCursoBuscar = new JButton("Buscar");
		btnCursoBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoBuscar.setBounds(131, 128, 106, 35);
		cursos.add(btnCursoBuscar);

		JButton btnCursoListarTodos = new JButton("Listar");
		btnCursoListarTodos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoListarTodos.setBounds(15, 128, 106, 35);
		cursos.add(btnCursoListarTodos);

		JButton btnCursoLimpar = new JButton("Limpar");
		btnCursoLimpar.setBounds(740, 148, 89, 23);
		cursos.add(btnCursoLimpar);

		JButton btnCursoDeletar = new JButton("Deletar");
		btnCursoDeletar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoDeletar.setBounds(363, 128, 106, 35);
		cursos.add(btnCursoDeletar);

		JButton btnCursoAtualizar = new JButton("Atualizar");
		btnCursoAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoAtualizar.setBounds(479, 128, 106, 35);
		cursos.add(btnCursoAtualizar);

		JLabel lblCursoModoAlteracao = new JLabel("Modo de Alteração");
		lblCursoModoAlteracao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCursoModoAlteracao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCursoModoAlteracao.setBounds(678, 16, 151, 18);
		lblCursoModoAlteracao.setVisible(false);
		cursos.add(lblCursoModoAlteracao);

		JButton btnCursoSalvarAlteracao = new JButton("Salvar Alterações");
		btnCursoSalvarAlteracao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCursoSalvarAlteracao.setBounds(688, 48, 141, 21);
		btnCursoSalvarAlteracao.setVisible(false);
		btnCursoSalvarAlteracao.setEnabled(false);
		cursos.add(btnCursoSalvarAlteracao);

		CursoController cursoCont = new CursoController(tfCursoCodigo, tfCursoNome, cbCursoAreaConhecimento, taCurso,
				lblCursoModoAlteracao, btnCursoSalvarAlteracao);

		btnCursoCadastrar.addActionListener(cursoCont);
		btnCursoBuscar.addActionListener(cursoCont);
		btnCursoDeletar.addActionListener(cursoCont);
		btnCursoLimpar.addActionListener(cursoCont);
		btnCursoAtualizar.addActionListener(cursoCont);
		btnCursoSalvarAlteracao.addActionListener(cursoCont);
		btnCursoListarTodos.addActionListener(cursoCont);

//===============================================================================================================================================
//PROFESSORES

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

		cbProfessorAreaConhecimento.addItem("");
		cbProfessorAreaConhecimento.addItem("Ciências Exatas e da Terra");
		cbProfessorAreaConhecimento.addItem("Ciências Biológicas");
		cbProfessorAreaConhecimento.addItem("Engenharias");
		cbProfessorAreaConhecimento.addItem("Ciências da Saúde");
		cbProfessorAreaConhecimento.addItem("Ciências Agrárias");
		cbProfessorAreaConhecimento.addItem("Linguística, Letras e Artes");
		cbProfessorAreaConhecimento.addItem("Ciências Sociais Aplicadas");
		cbProfessorAreaConhecimento.addItem("Ciências Humanas");

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
		btnProfessorCadastrar.setBounds(247, 128, 106, 35);
		professores.add(btnProfessorCadastrar);

		JButton btnProfessorBuscar = new JButton("Buscar");
		btnProfessorBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorBuscar.setBounds(131, 128, 106, 35);
		professores.add(btnProfessorBuscar);

		JButton btnProfessorListarTodos = new JButton("Listar");
		btnProfessorListarTodos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorListarTodos.setBounds(15, 128, 106, 35);
		professores.add(btnProfessorListarTodos);

		JButton btnProfessorDeletar = new JButton("Deletar");
		btnProfessorDeletar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorDeletar.setBounds(363, 128, 106, 35);
		professores.add(btnProfessorDeletar);

		JButton btnProfessorLimpar = new JButton("Limpar");
		btnProfessorLimpar.setBounds(740, 148, 89, 23);
		professores.add(btnProfessorLimpar);

		JLabel lblProfessorModoAlteracao = new JLabel("Modo de Alteração");
		lblProfessorModoAlteracao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProfessorModoAlteracao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProfessorModoAlteracao.setBounds(678, 16, 151, 18);
		lblProfessorModoAlteracao.setVisible(false);
		professores.add(lblProfessorModoAlteracao);

		JButton btnProfessorSalvarAlteracao = new JButton("Salvar Alterações");
		btnProfessorSalvarAlteracao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorSalvarAlteracao.setBounds(688, 48, 141, 21);
		btnProfessorSalvarAlteracao.setVisible(false);
		btnProfessorSalvarAlteracao.setEnabled(false);
		professores.add(btnProfessorSalvarAlteracao);

		JButton btnProfessorAtualizar = new JButton("Atualizar");
		btnProfessorAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfessorAtualizar.setBounds(479, 128, 106, 35);
		professores.add(btnProfessorAtualizar);

		ProfessorController profCont = new ProfessorController(tfProfessorCPF, tfProfessorNome,
				cbProfessorAreaConhecimento, tfProfessorPontos, taProfessor, lblProfessorModoAlteracao,
				btnProfessorAtualizar);

		// ===============================================================================================================================================
		// INSCRIÇÕES

		JPanel inscricoes = new JPanel();
		inscricoes.setToolTipText("Controle de Inscrições");
		tabbedPane.addTab("Inscrições", null, inscricoes, null);
		inscricoes.setLayout(null);

		JLabel lblInscricoesNomeDisciplina = new JLabel("Nome da Disciplina");
		lblInscricoesNomeDisciplina.setToolTipText("Nome da Disciplina");
		lblInscricoesNomeDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInscricoesNomeDisciplina.setBounds(10, 17, 118, 16);
		inscricoes.add(lblInscricoesNomeDisciplina);

		tfInscricoesNomeDisciplina = new JTextField();
		tfInscricoesNomeDisciplina.setBounds(138, 17, 220, 23);
		inscricoes.add(tfInscricoesNomeDisciplina);
		tfInscricoesNomeDisciplina.setColumns(10);
		
		JLabel lblnscricoesNomeProfessor = new JLabel("Nome do Professor");
		lblnscricoesNomeProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnscricoesNomeProfessor.setBounds(10, 55, 118, 14);
		inscricoes.add(lblnscricoesNomeProfessor);
		
		tfInscricoesNomeProfessor = new JTextField();
		tfInscricoesNomeProfessor.setColumns(10);
		tfInscricoesNomeProfessor.setBounds(138, 53, 220, 23);
		inscricoes.add(tfInscricoesNomeProfessor);

		JButton btnInscricoesListarTodos = new JButton("Listar");
		btnInscricoesListarTodos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricoesListarTodos.setBounds(15, 128, 106, 35);
		inscricoes.add(btnInscricoesListarTodos);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(16, 174, 813, 388);
		inscricoes.add(scrollPane_3);

		JTextArea taInscricoesInscritos = new JTextArea();
		taInscricoesInscritos.setEditable(false);
		scrollPane_3.setViewportView(taInscricoesInscritos);
		
		JButton btnInscricoesBuscar = new JButton("Buscar");
		btnInscricoesBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricoesBuscar.setBounds(131, 128, 106, 35);
		inscricoes.add(btnInscricoesBuscar);

		JButton btnInscricoesAtualizar = new JButton("Atualizar");
		btnInscricoesAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricoesAtualizar.setBounds(479, 128, 106, 35);
		inscricoes.add(btnInscricoesAtualizar);

		JButton btnInscricoesCadastrar = new JButton("Cadastrar");
		btnInscricoesCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricoesCadastrar.setBounds(247, 128, 106, 35);
		inscricoes.add(btnInscricoesCadastrar);

		JButton btnInscricoesDeletar = new JButton("Deletar");
		btnInscricoesDeletar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricoesDeletar.setBounds(363, 128, 106, 35);
		inscricoes.add(btnInscricoesDeletar);


		JLabel lblInscricaoModoAlteracao = new JLabel("Modo de Alteração");
		lblInscricaoModoAlteracao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInscricaoModoAlteracao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInscricaoModoAlteracao.setBounds(678, 16, 151, 18);
		lblInscricaoModoAlteracao.setVisible(false);
		inscricoes.add(lblInscricaoModoAlteracao);

		JButton btnInscricaoSalvarAlteracao = new JButton("Salvar Alterações");
		btnInscricaoSalvarAlteracao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscricaoSalvarAlteracao.setBounds(688, 48, 141, 21);
		btnInscricaoSalvarAlteracao.setVisible(false);
		btnInscricaoSalvarAlteracao.setEnabled(false);
		inscricoes.add(btnInscricaoSalvarAlteracao);

		InscricoesController inscCont = new InscricoesController(tfInscricoesNomeDisciplina, taInscricoesInscritos,
				lblInscricaoModoAlteracao, btnInscricaoSalvarAlteracao);
		
		JButton btnDisciplinaLimpar_1 = new JButton("Limpar");
		btnDisciplinaLimpar_1.setBounds(740, 147, 89, 23);
		inscricoes.add(btnDisciplinaLimpar_1);

		btnInscricaoSalvarAlteracao.addActionListener(inscCont);
		btnInscricoesListarTodos.addActionListener(inscCont);
		btnInscricoesAtualizar.addActionListener(inscCont);
		btnInscricoesCadastrar.addActionListener(inscCont);
		btnInscricoesDeletar.addActionListener(inscCont);
		//btnInscricoesLimpar.addActionListener(inscCont);

		btnProfessorSalvarAlteracao.addActionListener(profCont);
		btnProfessorBuscar.addActionListener(profCont);
		btnProfessorCadastrar.addActionListener(profCont);
		btnProfessorDeletar.addActionListener(profCont);
		btnProfessorLimpar.addActionListener(profCont);
		btnProfessorListarTodos.addActionListener(profCont);
		btnProfessorAtualizar.addActionListener(profCont);

	}
}