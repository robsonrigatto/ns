package br.com.rr.sociotorcedor.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteDTO {
	
	private Long id;
	private String email;
	private String nomeCompleto;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dataNascimento;
	
	private Long timeId;
	
	public ClienteDTO() {
		//
	}

	public ClienteDTO(Long id, String email, String nomeCompleto, Date dataNascimento, Long timeId) {
		this.id = id;
		this.email = email;
		this.nomeCompleto = nomeCompleto;
		this.dataNascimento = dataNascimento;
		this.timeId = timeId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Long getTimeId() {
		return timeId;
	}
	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}
}
