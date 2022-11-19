package com.inovateca.InovaTeca.repository;

import com.inovateca.InovaTeca.model.AutorModel;
import com.inovateca.InovaTeca.model.LivroModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<AutorModel, Long> {

    public Optional<AutorModel> findById(Long id);

    List<AutorModel> findAll();

    Page<AutorModel> findAll(Pageable pageable);

}
