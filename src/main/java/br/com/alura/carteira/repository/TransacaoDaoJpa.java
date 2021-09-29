package br.com.alura.carteira.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.alura.carteira.modelo.Transacao;

@Repository
public class TransacaoDaoJpa {

	@Autowired
	private EntityManager em;
	
	public void salvar(Transacao transacao) {
		em.persist(transacao);
	}
	
	public List<Transacao> listar(){
		 return em.createQuery("select t from Transacoes t", Transacao.class).getResultList();
	}
}
