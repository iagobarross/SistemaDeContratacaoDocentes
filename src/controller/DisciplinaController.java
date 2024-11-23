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

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista;
import br.edu.fateczl.Fila.Fila;
import model.Curso;
import model.Disciplina;

public class DisciplinaController implements ActionListener {
	private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaNome;
	private JComboBox<String> cbDisciplinaDiaSemana;
	private JTextField tfDisciplinaHora;
	private JTextField tfDisciplinaTotalHoras;
	private JTextField tfDisciplinaCodigoCurso;
	private JTextArea taDisciplina;
	private int posicao= -1;
	Lista<Disciplina> listaDisciplinas = new Lista<>();
	MetodosPrincipaisController metodosPrincipais=new MetodosPrincipaisController();
//private static int codigoProcesso;

	public DisciplinaController(JTextField tfDisciplinaCodigo, JTextField tfDisciplinaNome,
			JComboBox<String> cbDisciplinaDiaSemana, JTextField tfDisciplinaHora, JTextField tfDisciplinaTotalHoras,
			JTextField tfDisciplinaCodigoCurso, JTextArea taDisciplina) {
		this.tfDisciplinaCodigo = tfDisciplinaCodigo;
		this.tfDisciplinaNome = tfDisciplinaNome;
		this.cbDisciplinaDiaSemana = cbDisciplinaDiaSemana;
		this.tfDisciplinaHora = tfDisciplinaHora;
		this.tfDisciplinaTotalHoras = tfDisciplinaTotalHoras;
		this.tfDisciplinaCodigoCurso = tfDisciplinaCodigoCurso;
		this.taDisciplina = taDisciplina;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Cadastrar")) {
			try {
				cadastrarDisciplina();
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
			deletarDisciplina();
		}
		if (cmd.equals("Limpar")) {
			limparDisciplina();
		}
		if (cmd.equals("Listar")) {
			try {
				limparTADisciplina();
				allCourses();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Atualizar")) {
			try {
				limparTADisciplina();
				posicao = atualizarCurso();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Salvar Alterações")) {
			try {
				limparDisciplina();
				salvarAlteracoes(posicao);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void limparTADisciplina() {
		taDisciplina.setText("");
	}

	private void salvarAlteracoes(int posicao2) {
		// TODO Auto-generated method stub
		
	}

	private int atualizarCurso() {
		// TODO Auto-generated method stub
		return 0;
	}

	private void cadastrarDisciplina() throws Exception {
	
		Disciplina disciplina = new Disciplina();
		disciplina.setNomeDisciplina(tfDisciplinaNome.getText());
		disciplina.setCodigoDisciplina(tfDisciplinaCodigo.getText());
		disciplina.setCodigoCurso(tfDisciplinaCodigoCurso.getText());
		disciplina.setDiaSemana((String) cbDisciplinaDiaSemana.getSelectedItem());
		disciplina.setHoraInicial(tfDisciplinaHora.getText());
		disciplina.setHorasDiarias(tfDisciplinaTotalHoras.getText());

		listaDisciplinas.addLast(disciplina);

		insertDisc(disciplina.toString());
		tfDisciplinaNome.setText("");
		tfDisciplinaCodigo.setText("");
		tfDisciplinaCodigoCurso.setText("");
		cbDisciplinaDiaSemana.setSelectedItem(null);
		tfDisciplinaHora.setText("");
		tfDisciplinaTotalHoras.setText("");

	}

	private void insertDisc(String csvDisciplina) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File dir = new File(path);

		if (!dir.exists()) {
			dir.mkdir();
		}

		File arq = new File(path, "Disciplinas.csv");
		boolean exists = false;

		if (arq.exists()) {
			exists = true;
		}

		FileWriter fw = new FileWriter(arq, exists);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvDisciplina + "\r\n");
		pw.flush();
		pw.close();
		fw.close();

	}

	private void buscarDisciplina() throws Exception {
		
		//Captura dos dados do WB
		Disciplina disciplina = new Disciplina();
		Fila<Disciplina>disciplinaEncontrada=new Fila<>();
		disciplina.setNomeDisciplina(tfDisciplinaNome.getText());
		disciplina.setCodigoDisciplina(tfDisciplinaCodigo.getText());
		disciplina.setCodigoCurso(tfDisciplinaCodigoCurso.getText());
		disciplina.setDiaSemana((String)cbDisciplinaDiaSemana.getSelectedItem());
		
		
		if (!disciplina.getNomeDisciplina().isBlank()) {
			disciplinaEncontrada = searchName(disciplina,disciplinaEncontrada);
		} else if (!disciplina.getCodigoDisciplina().isBlank()) {
			disciplinaEncontrada = searchCodeDisc(disciplina,disciplinaEncontrada);
		} else if (!disciplina.getCodigoCurso().isBlank()) {
			 disciplinaEncontrada= searchCodeCourse(disciplina,disciplinaEncontrada);
		}else if (!disciplina.getDiaSemana().isEmpty()) { 
			disciplinaEncontrada=searchDiaSemana(disciplina,disciplinaEncontrada);
		}
		else {
			JOptionPane.showMessageDialog(null, "Digite em um campo para pesquisar", "ERRO", JOptionPane.ERROR_MESSAGE);
			disciplina = null;
		}

		if (disciplina != null && disciplinaEncontrada.size()>0) {
			taDisciplina.append(String.format("%-30s %-20s %-15s %-14s %-15s %-15s%n","Nome","Cód. da Disciplina","Dia da Semana",
					"Hora Inicial","Horas Diárias","Cód. do Curso"));
			while(!disciplinaEncontrada.isEmpty())
			{
				disciplina=disciplinaEncontrada.remove();
			taDisciplina.append(String.format("%-30s %-20s %-15s %-14s %-15s %-15s%n","Nome","Cód. da Disciplina","Dia da Semana",
					"Hora Inicial","Horas Diárias","Cód. do Curso",disciplina.getNomeDisciplina() ,disciplina.getCodigoDisciplina(),
					disciplina.getDiaSemana(), disciplina.getHoraInicial(), disciplina.getHorasDiarias(),disciplina.getCodigoCurso() ));
			}
			} else {
				JOptionPane.showMessageDialog(null, "Disciplina não encontrado", "ERRO", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private Fila<Disciplina> searchDiaSemana(Disciplina disciplina, Fila<Disciplina> disciplinaEncontrada) throws IOException {
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
					Disciplina disciplinaAdd=new Disciplina();
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

		//**remover ? 
		if (disciplina.getCodigoDisciplina().equals("")) {
			return null;
		}

		tfDisciplinaNome.setText("");
		return disciplinaEncontrada;
	}

	private Fila<Disciplina> searchName(Disciplina disciplina,Fila<Disciplina>disciplinaEncontrada) throws IOException {
		
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
					Disciplina disciplinaAdd=new Disciplina();
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

		//**remover ? 
		if (disciplina.getCodigoDisciplina().equals("")) {
			return null;
		}

		tfDisciplinaNome.setText("");
		return disciplinaEncontrada;
	}

	private Fila<Disciplina> searchCodeDisc(Disciplina disciplina,Fila<Disciplina>disciplinaEncontrada) throws IOException {
		
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
					Disciplina disciplinaAdd=new Disciplina();
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

		if (disciplina.getNomeDisciplina().equals("")) {
			return null;
		}

		tfDisciplinaCodigo.setText("");

		return disciplinaEncontrada;
	}

	private Fila<Disciplina> searchCodeCourse(Disciplina disciplina,Fila<Disciplina>disciplinaEncontrada) throws Exception {
	
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
					Disciplina disciplinaAdd=new Disciplina();
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

		if (disciplina.getNomeDisciplina().equals("")) {
			return null;
		}

		tfDisciplinaCodigoCurso.setText("");

		return disciplinaEncontrada;
	}
	private void allCourses() throws Exception {
		Fila<Disciplina> disciplinaEncontrada = new Fila<>();
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Cursos.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				Disciplina disciplina = new Disciplina();
				if (vetLinha[5].equals(disciplina.getCodigoCurso())) {
					disciplina.setCodigoDisciplina(vetLinha[0]);
					disciplina.setNomeDisciplina(vetLinha[1]);
					disciplina.setDiaSemana(vetLinha[2]);
					disciplina.setHoraInicial(vetLinha[3]);
					disciplina.setHorasDiarias(vetLinha[4]);
					disciplinaEncontrada.insert(disciplina);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
	
			taDisciplina.append(String.format("%-30s %-20s %-15s %-14s %-15s %-15s%n","Nome","Cód. da Disciplina","Dia da Semana",
					"Hora Inicial","Horas Diárias","Cód. do Curso"));
			while(!disciplinaEncontrada.isEmpty())
			{
				Disciplina disciplinaAux=new Disciplina();
				disciplinaAux=disciplinaEncontrada.remove();
			taDisciplina.append(String.format("%-30s %-20s %-15s %-14s %-15s %-15s%n",disciplinaAux.getNomeDisciplina() ,disciplinaAux.getCodigoDisciplina(),
					disciplinaAux.getDiaSemana(), disciplinaAux.getHoraInicial(), disciplinaAux.getHorasDiarias(),disciplinaAux.getCodigoCurso() ));
			}

	}
	private void deletarDisciplina() {

		// Capturo o dado para ser removido
//				Disciplina disciplina = new Disciplina();
//				disciplina.(tfCursoCodigo.getText());
//				disciplina.(tfCursoNome.getText());
//				disciplina.((String) cbCursoAreaConhecimento.getSelectedItem());
//			
//				Lista<Curso> listagemDeCursos = new Lista<Curso>();
//
//				// Todo o arquivo csv é passado para uma lista
//				String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
//				File arq = new File(path, "Cursos.csv");
//				metodosPrincipais.alimentarLista("Cursos.csv", listagemDeCursos);
//
//				// buscar o dado para ser removido na lista
//				int tamanhoAnterior = listagemDeCursos.size();
//				int tamanho = listagemDeCursos.size();
//				for (int i = 0; i < tamanho; i++) {
//					Curso cursoLista = new Curso();
//					cursoLista = listagemDeCursos.get(i);
//
//					if (!curso.getNomeCurso().isBlank() && cursoLista.getNomeCurso().contains(curso.getNomeCurso())
//							|| !curso.getCodigoCurso().isBlank() && cursoLista.getCodigoCurso().contains(curso.getCodigoCurso())
//							|| !curso.getAreaConhecimento().isEmpty()
//									&& cursoLista.getAreaConhecimento().contains(curso.getAreaConhecimento())) {
//						listagemDeCursos.remove(i);
//						tamanho--;
//						JOptionPane.showMessageDialog(null, "Curso removido com sucesso!", "SUCESSO",
//								JOptionPane.PLAIN_MESSAGE);
//
//					}
//				}
//
//				// comparativo pra saber se teve algum dado removido
//				if (tamanhoAnterior == tamanho) {
//					JOptionPane.showMessageDialog(null,
//							"Nenhum curso cadastrado com esses dados foi encontrado para ser removido", "ERRO",
//							JOptionPane.ERROR_MESSAGE);
//				}
//
//				metodosPrincipais.limparArquivo("Cursos.csv");
//
//				// reescrever o novo arquivo
//				for (int i = 0; i < tamanho; i++) {
//					Curso cursoLista = new Curso();
//					cursoLista = listagemDeCursos.get(i);
//					metodosPrincipais.inserirNoArquivo(cursoLista.toString(), "Cursos.csv");
//				}
		}
	

	private void limparDisciplina() {
		taDisciplina.setText("");

	}

}
