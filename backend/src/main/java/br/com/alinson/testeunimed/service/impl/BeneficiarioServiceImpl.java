package br.com.alinson.testeunimed.service.impl;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.alinson.testeunimed.exception.ArgumentoInvalidoException;
import br.com.alinson.testeunimed.model.Beneficiario;
import br.com.alinson.testeunimed.model.Plano;
import br.com.alinson.testeunimed.repository.BeneficiarioRepository;
import br.com.alinson.testeunimed.repository.PlanoRepository;
import br.com.alinson.testeunimed.service.BeneficiarioService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BeneficiarioServiceImpl implements BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;
    private final PlanoRepository planoRepository;

    public List<Beneficiario> getBeneficiarios(Beneficiario filtro) {
    	if (filtro == null)
    		return beneficiarioRepository.findAll();
    	
    	ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
    	Example<Beneficiario> example = Example.of(filtro, matcher);

    	return beneficiarioRepository.findAll(example);
    }
    
    public Beneficiario criarBeneficiario(Beneficiario beneficiario) {
    	Plano planoExistente = planoRepository.findById(beneficiario.getPlano().getId())
                .orElseThrow(() -> new ArgumentoInvalidoException("Plano não encontrado: " + beneficiario.getPlano().getId()));
    	
    	return beneficiarioRepository.save(beneficiario.withPlano(planoExistente));
	}
    
    public void excluirBeneficiario(Long id) {
    	Beneficiario beneficiarioExistente = beneficiarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiario não encontrado"));
    	
    	beneficiarioRepository.delete(beneficiarioExistente);
	}
    
    public Beneficiario atualizarBeneficiario(Long id, Beneficiario beneficiario) {
        Beneficiario beneficiarioExistente = beneficiarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiario não encontrado"));
        
        Plano planoExistente = planoRepository.findById(beneficiario.getPlano().getId())
                .orElseThrow(() -> new ArgumentoInvalidoException("Plano não encontrado: " + beneficiario.getPlano().getId()));
        
        beneficiario.setId(beneficiarioExistente.getId());
        beneficiario.setPlano(beneficiarioExistente.getPlano());

        return beneficiarioRepository.save(beneficiario.withPlano(planoExistente));
    }

	public Beneficiario obterBeneficiarioPorId(Long id) {
        return beneficiarioRepository.findById(id)
    		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiario não encontrado"));
    }
}