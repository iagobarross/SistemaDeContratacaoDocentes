package controller;

import model.Curso;
import model.Disciplina;
import model.Inscricoes;
import model.Professor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

import br.edu.fateczl.Lista;
import br.edu.fateczl.Fila.Fila;

public class ProcessosController {

	private Lista<Disciplina>[] tabela;
	private Fila<Curso> cursosEncontrados = new Fila<Curso>();
	private Fila<Disciplina> disciplinaEncontrada = new Fila<Disciplina>();

	private JTextArea taProcessos;

	public ProcessosController(JTextArea taProcessos) {
		this.taProcessos = taProcessos;
	}

	public void listarProcessosAtivos() {

		try {
			allCourses();
			allDisciplines();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		inicializarLista();

		while (!disciplinaEncontrada.isEmpty()) {
			try {
				Disciplina discAux = new Disciplina();
				discAux = disciplinaEncontrada.remove();
				addDisciplina(discAux);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		taProcessos.append(String.format("%-40s %-50s %-40s%n", "Cód.Processo", "Nome Disciplina", "Cód. Curso"));

		try {
			int tamanho = tabela.length;
			for (int i = 0; i < tamanho; i++) {
				int tam = tabela[i].size();
				for (int j = 0; j < tam; j++) {
					Disciplina disciplina = new Disciplina();
					disciplina = tabela[i].get(j);
					taProcessos.append(String.format("%-40s %-50s %-40s%n", disciplina.getCodigoProcesso(),
							disciplina.getNomeDisciplina(), disciplina.getCodigoCurso()));
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private void allCourses() throws Exception {
		cursosEncontrados = new Fila<>();
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Cursos.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				Curso cursoadd = new Curso();
				cursoadd.setCodigoCurso(vetLinha[0]);
				cursoadd.setNomeCurso(vetLinha[1]);
				cursoadd.setAreaConhecimento(vetLinha[2]);
				cursosEncontrados.insert(cursoadd);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
	}

	private void allDisciplines() throws Exception {

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
				disciplina.setCodigoProcesso(vetLinha[6]);
				disciplinaEncontrada.insert(disciplina);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
	}

	private void inicializarLista() {
		tabela = new Lista[26];
		int tamanho = tabela.length;
		for (int i = 0; i < tamanho; i++) {
			tabela[i] = new Lista<>();
		}
	}

	private void addDisciplina(Disciplina disciplina) throws Exception {

		int posicao = disciplina.hashCode();
		tabela[posicao].addLast(disciplina);
	}
}
