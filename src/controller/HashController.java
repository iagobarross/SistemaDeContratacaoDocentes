package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import br.edu.fateczl.Lista;
import br.edu.fateczl.Fila.Fila;
import model.Curso;
import model.Disciplina;

public class HashController {
	
	
	public HashController() {
		super();
	}
//	public HashController(Lista<Disciplina>[]tabela,Fila<Curso>cursosEncontrados,Fila<Disciplina>disciplinaEncontrada) {
//		this.tabela=tabela;
//		this.cursosEncontrados=cursosEncontrados;
//		this.disciplinaEncontrada=disciplinaEncontrada;
//	}
	
		
//		tabela = new Lista[cursosEncontrados.size()];
//		inicializarLista();
//		while(!disciplinaEncontrada.isEmpty()) {
//			try {
//				Disciplina discAux=new Disciplina();
//				discAux=disciplinaEncontrada.remove();
//				addDisciplina(discAux);
//			} catch (Exception e) {
//				System.err.println(e.getMessage());
//			}
//			
//		}
//		}
//	
//	private void allCourses() throws Exception {
//		cursosEncontrados = new Fila<>();
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
//	}
//	private void allDisciplines() throws Exception {
//		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
//		File arq = new File(path, "Disciplinas.csv");
//		if (arq.exists() && arq.isFile()) {
//			FileInputStream fis = new FileInputStream(arq);
//			InputStreamReader isr = new InputStreamReader(fis);
//			BufferedReader buffer = new BufferedReader(isr);
//			String linha = buffer.readLine();
//			while (linha != null) {
//				String[] vetLinha = linha.split(";");
//				Disciplina disciplina = new Disciplina();
//				disciplina.setCodigoDisciplina(vetLinha[0]);
//				disciplina.setNomeDisciplina(vetLinha[1]);
//				disciplina.setDiaSemana(vetLinha[2]);
//				disciplina.setHoraInicial(vetLinha[3]);
//				disciplina.setHorasDiarias(vetLinha[4]);
//				disciplina.setCodigoCurso(vetLinha[5]);
//				disciplinaEncontrada.insert(disciplina);
//				linha = buffer.readLine();
//			}
//			buffer.close();
//			isr.close();
//			fis.close();
//		}
//	}
//	
	
//	private void addDisciplina(Disciplina disciplina) {
//		
//		int posicao = disciplina.hashCode();
//		try {
//			tabela[posicao].addLast(disciplina);
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//	}
	
//	public Disciplina findDisciplina(Disciplina disciplina) throws Exception {
//		int posicao = disciplina.hashCode() - 1;
//		int tamanho = tabela[posicao].size();
//		
//		for(int i = 0; i < tamanho; i++) {
//			Disciplina d = (Disciplina) tabela[posicao].get(i);
//			if(d.getNomeDisciplina().equals(disciplina.getNomeDisciplina())){
//				return d;
//			}
//		}
//		
//		throw new Exception ("Processos não encontrados");
//	}
}