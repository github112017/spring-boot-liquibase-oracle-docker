package com.example.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.dao.LivroDAO;
import com.example.model.dto.LivroDTO;
import com.example.model.entity.Livro;

@Service
public class LivroService {
	
	@Autowired
	private LivroDAO livroDAO;
	
	public LivroDTO salvar(LivroDTO dto) throws Exception {
		
		try {
			Livro livro = converterParaLivro(dto);
			livro = livroDAO.saveAndFlush(livro);
			
			return converterParaLivroDTO(livro);
			
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass())
				.error("ERRO AO TENTAR CADASTRAR LIVRO: " + e);
			
			e.printStackTrace();
			
			throw e;
		}
	}
	
	@Transactional(readOnly = true)
	public List<LivroDTO> listar() {
		
		List<Livro> livros = livroDAO.findAll();
		
		List<LivroDTO> livrosDTO = livros.stream()
				.map(livro -> converterParaLivroDTO(livro))
				.collect(Collectors.toList());
		
		return livrosDTO;
	}
	
	/* 
	 * Pq usar @Transactional na camada Service e nao no Repository ou Controller: 
	 * https://stackoverflow.com/questions/18498115/why-use-transactional-with-service-instead-of-with-controller
	 */
	@Transactional(readOnly = true)
	public LivroDTO getLivroPorId(Long id) {
		
		try {
			Livro livro = livroDAO.findById(id).get();
			return converterParaLivroDTO(livro);
			
		} catch (NoSuchElementException e) {
			LoggerFactory.getLogger(this.getClass())
				.error(String.format("Livro com id %s nao existe: ", id) + e.getMessage());
			return null;
		}
	}
	
	public boolean deletar(Long id) {
		
		try {
			livroDAO.deleteById(id);
			return Boolean.TRUE;
			
		} catch (EmptyResultDataAccessException e) {
			LoggerFactory.getLogger(this.getClass())
				.error("ERRO AO TENTAR DELETAR LIVRO: " + e.getMessage());
			return Boolean.FALSE;
		}
	}
	
	/* ==== Conversores ==== */
	
	private LivroDTO converterParaLivroDTO(Livro livro) {
		
		if (livro != null) {
			LivroDTO dto = new LivroDTO(livro.getTitulo(), 
					livro.getNomeAutor(), livro.getIsbn(), 
					livro.getDataCadastro());
			
			dto.setId(livro.getId());
			dto.setValorUnitario(livro.getValorUnitario());
			
			return dto;
		}
		
		return null;
	}
	
	private Livro converterParaLivro(LivroDTO dto) {
		
		if (dto != null) {
			Livro livro = new Livro(dto.getTitulo(), 
					dto.getNomeAutor(), dto.getIsbn());
			
			livro.setValorUnitario(dto.getValorUnitario());
			
			return livro;
		}
		
		return null;
	}
}
