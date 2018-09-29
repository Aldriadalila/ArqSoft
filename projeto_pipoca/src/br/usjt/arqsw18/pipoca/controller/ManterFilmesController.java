package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;
import br.usjt.arqsw18.pipoca.model.service.FilmeService;
import br.usjt.arqsw18.pipoca.model.service.GeneroService;

@Controller
public class ManterFilmesController {
	private FilmeService fService;
	private GeneroService gService;

	@Autowired
	public ManterFilmesController(FilmeService fService, GeneroService gService) {
		this.fService = fService;
		//Fazer o mesmo com o GeneroService
		this.gService = gService;
	}

	@RequestMapping("index")
	public String iniciar() {
		return "index";
	}
	
	@RequestMapping("/NOVO_FILME")
	public String novo(Model model) {
		try {
			ArrayList<Genero> generos = gService.listarGeneros();
			model.addAttribute("GENEROS", generos);
			return "CRIARFILME";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("ERRO", e);
			return "ERRO";
		}
	}

	@RequestMapping("/CRIAR_FILME")
	public String criarFilme(Filme filme, Model model) {
		try {
			
			Genero genero = new Genero();
			genero.setId(filme.getGenero().getId());
			genero.setNome(gService.buscarGenero(genero.getId()).getNome());
			filme.setGenero(genero);

			filme = fService.inserirFilme(filme);

			model.addAttribute("FILME", filme);

			return "VISUALIZARFILME";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("ERRO", e);
			return "ERRO";
		}
	}

	@RequestMapping("/reiniciar_lista")
	public String reiniciarLista(HttpSession session) {
		session.setAttribute("lista", null);
		return "LISTARFILMES";
	}

	@RequestMapping("/VISUALIZAR_FILME")
	public String visualizarFilme(HttpServletRequest request) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Filme filme = fService.buscarFilme(id);
		request.setAttribute("FILME", filme);
		return "VISUALIZARFILME";
	}
	
	@RequestMapping("/LISTAR_FILMES")
	public String listarFilmes(HttpSession session, Model model, String chave) {
		try {
			//HttpSession session = ((HttpServletRequest) model).getSession();

			ArrayList<Filme> lista;
			if (chave != null && chave.length() > 0) {
				lista = fService.listarFilmes(chave);
			} else {
				lista = fService.listarFilmes();
			}
			session.setAttribute("LISTA", lista);
			return "LISTAR_FILMES";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("ERRO", e);
			return "ERRO";
		}
	}
}
