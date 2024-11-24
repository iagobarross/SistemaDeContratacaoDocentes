package controller;
/*  quando deleta após buscar da erro
 * 	formatação errada com nomes curtos 
 * 	
 *  
 *  
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista;
import br.edu.fateczl.Fila.Fila;
import model.Curso;
import model.Professor;

public class ProfessorController implements ActionListener{
	private JTextField tfProfessorCPF;
	private JTextField tfProfessorNome;
	private JComboBox<String> cbProfessorAreaConhecimento;
	private JTextField tfProfessorPontos;
	private JTextArea taProfessor;
	private JLabel lblProfessorModoAlteracao;
	private JButton btnProfessorSalvarAlteracao;
	private int posicao = -1;
	Lista<Professor> listaProfessores = new Lista<Professor>();
	MetodosPrincipaisController metodosPrincipais = new MetodosPrincipaisController();

	public ProfessorController(JTextField tfProfessorCPF, JTextField tfProfessorNome,
			JComboBox<String> cbProfessorAreaConhecimento, JTextField tfProfessorPontos, JTextArea taProfessor,JLabel lblProfessorModoAlteracao,JButton btnProfessorSalvarAlteracao) {
		this.tfProfessorCPF = tfProfessorCPF;
		this.tfProfessorNome = tfProfessorNome;
		this.cbProfessorAreaConhecimento = cbProfessorAreaConhecimento;
		this.tfProfessorPontos = tfProfessorPontos;
		this.taProfessor = taProfessor;
		this.btnProfessorSalvarAlteracao=btnProfessorSalvarAlteracao;
		this.lblProfessorModoAlteracao=lblProfessorModoAlteracao;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		// Cadastra o curso na lista de cursos e no Cursos.csv
				if (cmd.equals("Cadastrar")) {
					try {
						limparTAProfessor();
						cadastrarProfessor();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				// Busca o curso com base nos dados passados para a busca
				if (cmd.equals("Buscar")) {
					try {
						limparTAProfessor();
						buscarProfessor();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if (cmd.equals("Deletar")) {
					try {
						limparTAProfessor();
						deletarProfessor();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if (cmd.equals("Limpar")) {
					limparTAProfessor();
				}
				if (cmd.equals("Listar")) {
					try {
						limparTAProfessor();
						allProfessores();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if (cmd.equals("Atualizar")) {
					try {
						limparTAProfessor();
						posicao = atualizarCurso();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if (cmd.equals("Salvar Alterações")) {
					try {
						limparTAProfessor();
						salvarAlteracoes(posicao);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
	}

	private void cadastrarProfessor() throws Exception {

		// capturar os dados do windowbuilder e colocar na classe Curso
		Professor professor = new Professor();
		professor.setAreaConhecimento(cbProfessorAreaConhecimento.getSelectedItem().toString());
		professor.setCpf(tfProfessorCPF.getText());
		professor.setNome(tfProfessorNome.getText());
		professor.setPontos(tfProfessorPontos.getText());
		if (!professor.getAreaConhecimento().equals(null) && !professor.getCpf().equals("")
				&& !professor.getNome().equals("") && !professor.getPontos().equals("")) {
			// adicionar o curso na lista de cursos
			listaProfessores.addLast(professor);

			// inserir o curso no Curso.csv
			metodosPrincipais.inserirNoArquivo(professor.toString(), "Professores.csv");

			// limpar os campos
			limparCamposProfessor();
			taProfessor.setText("Professor adicionado!.");

		} else {
			JOptionPane.showMessageDialog(null,
					"Todas as informações devem ser preenchidas para cadastrar um novo curso", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void limparCamposProfessor() {
		tfProfessorCPF.setText("");
		tfProfessorNome.setText("");
		tfProfessorPontos.setText("");
		cbProfessorAreaConhecimento.setSelectedItem("");
		
	}

	private void buscarProfessor() throws Exception {
		
		Professor professor = new Professor();
		Fila<Professor> professorEncontrado = new Fila<>();
		professor.setAreaConhecimento(cbProfessorAreaConhecimento.getSelectedItem().toString());
		professor.setCpf(tfProfessorCPF.getText());
		professor.setNome(tfProfessorNome.getText());
		professor.setPontos(tfProfessorPontos.getText());

		if (!professor.getAreaConhecimento().isEmpty()) {
			professorEncontrado = searchArea(professor, professorEncontrado);
		} else if (!professor.getCpf().isBlank()) {
			professorEncontrado = searchCpf(professor, professorEncontrado);
		} else if (!professor.getNome().isBlank()) {
			professorEncontrado = searchNome(professor, professorEncontrado);
		}else if(!professor.getPontos().isBlank()) { // Se o professor tirar 0 ?
			professorEncontrado = searchPontos(professor, professorEncontrado);
		} else {
			JOptionPane.showMessageDialog(null, "Digite em um campo para pesquisar", "ERRO", JOptionPane.ERROR_MESSAGE);
			professor = null;
		}

		if (professor != null && professorEncontrado.size() > 0) {
			taProfessor.append("Nome\t\tCpf\t\t Area Conhecimento\t\t Pontos\r\n");
			while (!professorEncontrado.isEmpty()) {
				professor = professorEncontrado.remove();
				taProfessor.append(professor.getNome() + "\t" + professor.getCpf() + "\t\t"
						+ professor.getAreaConhecimento() +"\t\t"+professor.getPontos()+ "\r\n");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Professor não encontrado", "ERRO", JOptionPane.INFORMATION_MESSAGE);

		}

	}

	private Fila<Professor> searchArea(Professor professor, Fila<Professor> professorEncontrado) throws Exception {
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Professores.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[2].equals(professor.getAreaConhecimento())) {
					Professor professoradd = new Professor();
					professoradd.setNome(vetLinha[1]);
					professoradd.setCpf(vetLinha[0]);
					professoradd.setAreaConhecimento(vetLinha[2]);
					professoradd.setPontos(vetLinha[3]);
					professorEncontrado.insert(professoradd);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return professorEncontrado;
	}

	private Fila<Professor> searchCpf(Professor professor, Fila<Professor> professorEncontrado) throws Exception {
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Professores.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[0].equals(professor.getCpf())) {
					Professor professoradd = new Professor();
					professoradd.setNome(vetLinha[1]);
					professoradd.setCpf(vetLinha[0]);
					professoradd.setAreaConhecimento(vetLinha[2]);
					professoradd.setPontos(vetLinha[3]);
					professorEncontrado.insert(professoradd);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return professorEncontrado;
	}

	private Fila<Professor> searchNome(Professor professor, Fila<Professor> professorEncontrado) throws Exception {
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Professores.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[1].equals(professor.getNome())) {
					Professor professoradd = new Professor();
					professoradd.setNome(vetLinha[1]);
					professoradd.setCpf(vetLinha[0]);
					professoradd.setAreaConhecimento(vetLinha[2]);
					professoradd.setPontos(vetLinha[3]);
					professorEncontrado.insert(professoradd);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return professorEncontrado;
	}

	private Fila<Professor> searchPontos(Professor professor, Fila<Professor> professorEncontrado) throws Exception {
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Professores.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[3].equals(professor.getPontos())) {
					Professor professoradd = new Professor();
					professoradd.setNome(vetLinha[1]);
					professoradd.setCpf(vetLinha[0]);
					professoradd.setAreaConhecimento(vetLinha[2]);
					professoradd.setPontos(vetLinha[3]);
					professorEncontrado.insert(professoradd);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return professorEncontrado;
	}

	private void deletarProfessor() throws Exception {
		// Capturo o dado para ser removido
		Professor professor = new Professor();
		Lista<Professor> listagemProfessores = new Lista<>();
		professor.setAreaConhecimento(cbProfessorAreaConhecimento.getSelectedItem().toString());
		professor.setCpf(tfProfessorCPF.getText());
		professor.setNome(tfProfessorNome.getText());
		professor.setPontos(tfProfessorPontos.getText());

		// Todo o arquivo csv é passado para uma lista
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Professores.csv");
		alimentarLista("Professores.csv", listagemProfessores);

		// buscar o dado para ser removido na lista
		int tamanhoAnterior = listagemProfessores.size();
		int tamanho = listagemProfessores.size();
		for (int i = 0; i < tamanho; i++) {
			Professor professorLista = new Professor();
			professorLista = listagemProfessores.get(i);

			if (!professor.getNome().isBlank() && professorLista.getNome().contains(professor.getNome())
					|| professor.getPontos().isBlank() && professorLista.getPontos().contains(professor.getPontos())
					||professor.getCpf().isBlank()&& professorLista.getCpf().contains(professor.getCpf())
					|| !professor.getAreaConhecimento().isEmpty()
							&& professorLista.getAreaConhecimento().contains(professor.getAreaConhecimento())) {
				listagemProfessores.remove(i);
				tamanho--;
				JOptionPane.showMessageDialog(null, "Professor removido com sucesso!", "SUCESSO",
						JOptionPane.PLAIN_MESSAGE);

			}
		}

		// comparativo pra saber se teve algum dado removido
		if (tamanhoAnterior == tamanho) {
			JOptionPane.showMessageDialog(null,
					"Nenhum Professor cadastrado com esses dados foi encontrado para ser removido", "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		metodosPrincipais.limparArquivo("Professores.csv");

		// reescrever o novo arquivo
		for (int i = 0; i < tamanho; i++) {
			Professor professorLista = new Professor();
			professorLista = listagemProfessores.get(i);
			metodosPrincipais.inserirNoArquivo(professorLista.toString(), "Professores.csv");
		}
	}

	private void limparTAProfessor() {
		taProfessor.setText("");
	}

	private int atualizarCurso() throws Exception {
		
		Professor ItemListaDeProfessores = new Professor();
		Professor professor = new Professor();
		Fila<Professor> professoresEncontrados = new Fila<>();
		Lista<Professor> listagemProfessores = new Lista<>();
		
		professor.setAreaConhecimento(cbProfessorAreaConhecimento.getSelectedItem().toString());
		professor.setCpf(tfProfessorCPF.getText());
		professor.setNome(tfProfessorNome.getText());
		professor.setPontos(tfProfessorPontos.getText());
		
		if (!professor.getAreaConhecimento().isEmpty()) {
			professoresEncontrados = searchArea(professor, professoresEncontrados);
		} else if (!professor.getCpf().isBlank()) {
			professoresEncontrados= searchCpf(professor, professoresEncontrados);
		} else if (!professor.getNome().isBlank()) {
			professoresEncontrados = searchNome(professor, professoresEncontrados);
		}else if(professor.getPontos().isBlank()) { // Se o professor tirar 0 ?
			professoresEncontrados = searchPontos(professor, professoresEncontrados);
		} else {
			JOptionPane.showMessageDialog(null, "Digite em um campo para pesquisar", "ERRO", JOptionPane.ERROR_MESSAGE);
			professor = null;
			return -1;
		}

		if (professoresEncontrados.size() > 1) {
			JOptionPane.showMessageDialog(null, "A alteração deve ser feita apenas um profesor por vez", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		} else if (professoresEncontrados.size() == 0) {
			JOptionPane.showMessageDialog(null, "Professor não encontrado", "ERRO", JOptionPane.INFORMATION_MESSAGE);
		} else {
			professor = professoresEncontrados.remove();
			listagemProfessores = alimentarLista("Professores.csv", listagemProfessores);
			int tamanho = listagemProfessores.size();
			for (int i = 0; i < tamanho; i++) {
				ItemListaDeProfessores = listagemProfessores.get(i);
				if (!professor.getNome().isBlank() && ItemListaDeProfessores.getNome().contains(professor.getNome())
						&&professor.getPontos().isBlank()&&ItemListaDeProfessores.getPontos()==professor.getPontos()
						&& !professor.getCpf().isBlank()
						&& ItemListaDeProfessores.getCpf().contains(professor.getCpf())
						&& !professor.getAreaConhecimento().isBlank()
						&& ItemListaDeProfessores.getAreaConhecimento().contains(professor.getAreaConhecimento())) {
					lblProfessorModoAlteracao.setVisible(true);
					btnProfessorSalvarAlteracao.setVisible(true);
					btnProfessorSalvarAlteracao.setEnabled(true);
					taProfessor.setText(
							"Curso encontrado! Digite nos campos acima quais valores deseja alterar.\nOs campos que ficarem em branco não serão substituídos.");
					return i;
				}
			}
		}
		return -1;

	}

//	private void salvarAlteracoes(int posicao) throws Exception {
//		Lista<Curso> ListagemDeCursos = new Lista<>();
//		Curso curso = new Curso();
//		Curso cursoEncontrado = new Curso();
//
//		curso.setCodigoCurso(tfCursoCodigo.getText());
//		curso.setNomeCurso(tfCursoNome.getText());
//		curso.setAreaConhecimento((String) cbCursoAreaConhecimento.getSelectedItem());
//		ListagemDeCursos = metodosPrincipais.alimentarLista("Cursos.csv", ListagemDeCursos);
//
//		cursoEncontrado = ListagemDeCursos.get(posicao);
//
//		if (!curso.getNomeCurso().isBlank()) {
//			cursoEncontrado.setNomeCurso(curso.getNomeCurso());
//		}
//		if (!curso.getCodigoCurso().isBlank()) {
//			cursoEncontrado.setCodigoCurso(curso.getCodigoCurso());
//		}
//		if (!curso.getAreaConhecimento().isEmpty()) {
//			cursoEncontrado.setAreaConhecimento(curso.getAreaConhecimento());
//		}
//
//		ListagemDeCursos.add(cursoEncontrado, posicao);
//		ListagemDeCursos.remove(posicao + 1);
//
//		metodosPrincipais.limparArquivo("Cursos.csv");
//		int tamanho = ListagemDeCursos.size();
//		for (int i = 0; i < tamanho; i++) {
//			Curso inserirCurso = new Curso();
//			inserirCurso = ListagemDeCursos.get(i);
//			metodosPrincipais.inserirNoArquivo(inserirCurso.toString(), "Cursos.csv");
//		}
//
//		lblCursoModoAlteracao.setVisible(false);
//		btnCursoSalvarAlteracao.setVisible(false);
//		btnCursoSalvarAlteracao.setEnabled(false);
//		JOptionPane.showMessageDialog(null, "Curso alterado!", "SUCESSO", JOptionPane.PLAIN_MESSAGE);
//
//	}
//
//	private void allCourses() throws Exception {
//		Fila<Curso> cursosEncontrados = new Fila<>();
//		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
//		File arq = new File(path, "Cursos.csv");
//		if (arq.exists() && arq.isFile()) {
//			FileInputStream fis = new FileInputStream(arq);
//			InputStreamReader isr = new InputStreamReader(fis);
//			BufferedReader buffer = new BufferedReader(isr);
//			String linha = buffer.readLine();
//			while (linha != null) {
//				String[] vetLinha = linha.split(";");
//				Curso cursoadd = new Curso();
//				cursoadd.setCodigoCurso(vetLinha[0]);
//				cursoadd.setNomeCurso(vetLinha[1]);
//				cursoadd.setAreaConhecimento(vetLinha[2]);
//				cursosEncontrados.insert(cursoadd);
//				linha = buffer.readLine();
//			}
//			buffer.close();
//			isr.close();
//			fis.close();
//		}
//
//		taCurso.append("Cód. Curso\tNome\t\t\tÁrea de conhecimento\r\n");
//		while (!cursosEncontrados.isEmpty()) {
//			Curso curso = new Curso();
//			curso = cursosEncontrados.remove();
//			taCurso.append(curso.getCodigoCurso() + "\t" + curso.getNomeCurso() + "\t" + curso.getAreaConhecimento()
//					+ "\r\n");
//		}
//
//	}
	public Lista<Professor> alimentarLista(String arquivoNome, Lista<Professor> listaDeItens) throws Exception{
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, arquivoNome);
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				Professor professoradd = new Professor();
				professoradd.setNome(vetLinha[1]);
				professoradd.setCpf(vetLinha[0]);
				professoradd.setAreaConhecimento(vetLinha[2]);
				professoradd.setPontos(vetLinha[3]);
				listaDeItens.addLast(professoradd);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return listaDeItens;
	}
	private void allProfessores() throws Exception {
		Fila<Professor> professoresEncontrados = new Fila<>();
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Professores.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				Professor professoradd = new Professor();
				professoradd.setNome(vetLinha[1]);
				professoradd.setCpf(vetLinha[0]);
				professoradd.setAreaConhecimento(vetLinha[2]);
				professoradd.setPontos(vetLinha[3]);
				professoresEncontrados.insert(professoradd);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
	

		taProfessor.append("Nome\t\tCpf\t\t Area Conhecimento\t\t Pontos\r\n");
			while (!professoresEncontrados.isEmpty()) {
				Professor professor=new Professor();
				professor = professoresEncontrados.remove();
				taProfessor.append(professor.getNome() + "\t" + professor.getCpf() + "\t\t"
						+ professor.getAreaConhecimento() +"\t\t"+professor.getPontos()+ "\r\n");
			}
	}
	private void salvarAlteracoes(int posicao) throws Exception {
	
		Lista<Professor> ListagemDeProfessores = new Lista<>();
		Professor professor = new Professor();
		Professor professorEncontrado = new Professor();

		professor.setAreaConhecimento(cbProfessorAreaConhecimento.getSelectedItem().toString());
		professor.setCpf(tfProfessorCPF.getText());
		professor.setNome(tfProfessorNome.getText());
		professor.setPontos(tfProfessorPontos.getText());
		ListagemDeProfessores=alimentarLista("Professores.csv", ListagemDeProfessores);
		
		professorEncontrado = ListagemDeProfessores.get(posicao);
		
		if (!professor.getAreaConhecimento().isEmpty()) {
			professorEncontrado.setAreaConhecimento(professor.getAreaConhecimento());
		} 
		if (!professor.getCpf().isBlank()) {
			professorEncontrado.setCpf(professor.getCpf());
		}
		 if (!professor.getNome().isBlank()) {
			 professorEncontrado.setNome(professor.getNome());
		 }if(professor.getPontos().isBlank()) { // Se o professor tirar 0 ?
			 professorEncontrado.setPontos(professor.getPontos());
		 }
		
		ListagemDeProfessores.add(professorEncontrado, posicao);
		ListagemDeProfessores.remove(posicao + 1);
		
		
		metodosPrincipais.limparArquivo("Professores.csv");
		int tamanho = ListagemDeProfessores.size();
		for (int i = 0; i < tamanho; i++) {
			Professor inserirProfessor = new Professor();
			inserirProfessor = ListagemDeProfessores.get(i);
			metodosPrincipais.inserirNoArquivo(inserirProfessor.toString(), "Professores.csv");
		}
		

		lblProfessorModoAlteracao.setVisible(false);
		btnProfessorSalvarAlteracao.setVisible(false);
		btnProfessorSalvarAlteracao.setEnabled(false);
		JOptionPane.showMessageDialog(null, "Curso alterado!", "SUCESSO", JOptionPane.PLAIN_MESSAGE);

	}
}
