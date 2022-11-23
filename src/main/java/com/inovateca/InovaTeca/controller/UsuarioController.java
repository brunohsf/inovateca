package com.inovateca.InovaTeca.controller;

import com.inovateca.InovaTeca.model.UsuarioModel;
import com.inovateca.InovaTeca.repository.UsuarioRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insert user in table user")
    public UsuarioModel save(@RequestBody UsuarioModel usuario) {
        return repository.save(usuario);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Return a person list")
    public List<UsuarioModel> list() {
        return repository.findAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a person")
    public void update(@PathVariable Long id, @RequestBody UsuarioModel usuarioUpdated){
        repository.findById(id)
                .map(usuario -> {
                    usuarioUpdated.setIdUsuario(usuario.getIdUsuario());
                    return repository.save(usuarioUpdated);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado"
                ));
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a person by id")
    public void deleteById(@PathVariable Long id){
        repository.findById(id).map(person -> {
                    repository.delete(person);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

}
