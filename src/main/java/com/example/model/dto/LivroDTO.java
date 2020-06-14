package com.example.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LivroDTO {
	
	private static final SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private Long id;
	private String titulo;
	private String nomeAutor;
	private String isbn;
	private Date dataCadastro;
	
	public LivroDTO(String titulo, String nomeAutor, String isbn, Date dataCadastro) {
		super();
		this.titulo = titulo;
		this.nomeAutor = nomeAutor;
		this.isbn = isbn;
		this.dataCadastro = dataCadastro;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getDataCadastro() {
		if (dataCadastro != null) {
			return formatoData.format(dataCadastro);
		}
		return "";
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	@Override
	public String toString() {
		
		// converter para formato json
		try {
			ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
            
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
		
		return super.toString();
	}
	
	public static void main(String[] args) {
		LivroDTO dto = new LivroDTO("Homo Sapiens Pacificus", "Waldo Vieira", "ABC11071234567", new Date());
		System.out.println(dto.toString());
	}

}
