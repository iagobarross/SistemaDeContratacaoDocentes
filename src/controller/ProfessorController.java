package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProfessorController implements ActionListener{
	private JTextField tfProfessorCPF;
	private JTextField tfProfessorNome;
	private JComboBox<String> cbProfessorAreaConhecimento;
	private JTextField tfProfessorPontos;
	private JTextArea taProfessor;

	public ProfessorController(JTextField tfProfessorCPF, JTextField tfProfessorNome,
			JComboBox<String> cbProfessorAreaConhecimento, JTextField tfProfessorPontos, JTextArea taProfessor) {
		this.tfProfessorCPF = tfProfessorCPF;
		this.tfProfessorNome = tfProfessorNome;
		this.cbProfessorAreaConhecimento = cbProfessorAreaConhecimento;
		this.tfProfessorPontos = tfProfessorPontos;
		this.taProfessor = taProfessor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Cadastrar")) {
			cadastrarProfessor();
		}
		if(cmd.equals("Buscar")) {
			buscarProfessor();
		}
		if(cmd.equals("Deletar")) {
			deletarProfessor();
		}
		if(cmd.equals("Limpar")) {
			limparProfessor();
		}
	}

	private void cadastrarProfessor() {
		
	}
	
	private void buscarProfessor() {
		
	}
	
	private void deletarProfessor() {
		
	}
	
	private void limparProfessor() {
		
	}
	
}
