package controller;

import br.edu.fateczl.Lista;
import model.Disciplina;

public class HashController {
	private Lista<Disciplina>[] tabela;
	private static final int TAMANHO_TABELA = 10;

	public HashController() {
		tabela = new Lista[10];
		inicializarLista();
		}

	private void inicializarLista() {
		int tamanho = tabela.length;
		for(int i = 0; i < tamanho; i++) {
			tabela[i] = new Lista<>();
		}
	}
	
	public void addDisciplina(Disciplina disciplina) {
		int posicao = disciplina.hashCode() - 1;
		try {
			tabela[posicao].addLast(disciplina);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Disciplina findDisciplina(Disciplina disciplina) throws Exception {
		int posicao = disciplina.hashCode() - 1;
		int tamanho = tabela[posicao].size();
		
		for(int i = 0; i < tamanho; i++) {
			Disciplina d = (Disciplina) tabela[posicao].get(i);
			if(d.getNomeDisciplina().equals(disciplina.getNomeDisciplina())){
				return d;
			}
		}
		
		throw new Exception ("Processos não encontrados");
	}
}