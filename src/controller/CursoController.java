package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CursoController implements ActionListener {
	private JTextField tfCursoCodigo;
	private JTextField tfCursoNome;
	private JComboBox<String> cbCursoAreaConhecimento;
	private JTextArea taCurso;

	public CursoController(JTextField tfCursoCodigo, JTextField tfCursoNome,
			JComboBox<String> cbCursoAreaConhecimento, JTextArea taCurso) {
		this.tfCursoCodigo = tfCursoCodigo;
		this.tfCursoNome = tfCursoNome;
		this.cbCursoAreaConhecimento = cbCursoAreaConhecimento;
		this.taCurso = taCurso;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Cadastrar")) {
			cadastrarCurso();
		}
		if(cmd.equals("Buscar")) {
			buscarCurso();
		}
		if(cmd.equals("Deletar")) {
			deletarCurso();
		}
		if(cmd.equals("Limpar")) {
			limparCurso();
		}
	}

	private void cadastrarCurso() {
		// TODO Auto-generated method stub
		
	}
	
	private void buscarCurso() {
		// TODO Auto-generated method stub
		
	}
	
	private void deletarCurso() {
		// TODO Auto-generated method stub
		
	}
	
	private void limparCurso() {
		// TODO Auto-generated method stub
		
	}
}
	
	

