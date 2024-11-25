package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista;
import model.Inscricoes;

public class InscritosController implements ActionListener {
	
	private JTextField tfInscritosNomeDisciplina;
	private JTextArea taInscritos;

	public InscritosController(JTextField tfInscritosNomeDisciplina, JTextArea taInscritos) {
		this.tfInscritosNomeDisciplina = tfInscritosNomeDisciplina;
		this.taInscritos = taInscritos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Listar Inscritos")) {
			try {
				listarInscritos();
			} catch (Exception e1) {
				System.err.println(e1.getMessage());
			}
		}
		
		if(cmd.equals("Limpar")) {
			limparInscritos();
		}
	}

	private void listarInscritos() throws Exception {
		Lista<Inscricoes> lista = new Lista<>();
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
				inscricoesAdd.setCpfProfessor(vetLinha[0]);
				inscricoesAdd.setCodigoDisciplina(linha);
				inscricoesAdd.setCodigoProcesso(vetLinha[2]);
				lista.addLast(inscricoesAdd);

				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
			
			//falta printar a saida formatada
		}
	}

	private void limparInscritos() {
		taInscritos.setText("");
	}
	
	
	
	

}