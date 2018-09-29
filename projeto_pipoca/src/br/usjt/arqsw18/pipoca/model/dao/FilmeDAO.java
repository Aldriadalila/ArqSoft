package br.usjt.arqsw18.pipoca.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw18.pipoca.model.entity.Filme;

@Repository
public class FilmeDAO {
	@PersistenceContext
	EntityManager manager;
	
	public int criar(Filme filme) {
		manager.persist(filme);
		return filme.getId();
	}

	public void atualizar(Filme filme) {
		manager.merge(filme);
	}

	public void remover(Filme filme) {
		manager.remove(filme);
	}

	public Filme selecionar(int id) {
		return manager.find(Filme.class, id);
	}

	@SuppressWarnings("unchecked")

	public List<Filme> listarFilmes() {
		return manager.createQuery("select f from Filme f").getResultList();
	}
	
	public List<Filme> listarFilmes(String chave) {
		String jpql = "select f from Filme f where f.titulo like :chave";
		
		Query query = manager.createQuery(jpql);
		manager.setProperty("chave", "%"+chave+"%");
		
		return query.getResultList();
	}
}
