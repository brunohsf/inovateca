package com.inovateca.InovaTeca.service;

import com.inovateca.InovaTeca.model.LivroModel;
import com.inovateca.InovaTeca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public Optional<LivroModel> findById(long id) {
        return repository.findById(id);
    }
    public Page<LivroModel> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public LivroModel save(LivroModel model) {
        return repository.save(model);
    }
    public LivroModel update(LivroModel model) {
        Optional<LivroModel> l = repository.findById(model.getId());
        if (!l.isPresent()) {
            return null;
        }
        l.get().setTitulo(model.getTitulo());
        l.get().setAutor(model.getAutor());

        return repository.save(l.get());
    }
    public void delete(long id) {
        Optional<LivroModel> l = repository.findById(id);
        if (l.isPresent()) {
            repository.delete(l.get());
        }
    }
}