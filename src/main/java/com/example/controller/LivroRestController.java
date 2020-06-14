package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.dto.LivroDTO;
import com.example.service.LivroService;

@RestController
@RequestMapping("/api/livro")
public class LivroRestController {
	
	@Autowired
	private LivroService livroService;
	
	@GetMapping()
	public List<LivroDTO> listar() {
		List<LivroDTO> livros = livroService.listar();
		return livros;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getLivroPorId(@PathVariable(value = "id") Long id) {
		LivroDTO livroDTO = livroService.getLivroPorId(id);
		
		if (livroDTO == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(String.format("Livro com id %s nao encontrado: ", id));
			
	    return ResponseEntity.ok().body(livroDTO);
    }
	
	@PostMapping(value = "/cadastrar", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public LivroDTO cadastrar(@RequestBody LivroDTO livroDTO) {
		LivroDTO livroCadastrado = livroService.salvar(livroDTO);
		return livroCadastrado;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable(value = "id") Long id) {
	    
		boolean foiDeletado = livroService.deletar(id);
		
		if (!foiDeletado)
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Livro nao foi deletado. Possivelmente este id nao existe");
			
	    return ResponseEntity.ok().body("Livro deletado com sucesso");
	}

}
