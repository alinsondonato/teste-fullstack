package br.com.alinson.testeunimed.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Entity
public class Plano {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotEmpty(message = "{nome.obrigatorio}")
    private String nome;
	
	@NotNull(message = "{valor.obrigatorio}")
    private BigDecimal valor;
    
    @JsonIgnore
    @OneToMany(mappedBy = "plano")
    private List<Beneficiario> beneficiarios;

}
