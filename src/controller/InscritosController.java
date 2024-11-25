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
import br.edu.fateczl.Fila.Fila;
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
				limparInscritos();
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
		Inscricoes inscricao = new Inscricoes();
		inscricao.setNomeDisciplina(tfInscritosNomeDisciplina.getText());
		
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
				if(vetLinha[4].equals(inscricao.getNomeDisciplina())){
					Inscricoes inscricoesAdd = new Inscricoes();
					inscricoesAdd.setNomeProfessor(vetLinha[0]);
					inscricoesAdd.setCpfProfessor(vetLinha[1]);
					inscricoesAdd.setPontos(vetLinha[2]);
					inscricoesAdd.setAreaConhecimento(vetLinha[3]);
					inscricoesAdd.setNomeDisciplina(vetLinha[4]);
					inscricoesAdd.setCodigoDisciplina(vetLinha[5]);
					inscricoesAdd.setCodigoProcesso(vetLinha[6]);
					lista.addLast(inscricoesAdd);					
				}

				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
			
			taInscritos.append(String.format("%-28s %-14s %-30s %-14s %-14s%n","Nome","CPF","Área de Conhecimento","Cód. Processo","Pontuação"));	
			
			
			int tamanho = lista.size();
			for(int i = 0 ; i < tamanho-1 ; i ++) {
				for(int j=i+1;j<tamanho;j++) {				
					if(Integer.parseInt(lista.get(i).getPontos())>Integer.parseInt(lista.get(j).getPontos())) {
						Inscricoes inscricaoaux=lista.get(i);
						lista.add(lista.get(j), i);
						lista.add(inscricaoaux, j);
					}
				}
			}
			for(int i=0;i<tamanho;i++) {
				Inscricoes insc=new Inscricoes();
				insc=lista.get(i);
				taInscritos.append(String.format("%-28s %-14s %-30s %-14s %-14s%n",insc.getNomeProfessor(),insc.getCpfProfessor(),insc.getAreaConhecimento(),insc.getCodigoProcesso(),insc.getPontos()));	
			}
		}	
	}

	private void limparInscritos() {
		taInscritos.setText("");
	}
	
	
	
	

}