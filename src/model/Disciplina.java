package model;

public class Disciplina {
	private String codigoDisciplina;
	private String nomeDisciplina;
	private String diaSemana;
	private String horaInicial;
	private String horasDiarias;
	private String codigoCurso;
	private String codigoProcesso;

	public Disciplina(String codigoDisciplina, String nomeDisciplina, String diaSemana, String horaInicial,
			String horasDiarias, String codigoCurso, String codigoProcesso) {
		this.codigoDisciplina = codigoDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.diaSemana = diaSemana;
		this.horaInicial = horaInicial;
		this.horasDiarias = horasDiarias;
		this.codigoCurso = codigoCurso;
		this.codigoProcesso = codigoProcesso;
	}

	public Disciplina() {
		super();
	}

	@Override
	public String toString() {
		return codigoDisciplina + ";" + nomeDisciplina + ";" + diaSemana + ";" + horaInicial + ";" + horasDiarias + ";"
				+ codigoCurso + ";" + codigoProcesso;
	}
	
	public int hashCode() {
		int posicao = Integer.parseInt(codigoDisciplina)/100;
		return posicao;
	}

	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	public String getHorasDiarias() {
		return horasDiarias;
	}

	public void setHorasDiarias(String horasDiarias) {
		this.horasDiarias = horasDiarias;
	}

	public String getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public String getCodigoProcesso() {
		return codigoProcesso;
	}

	public void setCodigoProcesso(String codigoProcesso) {
		this.codigoProcesso = codigoProcesso;
	}
	
	

}
