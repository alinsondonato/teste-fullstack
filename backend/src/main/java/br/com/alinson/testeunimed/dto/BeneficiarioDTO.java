package br.com.alinson.testeunimed.dto;

import br.com.alinson.testeunimed.model.Beneficiario;

public class BeneficiarioDTO {
	
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private Byte idade;
	private Long planoId;
	
	public Beneficiario asBeneficiario() {
		Beneficiario beneficiario = new Beneficiario();
		beneficiario.setId(id);
		beneficiario.setNome(nome);
		beneficiario.setCpf(cpf);
		beneficiario.setEmail(email);
		beneficiario.setIdade(idade);
		
		return beneficiario;
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
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Byte getIdade() {
		return idade;
	}
	public void setIdade(Byte idade) {
		this.idade = idade;
	}
	public Long getPlanoId() {
		return planoId;
	}
	public void setPlanoId(Long planoId) {
		this.planoId = planoId;
	}
}
