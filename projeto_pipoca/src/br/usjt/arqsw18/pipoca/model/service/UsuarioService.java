package br.usjt.arqsw18.pipoca.model.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw18.pipoca.model.dao.UsuarioDAO;
import br.usjt.arqsw18.pipoca.model.entity.Usuario;

@Service
public class UsuarioService {
	private UsuarioDAO dao;
	
	@Autowired
	public UsuarioService(UsuarioDAO dao) {
		this.dao = dao;
	}
	
	public boolean existe(Usuario usuario) throws IOException{
		Usuario u = dao.buscarUsuario(usuario);
		System.out.println(usuario.toString());
		System.out.println(u.toString());
		
		if(u == null)
			return false;
		else if(u.getSenha().equals(usuario.getSenha()))
			return true;
		
		return false; 		
	}
	
	public Usuario inserirUsuario(Usuario usuario) throws IOException {
		int id = dao.criar(usuario);
		usuario.setId(id);
		return usuario;
	}

}
