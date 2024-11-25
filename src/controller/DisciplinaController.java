package controller;

/*     Se sobrar tempo adicionar cabeçalho na primeira gravação do CSV ( antes da criação )
 * 		Encurtar chamada de criação dos arquivos
 * 
 * 
 * 
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista;
import br.edu.fateczl.Fila.Fila;
import model.Curso;
import model.Disciplina;
import model.Inscricoes;

public class DisciplinaController implements ActionListener {
	private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaNome;
	private JComboBox<String> cbDisciplinaDiaSemana;
	private JTextField tfDisciplinaHora;
	private JTextField tfDisciplinaTotalHoras;
	private JTextField tfDisciplinaCodigoCurso;
	private JTextArea taDisciplina;
	private JLabel lblDisciplinaModoAlteracao;
	private JButton btnDisciplinaSalvarAlteracao;
	private int posicao = -1;
	private static String codProcesso="2024";
	Lista<Disciplina> listaDisciplinas = new Lista<>();
	MetodosPrincipaisController metodosPrincipais = new MetodosPrincipaisController();
//private static int codigoProcesso;

	public DisciplinaController(JTextField tfDisciplinaCodigo, JTextField tfDisciplinaNome,
			JComboBox<String> cbDisciplinaDiaSemana, JTextField tfDisciplinaHora, JTextField tfDisciplinaTotalHoras,
			JTextField tfDisciplinaCodigoCurso, JTextArea taDisciplina, JLabel lblDisciplinaModoAlteracao,
			JButton btnDisciplinaSalvarAlteracao) {
		this.tfDisciplinaCodigo = tfDisciplinaCodigo;
		this.tfDisciplinaNome = tfDisciplinaNome;
		this.cbDisciplinaDiaSemana = cbDisciplinaDiaSemana;
		this.tfDisciplinaHora = tfDisciplinaHora;
		this.tfDisciplinaTotalHoras = tfDisciplinaTotalHoras;
		this.tfDisciplinaCodigoCurso = tfDisciplinaCodigoCurso;
		this.taDisciplina = taDisciplina;
		this.lblDisciplinaModoAlteracao = lblDisciplinaModoAlteracao;
		this.btnDisciplinaSalvarAlteracao = btnDisciplinaSalvarAlteracao;
	}
	
	public DisciplinaController() {
		super();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Cadastrar")) {
			try {
				limparTADisciplina();
				validarRepetidas();
			} catch (Exception e1) {
				System.err.println(e1.getMessage());
			}
		}
		if (cmd.equals("Buscar")) {
			try {
				limparTADisciplina();
				buscarDisciplina();

			} catch (Exception e1) {
				System.err.println(e1.getMessage());
			}
		}
		if (cmd.equals("Deletar")) {
			try {
				limparTADisciplina();
				deletarDisciplina();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Limpar")) {
			limparTADisciplina();
			limparCamposDisciplina();
		}
		if (cmd.equals("Listar")) {
			try {
				limparTADisciplina();
				allDisciplines();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Atualizar")) {
			try {
				limparTADisciplina();
				posicao = atualizarDisciplina();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Salvar Alterações")) {
			try {
				limparTADisciplina();
				salvarAlteracoes(posicao);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void validarRepetidas() throws Exception {
		Disciplina disciplina = new Disciplina();
		disciplina.setNomeDisciplina(tfDisciplinaNome.getText());
		disciplina.setCodigoDisciplina(tfDisciplinaCodigo.getText());
		disciplina.setCodigoCurso(tfDisciplinaCodigoCurso.getText());
		disciplina.setDiaSemana((String) cbDisciplinaDiaSemana.getSelectedItem());
		disciplina.setHoraInicial(tfDisciplinaHora.getText());
		disciplina.setHorasDiarias(tfDisciplinaTotalHoras.getText());
                codProcesso=codProcesso+disciplina.getCodigoDisciplina();
			
			disciplina.setCodigoProcesso(codProcesso);
		Lista<Disciplina> todosAsDisciplinas = new Lista<Disciplina>();

		todosAsDisciplinas = alimentarLista("Disciplinas.csv", todosAsDisciplinas);

		int tamanho = todosAsDisciplinas.size();

		for (int i = 0; i < tamanho; i++) {
			Disciplina d = new Disciplina();
			d = todosAsDisciplinas.get(i);
			if (disciplina.getDiaSemana().equals(d.getDiaSemana())
					&& disciplina.getNomeDisciplina().equals(d.getNomeDisciplina()) && disciplina.getCodigoDisciplina().equals(d.getCodigoDisciplina())
					&& disciplina.getHoraInicial().equals(d.getHoraInicial()) && disciplina.getHorasDiarias().equals(d.getHorasDiarias())) {
				JOptionPane.showMessageDialog(null, "Esta disciplina já está cadastrada no sistema.\nA disciplina só poderá ser cadastrada em apenas um curso.", "ERRO",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		cadastrarDisciplina(disciplina);
	}

	private void cadastrarDisciplina(Disciplina disciplina) throws Exception {

		if (!disciplina.getDiaSemana().equals("") && !disciplina.getCodigoCurso().equals("")
				&& !disciplina.getNomeDisciplina().equals("") && !disciplina.getCodigoDisciplina().equals("")
				&& !disciplina.getHoraInicial().equals("") && !disciplina.getHorasDiarias().equals("")) {
			listaDisciplinas.addLast(disciplina);

			metodosPrincipais.inserirNoArquivo(disciplina.toString(), "Disciplinas.csv");

			limparCamposDisciplina();
			taDisciplina.setText("Disciplina adicionada!");

		} else {
			JOptionPane.showMessageDialog(null,
					"Todas as informações devem ser preenchidas para cadastrar uma nova disciplina", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void buscarDisciplina() throws Exception {

		// Captura dos dados do WB
		Disciplina disciplina = new Disciplina();
		Fila<Disciplina> disciplinaEncontrada = new Fila<>();
		disciplina.setNomeDisciplina(tfDisciplinaNome.getText());
		disciplina.setCodigoDisciplina(tfDisciplinaCodigo.getText());
		disciplina.setCodigoCurso(tfDisciplinaCodigoCurso.getText());
		disciplina.setDiaSemana((String) cbDisciplinaDiaSemana.getSelectedItem());

		if (!disciplina.getNomeDisciplina().isBlank()) {
			disciplinaEncontrada = searchName(disciplina, disciplinaEncontrada);
		} else if (!disciplina.getCodigoDisciplina().isBlank()) {
			disciplinaEncontrada = searchCodeDisc(disciplina, disciplinaEncontrada);
		} else if (!disciplina.getCodigoCurso().isBlank()) {
			disciplinaEncontrada = searchCodeCourse(disciplina, disciplinaEncontrada);
		} else if (!disciplina.getDiaSemana().equals("")) {
			disciplinaEncontrada = searchDiaSemana(disciplina, disciplinaEncontrada);
		} else {
			JOptionPane.showMessageDialog(null, "Digite em um campo para pesquisar", "ERRO", JOptionPane.ERROR_MESSAGE);
			disciplina = null;
			return;
		}

		if (disciplina != null && disciplinaEncontrada.size() > 0) {
			taDisciplina.append(String.format("%-30s %-20s %-15s %-14s %-15s %-15s%n", "Nome", "Cód. da Disciplina",
					"Dia da Semana", "Hora Inicial", "Horas Diárias", "Cód. do Curso"));
			while (!disciplinaEncontrada.isEmpty()) {
				disciplina = disciplinaEncontrada.remove();
				taDisciplina.append(String.format("%-30s %-20s %-15s %-14s %-15s %-15s%n",
						disciplina.getNomeDisciplina(), disciplina.getCodigoDisciplina(), disciplina.getDiaSemana(),
						disciplina.getHoraInicial(), disciplina.getHorasDiarias(), disciplina.getCodigoCurso()));
			}
		} else {
			JOptionPane.showMessageDialog(null, "Disciplina não encontrada", "ERRO", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private Fila<Disciplina> searchDiaSemana(Disciplina disciplina, Fila<Disciplina> disciplinaEncontrada)
			throws IOException {
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Disciplinas.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[2].equals(disciplina.getDiaSemana())) {
					Disciplina disciplinaAdd = new Disciplina();
					disciplinaAdd.setCodigoDisciplina(vetLinha[0]);
					disciplinaAdd.setNomeDisciplina(vetLinha[1]);
					disciplinaAdd.setDiaSemana(vetLinha[2]);
					disciplinaAdd.setHoraInicial(vetLinha[3]);
					disciplinaAdd.setHorasDiarias(vetLinha[4]);
					disciplinaAdd.setCodigoCurso(vetLinha[5]);
					disciplinaEncontrada.insert(disciplinaAdd);
				}

				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		tfDisciplinaNome.setText("");
		return disciplinaEncontrada;
	}

	Fila<Disciplina> searchName(Disciplina disciplina, Fila<Disciplina> disciplinaEncontrada)
			throws IOException {

		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Disciplinas.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[1].equals(disciplina.getNomeDisciplina())) {
					Disciplina disciplinaAdd = new Disciplina();
					disciplinaAdd.setCodigoDisciplina(vetLinha[0]);
					disciplinaAdd.setNomeDisciplina(vetLinha[1]);
					disciplinaAdd.setDiaSemana(vetLinha[2]);
					disciplinaAdd.setHoraInicial(vetLinha[3]);
					disciplinaAdd.setHorasDiarias(vetLinha[4]);
					disciplinaAdd.setCodigoCurso(vetLinha[5]);
					disciplinaEncontrada.insert(disciplinaAdd);
				}

				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}

		tfDisciplinaNome.setText("");
		return disciplinaEncontrada;
	}

	private Fila<Disciplina> searchCodeDisc(Disciplina disciplina, Fila<Disciplina> disciplinaEncontrada)
			throws IOException {

		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Disciplinas.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[0].equals(disciplina.getCodigoDisciplina())) {
					Disciplina disciplinaAdd = new Disciplina();
					disciplinaAdd.setCodigoDisciplina(vetLinha[0]);
					disciplinaAdd.setNomeDisciplina(vetLinha[1]);
					disciplinaAdd.setDiaSemana(vetLinha[2]);
					disciplinaAdd.setHoraInicial(vetLinha[3]);
					disciplinaAdd.setHorasDiarias(vetLinha[4]);
					disciplinaAdd.setCodigoCurso(vetLinha[5]);
					disciplinaEncontrada.insert(disciplinaAdd);
				}

				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}

		tfDisciplinaCodigo.setText("");

		return disciplinaEncontrada;
	}

	private Fila<Disciplina> searchCodeCourse(Disciplina disciplina, Fila<Disciplina> disciplinaEncontrada)
			throws Exception {

		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Disciplinas.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[5].equals(disciplina.getCodigoCurso())) {
					Disciplina disciplinaAdd = new Disciplina();
					disciplinaAdd.setCodigoDisciplina(vetLinha[0]);
					disciplinaAdd.setNomeDisciplina(vetLinha[1]);
					disciplinaAdd.setDiaSemana(vetLinha[2]);
					disciplinaAdd.setHoraInicial(vetLinha[3]);
					disciplinaAdd.setHorasDiarias(vetLinha[4]);
					disciplinaAdd.setCodigoCurso(vetLinha[5]);
					disciplinaEncontrada.insert(disciplinaAdd);
				}

				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}

		tfDisciplinaCodigoCurso.setText("");

		return disciplinaEncontrada;
	}

	private void deletarDisciplina() throws Exception {
		// Capturo o dado para ser removido
		Disciplina disciplina = new Disciplina();
		disciplina.setNomeDisciplina(tfDisciplinaNome.getText());
		disciplina.setCodigoDisciplina(tfDisciplinaCodigo.getText());
		disciplina.setCodigoCurso(tfDisciplinaCodigoCurso.getText());
		disciplina.setDiaSemana((String) cbDisciplinaDiaSemana.getSelectedItem());
		Lista<Disciplina> listagemDeDisciplinas = new Lista<Disciplina>();

		// Todo o arquivo csv é passado para uma lista
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Disciplinas.csv");
		alimentarLista("Disciplinas.csv", listagemDeDisciplinas);

		// buscar o dado para ser removido na lista
		int tamanhoAnterior = listagemDeDisciplinas.size();
		int tamanho = listagemDeDisciplinas.size();
		for (int i = 0; i < tamanho; i++) {
			Disciplina discLista = new Disciplina();
			discLista = listagemDeDisciplinas.get(i);

			if (!disciplina.getNomeDisciplina().isBlank()
					&& discLista.getNomeDisciplina().contains(disciplina.getNomeDisciplina())
					|| !disciplina.getCodigoCurso().isBlank()
							&& discLista.getCodigoCurso().contains(disciplina.getCodigoCurso())
					|| !disciplina.getDiaSemana().equals("")
							&& discLista.getDiaSemana().contains(disciplina.getDiaSemana())
					|| !disciplina.getCodigoDisciplina().isEmpty()
							&& discLista.getCodigoDisciplina().contains(disciplina.getCodigoDisciplina())) {
				listagemDeDisciplinas.remove(i);
				deletarInscricoesDaDisciplina(discLista);
				tamanho--;
				i--;
				JOptionPane.showMessageDialog(null, "Disciplina removida com sucesso!", "SUCESSO",
						JOptionPane.PLAIN_MESSAGE);

			}
		}

		// comparativo pra saber se teve algum dado removido
		if (tamanhoAnterior == tamanho) {
			JOptionPane.showMessageDialog(null,
					"Nenhuma disciplina cadastrada com esses dados foi encontrada para ser removida", "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		metodosPrincipais.limparArquivo("Disciplinas.csv");

		// reescrever o novo arquivo
		for (int i = 0; i < tamanho; i++) {
			Disciplina discLista = new Disciplina();
			discLista = listagemDeDisciplinas.get(i);
			metodosPrincipais.inserirNoArquivo(discLista.toString(), "Disciplinas.csv");
		}
	}

	
	private void deletarInscricoesDaDisciplina(Disciplina disciplina) throws Exception {
		Lista<Inscricoes> todasAsInscricoes = new Lista<Inscricoes>();
		InscricoesController ic = new InscricoesController();
		todasAsInscricoes = ic.alimentarLista("Inscricoes.csv", todasAsInscricoes);
		
		int tamanho = todasAsInscricoes.size();
		
		for(int i = 0 ; i < tamanho ;i ++) {
			Inscricoes insc = new Inscricoes();
			insc = todasAsInscricoes.get(i);
			if(disciplina.getCodigoProcesso().equals(insc.getCodigoProcesso())) {
				todasAsInscricoes.remove(i);
				tamanho--;
				i--;
			}
		}
		
		metodosPrincipais.limparArquivo("Inscricoes.csv");

		for (int i = 0; i < tamanho; i++) {
			Inscricoes insc = new Inscricoes();			
			insc = todasAsInscricoes.get(i);
			metodosPrincipais.inserirNoArquivo(insc.toString(), "Inscricoes.csv");
		}
				
	}
	
	private void limparTADisciplina() {
		taDisciplina.setText("");
	}

	private void limparCamposDisciplina() {
		tfDisciplinaNome.setText("");
		tfDisciplinaCodigo.setText("");
		tfDisciplinaCodigoCurso.setText("");
		cbDisciplinaDiaSemana.setSelectedItem("");
		tfDisciplinaHora.setText("");
		tfDisciplinaTotalHoras.setText("");

	}

	private int atualizarDisciplina() throws Exception {
		Disciplina disciplina = new Disciplina();
		Disciplina ItemListaDeDisciplina = new Disciplina();
		Lista<Disciplina> ListagemDeDisciplinas = new Lista<>();
		Fila<Disciplina> discEncontradas = new Fila<>();

		disciplina.setNomeDisciplina(tfDisciplinaNome.getText());
		disciplina.setCodigoDisciplina(tfDisciplinaCodigo.getText());
		disciplina.setCodigoCurso(tfDisciplinaCodigoCurso.getText());
		disciplina.setDiaSemana((String) cbDisciplinaDiaSemana.getSelectedItem());

		if (!disciplina.getNomeDisciplina().isBlank()) {
			searchName(disciplina, discEncontradas);
		} else if (!disciplina.getCodigoDisciplina().isBlank()) {
			searchCodeDisc(disciplina, discEncontradas);
		} else if (!disciplina.getCodigoCurso().isBlank()) {
			searchCodeCourse(disciplina, discEncontradas);
		} else if (!disciplina.getDiaSemana().equals("")) {
			searchDiaSemana(disciplina, discEncontradas);
		} else {
			JOptionPane.showMessageDialog(null, "Digite em um campo para pesquisar", "ERRO", JOptionPane.ERROR_MESSAGE);
			disciplina = null;
			return -1;
		}

		if (discEncontradas.size() > 1) {
			JOptionPane.showMessageDialog(null, "A alteração deve ser feita apenas uma disciplina por vez", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		} else if (discEncontradas.size() == 0) {
			JOptionPane.showMessageDialog(null, "Disciplina não encontrada", "ERRO", JOptionPane.INFORMATION_MESSAGE);
		} else {
			disciplina = discEncontradas.remove();
			ListagemDeDisciplinas = alimentarLista("Disciplinas.csv", ListagemDeDisciplinas);
			int tamanho = ListagemDeDisciplinas.size();
			for (int i = 0; i < tamanho; i++) {
				ItemListaDeDisciplina = ListagemDeDisciplinas.get(i);
				if (!disciplina.getNomeDisciplina().isBlank()
						&& ItemListaDeDisciplina.getNomeDisciplina().contains(disciplina.getNomeDisciplina())
						&& !disciplina.getCodigoCurso().isBlank()
						&& ItemListaDeDisciplina.getCodigoCurso().contains(disciplina.getCodigoCurso())
						&& !disciplina.getDiaSemana().equals("")
						&& ItemListaDeDisciplina.getDiaSemana().contains(disciplina.getDiaSemana())
						&& !disciplina.getCodigoDisciplina().isEmpty()
						&& ItemListaDeDisciplina.getCodigoDisciplina().contains(disciplina.getCodigoDisciplina())) {
					lblDisciplinaModoAlteracao.setVisible(true);
					btnDisciplinaSalvarAlteracao.setVisible(true);
					btnDisciplinaSalvarAlteracao.setEnabled(true);
					taDisciplina.setText(
							"Disciplina encontrada! Digite nos campos acima quais valores deseja alterar.\nOs campos que ficarem em branco não serão substituídos.");
					return i;
				}
			}
		}
		return -1;
	}

	private void salvarAlteracoes(int posicao) throws Exception {
		Lista<Disciplina> ListagemDeDisciplinas = new Lista<>();
		Disciplina disciplina = new Disciplina();
		Disciplina discEncontrada = new Disciplina();

		disciplina.setNomeDisciplina(tfDisciplinaNome.getText());
		disciplina.setCodigoDisciplina(tfDisciplinaCodigo.getText());
		disciplina.setCodigoCurso(tfDisciplinaCodigoCurso.getText());
		disciplina.setDiaSemana((String) cbDisciplinaDiaSemana.getSelectedItem());

		ListagemDeDisciplinas = alimentarLista("Disciplinas.csv", ListagemDeDisciplinas);

		discEncontrada = ListagemDeDisciplinas.get(posicao);

		if (!disciplina.getNomeDisciplina().isBlank()) {
			discEncontrada.setNomeDisciplina(disciplina.getNomeDisciplina());
		}
		if (!disciplina.getCodigoDisciplina().isBlank()) {
			discEncontrada.setCodigoDisciplina(disciplina.getCodigoDisciplina());
		}
		if (!disciplina.getCodigoCurso().isBlank()) {
			discEncontrada.setCodigoCurso(disciplina.getCodigoCurso());
		}
		if (!disciplina.getDiaSemana().equals("")) {
			discEncontrada.setDiaSemana(disciplina.getDiaSemana());
		}

		ListagemDeDisciplinas.add(discEncontrada, posicao);
		ListagemDeDisciplinas.remove(posicao + 1);

		metodosPrincipais.limparArquivo("Disciplinas.csv");
		int tamanho = ListagemDeDisciplinas.size();
		for (int i = 0; i < tamanho; i++) {
			Disciplina inserirDisc = new Disciplina();
			inserirDisc = ListagemDeDisciplinas.get(i);
			metodosPrincipais.inserirNoArquivo(inserirDisc.toString(), "Disciplinas.csv");
		}

		lblDisciplinaModoAlteracao.setVisible(false);
		btnDisciplinaSalvarAlteracao.setVisible(false);
		btnDisciplinaSalvarAlteracao.setEnabled(false);
		JOptionPane.showMessageDialog(null, "Disciplina alterada!", "SUCESSO", JOptionPane.PLAIN_MESSAGE);

	}

	private void allDisciplines() throws Exception {
		Fila<Disciplina> disciplinaEncontrada = new Fila<>();
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Disciplinas.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				Disciplina disciplina = new Disciplina();
				disciplina.setCodigoDisciplina(vetLinha[0]);
				disciplina.setNomeDisciplina(vetLinha[1]);
				disciplina.setDiaSemana(vetLinha[2]);
				disciplina.setHoraInicial(vetLinha[3]);
				disciplina.setHorasDiarias(vetLinha[4]);
				disciplina.setCodigoCurso(vetLinha[5]);
				disciplinaEncontrada.insert(disciplina);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}

		taDisciplina.append(String.format("%-30s %-20s %-15s %-14s %-15s %-15s%n", "Nome", "Cód. da Disciplina",
				"Dia da Semana", "Hora Inicial", "Horas Diárias", "Cód. do Curso"));
		while (!disciplinaEncontrada.isEmpty()) {
			Disciplina disciplinaAux = new Disciplina();
			disciplinaAux = disciplinaEncontrada.remove();
			taDisciplina.append(String.format("%-30s %-20s %-15s %-14s %-15s %-15s%n",
					disciplinaAux.getNomeDisciplina(), disciplinaAux.getCodigoDisciplina(),
					disciplinaAux.getDiaSemana(), disciplinaAux.getHoraInicial(), disciplinaAux.getHorasDiarias(),
					disciplinaAux.getCodigoCurso()));
		}

	}

	public Lista<Disciplina> alimentarLista(String arquivoNome, Lista<Disciplina> listaDeItens) throws Exception {
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, arquivoNome);
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				Disciplina disciplinaAdd = new Disciplina();
				disciplinaAdd.setCodigoDisciplina(vetLinha[0]);
				disciplinaAdd.setNomeDisciplina(vetLinha[1]);
				disciplinaAdd.setDiaSemana(vetLinha[2]);
				disciplinaAdd.setHoraInicial(vetLinha[3]);
				disciplinaAdd.setHorasDiarias(vetLinha[4]);
				disciplinaAdd.setCodigoCurso(vetLinha[5]);
				listaDeItens.addLast(disciplinaAdd);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return listaDeItens;
	}

}