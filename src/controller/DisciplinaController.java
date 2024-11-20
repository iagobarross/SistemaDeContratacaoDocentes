package controller;

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
import model.Disciplina;

public class DisciplinaController implements ActionListener {
	private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaNome;
	private JComboBox<String> cbDisciplinaDiaSemana;
	private JTextField tfDisciplinaHora;
	private JTextField tfDisciplinaTotalHoras;
	private JTextField tfDisciplinaCodigoCurso;
	private JTextArea taDisciplina;
	Lista<Disciplina> listaDisciplinas = new Lista<>();
	private static int codigoProcesso;

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
		Disciplina disciplina = new Disciplina();
		disciplina.setNomeDisciplina(tfDisciplinaNome.getText());
		disciplina.setCodigoDisciplina(tfDisciplinaCodigo.getText());
		disciplina.setCodigoCurso(tfDisciplinaCodigoCurso.getText());
		Lista<Disciplina> discFound = new Lista<>();

		if (!disciplina.getNomeDisciplina().isBlank()) {
			disciplina = searchName(disciplina);
		} else if (!disciplina.getCodigoDisciplina().isBlank()) {
			discFound = searchCodeDisc(disciplina);
		} else if (!disciplina.getCodigoCurso().isBlank()) {
			discFound = searchCodeCourse(disciplina);
		} else {
			JOptionPane.showMessageDialog(null, "Digite em um campo para pesquisar", "ERRO", JOptionPane.ERROR_MESSAGE);
			disciplina = null;
		}

		if (disciplina != null) {
			taDisciplina.setText("Cód. Disciplina: " + disciplina.getCodigoDisciplina() + " Nome: "
					+ disciplina.getNomeDisciplina() + " Dia da Semana: " + disciplina.getDiaSemana()
					+ " Hora Inicial: " + disciplina.getHoraInicial() + " Total de Horas Diárias: "
					+ disciplina.getHorasDiarias() + " Cód. Curso: " + disciplina.getCodigoCurso());
		} else {
			taDisciplina.setText("Disciplina não encontrada");
		}

	}

	private Disciplina searchName(Disciplina disciplina) throws IOException {
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
					disciplina.setCodigoDisciplina(vetLinha[0]);
					disciplina.setDiaSemana(vetLinha[2]);
					disciplina.setHoraInicial(vetLinha[3]);
					disciplina.setHorasDiarias(vetLinha[4]);
					disciplina.setCodigoCurso(vetLinha[5]);
					break;
				}

				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}

		if (disciplina.getCodigoDisciplina().equals("")) {
			return null;
		}

		tfDisciplinaNome.setText("");
		return disciplina;
	}

	private Disciplina searchCodeDisc(Disciplina disciplina) throws IOException {
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
					disciplina.setNomeDisciplina(vetLinha[1]);
					disciplina.setDiaSemana(vetLinha[2]);
					disciplina.setHoraInicial(vetLinha[3]);
					disciplina.setHorasDiarias(vetLinha[4]);
					disciplina.setCodigoCurso(vetLinha[5]);
				}

				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}

		if (disciplina.getNomeDisciplina().equals("") ) {
			return null;
		}

		tfDisciplinaCodigo.setText("");

		return disciplina;
	}

	private Lista<Disciplina> searchCodeCourse(Disciplina disciplina) throws Exception {
		Lista<Disciplina> lista = new Lista<>();
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
					disciplina.setCodigoDisciplina(vetLinha[0]);
					disciplina.setNomeDisciplina(vetLinha[1]);
					disciplina.setDiaSemana(vetLinha[2]);
					disciplina.setHoraInicial(vetLinha[3]);
					disciplina.setHorasDiarias(vetLinha[4]);
					lista.addLast(disciplina);
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

		return lista;
	}

	private void deletarDisciplina() {
		// TODO Auto-generated method stub

	}

	private void limparDisciplina() {
		taDisciplina.setText("");

	}

}
