package com.inovateca.InovaTeca.controller;

import com.inovateca.InovaTeca.model.AutorModel;
import com.inovateca.InovaTeca.model.LivroModel;
import com.inovateca.InovaTeca.service.LivroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livro/v1")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retorna um livro atraves do ID", produces = "JSON", response = LivroModel.class)
    public LivroModel findById(@ApiParam(name = "id", required = true, type = "Long") @PathVariable long id) {
        var model = service.findById(id);
        if (model.isPresent()) {
            buildEntityLinks(model.get());
        }
        //.return the model with links
        return model.get();
    }

    @GetMapping
    public ResponseEntity<PagedModel<LivroModel>> findAll(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam(value = "direction", defaultValue = "asc") String direction, PagedResourcesAssembler<LivroModel> assembler) {
        //..the direction of sort
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        //..a PageAble object is an object containing the list of resources
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "fullName"));

        //..a Page containing the resource models
        Page<LivroModel> pageModel = service.findAll(pageable);

        //..create the HATEOAS links
        for (LivroModel livro : pageModel) {
            buildEntityLinks(livro);
        }
        //return the ResponseEntity
        return new ResponseEntity(assembler.toModel(pageModel), HttpStatus.OK);
    }

    @PutMapping
    public LivroModel update(@RequestBody LivroModel model) {
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    public void buildEntityLinks(LivroModel model) {

        model.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(this.getClass())
                                .findById(model.getId())
                ).withSelfRel()
        );
        Link link = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AutorController.class)
                        .findById(model.getAutor().getId())
        ).withSelfRel();
        model.getAutor().add(link);
    }

}
