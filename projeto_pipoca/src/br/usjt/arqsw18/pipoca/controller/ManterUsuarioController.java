package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw18.pipoca.model.entity.Usuario;
import br.usjt.arqsw18.pipoca.model.service.UsuarioService;

@Controller
public class ManterUsuarioController {
    
	private UsuarioService uService;
	
	@Autowired
	public ManterUsuarioController (UsuarioService uService) {
		this.uService = uService;
	}
	
	@Transactional
	@RequestMapping ("/cadastro")
	public String novoUsuario() {
		return "CRIAR USUARIO";
	}
	
	@Transactional
	@RequestMapping ("/fazerLogin")
	public String fazerLogin (Usuario usuario, HttpServletRequest request) throws IOException {
		if(uService.existe(usuario)) {	
			request.getSession().setAttribute("USUARIO LOGADO", usuario);
			return "index";
		}		
		
		return "LOGIN";
	}
	
	@Transactional
	@RequestMapping ("/LOGOUT")
	public String fazerLogin (HttpServletRequest request) throws IOException {
		request.getSession().invalidate();
		return "LOGIN";
	}
	
	@Transactional
	@RequestMapping ("/LOGIN")
	public String Login () {
		return "Login";
	}
	
	@Transactional
	@RequestMapping ("/CRIAR_USUARIO")
	public String criarUsuario (Usuario usuario, HttpServletRequest request) throws IOException {
		uService.inserirUsuario(usuario);
		return this.fazerLogin(usuario, request);
	}
}
