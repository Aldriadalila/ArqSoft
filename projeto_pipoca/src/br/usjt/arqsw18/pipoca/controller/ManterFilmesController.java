package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;
import br.usjt.arqsw18.pipoca.model.service.FilmeService;
import br.usjt.arqsw18.pipoca.model.service.GeneroService;

@Controller
public class ManterFilmesController {
	@Autowired
	private FilmeService fService;
	
	@Autowired
	private GeneroService gService;

	

	@Transactional
	@RequestMapping("index")
	public String iniciar() {
		return "index";
	}

	@Transactional
	@RequestMapping("/novo_filme")
	public String novo(Model model) {
		try {
			List<Genero> generos = gService.listarGeneros();
			model.addAttribute("generos", generos);
			return "CriarFilme";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}

	@Transactional
	@RequestMapping("/criar_filme")
	public String criarFilme(@Valid Filme filme, Model model, BindingResult result) {
		if (result.hasErrors())
			return "CriarFilme";

		try {

			Genero genero = new Genero();
			genero.setId(filme.getGenero().getId());
			genero.setNome(gService.buscarGenero(genero.getId()).getNome());
			filme.setGenero(genero);

			filme = fService.inserirFilme(filme);

			model.addAttribute("filme", filme);

			return "VisualizarFilme";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}

	@Transactional
	@RequestMapping("/reiniciar_lista")
	public String reiniciarLista(HttpSession session) {
		session.setAttribute("lista", null);
		session.setAttribute("generos", null);
		return "ListarFilmes";
	}
	
	@RequestMapping("/visualizar_filme")
	public String visualizarFilme(Model model, HttpServletRequest request) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Filme filme = fService.buscarFilme(id);
		model.addAttribute("filme", filme);
		return "VisualizarFilme";
	}
	
	@Transactional
	@RequestMapping("/listar_filmes")
	public String listarFilmes(HttpSession session, Model model, String chave) {
		try {
			// HttpSession session = ((HttpServletRequest) model).getSession();

			List<Filme> lista;
			if (chave != null && chave.length() > 0) {
				lista = fService.listarFilmes(chave);
			} else {
				lista = fService.listarFilmes();
			}
			session.setAttribute("lista", lista);
			return "ListarFilmes";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
}
