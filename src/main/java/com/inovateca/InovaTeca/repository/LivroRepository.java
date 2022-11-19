package com.inovateca.InovaTeca.repository;

import com.inovateca.InovaTeca.model.LivroModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<LivroModel, Long> {

    public Optional<LivroModel> findById(Long id);

    List<LivroModel> findAll();

    Page<LivroModel> findAll(Pageable pageable);
}
