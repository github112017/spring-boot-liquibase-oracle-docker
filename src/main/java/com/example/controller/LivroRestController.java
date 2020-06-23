package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
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
	public ResponseEntity<?> cadastrar(@Valid @RequestBody LivroDTO livroDTO, Errors errors) {
		
		Map<String, Map<String, String>> mapErros = new HashMap<String, Map<String, String>>();
		Map<String, String> mapErrosDetalhes = new HashMap<String, String>();
		
		try {
			
			// antes de cadastrar, verificar se ha erros nos atributos, validados pela anotacao @Valid, do json livroDTO
			if (errors.hasErrors()) {
				
				List<FieldError> erros = errors.getFieldErrors();
		        for (FieldError e : erros) {
		        	mapErrosDetalhes.put(e.getField(), 
		        			String.format("%s. Erro -> %s = %s", 
		        					e.getDefaultMessage(), e.getField(), e.getRejectedValue())
		        	);
		        }
		        
		        mapErros.put("erros", mapErrosDetalhes);
		        
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapErros);
			}
			
			//cadastrar livro
			LivroDTO livroCadastrado = livroService.salvar(livroDTO);
			return ResponseEntity.ok().body(livroCadastrado);
			
		} catch (Exception e) {
			
			mapErrosDetalhes.put("excecao", String.format("Erro ao tentar cadastrar Livro: %s", e.getMessage()));
			mapErros.put("erros", mapErrosDetalhes);
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapErros);
			
		} finally {
			// para o garbage collector
			mapErros = null;
			mapErrosDetalhes = null;
		}
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
