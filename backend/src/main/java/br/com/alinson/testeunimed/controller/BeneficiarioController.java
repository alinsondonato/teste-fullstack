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

import br.com.alinson.testeunimed.model.Beneficiario;
import br.com.alinson.testeunimed.service.BeneficiarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/beneficiarios")
@RequiredArgsConstructor
public class BeneficiarioController {

    private final BeneficiarioService beneficiarioService;

    @GetMapping
    public List<Beneficiario> encontrarBeneficiarios(Beneficiario filtro) {
        return beneficiarioService.getBeneficiarios(filtro);
    }
    
    @GetMapping("/{id}")
    public Beneficiario obterBeneficiarioPorId(@PathVariable Long id) {
        return beneficiarioService.obterBeneficiarioPorId(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beneficiario criarBeneficiario(@RequestBody @Valid Beneficiario beneficiario) {
        return beneficiarioService.criarBeneficiario(beneficiario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirBeneficiario(@PathVariable Long id) {
        beneficiarioService.excluirBeneficiario(id);
    }

    @PutMapping("/{id}")
    public Beneficiario atualizarBeneficiario(@PathVariable Long id, @RequestBody @Valid Beneficiario beneficiario) {
        return beneficiarioService.atualizarBeneficiario(id, beneficiario);
    }
}
