package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import br.edu.fateczl.Lista;
import model.Curso;

public class MetodosPrincipaisController {

	public void inserirNoArquivo(String csvModel, String arquivoNome) throws IOException {
		//insere o model no Model.csv
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File dir = new File(path);

		if (!dir.exists()) {
			dir.mkdir();
		}

		File arq = new File(path, arquivoNome);
		boolean exists = false;

		if (arq.exists()) {
			exists = true;
		}

		FileWriter fw = new FileWriter(arq, exists);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvModel + "\r\n");
		pw.flush();
		pw.close();
		fw.close();
	}
	
	public void limparArquivo(String arquivoNome) throws IOException {
		//limpa o Model.csv
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, arquivoNome);
		
		if (arq.exists() && arq.isFile()) {
            try (FileWriter fw = new FileWriter(arq, false)) {
                fw.write("");
            } catch (IOException e) {
                System.err.println("Erro ao limpar o arquivo: " + e.getMessage());
            }
		}
	}

	public <T> Lista<T> alimentarLista(String arquivoNome, Lista<T> listaDeItens) throws Exception{
		String path = System.getProperty("user.home") + File.separator + "Sistema de Contratação de Docentes";
		File arq = new File(path, arquivoNome);
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
				listaDeItens.addLast((T) cursoadd);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		limparArquivo(arquivoNome);
		return listaDeItens;
	}
}