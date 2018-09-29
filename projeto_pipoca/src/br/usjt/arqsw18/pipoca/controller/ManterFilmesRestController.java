package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.service.FilmeService;
import br.usjt.arqsw18.pipoca.model.service.GeneroService;

@RestController
public class ManterFilmesRestController {
	
	@Autowired
	private FilmeService fService;
	
	@Autowired
	private GeneroService gService;
	
	@RequestMapping(method=RequestMethod.GET, value="rest/filmes")
	public List<Filme> listarFilmes(){		
		try {
			return fService.listarFilmes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="rest/filmes")
	public ResponseEntity<Filme> criarFilme(@RequestBody Filme filme) {
		try {
			System.out.println(filme.toString());
			filme = fService.inserirFilme(filme);
			return new ResponseEntity<Filme>(filme, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Filme>(filme, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="rest/filme")
	public ResponseEntity<Filme> editarFilme(@RequestBody Filme filme){		
		try {			
			filme = fService.atualizarFilme(filme);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Filme>(filme, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}