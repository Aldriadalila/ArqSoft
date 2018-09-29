package br.usjt.arqsw18.pipoca.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw18.pipoca.model.dao.FilmeDAO;
import br.usjt.arqsw18.pipoca.model.entity.Filme;

@Service
public class FilmeService {
	@Autowired
	private FilmeDAO dao;
		
	public FilmeService() {
	}
	
	public Filme buscarFilme(int id) throws IOException{
		return dao.selecionar(id);
	}

	public Filme inserirFilme(Filme filme) throws IOException {
		int id = dao.criar(filme);
		filme.setId(id);
		return filme;
	}
	
	public List<Filme> listarFilmes(String chave) throws IOException{
		return dao.listarFilmes(chave);
	}

	public List<Filme> listarFilmes() throws IOException{
		return dao.listarFilmes();
	}

}
