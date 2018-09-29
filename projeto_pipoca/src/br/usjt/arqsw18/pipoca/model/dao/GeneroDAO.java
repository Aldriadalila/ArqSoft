package br.usjt.arqsw18.pipoca.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw18.pipoca.model.entity.Genero;

@Repository

public class GeneroDAO {
	@PersistenceContext
	EntityManager manager;

	public void criar(Genero genero) {
		manager.persist(genero);
	}

	public void atualizar(Genero genero) {
		manager.merge(genero);
	}

	public void remover(Genero genero) {
		manager.remove(genero);
	}

	public Genero buscarGenero(int id) {
		return manager.find(Genero.class, id);
	}

	@SuppressWarnings("unchecked")

	public List<Genero> listarGeneros() {
		return manager.createQuery("select g from Genero g").getResultList();
	}
}
