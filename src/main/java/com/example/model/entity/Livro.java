package com.example.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LIVRO")
@SequenceGenerator(name = "SQ_LIVRO", sequenceName = "SQ_LIVRO", initialValue = 1, allocationSize = 1)
public class Livro {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LIVRO")
    @Column(name = "ID", nullable = false)
    private Long id;
	
	@Column(name = "TITULO")
    private String titulo;
	
	@Column(name = "NM_AUTOR")
    private String nomeAutor;
	
    @Column(name="DT_CADASTRO")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dataCadastro;
	
	@Column(name = "ISBN")
    private String isbn;
	
	// atributo/coluna para testar e analisar a excecao ORA-01438. a validacao soh funciona no LivroDTO
	@Column(name = "VL_UNIT")
    private BigDecimal valorUnitario;
	
	public Livro() {
		this.dataCadastro = new Date();
	}
	
	public Livro(String titulo, String nomeAutor, String isbn) {
		super();
		this.titulo = titulo;
		this.nomeAutor = nomeAutor;
		this.dataCadastro = new Date();
		this.isbn = isbn;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
}
