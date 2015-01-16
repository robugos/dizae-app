package br.com.dizae.dominio;

public class Ocorrencia {

	private String id_ocorrencia;
	private String titulo_ocorrencia;
	private String descricao_ocorrencia;
	private String genero_ocorrencia;
	private int qtdApoio_ocorrencia;
	private String endereco_ocorrencia;
	private String cooLatitude_ocorrencia;
	private String cooLongitude_ocorrencia;
	private String data_ocorrencia;
	private String atualizacao_ocorrencia;
	private String id_usuario;
	private boolean selected = false;

	public Ocorrencia(String id_ocorrencia, String titulo_ocorrencia, String descricao_ocorrencia, boolean selected) {
		super();
		this.id_ocorrencia = id_ocorrencia;
		this.titulo_ocorrencia = titulo_ocorrencia;
		this.descricao_ocorrencia = descricao_ocorrencia;
		this.selected = selected;
	}

	
	public String getId_ocorrencia() {
		return id_ocorrencia;
	}


	public void setId_ocorrencia(String id_ocorrencia) {
		this.id_ocorrencia = id_ocorrencia;
	}


	public String getTitulo_ocorrencia() {
		return titulo_ocorrencia;
	}


	public void setTitulo_ocorrencia(String titulo_ocorrencia) {
		this.titulo_ocorrencia = titulo_ocorrencia;
	}


	public String getDescricao_ocorrencia() {
		return descricao_ocorrencia;
	}


	public void setDescricao_ocorrencia(String descricao_ocorrencia) {
		this.descricao_ocorrencia = descricao_ocorrencia;
	}


	public String getGenero_ocorrencia() {
		return genero_ocorrencia;
	}


	public void setGenero_ocorrencia(String genero_ocorrencia) {
		this.genero_ocorrencia = genero_ocorrencia;
	}


	public int getQtdApoio_ocorrencia() {
		return qtdApoio_ocorrencia;
	}


	public void setQtdApoio_ocorrencia(int qtdApoio_ocorrencia) {
		this.qtdApoio_ocorrencia = qtdApoio_ocorrencia;
	}


	public String getEndereco_ocorrencia() {
		return endereco_ocorrencia;
	}


	public void setEndereco_ocorrencia(String endereco_ocorrencia) {
		this.endereco_ocorrencia = endereco_ocorrencia;
	}


	public String getCooLatitude_ocorrencia() {
		return cooLatitude_ocorrencia;
	}


	public void setCooLatitude_ocorrencia(String cooLatitude_ocorrencia) {
		this.cooLatitude_ocorrencia = cooLatitude_ocorrencia;
	}


	public String getCooLongitude_ocorrencia() {
		return cooLongitude_ocorrencia;
	}


	public void setCooLongitude_ocorrencia(String cooLongitude_ocorrencia) {
		this.cooLongitude_ocorrencia = cooLongitude_ocorrencia;
	}


	public String getData_ocorrencia() {
		return data_ocorrencia;
	}


	public void setData_ocorrencia(String data_ocorrencia) {
		this.data_ocorrencia = data_ocorrencia;
	}


	public String getAtualizacao_ocorrencia() {
		return atualizacao_ocorrencia;
	}


	public void setAtualizacao_ocorrencia(String atualizacao_ocorrencia) {
		this.atualizacao_ocorrencia = atualizacao_ocorrencia;
	}


	public String getId_usuario() {
		return id_usuario;
	}


	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}


	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
