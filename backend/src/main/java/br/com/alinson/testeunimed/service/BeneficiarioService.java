package br.com.alinson.testeunimed.service;

import java.util.List;

import br.com.alinson.testeunimed.model.Beneficiario;

public interface BeneficiarioService {

    public List<Beneficiario> getBeneficiarios(Beneficiario filtro);
    
    public Beneficiario criarBeneficiario(Beneficiario beneficiario) ;
    
    public void excluirBeneficiario(Long id);
    
    public Beneficiario atualizarBeneficiario(Long id, Beneficiario beneficiario);

	public Beneficiario obterBeneficiarioPorId(Long id);
}