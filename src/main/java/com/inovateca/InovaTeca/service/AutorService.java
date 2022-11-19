package com.inovateca.InovaTeca.service;

import com.inovateca.InovaTeca.model.AutorModel;
import com.inovateca.InovaTeca.model.LivroModel;
import com.inovateca.InovaTeca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class AutorService {

    @Autowired
    private AutorRepository repository;

    public Optional<AutorModel> findById(long id) {
        return repository.findById(id);
    }
    public Page<AutorModel> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public AutorModel save(AutorModel model) {
        return repository.save(model);
    }
    public AutorModel update(AutorModel model) {
        Optional<AutorModel> a = repository.findById(model.getId());
        if (!a.isPresent()) {
            return null;
        }
        a.get().setNome(model.getNome());
        return repository.save(a.get());
    }
    public void delete(long id) {
        Optional<AutorModel> a = repository.findById(id);
        if (a.isPresent()) {
            repository.delete(a.get());
        }
    }
}