package br.com.alinson.testeunimed.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alinson.testeunimed.model.Plano;
import br.com.alinson.testeunimed.service.PlanoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/planos")
@RequiredArgsConstructor
public class PlanoController {
	
	private final PlanoService planoService;
	
	@GetMapping
	public List<Plano> getPlanos(Plano filtro) {
		return planoService.getPlanos(filtro);
	}
    
    @GetMapping("/{id}")
    public Plano obterPlanoPorId(@PathVariable Long id) {
        return planoService.obterPlanoPorId(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Plano criarPlano(@RequestBody @Valid Plano plano) {
        return planoService.criarPlano(plano);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPlano(@PathVariable Long id) {
        planoService.excluirPlano(id);
    }

    @PutMapping("/{id}")
    public Plano atualizarPlano(@PathVariable Long id, @RequestBody @Valid Plano plano) {
        return planoService.atualizarPlano(id, plano);
    }

}
