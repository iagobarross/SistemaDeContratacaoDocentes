package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InscricoesController implements ActionListener {
	
	private JTextField tfInscricoesNomeDisciplina;
	private JTextArea taInscricoesInscritos;
	
	public InscricoesController(JTextField tfInscricoesNomeDisciplina, JTextArea taInscricoesInscritos) {
		this.tfInscricoesNomeDisciplina = tfInscricoesNomeDisciplina;
		this.taInscricoesInscritos = taInscricoesInscritos;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
	}

}
