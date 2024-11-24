package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista;
import br.edu.fateczl.Fila.Fila;
import model.Curso;
import model.Inscricoes;

public class InscricoesController implements ActionListener {
	
	private JTextField tfInscricoesNomeDisciplina;
	private JTextArea taInscricoesInscritos;
	private JLabel lblCursoModoAlteracao;
	private JButton btnCursoSalvarAlteracao;
	private int posicao = -1;
	Lista<Inscricoes> listaCursos = new Lista<Inscricoes>();
	MetodosPrincipaisController metodosPrincipais = new MetodosPrincipaisController();
	
	public InscricoesController(JTextField tfInscricoesNomeDisciplina, JTextArea taInscricoesInscritos,JLabel lblCursoModoAlterecao,JButton btnCursoSalvaAlterecao) {
		this.tfInscricoesNomeDisciplina = tfInscricoesNomeDisciplina;
		this.taInscricoesInscritos = taInscricoesInscritos;
		this.btnCursoSalvarAlteracao=btnCursoSalvaAlterecao;
		this.lblCursoModoAlteracao=lblCursoModoAlterecao;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		// Cadastra o curso na lista de cursos e no Cursos.csv
				if (cmd.equals("Cadastrar")) {
					try {
						limparTAInscricoes();
						cadastrarInscricoes();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				// Busca o curso com base nos dados passados para a busca
				if (cmd.equals("Buscar")) {
					try {
						limparTAInscricoes();
						buscarInscricao();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if (cmd.equals("Deletar")) {
					try {
						limparTAInscricoes();
						deletarIncricoes();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if (cmd.equals("Limpar")) {
					limparTAInscricoes();
				}
				if (cmd.equals("Listar")) {
					try {
						limparTAInscricoes();
						allInscricoes();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if (cmd.equals("Atualizar")) {
					try {
						limparTAInscricoes();
						posicao = atualizarInscricao();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if (cmd.equals("Salvar Alterações")) {
					try {
						limparTAInscricoes();
						salvarAlteracoes(posicao);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
	}


	private void salvarAlteracoes(int posicao) {
		Lista<Inscricoes> ListagemDeInscricoes = new Lista<>();
		Inscricoes Inscricao = new Inscricoes();
		Inscricoes InscricaoEncontrado = new Inscricoes();

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

		
	}


	private int atualizarInscricao() {
		Inscricoes inscricao = new Inscricoes();
		Inscricoes ItemListaDeInscricao = new Inscricoes();
		Lista<Inscricoes> ListagemDeCursos = new Lista<Inscricoes>();
		Fila<Inscricoes> cursosEncontrados = new Fila<>();

//		curso.setCodigoCurso(tfCursoCodigo.getText());
//		curso.setNomeCurso(tfCursoNome.getText());
//		curso.setAreaConhecimento((String) cbCursoAreaConhecimento.getSelectedItem());
//
//		if (!curso.getCodigoCurso().isBlank()) {
//			searchCodigo(curso, cursosEncontrados);
//		} else if (!curso.getNomeCurso().isBlank()) {
//			searchNome(curso, cursosEncontrados);
//		} else if (!curso.getAreaConhecimento().isEmpty()) {
//			searchArea(curso, cursosEncontrados);
//		} else {
//			JOptionPane.showMessageDialog(null, "Digite em um campo para pesquisar", "ERRO", JOptionPane.ERROR_MESSAGE);
//			curso = null;
//			return -1;
//		}
//
//		if (cursosEncontrados.size() > 1) {
//			JOptionPane.showMessageDialog(null, "A alteração deve ser feita apenas um curso por vez", "ERRO",
//					JOptionPane.ERROR_MESSAGE);
//		} else if (cursosEncontrados.size() == 0) {
//			JOptionPane.showMessageDialog(null, "Curso não encontrado", "ERRO", JOptionPane.INFORMATION_MESSAGE);
//		} else {
//			curso = cursosEncontrados.remove();
//			ListagemDeCursos = metodosPrincipais.alimentarLista("Cursos.csv", ListagemDeCursos);
//			int tamanho = ListagemDeCursos.size();
//			for (int i = 0; i < tamanho; i++) {
//				ItemListaDeCursos = ListagemDeCursos.get(i);
//				if (!curso.getNomeCurso().isBlank() && ItemListaDeCursos.getNomeCurso().contains(curso.getNomeCurso())
//						&& !curso.getCodigoCurso().isBlank()
//						&& ItemListaDeCursos.getCodigoCurso().contains(curso.getCodigoCurso())
//						&& !curso.getAreaConhecimento().isBlank()
//						&& ItemListaDeCursos.getAreaConhecimento().contains(curso.getAreaConhecimento())) {
//					lblCursoModoAlteracao.setVisible(true);
//					btnCursoSalvarAlteracao.setVisible(true);
//					btnCursoSalvarAlteracao.setEnabled(true);
//					taCurso.setText(
//							"Curso encontrado! Digite nos campos acima quais valores deseja alterar.\nOs campos que ficarem em branco não serão substituídos.");
//					return i;
//				}
//			}
//		}
		return -1;
	}


	private void allInscricoes() {
//		Fila<Inscricoes> InscricoesEncontradas = new Fila<>();
//		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
//		File arq = new File(path, "Cursos.csv");
//		if (arq.exists() && arq.isFile()) {
//			FileInputStream fis = new FileInputStream(arq);
//			InputStreamReader isr = new InputStreamReader(fis);
//			BufferedReader buffer = new BufferedReader(isr);
//			String linha = buffer.readLine();
//			while (linha != null) {
//				String[] vetLinha = linha.split(";");
//				Inscricoes inscricaoadd = new Inscricoes();
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
//		
	}


	private void deletarIncricoes() {
		// Capturo o dado para ser removido
//				Incricoes inscricao = new Inscricoes();
//				curso.setCodigoCurso(tfCursoCodigo.getText());
//				curso.setNomeCurso(tfCursoNome.getText());
//				curso.setAreaConhecimento((String) cbCursoAreaConhecimento.getSelectedItem());
//				Lista<Inscricoes> listagemDeCursos = new Lista<Inscricoes>();
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


	private void buscarInscricao() {
		Inscricoes incricao = new Inscricoes();
		Fila<Inscricoes> cursosEncontrados = new Fila<>();
		System.out.println("Em construcao");
//		curso.setCodigoCurso(tfCursoCodigo.getText());
//		curso.setNomeCurso(tfCursoNome.getText());
//		curso.setAreaConhecimento((String) cbCursoAreaConhecimento.getSelectedItem());
//
//		if (!curso.getCodigoCurso().isBlank()) {
//			cursosEncontrados = searchCodigo(curso, cursosEncontrados);
//		} else if (!curso.getNomeCurso().isBlank()) {
//			cursosEncontrados = searchNome(curso, cursosEncontrados);
//		} else if (!curso.getAreaConhecimento().isEmpty()) {
//			cursosEncontrados = searchArea(curso, cursosEncontrados);
//		} else {
//			JOptionPane.showMessageDialog(null, "Digite em um campo para pesquisar", "ERRO", JOptionPane.ERROR_MESSAGE);
//			curso = null;
//		}
//
//		if (curso != null && cursosEncontrados.size() > 0) {
//			taCurso.append("Cód. Curso\tNome\t\t\tÁrea de conhecimento\r\n");
//			while (!cursosEncontrados.isEmpty()) {
//				curso = cursosEncontrados.remove();
//				taCurso.append(curso.getCodigoCurso() + "\t" + curso.getNomeCurso() + "\t"
//						+ curso.getAreaConhecimento() + "\r\n");
//			}
//		} else {
//			JOptionPane.showMessageDialog(null, "Curso não encontrado", "ERRO", JOptionPane.INFORMATION_MESSAGE);
//
//		}
//
//		
	}


	private void cadastrarInscricoes()throws Exception {
		Inscricoes inscricao = new Inscricoes();
		System.out.println("Em construcao");
//		inscricao.setCodigoDisciplina();
//		inscricao.setNomeCurso(tfCursoNome.getText());
//		curso.setAreaConhecimento((String) cbCursoAreaConhecimento.getSelectedItem());
//
//		if (!curso.getAreaConhecimento().equals(null) && !curso.getCodigoCurso().equals("")
//				&& !curso.getNomeCurso().equals("")) {
//			// adicionar o curso na lista de cursos
//			listaCursos.addLast(curso);
//
//			// inserir o curso no Curso.csv
//			metodosPrincipais.inserirNoArquivo(curso.toString(), "Cursos.csv");
//
//			// limpar os campos
//			limparCamposCurso();
//			taCurso.setText("Curso adicionado!.");
//
//		} else {
//			JOptionPane.showMessageDialog(null,
//					"Todas as informações devem ser preenchidas para cadastrar um novo curso", "ERRO",
//					JOptionPane.ERROR_MESSAGE);
//		}
//		
	}


	private void limparTAInscricoes() {
	
		taInscricoesInscritos.setText("");
		
	}

}
