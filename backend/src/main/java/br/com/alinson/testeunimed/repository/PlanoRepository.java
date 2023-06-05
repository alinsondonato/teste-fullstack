package br.com.alinson.testeunimed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alinson.testeunimed.model.Plano;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long>{

}
