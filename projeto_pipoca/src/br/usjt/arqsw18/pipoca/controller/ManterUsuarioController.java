package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw18.pipoca.model.entity.Usuario;
import br.usjt.arqsw18.pipoca.model.service.UsuarioService;

@Controller
public class ManterUsuarioController {

	@Autowired
	private UsuarioService uService;

	@Transactional
	@RequestMapping("/cadastro")
	public String novoUsuario() {
		return "CriarUsuario";
	}

	@Transactional
	@RequestMapping("/fazer_login")
	public String fazerLogin(Model model, Usuario usuario, HttpServletRequest request) throws IOException {
		System.out.println(usuario.toString());
		if (uService.existe(usuario)) {
			System.out.println(usuario.toString());
			request.getSession().setAttribute("usuario_logado", usuario);
			return "index";
		}

		return "Login";
	}

	@Transactional
	@RequestMapping("/logout")
	public String fazerLogin(HttpServletRequest request) throws IOException {
		request.getSession().invalidate();
		return "Login";
	}

	@Transactional
	@RequestMapping("/login")
	public String Login() {
		return "Login";
	}

	@Transactional
	@RequestMapping("/criar_usuario")
	public String criarUsuario(@Valid Usuario usuario, HttpServletRequest request) throws IOException {
		System.out.println(usuario.toString());
		uService.inserirUsuario(usuario);

		return this.fazerLogin(null, usuario, request);
	}
}
