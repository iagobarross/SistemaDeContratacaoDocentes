package model;

public class Professor {
	private String cpf;
	private String nome;
	private String areaConhecimento;
	private String pontos;

	public Professor(String cpf, String nome, String areaConhecimento, String pontos) {
		this.cpf = cpf;
		this.nome = nome;
		this.areaConhecimento = areaConhecimento;
		this.pontos = pontos;
	}

	public Professor() {
		super();
	}

	@Override
	public String toString() {
		return cpf + ";" + nome + ";" + areaConhecimento + ";" + pontos;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAreaConhecimento() {
		return areaConhecimento;
	}

	public void setAreaConhecimento(String areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	public String getPontos() {
		return pontos;
	}

	public void setPontos(String pontos) {
		this.pontos = pontos;
	}

}