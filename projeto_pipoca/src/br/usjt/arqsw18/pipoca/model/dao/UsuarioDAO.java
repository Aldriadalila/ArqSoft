package br.usjt.arqsw18.pipoca.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw18.pipoca.model.entity.Genero;
import br.usjt.arqsw18.pipoca.model.entity.Usuario;

@Repository
public class UsuarioDAO {
	@PersistenceContext
	EntityManager manager;

	public int criar(Usuario usuario) {
		manager.persist(usuario);
		return usuario.getId();
	}

	public void atualizar(Usuario usuario) {
		manager.merge(usuario);
	}

	public void remover(Usuario usuario) {
		manager.remove(usuario);
	}

	public Usuario buscarUsuario(Usuario usuario) {
		String jpql = "select u from Usuario u where u.login = :login";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("login", usuario.getLogin());
		Usuario usuarioEncontrado = (Usuario) query.getSingleResult();
		
		
		if(usuarioEncontrado == null)
			return null;
		else 
			return usuarioEncontrado;			
	}

	@SuppressWarnings("unchecked")

	public List<Genero> listarUsuario() {
		return manager.createQuery("select g from Usuario g").getResultList();
	}
}