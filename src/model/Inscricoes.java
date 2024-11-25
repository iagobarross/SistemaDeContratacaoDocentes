package model;

public class Inscricoes {
	private String cpfProfessor;
	private String codigoDisciplina;
	private String codigoProcesso;
	private String nomeProfessor;
	private String nomeDisciplina;
	private String pontos ;
	private String areaConhecimento; 

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public Inscricoes(String cpfProfessor, String codigoDisciplina, String codigoProcesso,String nomeProfessor,String nomeDisciplina,String areaConhecimento,String pontos) {
		this.cpfProfessor = cpfProfessor;
		this.codigoDisciplina = codigoDisciplina;
		this.codigoProcesso = codigoProcesso;
		this.nomeProfessor=nomeProfessor;
		this.nomeDisciplina=nomeDisciplina;
		this.pontos=pontos;
		this.areaConhecimento=areaConhecimento;
	}

	public Inscricoes() {
		super();
	}

	public String getPontos() {
		return pontos;
	}

	public void setPontos(String pontos) {
		this.pontos = pontos;
	}

	public String getAreaConhecimento() {
		return areaConhecimento;
	}

	public void setAreaConhecimento(String areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	@Override
	public String toString() {
		return nomeProfessor+";"+cpfProfessor + ";"+pontos+";"+areaConhecimento+";"+nomeDisciplina +";"+ codigoDisciplina + ";" + codigoProcesso;
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

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

}
