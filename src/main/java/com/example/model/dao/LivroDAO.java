package com.example.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.entity.Livro;

@Repository
public interface LivroDAO extends JpaRepository<Livro, Long> {

}
