package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista;
import br.edu.fateczl.Fila.Fila;
import model.Curso;
import model.Disciplina;
import model.Inscricoes;
import model.Professor;

public class InscricoesController implements ActionListener {

	private JTextField tfInscricoesNomeDisciplina;
	private JTextField tfInscricoesNomeProfessor;
	private JTextArea taInscricoesInscritos;
	private int posicao = -1;
	Lista<Inscricoes> listaInscricoes = new Lista<Inscricoes>();
	MetodosPrincipaisController metodosPrincipais = new MetodosPrincipaisController();

	public InscricoesController(JTextField tfInscricoesNomeDisciplina, JTextField tfInscricoesNomeProfessor,
			JTextArea taInscricoesInscritos) {
		this.tfInscricoesNomeProfessor = tfInscricoesNomeProfessor;
		this.tfInscricoesNomeDisciplina = tfInscricoesNomeDisciplina;
		this.taInscricoesInscritos = taInscricoesInscritos;

	}

	public InscricoesController() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		// Cadastra o curso na lista de cursos e no Cursos.csv
		if (cmd.equals("Cadastrar")) {
			try {
				limparTAInscricao();
				cadastrarInscricao();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		// Busca o curso com base nos dados passados para a busca
		if (cmd.equals("Buscar")) {
			try {
				limparTAInscricao();
				buscarInscricao();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Deletar")) {
			try {
				limparTAInscricao();
				deletarInscricao();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Limpar")) {
			limparTAInscricao();
		}
		if (cmd.equals("Listar")) {
			try {
				limparTAInscricao();
				allInscricoes();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	
	}


	private void allInscricoes() throws  Exception {
		Fila<Inscricoes> inscricoesEncontradas = new Fila<>();
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Inscricoes.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				Inscricoes inscricoesAdd = new Inscricoes();
				inscricoesAdd.setNomeProfessor(vetLinha[0]);
				inscricoesAdd.setCpfProfessor(vetLinha[1]);
				inscricoesAdd.setPontos(vetLinha[2]);
				inscricoesAdd.setAreaConhecimento(vetLinha[3]);
				inscricoesAdd.setNomeDisciplina(vetLinha[4]);
				inscricoesAdd.setCodigoDisciplina(vetLinha[5]);
				inscricoesAdd.setCodigoProcesso(vetLinha[6]);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}

		taInscricoesInscritos.append(String.format("%-28s %-12s %-30s %-20s %-15s%n", "Nome Prof", "Cpf",
				"Nome Disc.", "Cód Disciplina", "Cód Processo"));
		while (!inscricoesEncontradas.isEmpty()) {
			Inscricoes inscricao=new Inscricoes();
			inscricao = inscricoesEncontradas.remove();
			taInscricoesInscritos.append(String.format("%-28s %-12s %-30s %-20s %-15s%n",
					inscricao.getNomeProfessor(), inscricao.getCpfProfessor(), inscricao.getNomeDisciplina(),
					inscricao.getCodigoDisciplina(), inscricao.getCodigoProcesso()));
			;
		}

		
	}

	private void deletarInscricao()throws Exception {
		// Capturo o dado para ser removido
		Inscricoes inscricao = new Inscricoes();
		inscricao.setNomeProfessor(tfInscricoesNomeProfessor.getText());
		inscricao.setNomeDisciplina(tfInscricoesNomeDisciplina.getText());
		Fila<Inscricoes>inscricaoEncontrada=new Fila<Inscricoes>();
		inscricaoEncontrada=searchNome(inscricao, inscricaoEncontrada);
		inscricao=inscricaoEncontrada.remove();
		
		Lista<Inscricoes> listagemDeInscricoes = new Lista<Inscricoes>();

		// Todo o arquivo csv é passado para uma lista
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Inscricoes.csv");
		alimentarLista("Inscricoes.csv", listagemDeInscricoes);

		// buscar o dado para ser removido na lista
		int tamanhoAnterior = listagemDeInscricoes.size();
		int tamanho = listagemDeInscricoes.size();
		for (int i = 0; i < tamanho; i++) {
			Inscricoes inscricaoLista = new Inscricoes();
			inscricaoLista = listagemDeInscricoes.get(i);

			if (!inscricao.getNomeProfessor().isBlank()&&inscricaoLista.getNomeProfessor().contains(inscricao.getNomeProfessor())
					||!inscricao.getNomeDisciplina().isBlank()&&inscricaoLista.getNomeDisciplina().contains(inscricao.getNomeDisciplina())) {
				listagemDeInscricoes.remove(i);
				i--;
				tamanho--;
				JOptionPane.showMessageDialog(null, "Inscrição removida com sucesso!", "SUCESSO",
						JOptionPane.PLAIN_MESSAGE);

			}
		}

		// comparativo pra saber se teve algum dado removido
		if (tamanhoAnterior == tamanho) {
			JOptionPane.showMessageDialog(null,
					"Nenhuma inscrição cadastrada com esses dados foi encontrada para ser removida", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}

		metodosPrincipais.limparArquivo("Inscricoes.csv");

		// reescrever o novo arquivo
		for (int i = 0; i < tamanho; i++) {
			Inscricoes inscricaoLista = new Inscricoes();
			inscricaoLista = listagemDeInscricoes.get(i);
			metodosPrincipais.inserirNoArquivo(inscricaoLista.toString(), "Inscricoes.csv");
		}

	}

	Lista<Inscricoes> alimentarLista(String string, Lista<Inscricoes> listagemDeInscricoes) throws Exception {
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Inscricoes.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {
				String[] vetLinha = linha.split(";");
					Inscricoes inscricoesAdd = new Inscricoes();
					inscricoesAdd.setNomeProfessor(vetLinha[0]);
					inscricoesAdd.setCpfProfessor(vetLinha[1]);
					inscricoesAdd.setPontos(vetLinha[2]);
					inscricoesAdd.setAreaConhecimento(vetLinha[3]);
					inscricoesAdd.setNomeDisciplina(vetLinha[4]);
					inscricoesAdd.setCodigoDisciplina(vetLinha[5]);
					inscricoesAdd.setCodigoProcesso(vetLinha[6]);
					listagemDeInscricoes.addLast(inscricoesAdd);
					linha = buffer.readLine();
				}
			buffer.close();
			isr.close();
			fis.close();
		}

		return listagemDeInscricoes;

		
	}

	private void buscarInscricao() throws Exception {
		Inscricoes inscricao = new Inscricoes();
		Fila<Inscricoes> inscricoesEncontradas = new Fila<>();
		inscricao.setNomeProfessor(tfInscricoesNomeProfessor.getText());
		inscricao.setNomeDisciplina(tfInscricoesNomeDisciplina.getText());

		if (!inscricao.getNomeProfessor().isBlank()) {
			inscricoesEncontradas = searchNome(inscricao, inscricoesEncontradas);
		} else if (!inscricao.getNomeDisciplina().isBlank()) {
			inscricoesEncontradas = searchDisciplina(inscricao, inscricoesEncontradas);
		} else {
			JOptionPane.showMessageDialog(null, "Digite em um campo para pesquisar", "ERRO", JOptionPane.ERROR_MESSAGE);
			inscricao = null;
		}

		if (inscricao != null && inscricoesEncontradas.size() > 0) {
			taInscricoesInscritos.append(String.format("%-28s %-12s %-30s %-10s %-10%n", "Nome Prof", "Cpf",
					"Nome Disc.", "Cód Disciplina", "Cód Processo"));
			while (!inscricoesEncontradas.isEmpty()) {
				inscricao = inscricoesEncontradas.remove();
				taInscricoesInscritos.append(String.format("%-28s %-12s %-30s %-10s %-10s%n",
						inscricao.getNomeProfessor(), inscricao.getCpfProfessor(), inscricao.getNomeDisciplina(),
						inscricao.getCodigoDisciplina(), inscricao.getCodigoProcesso()));
				;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Inscricao não encontrado", "ERRO", JOptionPane.INFORMATION_MESSAGE);

		}

	}

	private Fila<Inscricoes> searchDisciplina(Inscricoes inscricao, Fila<Inscricoes> inscricoesEncontradas)
			throws Exception {
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Inscricoes.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[2].equals(inscricao.getNomeDisciplina())) {
					Inscricoes inscricoesAdd = new Inscricoes();
					inscricoesAdd.setNomeProfessor(vetLinha[0]);
					inscricoesAdd.setCpfProfessor(vetLinha[1]);
					inscricoesAdd.setPontos(vetLinha[2]);
					inscricoesAdd.setAreaConhecimento(vetLinha[3]);
					inscricoesAdd.setNomeDisciplina(vetLinha[4]);
					inscricoesAdd.setCodigoDisciplina(vetLinha[5]);
					inscricoesAdd.setCodigoProcesso(vetLinha[6]);
					inscricoesEncontradas.insert(inscricoesAdd);
				}

				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}

		return inscricoesEncontradas;

	}

	private Fila<Inscricoes> searchNome(Inscricoes inscricao, Fila<Inscricoes> inscricoesEncontradas) throws Exception {

		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Inscricoes.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();

			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[0].equals(inscricao.getNomeProfessor())) {
					Inscricoes inscricoesAdd = new Inscricoes();
					inscricoesAdd.setNomeProfessor(vetLinha[0]);
					inscricoesAdd.setCpfProfessor(vetLinha[1]);
					inscricoesAdd.setPontos(vetLinha[2]);
					inscricoesAdd.setAreaConhecimento(vetLinha[3]);
					inscricoesAdd.setNomeDisciplina(vetLinha[4]);
					inscricoesAdd.setCodigoDisciplina(vetLinha[5]);
					inscricoesAdd.setCodigoProcesso(vetLinha[6]);
					inscricoesEncontradas.insert(inscricoesAdd);
				}

				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}

		return inscricoesEncontradas;

	}

	private void cadastrarInscricao() throws Exception {

		Inscricoes inscricao = new Inscricoes();
		Disciplina disciplina = new Disciplina();
		Fila<Disciplina> filaDisciplina = new Fila<Disciplina>();
		Fila<Professor> filaProfessor = new Fila<>();
		Professor professor = new Professor();
		inscricao.setNomeProfessor(tfInscricoesNomeProfessor.getText());
		inscricao.setNomeDisciplina(tfInscricoesNomeDisciplina.getText());
		disciplina.setNomeDisciplina(inscricao.getNomeDisciplina());

		DisciplinaController discCont = new DisciplinaController();
		filaDisciplina = discCont.searchName(disciplina, filaDisciplina);
		disciplina = filaDisciplina.remove();

		ProfessorController profCont = new ProfessorController();
		filaProfessor = profCont.searchNome(professor, filaProfessor);
		professor = filaProfessor.remove();

		inscricao.setCodigoDisciplina(disciplina.getCodigoDisciplina());
		inscricao.setCodigoProcesso(disciplina.getCodigoProcesso());
		inscricao.setCpfProfessor(professor.getCpf());
		inscricao.setPontos(professor.getPontos());
		inscricao.setAreaConhecimento(professor.getAreaConhecimento());

		if (!inscricao.getNomeDisciplina().equals("") && !inscricao.getCodigoDisciplina().equals("")
				&& !inscricao.getCodigoProcesso().equals("") && !inscricao.getCpfProfessor().equals("")
				&& !inscricao.getNomeProfessor().equals("") && !inscricao.getAreaConhecimento().equals("")
				&& !inscricao.getPontos().equals("")) {
			// adicionar o curso na lista de cursos
			listaInscricoes.addLast(inscricao);

			// inserir o curso no Curso.csv
			metodosPrincipais.inserirNoArquivo(inscricao.toString(), "Inscricoes.csv");

			// limpar os campos
			limparCamposInscricao();
			taInscricoesInscritos.setText("Inscrição adicionada!.");

		} else {
			JOptionPane.showMessageDialog(null,
					"Todas as informações devem ser preenchidas para cadastrar um novo curso", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void limparCamposInscricao() {
		tfInscricoesNomeDisciplina.setText("");
		tfInscricoesNomeProfessor.setText("");

	}

	private void limparTAInscricao() {

		taInscricoesInscritos.setText("");

	}

}
