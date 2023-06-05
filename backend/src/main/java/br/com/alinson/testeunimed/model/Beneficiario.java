package br.com.alinson.testeunimed.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import br.com.alinson.testeunimed.service.validation.NotEmptyObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Entity
public class Beneficiario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "{nome.obrigatorio}")
	private String nome;
	
	@NotEmpty(message = "{cpf.obrigatorio}")
	@CPF(message = "CPF Inválido")
	private String cpf;
	
	@NotEmpty(message = "{email.obrigatorio}")
	@Email(message = "{email.invalido}")
	private String email;
	
	private Byte idade;

	@NotEmptyObject
	@ManyToOne
	@JoinColumn(name= "plano_id")
	private Plano plano;
}
