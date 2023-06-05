package br.com.alinson.testeunimed.service;

import java.util.List;

import br.com.alinson.testeunimed.model.Plano;

public interface PlanoService {
	
	public List<Plano> getPlanos(Plano filtro);
    
    public Plano criarPlano(Plano plano);
    
    public void excluirPlano(Long id);
    
    public Plano atualizarPlano(Long id, Plano plano);

	public Plano obterPlanoPorId(Long id);
}
