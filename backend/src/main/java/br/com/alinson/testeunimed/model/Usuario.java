package br.com.alinson.testeunimed.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotEmpty(message = "{login.obrigatorio}")
	private String login;
	
	@NotEmpty(message = "{nome.obrigatorio}")
    private String nome;
	
	@NotEmpty(message = "{senha.obrigatorio}")
    private String senha;
	
	private String roles;
}
