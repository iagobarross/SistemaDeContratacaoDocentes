package model;

public class Inscricoes {
	private String cpfProfessor;
	private String codigoDisciplina;
	private String codigoProcesso;

	public Inscricoes(String cpfProfessor, String codigoDisciplina, String codigoProcesso) {
		this.cpfProfessor = cpfProfessor;
		this.codigoDisciplina = codigoDisciplina;
		this.codigoProcesso = codigoProcesso;
	}

	public Inscricoes() {
		super();
	}

	@Override
	public String toString() {
		return cpfProfessor + ";" + codigoDisciplina + ";" + codigoProcesso;
	}

	public String getCpfProfessor() {
		return cpfProfessor;
	}

	public void setCpfProfessor(String cpfProfessor) {
		this.cpfProfessor = cpfProfessor;
	}

	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getCodigoProcesso() {
		return codigoProcesso;
	}

	public void setCodigoProcesso(String codigoProcesso) {
		this.codigoProcesso = codigoProcesso;
	}

}
