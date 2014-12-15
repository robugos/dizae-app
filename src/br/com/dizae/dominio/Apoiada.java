package br.com.dizae.dominio;



public class Apoiada {
	
	private String id;
	private String nome;
	private String quantAndar;
	private String observacao;
	private boolean selected;
	
	public Apoiada(String id, String nome, String quantAndar, boolean selected) {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getQuantAndar() {
		return quantAndar;
	}
	public void setQuantAndar(String quantAndar) {
		this.quantAndar = quantAndar;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
