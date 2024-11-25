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
import model.Professor;

public class ProfessorController implements ActionListener {
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
			JComboBox<String> cbProfessorAreaConhecimento, JTextField tfProfessorPontos, JTextArea taProfessor,
			JButton btnProfessorSalvarAlteracao, JLabel lblProfessorModoAlteracao) {
		this.tfProfessorCPF = tfProfessorCPF;
		this.tfProfessorNome = tfProfessorNome;
		this.cbProfessorAreaConhecimento = cbProfessorAreaConhecimento;
		this.tfProfessorPontos = tfProfessorPontos;
		this.taProfessor = taProfessor;
		this.btnProfessorSalvarAlteracao = btnProfessorSalvarAlteracao;
		this.lblProfessorModoAlteracao = lblProfessorModoAlteracao;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("Cadastrar")) {
			try {
				limparTAProfessor();
				validarRepetidas();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
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
				posicao = atualizarProfessor();
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

	public ProfessorController() {
		super();
	}
	
	private void validarRepetidas() throws Exception {
		Professor professor = new Professor();
		professor.setAreaConhecimento(cbProfessorAreaConhecimento.getSelectedItem().toString());
		professor.setCpf(tfProfessorCPF.getText());
		professor.setNome(tfProfessorNome.getText());
		professor.setPontos(tfProfessorPontos.getText());
		Lista<Professor> todosOsProfessores = new Lista<Professor>();

		todosOsProfessores = alimentarLista("Professores.csv", todosOsProfessores);

		int tamanho = todosOsProfessores.size();

		for (int i = 0; i < tamanho; i++) {
			Professor p = new Professor();
			p = todosOsProfessores.get(i);
			if (professor.getNome().equals(p.getNome()) && professor.getCpf().equals(p.getCpf())
					&& professor.getAreaConhecimento().equals(p.getAreaConhecimento())
					&& professor.getPontos().equals(p.getPontos())) {
				JOptionPane.showMessageDialog(null, "Este professor já está cadastrado no sistema.", "ERRO",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		cadastrarProfessor(professor);
	}

	private void cadastrarProfessor(Professor professor) throws Exception {
		if (!professor.getAreaConhecimento().equals("") && !professor.getCpf().equals("")
				&& !professor.getNome().equals("") && !professor.getPontos().equals("")) {
			listaProfessores.addLast(professor);

			metodosPrincipais.inserirNoArquivo(professor.toString(), "Professores.csv");

			limparCamposProfessor();
			taProfessor.setText("Professor adicionado!");

		} else {
			JOptionPane.showMessageDialog(null,
					"Todas as informações devem ser preenchidas para cadastrar um novo professor", "ERRO",
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

		if (!professor.getAreaConhecimento().equals("")) {
			professorEncontrado = searchArea(professor, professorEncontrado);
		} else if (!professor.getCpf().isBlank()) {
			professorEncontrado = searchCpf(professor, professorEncontrado);
		} else if (!professor.getNome().isBlank()) {
			professorEncontrado = searchNome(professor, professorEncontrado);
		} else if (!professor.getPontos().isBlank()) { // Se o professor tirar 0 ?
			professorEncontrado = searchPontos(professor, professorEncontrado);
		} else {
			JOptionPane.showMessageDialog(null, "Digite em um campo para pesquisar", "ERRO", JOptionPane.ERROR_MESSAGE);
			professor = null;
		}

		if (professor != null && professorEncontrado.size() > 0) {
			taProfessor.append(String.format("%-30s %-12s %-40s %-10s%n","Nome","Cpf","Area Conhecimento","Pontos"));
			while (!professorEncontrado.isEmpty()) {
				professor = professorEncontrado.remove();
				taProfessor.append(String.format("%-30s %-12s %-40s %-10s%n",professor.getNome(),professor.getCpf(),professor.getAreaConhecimento(),professor.getPontos()));
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
				if (vetLinha[2].contains(professor.getAreaConhecimento())) {
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
				if (vetLinha[0].contains(professor.getCpf())) {
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

	Fila<Professor> searchNome(Professor professor, Fila<Professor> professorEncontrado) throws Exception {
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, "Professores.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[1].contains(professor.getNome())) {
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
				if (vetLinha[3].contains(professor.getPontos())) {
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
					|| professor.getCpf().isBlank() && professorLista.getCpf().contains(professor.getCpf())
					|| !professor.getAreaConhecimento().equals("")
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

	private int atualizarProfessor() throws Exception {

		Professor ItemListaDeProfessores = new Professor();
		Professor professor = new Professor();
		Fila<Professor> professoresEncontrados = new Fila<>();
		Lista<Professor> listagemProfessores = new Lista<>();

		professor.setAreaConhecimento(cbProfessorAreaConhecimento.getSelectedItem().toString());
		professor.setCpf(tfProfessorCPF.getText());
		professor.setNome(tfProfessorNome.getText());
		professor.setPontos(tfProfessorPontos.getText());

		if (!professor.getAreaConhecimento().equals("")) {
			professoresEncontrados = searchArea(professor, professoresEncontrados);
		} else if (!professor.getCpf().isBlank()) {
			professoresEncontrados = searchCpf(professor, professoresEncontrados);
		} else if (!professor.getNome().isBlank()) {
			professoresEncontrados = searchNome(professor, professoresEncontrados);
		} else if (professor.getPontos().isBlank()) { // Se o professor tirar 0 ?
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
						&& !professor.getPontos().isBlank()
						&& ItemListaDeProfessores.getPontos().contains(professor.getPontos())
						&& !professor.getCpf().isBlank() && ItemListaDeProfessores.getCpf().contains(professor.getCpf())
						&& !professor.getAreaConhecimento().equals("")
						&& ItemListaDeProfessores.getAreaConhecimento().contains(professor.getAreaConhecimento())) {
					lblProfessorModoAlteracao.setVisible(true);
					btnProfessorSalvarAlteracao.setVisible(true);
					btnProfessorSalvarAlteracao.setEnabled(true);
					taProfessor.setText(
							"Professor encontrado! Digite nos campos acima quais valores deseja alterar.\nOs campos que ficarem em branco não serão substituídos.");
					return i;
				}
			}
		}
		return -1;

	}

	public Lista<Professor> alimentarLista(String arquivoNome, Lista<Professor> listaDeItens) throws Exception {
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

		taProfessor.append(String.format("%-30s %-12s %-40s %-10s%n","Nome","Cpf","Area Conhecimento","Pontos"));
		while (!professoresEncontrados.isEmpty()) {
			Professor professor=new Professor();
			professor = professoresEncontrados.remove();
			taProfessor.append(String.format("%-30s %-12s %-40s %-10s%n",professor.getNome(),professor.getCpf(),professor.getAreaConhecimento(),professor.getPontos()));
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
		ListagemDeProfessores = alimentarLista("Professores.csv", ListagemDeProfessores);

		professorEncontrado = ListagemDeProfessores.get(posicao);

		if (!professor.getAreaConhecimento().equals("")) {
			professorEncontrado.setAreaConhecimento(professor.getAreaConhecimento());
		}
		if (!professor.getCpf().isBlank()) {
			professorEncontrado.setCpf(professor.getCpf());
		}
		if (!professor.getNome().isBlank()) {
			professorEncontrado.setNome(professor.getNome());
		}
		if (!professor.getPontos().isBlank()) {
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
		JOptionPane.showMessageDialog(null, "Professor alterado!", "SUCESSO", JOptionPane.PLAIN_MESSAGE);

	}

}
