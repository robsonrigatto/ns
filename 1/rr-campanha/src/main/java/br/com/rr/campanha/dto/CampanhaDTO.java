package br.com.rr.campanha.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CampanhaDTO {
	
	private Long id;
	private String nome;
	private Long timeId;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date inicioVigencia;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date terminoVigencia;
	
	public CampanhaDTO() {
		//
	}
	
	public CampanhaDTO(Long id, String nome, Long timeId, Date inicioVigencia, Date terminoVigencia) {
		this.id = id;
		this.nome = nome;
		this.inicioVigencia = inicioVigencia;
		this.terminoVigencia = terminoVigencia;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public Long getTimeId() {
		return timeId;
	}
	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	public Date getTerminoVigencia() {
		return terminoVigencia;
	}
	public void setTerminoVigencia(Date terminoVigencia) {
		this.terminoVigencia = terminoVigencia;
	}
}
