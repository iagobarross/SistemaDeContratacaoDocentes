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
	Lista<Inscricoes> listaInscricoes = new Lista<Inscricoes>();
	MetodosPrincipaisController metodosPrincipais = new MetodosPrincipaisController();

	public InscricoesController(JTextField tfInscricoesNomeDisciplina, JTextField tfInscricoesNomeProfessor,
			JTextArea taInscricoesInscritos) {
		this.tfInscricoesNomeProfessor = tfInscricoesNomeProfessor;
		this.tfInscricoesNomeDisciplina = tfInscricoesNomeDisciplina;
		this.taInscricoesInscritos = taInscricoesInscritos;

	}

	public InscricoesController(JTextField tfInscricoesNomeDisciplina2, JTextArea taInscricoesInscritos2,
			JLabel lblInscricaoModoAlteracao, JButton btnInscricaoSalvarAlteracao) {

	}

	public InscricoesController() {
		super();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Cadastrar")) {
			try {
				limparTAInscricao();
				cadastrarInscricao();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
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
			limparCamposInscricao();
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

	private void allInscricoes() throws Exception {
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
				inscricoesEncontradas.insert(inscricoesAdd);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		
		taInscricoesInscritos.append(String.format("%-25s %-15s %-25s %-15s %-20s%n", "Nome Prof", "Cpf", "Nome Disc.",
				"Cód. Disciplina", "Cód. Processo"));
		while (!inscricoesEncontradas.isEmpty()) {
			Inscricoes inscricao = new Inscricoes();
			inscricao = inscricoesEncontradas.remove();
			taInscricoesInscritos.append(String.format("%-25s %-15s %-25s %-15s %-20s%n", inscricao.getNomeProfessor(),
					inscricao.getCpfProfessor(), inscricao.getNomeDisciplina(), inscricao.getCodigoDisciplina(),
					inscricao.getCodigoProcesso()));
			;
		}

	}

	private void deletarInscricao() throws Exception {
		Inscricoes inscricao = new Inscricoes();
		inscricao.setNomeProfessor(tfInscricoesNomeProfessor.getText());
		inscricao.setNomeDisciplina(tfInscricoesNomeDisciplina.getText());

		Lista<Inscricoes> listagemDeInscricoes = new Lista<Inscricoes>();
		alimentarLista("Inscricoes.csv", listagemDeInscricoes);

		int tamanhoAnterior = listagemDeInscricoes.size();
		int tamanho = listagemDeInscricoes.size();
		for (int i = 0; i < tamanho; i++) {
			Inscricoes inscricaoLista = new Inscricoes();
			inscricaoLista = listagemDeInscricoes.get(i);

			if (!inscricao.getNomeProfessor().equals("") && inscricaoLista.getNomeProfessor().contains(inscricao.getNomeProfessor())
					|| !inscricao.getNomeDisciplina().equals("") && inscricaoLista.getNomeDisciplina().contains(inscricao.getNomeDisciplina())) {
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

	public Lista<Inscricoes> alimentarLista(String string, Lista<Inscricoes> listagemDeInscricoes) throws Exception {
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
			return;
		}

		if (inscricao != null && inscricoesEncontradas.size() > 0) {
			taInscricoesInscritos.append(String.format("%-25s %-15s %-25s %-15s %-20s%n", "Nome Prof", "Cpf", "Nome Disc.",
					"Cód Disciplina", "Cód Processo"));
			while (!inscricoesEncontradas.isEmpty()) {
				inscricao = inscricoesEncontradas.remove();
				taInscricoesInscritos.append(String.format("%-25s %-15s %-25s %-15s %-20s%n", inscricao.getNomeProfessor(),
						inscricao.getCpfProfessor(), inscricao.getNomeDisciplina(), inscricao.getCodigoDisciplina(),
						inscricao.getCodigoProcesso()));
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
				if (vetLinha[4].equals(inscricao.getNomeDisciplina())) {
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
		inscricao.setNomeProfessor(tfInscricoesNomeProfessor.getText());
		inscricao.setNomeDisciplina(tfInscricoesNomeDisciplina.getText());

		Lista<Disciplina> listaDeDisciplinas = new Lista<Disciplina>();
		DisciplinaController discCont = new DisciplinaController();
		listaDeDisciplinas = discCont.alimentarLista("Disciplinas.csv", listaDeDisciplinas);
		int tamanho = listaDeDisciplinas.size();
		for (int i = 0; i < tamanho; i++) {
			Disciplina d = new Disciplina();
			d = listaDeDisciplinas.get(i);
			if (d.getNomeDisciplina().equals(inscricao.getNomeDisciplina())) {
				inscricao.setCodigoDisciplina(d.getCodigoDisciplina());
				inscricao.setCodigoProcesso(d.getCodigoProcesso());
				break;
			}
		}

		Lista<Professor> listaDeProfessores = new Lista<Professor>();
		ProfessorController profCont = new ProfessorController();
		listaDeProfessores = profCont.alimentarLista("Professores.csv", listaDeProfessores);
		int tamanho2 = listaDeProfessores.size();
		for (int i = 0; i < tamanho2; i++) {
			Professor p = new Professor();
			p = listaDeProfessores.get(i);
			if (p.getNome().equals(inscricao.getNomeProfessor())) {
				inscricao.setCpfProfessor(p.getCpf());
				inscricao.setAreaConhecimento(p.getAreaConhecimento());
				inscricao.setPontos(p.getPontos());
				break;
			}
		}

		if (!inscricao.getAreaConhecimento().isEmpty() && !inscricao.getCodigoDisciplina().isEmpty()
				&& !inscricao.getCpfProfessor().isEmpty() && !inscricao.getNomeDisciplina().isEmpty()
				&& !inscricao.getNomeProfessor().isEmpty() && !inscricao.getPontos().isEmpty()) {

			listaInscricoes.addLast(inscricao);
			metodosPrincipais.inserirNoArquivo(inscricao.toString(), "Inscricoes.csv");
			
			limparCamposInscricao();
			taInscricoesInscritos.append("Inscrição adicionada!");
		} else {
			JOptionPane.showMessageDialog(null,
					"Todas as informações devem ser preenchidas para cadastrar uma nova inscrição", "ERRO",
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