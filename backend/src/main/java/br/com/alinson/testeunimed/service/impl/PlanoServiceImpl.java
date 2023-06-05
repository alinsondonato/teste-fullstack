package br.com.alinson.testeunimed.service.impl;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.alinson.testeunimed.exception.ArgumentoInvalidoException;
import br.com.alinson.testeunimed.model.Plano;
import br.com.alinson.testeunimed.repository.PlanoRepository;
import br.com.alinson.testeunimed.service.PlanoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanoServiceImpl implements PlanoService {
	
	private final PlanoRepository planoRepository;
	
	public List<Plano> getPlanos(Plano filtro) {
    	if (filtro == null)
    		return planoRepository.findAll();
    	
    	ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
    	Example<Plano> example = Example.of(filtro, matcher);

    	return planoRepository.findAll(example);
    }
    
    public Plano criarPlano(Plano plano) {
    	return planoRepository.save(plano);
	}
    
    public void excluirPlano(Long id) {
    	Plano planoExistente = planoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado"));
    	
    	planoRepository.delete(planoExistente);
	}
    
    public Plano atualizarPlano(Long id, Plano plano) {
        Plano planoExistente = planoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado"));
        
        plano.setId(planoExistente.getId());

        return planoRepository.save(plano);
    }

	public Plano obterPlanoPorId(Long id) {
        return planoRepository.findById(id)
    		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado"));
    }
	
}
