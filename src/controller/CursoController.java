package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista;
import br.edu.fateczl.Fila.Fila;
import model.Curso;


	public class CursoController implements ActionListener {
		private JTextField tfCursoCodigo;
		private JTextField tfCursoNome;
		private JComboBox<String> cbCursoAreaConhecimento;
		private JTextArea taCurso;
		Lista<Curso> listaCursos = new Lista<Curso>();
		MetodosPrincipaisController metodosPrincipais = new MetodosPrincipaisController();

		public CursoController(JTextField tfCursoCodigo, JTextField tfCursoNome, JComboBox<String> cbCursoAreaConhecimento,
				JTextArea taCurso) {
			this.tfCursoCodigo = tfCursoCodigo;
			this.tfCursoNome = tfCursoNome;
			this.cbCursoAreaConhecimento = cbCursoAreaConhecimento;
			this.taCurso = taCurso;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();

			// Cadastra o curso na lista de cursos e no Cursos.csv
			if (cmd.equals("Cadastrar")) {
				try {
					cadastrarCurso();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// Busca o curso com base nos dados passados para a busca
			if (cmd.equals("Buscar")) {
				try {
					buscarCurso();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (cmd.equals("Deletar")) {
				try {
					deletarCurso();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (cmd.equals("Limpar")) {
				limparTACurso();
			}
			if (cmd.equals("Todos")) {
				try {
					allCourses();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (cmd.equals("Atualizar")) {
				try {
					atualizarCurso();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}

		private void cadastrarCurso() throws Exception {

			// capturar os dados do windowbuilder e colocar na classe Curso
			Curso curso = new Curso();
			curso.setCodigoCurso(tfCursoCodigo.getText());
			curso.setNomeCurso(tfCursoNome.getText());
			curso.setAreaConhecimento((String) cbCursoAreaConhecimento.getSelectedItem());

			// adicionar o curso na lista de cursos
			listaCursos.addLast(curso);

			// inserir o curso no Curso.csv
			metodosPrincipais.inserirNoArquivo(curso.toString(), "Cursos.csv");

			// limpar os campos
			limparCamposCurso();
		}

		private void buscarCurso() throws Exception {
			Curso curso = new Curso();
			Fila<Curso> cursosEncontrados = new Fila<>();
			curso.setCodigoCurso(tfCursoCodigo.getText());
			curso.setNomeCurso(tfCursoNome.getText());
			curso.setAreaConhecimento((String) cbCursoAreaConhecimento.getSelectedItem());

			if (!curso.getCodigoCurso().isBlank()) {
				searchCodigo(curso, cursosEncontrados);
			} else if (!curso.getNomeCurso().isBlank()) {
				searchNome(curso, cursosEncontrados);
			} else if (curso.getAreaConhecimento() != null) {
				searchArea(curso, cursosEncontrados);
			} else {
				JOptionPane.showMessageDialog(null, "Digite em um campo para pesquisar", "ERRO", JOptionPane.ERROR_MESSAGE);
				curso = null;
			}

			if (curso != null && cursosEncontrados.size() > 0) {
				taCurso.append("Cód. Curso\tNome\tÁrea de conhecimento\r\n");
				while (!cursosEncontrados.isEmpty()) {
					curso = cursosEncontrados.remove();
					taCurso.append(curso.getCodigoCurso() + "\t" + curso.getNomeCurso() + "\t" + curso.getAreaConhecimento()
							+ "\r\n");
				}
			} else {
				taCurso.setText("Curso não encontrado");
			}

		}

		private void allCourses() throws Exception {
			Fila<Curso> cursosEncontrados = new Fila<>();
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

			taCurso.append("Cód. Curso\tNome\tÁrea de conhecimento\r\n");
			while (!cursosEncontrados.isEmpty()) {
				Curso curso = new Curso();
				curso = cursosEncontrados.remove();
				taCurso.append(
						curso.getCodigoCurso() + "\t" + curso.getNomeCurso() + "\t" + curso.getAreaConhecimento() + "\r\n");
			}

		}

		private Fila<Curso> searchArea(Curso curso, Fila<Curso> cursosEncontrados) throws Exception {
			String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
			File arq = new File(path, "Cursos.csv");
			if (arq.exists() && arq.isFile()) {
				FileInputStream fis = new FileInputStream(arq);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader buffer = new BufferedReader(isr);
				String linha = buffer.readLine();
				while (linha != null) {
					String[] vetLinha = linha.split(";");
					if (vetLinha[2].contains(curso.getAreaConhecimento())) {
						Curso cursoadd = new Curso();
						cursoadd.setCodigoCurso(vetLinha[0]);
						cursoadd.setNomeCurso(vetLinha[1]);
						cursoadd.setAreaConhecimento(vetLinha[2]);
						cursosEncontrados.insert(cursoadd);
					}
					linha = buffer.readLine();
				}
				buffer.close();
				isr.close();
				fis.close();
			}

			if (cursosEncontrados.size() == 0) {
				return null;
			}
			return cursosEncontrados;
		}

		private Fila<Curso> searchNome(Curso curso, Fila<Curso> cursosEncontrados) throws IOException {
			String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
			File arq = new File(path, "Cursos.csv");
			if (arq.exists() && arq.isFile()) {
				FileInputStream fis = new FileInputStream(arq);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader buffer = new BufferedReader(isr);
				String linha = buffer.readLine();
				while (linha != null) {
					String[] vetLinha = linha.split(";");
					if (vetLinha[1].contains(curso.getNomeCurso())) {
						Curso cursoadd = new Curso();
						cursoadd.setCodigoCurso(vetLinha[0]);
						cursoadd.setNomeCurso(vetLinha[1]);
						cursoadd.setAreaConhecimento(vetLinha[2]);
						cursosEncontrados.insert(cursoadd);
					}
					linha = buffer.readLine();
				}
				buffer.close();
				isr.close();
				fis.close();
			}

			if (curso.getCodigoCurso().equals("")) {
				return null;
			}
			return cursosEncontrados;
		}

		private Fila<Curso> searchCodigo(Curso curso, Fila<Curso> cursosEncontrados) throws IOException {
			String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
			File arq = new File(path, "Cursos.csv");
			if (arq.exists() && arq.isFile()) {
				FileInputStream fis = new FileInputStream(arq);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader buffer = new BufferedReader(isr);
				String linha = buffer.readLine();
				while (linha != null) {
					String[] vetLinha = linha.split(";");
					if (vetLinha[0].contains(curso.getCodigoCurso())) {
						Curso cursoadd = new Curso();
						cursoadd.setCodigoCurso(vetLinha[0]);
						cursoadd.setNomeCurso(vetLinha[1]);
						cursoadd.setAreaConhecimento(vetLinha[2]);
						cursosEncontrados.insert(cursoadd);
					}
					linha = buffer.readLine();
				}
				buffer.close();
				isr.close();
				fis.close();
			}

			if (curso.getCodigoCurso().equals("")) {
				return null;
			}
			return cursosEncontrados;
		}

		private void deletarCurso() throws Exception {
			// Capturo o dado para ser removido
			Curso curso = new Curso();
			curso.setCodigoCurso(tfCursoCodigo.getText());
			curso.setNomeCurso(tfCursoNome.getText());
			curso.setAreaConhecimento((String) cbCursoAreaConhecimento.getSelectedItem());
			Lista<Curso> listagemDeCursos = new Lista<Curso>();

			// Todo o arquivo csv é passado para uma lista
			String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
			File arq = new File(path, "Cursos.csv");
			metodosPrincipais.alimentarLista("Cursos.csv", listagemDeCursos);

			// buscar o dado para ser removido na lista
			int tamanhoAnterior = listagemDeCursos.size();
			int tamanho = listagemDeCursos.size();
			for (int i = 0; i < tamanho; i++) {
				Curso cursoLista = new Curso();
				cursoLista = listagemDeCursos.get(i);

				if (!curso.getNomeCurso().isBlank() && cursoLista.getNomeCurso().contains(curso.getNomeCurso())
						|| !curso.getCodigoCurso().isBlank() && cursoLista.getCodigoCurso().contains(curso.getCodigoCurso())
						|| !curso.getAreaConhecimento().isBlank()
								&& cursoLista.getAreaConhecimento().contains(curso.getAreaConhecimento())) {
					listagemDeCursos.remove(i);
					tamanho--;
					taCurso.setText("Curso(s) removido(s) com sucesso!");
				}
			}
			// comparativo pra saber se teve algum dado removido
			if (tamanhoAnterior == tamanho) {
				taCurso.setText("Nenhum curso cadastrado com esses dados foi encontrado para ser removido.");
			}

			// reescrever o novo arquivo
			for (int i = 0; i < tamanho; i++) {
				Curso cursoLista = new Curso();
				cursoLista = listagemDeCursos.get(i);
				metodosPrincipais.inserirNoArquivo(cursoLista.toString(), "Cursos.csv");
			}
		}

		private void limparTACurso() {
			taCurso.setText("");
		}

		private void limparCamposCurso() {
			tfCursoCodigo.setText("");
			tfCursoNome.setText("");
			cbCursoAreaConhecimento.setSelectedItem(null);
		}

		private void atualizarCurso() throws Exception {
			Curso curso = new Curso();
			Lista<Curso> ListagemDeCursos = new Lista<>();
			Fila<Curso> cursosEncontrados = new Fila<>();

			curso.setCodigoCurso(tfCursoCodigo.getText());
			curso.setNomeCurso(tfCursoNome.getText());
			curso.setAreaConhecimento((String) cbCursoAreaConhecimento.getSelectedItem());

			if (!curso.getCodigoCurso().isBlank()) {
				searchCodigo(curso, cursosEncontrados);
			} else if (!curso.getNomeCurso().isBlank()) {
				searchNome(curso, cursosEncontrados);
			} else if (!curso.getAreaConhecimento().equals(null)) {
				searchArea(curso, cursosEncontrados);
			} else {
				JOptionPane.showMessageDialog(null, "Digite em um campo para pesquisar", "ERRO", JOptionPane.ERROR_MESSAGE);
				curso = null;
			}

			if (cursosEncontrados.size() > 1) {
				JOptionPane.showMessageDialog(null, "A alteração deve ser feita apenas um curso por vez", "ERRO",
						JOptionPane.ERROR_MESSAGE);
				curso = null;
			}
			else {
				Curso cursoEncontrado = new Curso();
				cursoEncontrado = cursosEncontrados.remove();
				
				ListagemDeCursos = metodosPrincipais.alimentarLista("Cursos.csv", ListagemDeCursos);
				
				int tamanho = ListagemDeCursos.size();
				for(int i = 0 ; i < tamanho ; i ++) {
					cursoEncontrado = ListagemDeCursos.get(i);
					if(!curso.getNomeCurso().isBlank() && cursoEncontrado.getNomeCurso().contains(curso.getNomeCurso())
						&& !curso.getCodigoCurso().isBlank() && cursoEncontrado.getCodigoCurso().contains(curso.getCodigoCurso())
						&& !curso.getAreaConhecimento().isBlank() && cursoEncontrado.getAreaConhecimento().contains(curso.getAreaConhecimento())) {
							ListagemDeCursos.add(curso, i);
							ListagemDeCursos.remove(i+1);
							break;
					}
				}
				for(int i = 0 ; i < tamanho ; i ++) {
					Curso inserirCurso = new Curso();
					inserirCurso = ListagemDeCursos.get(i);
					metodosPrincipais.inserirNoArquivo(inserirCurso.toString(), "Cursos.csv");
				}
			}

		}
	}

