package com.example.model.dto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LivroDTO {
	
	private static final SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private Long id;
	
	@NotBlank(message = "titulo nao pode ser nulo ou estar vazio")
	private String titulo;
	
	private String nomeAutor;
	private String isbn;
	private Date dataCadastro;
	
	// essa validacao evita a excecao ORA-01438
	@DecimalMax(value = "999.99", message = "valorUnitario deve ter precisao de 5 digitos e escala/fracao de 2 digitos (Ex.: 999.99)")
	private BigDecimal valorUnitario;
	
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
	
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
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
		dto.setValorUnitario(new BigDecimal(3330));
		System.out.println(dto.toString());
	}

}
