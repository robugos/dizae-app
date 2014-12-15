package br.com.dizae.dominio;

public class Minha {

	private String idCurso;
	private String nome;
	private String departamento;
	private String turno;
	private String nomeCoordenador;
	private int quantPeriodos;
	private boolean selected = false;

	public Minha(String idCurso, String nome, String turno, boolean selected) {
		super();
		this.idCurso = idCurso;
		this.nome = nome;
		this.turno = turno;
		this.selected = selected;
	}

	public String getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}
	
	public int getIntIdCurso() {
		return Integer.parseInt(idCurso);
	}
	
	public void setIntIdCurso(int id) {
		this.idCurso = String.valueOf(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public int getQuantPeriodos() {
		return quantPeriodos;
	}

	public void setQuantPeriodos(int quantPeriodos) {
		this.quantPeriodos = quantPeriodos;
	}

	public String getNomeCoordenador() {
		return nomeCoordenador;
	}

	public void setNomeCoordenador(String nomeCoordenador) {
		this.nomeCoordenador = nomeCoordenador;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
